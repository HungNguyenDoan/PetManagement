package com.project.petmanagement.activity.pet;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.project.petmanagement.R;
import com.project.petmanagement.models.entity.Breed;
import com.project.petmanagement.models.entity.Species;
import com.project.petmanagement.payloads.requests.PetRequest;
import com.project.petmanagement.payloads.responses.ListSpeciesResponse;
import com.project.petmanagement.payloads.responses.PetResponse;
import com.project.petmanagement.services.ApiService;
import com.project.petmanagement.utils.DialogUtils;
import com.project.petmanagement.utils.FormatDateUtils;
import com.project.petmanagement.utils.ImageUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewPetActivity extends AppCompatActivity {

    private ImageView btnBack;
    private TextInputEditText dob, namePet;
    private Map<String, Species> speciesMap;
    private Map<String, Breed> breedsMap;
    private AutoCompleteTextView speciesView;
    private AutoCompleteTextView breedView;
    private ArrayAdapter<String> speciesAdapter;
    private ArrayAdapter<String> breedAdapter;
    private DatePickerDialog datePickerDialog;
    private String avatarBase64;
    private ImageView avatar;
    private Button btnAdd;
    private RadioGroup gender, neutered;
    private ImageView openCamera;
    private Bitmap imagePet;
    private String urlImage;
    private final static int CAMERA_PERMISSION_REQUEST_CODE = 100;
    private final static int READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE = 101;
    private final static int WRITE_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE = 102;

    private ActivityResultLauncher<Intent> cameraLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult o) {
            if(o.getResultCode() == Activity.RESULT_OK){
                Bundle bundle = o.getData().getExtras();
                if (bundle!=null){
                    imagePet = (Bitmap) bundle.get("data");
                    avatar.setImageBitmap(imagePet);
                }
            }
        }
    });
    private ActivityResultLauncher<Intent> galleryLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult o) {
            if(o.getResultCode() == Activity.RESULT_OK){
                Uri uri = o.getData().getData();
                try {
                    imagePet = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                    avatar.setImageBitmap(imagePet);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_pet);
        speciesView = findViewById(R.id.species);
        breedView = findViewById(R.id.breed);
        btnBack = findViewById(R.id.btn_back);
        dob = findViewById(R.id.dob);
        speciesMap = new LinkedHashMap<>();
        breedsMap = new LinkedHashMap<>();
        namePet = findViewById(R.id.name_pet);
        avatar = findViewById(R.id.image_pet);
        btnAdd = findViewById(R.id.btn_add);
        neutered = findViewById(R.id.neutered);
        gender = findViewById(R.id.gender);
        openCamera = findViewById(R.id.camera);
        getSpecies();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        speciesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                namePet.clearFocus();
                breedsMap.clear();
                String speciesSelect = parent.getItemAtPosition(position).toString();
                for (Breed breed: speciesMap.get(speciesSelect).getBreeds()){
                    breedsMap.put(breed.getName(), breed);
                }
                breedAdapter = new ArrayAdapter<>(AddNewPetActivity.this, R.layout.list_item_dropdown, new ArrayList<>(breedsMap.keySet()));
                breedView.setAdapter(breedAdapter);
            }
        });
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                namePet.clearFocus();
                customDob();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validation()){
                    addPet();
                }
            }
        });
        openCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOpenCameraDialog();
            }
        });
    }
    private void addPet() {
        String namePetString = namePet.getText().toString().trim();
        long breedId = breedsMap.get(breedView.getText().toString()).getId();
        Date dateOfBirth = null;
        try {
            dateOfBirth = FormatDateUtils.StringToDate1(dob.getText().toString().trim());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String dateOfBirth1 = FormatDateUtils.DateToString1(dateOfBirth);
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.R){
            if(ContextCompat.checkSelfPermission(AddNewPetActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                saveImage();
            }else{
                ActivityCompat.requestPermissions(AddNewPetActivity.this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE);
            }
        }else{
            saveImage();
        }
        int selectGenderId = gender.getCheckedRadioButtonId();
        int selectNeuteredId = neutered.getCheckedRadioButtonId();
        int genderInt = 0;
        RadioButton genderButton = findViewById(selectGenderId);
        if(genderButton.getText().equals("Đực")){
            genderInt = 1;
        }
        boolean neuteredId = false;
        RadioButton neuteredButton = findViewById(selectNeuteredId);
        if(neuteredButton.getText().toString().equals("Rồi")){
            neuteredId = true;
        }
        PetRequest petRequest = new PetRequest(namePetString, breedId, genderInt, dateOfBirth1, urlImage, neuteredId);
        ApiService.apiService.addPet(petRequest).enqueue(new Callback<PetResponse>() {
            @Override
            public void onResponse(Call<PetResponse> call, Response<PetResponse> response) {
                if(response.isSuccessful()){
                    Toast.makeText(AddNewPetActivity.this, "Thêm thú cưng thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(AddNewPetActivity.this, response.code()+" "+response.message(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<PetResponse> call, Throwable t) {
                Toast.makeText(AddNewPetActivity.this, "false", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getSpecies(){
        ApiService.apiService.getSpecies().enqueue(new Callback<ListSpeciesResponse>() {
            @Override
            public void onResponse(Call<ListSpeciesResponse> call, Response<ListSpeciesResponse> response){
                if(response.isSuccessful()){
                    ListSpeciesResponse speciesResponse = response.body();
                    if(speciesResponse!=null){
                        for(Species species1: speciesResponse.getData()){
                            speciesMap.put(species1.getName(), species1);
                            speciesAdapter = new ArrayAdapter<>(AddNewPetActivity.this, R.layout.list_item_dropdown, new ArrayList<>(speciesMap.keySet()));
                            speciesView.setAdapter(speciesAdapter);
                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<ListSpeciesResponse> call, Throwable t) {

            }
        });
    }
    private void customDob(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog = new DatePickerDialog(AddNewPetActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String date = dayOfMonth+"/"+ (month+1) +"/"+year;
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        try {
                            Date date1 = sdf.parse(date);
                            String date2 = sdf.format(date1);
                            dob.setText(date2);
                            dob.setError(null);
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }
    private boolean validation(){
        if (namePet.length() == 0){
            namePet.setError("Bạn chưa nhập tên thú cưng");
            return false;
        }
        if (dob.length()==0){
            dob.setError("Bạn chưa nhập ngày tháng năm sinh");
            return false;
        }
        if (speciesView.length() == 0){
            speciesView.setError("Bạn chưa chọn loài");
            return false;
        }
        if(breedView.length() == 0){
            breedView.setError("Bạn chưa chọn loài");
            return false;
        }
        if(gender.getCheckedRadioButtonId() == -1){
            DialogUtils.setUpDialog(this, "Bạn chưa chọn giới tính");
            return  false;
        }
        if(neutered.getCheckedRadioButtonId() == -1){
            DialogUtils.setUpDialog(this, "Bạn chưa chọn trạng thái triệt sản");
            return false;
        }
        return true;
    }
    public void setOpenCameraDialog(){
        AlertDialog.Builder arlertDialog = new AlertDialog.Builder(AddNewPetActivity.this);
        arlertDialog.setTitle("Chọn")
                .setPositiveButton("Gallery", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU){
                            if(ContextCompat.checkSelfPermission(AddNewPetActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                                ActivityCompat.requestPermissions(AddNewPetActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE);
                            }else{
                                startGallery();
                            }
                        }else {
                            startGallery();
                        }

                    }
                })
                .setNegativeButton("Camera", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(ContextCompat.checkSelfPermission(AddNewPetActivity.this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
                            ActivityCompat.requestPermissions(AddNewPetActivity.this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
                        }else{
                            startCamera();
                        }
                    }
                })
                .setNeutralButton("Huỷ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }

    public void startCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraLauncher.launch(intent);
    }
    public void startGallery(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryLauncher.launch(intent);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == CAMERA_PERMISSION_REQUEST_CODE){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                startCamera();
            }else{
                Toast.makeText(this, "Camera permission denied.", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE) {
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                startGallery();
            }else{
                Toast.makeText(this, "Gallery permission denied.", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == WRITE_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE) {
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                startGallery();
            }else{
                Toast.makeText(this, "Gallery permission denied.", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void saveImage(){
        File dir = new File(getFilesDir(), "pet_image");
        if(!dir.exists()){
            dir.mkdir();
        }
        if(imagePet != null){
            File file = new File(dir, "pet_"+ System.currentTimeMillis()+".jpg");
            OutputStream outputStream;
            try {
                outputStream = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            imagePet.compress(Bitmap.CompressFormat.JPEG,20,outputStream);
            urlImage = file.getAbsolutePath();
            try {
                outputStream.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}