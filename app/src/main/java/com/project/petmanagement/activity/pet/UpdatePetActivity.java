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
import android.os.Bundle;
import android.provider.MediaStore;
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

import com.google.android.material.tabs.TabLayoutMediator;
import com.google.android.material.textfield.TextInputEditText;
import com.project.petmanagement.R;
import com.project.petmanagement.adapters.PetDetailsViewPager2Adapter;
import com.project.petmanagement.models.Pet;
import com.project.petmanagement.models.Species;
import com.project.petmanagement.payload.request.PetRequest;
import com.project.petmanagement.payload.response.ListSpeciesResponse;
import com.project.petmanagement.payload.response.PetResponse;
import com.project.petmanagement.services.ApiService;
import com.project.petmanagement.utils.DialogUtils;
import com.project.petmanagement.utils.FormatDateUtils;
import com.project.petmanagement.utils.ImageUtils;

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

public class UpdatePetActivity extends AppCompatActivity {
    private ImageView btnBack;
    private TextInputEditText dob, namePet, weight;
    private Map<String, Species> species;
    private Map<String, Species> breeds;
    private AutoCompleteTextView speciesView;
    private AutoCompleteTextView breedView;
    private ArrayAdapter<String> speciesAdapter;
    private ArrayAdapter<String> breedAdapter;
    private DatePickerDialog datePickerDialog;
    private String avatarBase64;
    private ImageView avatar;
    private Button btnUpdate;
    private RadioGroup gender, neutered;
    private ImageView openCamera;
    private Pet pet;
    private long idPet;
    private final static int CAMERA_PERMISSION_REQUEST_CODE = 100;
    private ActivityResultLauncher<Intent> cameraLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult o) {
            if(o.getResultCode() == Activity.RESULT_OK){
                Bundle bundle = o.getData().getExtras();
                if (bundle!=null){
                    Bitmap image = (Bitmap) bundle.get("data");
                    avatar.setImageBitmap(image);
                    avatarBase64 = ImageUtils.endcodeBase64(image);
                }
            }
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pet);
        speciesView = findViewById(R.id.species);
        breedView = findViewById(R.id.breed);
        btnBack = findViewById(R.id.btn_back);
        dob = findViewById(R.id.dob);
        species = new LinkedHashMap<>();
        breeds = new LinkedHashMap<>();
        namePet = findViewById(R.id.name_pet);
        avatar = findViewById(R.id.image_pet);
        btnUpdate = findViewById(R.id.btn_update);
        neutered = findViewById(R.id.neutered);
        gender = findViewById(R.id.gender);
        openCamera = findViewById(R.id.camera);
        idPet = getIntent().getLongExtra("idPet", 0);
        showInfoPet();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        speciesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String speciesSelect = parent.getItemAtPosition(position).toString();
                breeds.clear();

                for (Species breed: species.get(speciesSelect).getBreed()){
                    breeds.put(breed.getName(), breed);
                }
                breedAdapter = new ArrayAdapter<>(UpdatePetActivity.this, R.layout.list_item_dropdown, new ArrayList<>(breeds.keySet()));
                breedView.setAdapter(breedAdapter);
                breedView.setText("");
            }
        });
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDob();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validation()){
                    updatePet();
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
    private void showInfoPet(){
        ApiService.apiService.getPetDetail(idPet).enqueue(new Callback<PetResponse>() {
            @Override
            public void onResponse(Call<PetResponse> call, Response<PetResponse> response) {
                if (response.isSuccessful()){
                    PetResponse petResponse = response.body();
                    if (petResponse!=null){
                        pet = petResponse.getData();
                        namePet.setText(pet.getFullname());
                        if(pet.getAvatar()!=null){
                            avatar.setImageBitmap(ImageUtils.decodeBase64(pet.getAvatar()));
                        }
                        breedView.setText(pet.getSpeciesName());
                        if(pet.getGender() == 1){
                            RadioButton male = findViewById(R.id.male);
                            male.setChecked(true);
                        }else{
                            RadioButton female = findViewById(R.id.female);
                            female.setChecked(true);
                        }
                        if(pet.getNeutered() == 1){
                            RadioButton neuter = findViewById(R.id.neuter);
                            neuter.setChecked(true);
                        }else{
                            RadioButton entire = findViewById(R.id.entire);
                            entire.setChecked(true);
                        }
                        dob.setText(FormatDateUtils.DateToString(pet.getDateOfBirth()));
                        if(pet.getAvatar() !=null){
                            avatarBase64 = pet.getAvatar();
                        }
                        getSpecies();
                    }
                }
            }

            @Override
            public void onFailure(Call<PetResponse> call, Throwable t) {
            }
        });
    }
    private void updatePet() {
        String namePetString = namePet.getText().toString().trim();
        long speciesId = breeds.get(breedView.getText().toString()).getId();
        Date dateOfBirth = null;
        try {
            dateOfBirth = FormatDateUtils.StringToDate1(dob.getText().toString().trim());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String dateOfBirth1 = FormatDateUtils.DateToString1(dateOfBirth);
        Date dateOfBirth2 = null;
        try {
            dateOfBirth2 = FormatDateUtils.StringToDate(dateOfBirth1);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        int selectGenderId = gender.getCheckedRadioButtonId();
        int selectNeuteredId = neutered.getCheckedRadioButtonId();
        int genderInt = 0;
        RadioButton genderButton = findViewById(selectGenderId);
        if(genderButton.getText().equals("Đực")){
            genderInt = 1;
        }
        int neuteredId = 0;
        RadioButton neuteredButton = findViewById(selectNeuteredId);
        if(neuteredButton.getText().toString().equals("Rồi")){
            neuteredId = 1;
        }
        PetRequest petRequest = new PetRequest(namePetString, dateOfBirth2, genderInt, neuteredId, avatarBase64, speciesId);
        ApiService.apiService.updatePet(petRequest, pet.getId()).enqueue(new Callback<PetResponse>() {
            @Override
            public void onResponse(Call<PetResponse> call, Response<PetResponse> response) {
                if(response.isSuccessful()){
                    Toast.makeText(UpdatePetActivity.this, "Cập nhập thú cưng thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(UpdatePetActivity.this, response.code()+" "+response.message(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<PetResponse> call, Throwable t) {
                Toast.makeText(UpdatePetActivity.this, "connect faild", Toast.LENGTH_SHORT).show();
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
                            species.put(species1.getName(), species1);
                            for (Species species2: species1.getBreed()){
                                if(species2.getName().equals(pet.getSpeciesName())){
                                    speciesView.setText(species1.getName());
                                    for (Species breed: species.get(species1.getName()).getBreed()){
                                        breeds.put(breed.getName(), breed);
                                    }
                                    breedAdapter = new ArrayAdapter<>(UpdatePetActivity.this, R.layout.list_item_dropdown, new ArrayList<>(breeds.keySet()));
                                    breedView.setAdapter(breedAdapter);
                                    break;
                                }
                            }
                        }
                        speciesAdapter = new ArrayAdapter<>(UpdatePetActivity.this, R.layout.list_item_dropdown, new ArrayList<>(species.keySet()));
                        speciesView.setAdapter(speciesAdapter);
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
                datePickerDialog = new DatePickerDialog(UpdatePetActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String date = dayOfMonth+"/"+ month +"/"+year;
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
        AlertDialog.Builder arlertDialog = new AlertDialog.Builder(UpdatePetActivity.this);
        arlertDialog.setTitle("Chọn")
                .setPositiveButton("Gallery", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("Camera", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(ContextCompat.checkSelfPermission(UpdatePetActivity.this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
                            ActivityCompat.requestPermissions(UpdatePetActivity.this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == CAMERA_PERMISSION_REQUEST_CODE){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                startCamera();
            }else{
                Toast.makeText(this, "Camera permission denied.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}