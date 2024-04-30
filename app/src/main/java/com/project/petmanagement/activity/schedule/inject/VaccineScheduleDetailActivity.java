package com.project.petmanagement.activity.schedule.inject;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;
import com.project.petmanagement.R;
import com.project.petmanagement.models.entity.OneTimeSchedule;
import com.project.petmanagement.models.entity.Pet;
import com.project.petmanagement.models.entity.VaccinationNotification;
import com.project.petmanagement.models.entity.Vaccine;
import com.project.petmanagement.payloads.requests.OneTimeScheduleRequest;
import com.project.petmanagement.payloads.requests.VaccinationNotificationRequest;
import com.project.petmanagement.payloads.responses.ListVaccineResponse;
import com.project.petmanagement.payloads.responses.VaccineNotificationResponse;
import com.project.petmanagement.services.ApiService;
import com.project.petmanagement.utils.FormatDateUtils;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VaccineScheduleDetailActivity extends AppCompatActivity {
    private Pet pet;
    private CircleImageView imagePet;
    private TextView namePet;
    private ArrayAdapter<String> vaccineAdapter;
    private Map<String, Vaccine> vaccineMap;
    private AutoCompleteTextView vaccineView;
    private TextView quantityInject;
    private List<OneTimeScheduleRequest> oneTimeScheduleRequestList;
    private CardView injectInfo;
    private LinearLayout parentLayout;
    private Button choosePet;
    private ImageView btnEdit;
    private TextInputEditText title, note;
    private VaccinationNotification vaccinationNotification;
    private final ActivityResultLauncher<Intent> launcherSchedule = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), o -> {
        if(o.getResultCode() == RESULT_OK){
            Intent data = o.getData();
            if (data != null) {
                oneTimeScheduleRequestList = (List<OneTimeScheduleRequest>) data.getSerializableExtra("listOneTime");
            }
        }
    });
    private final ActivityResultLauncher<Intent> launcherPet = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult o) {
            if(o.getResultCode() == RESULT_OK){
                Intent data = o.getData();
                if (data != null) {
                    pet = (Pet) data.getSerializableExtra("pet");
                    setInfoPet();
                }
            }
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_schedule_detail);
        ImageView returnArrow = findViewById(R.id.return_arrow);
        vaccineMap = new LinkedHashMap<>();
        vaccinationNotification = (VaccinationNotification) getIntent().getSerializableExtra("vaccineNotification");
        vaccineView = findViewById(R.id.vaccine_type);
        imagePet = findViewById(R.id.image_pet);
        namePet = findViewById(R.id.name_pet);
        quantityInject = findViewById(R.id.quantity_inject);
        pet = (Pet) getIntent().getSerializableExtra("pet");
        parentLayout = findViewById(R.id.parent_layout);
        title = findViewById(R.id.title_vaccine);
        note = findViewById(R.id.note);
        choosePet = findViewById(R.id.return_choose_pet);
        btnEdit = findViewById(R.id.btn_edit);
        if(vaccinationNotification!=null){
            title.setText(vaccinationNotification.getTitle());
            note.setText(vaccinationNotification.getNote());
            vaccineView.setText(vaccinationNotification.getVaccine().getName());
            quantityInject.setText(String.valueOf(vaccinationNotification.getVaccine().getVaccineDose()));
            oneTimeScheduleRequestList = new ArrayList<>();
            for(OneTimeSchedule oneTimeSchedule : vaccinationNotification.getSchedules()){
                OneTimeScheduleRequest oneTimeScheduleRequest = new OneTimeScheduleRequest();
                oneTimeScheduleRequest.setId(oneTimeSchedule.getId());
                oneTimeScheduleRequest.setTime(oneTimeSchedule.getTime());
                oneTimeScheduleRequest.setStatus(oneTimeSchedule.getVaccinationStatus());
                String date1 = FormatDateUtils.DateToString1(oneTimeSchedule.getDate());
                oneTimeScheduleRequest.setDate(date1);
                oneTimeScheduleRequestList.add(oneTimeScheduleRequest);
            }
        }
        btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(VaccineScheduleDetailActivity.this, SetVaccineNotificationActivity.class);
            intent.putExtra("listOneTime", (Serializable) oneTimeScheduleRequestList);
            launcherSchedule.launch(intent);
        });
        setInfoPet();
        vaccineView.setOnItemClickListener((parent, view, position, id) -> {
            vaccineView.setError(null);
            String vaccineName = vaccineAdapter.getItem(position);
            Vaccine vaccine = vaccineMap.get(vaccineName);
            if (vaccine != null) {
                quantityInject.setText(String.valueOf(vaccine.getVaccineDose()));
            }
        });
        choosePet.setOnClickListener(v -> {
            Intent intent = new Intent(VaccineScheduleDetailActivity.this, SelectPetToVaccineActivity.class);
            intent.putExtra("action", "reselect");
            launcherPet.launch(intent);
        });
        returnArrow.setOnClickListener(v -> finish());
        setUpOneTimeSchedule();
//        btnSave.setOnClickListener(v -> {
//            if(validation()){
//                Vaccine vaccine = vaccineMap.get(vaccineView.getText().toString());
//                VaccinationNotificationRequest vaccinationNotificationRequest = new VaccinationNotificationRequest(pet.getId(), title.getText().toString(), vaccine.getId(), note.getText().toString(), oneTimeScheduleRequestList);
//                ApiService.apiService.addVaccinationNotification(vaccinationNotificationRequest).enqueue(new Callback<VaccineNotificationResponse>() {
//                    @Override
//                    public void onResponse(Call<VaccineNotificationResponse> call, Response<VaccineNotificationResponse> response) {
//                        if(response.isSuccessful()){
//                            Intent intent = new Intent(VaccineScheduleDetailActivity.this, VaccineInjectionScheduleActivity.class);
//                            startActivity(intent);
//                            finish();
//                            Toast.makeText(VaccineScheduleDetailActivity.this, "Thêm lịch thành công", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<VaccineNotificationResponse> call, Throwable t) {
//
//                    }
//                });
//
//            }
//        });

    }

    private void setInfoPet(){
        if(pet!=null){
            namePet.setText(pet.getFullName());
            Glide.with(VaccineScheduleDetailActivity.this)
                    .load(pet.getImage())
                    .error(R.drawable.default_pet_no_image)
                    .into(imagePet);
            getVaccine();
        }
    }
    private boolean validation(){
        if(oneTimeScheduleRequestList == null || oneTimeScheduleRequestList.isEmpty()){
            return false;
        }
        if(title.length()==0){
            title.setError("Hành động không được để trống");
            return false;
        }
        if(vaccineView.length()==0){
            vaccineView.setError("Loại vaccine không được để trống");
            return false;
        }
        return true;
    }
    private void setUpOneTimeSchedule(){
        if(oneTimeScheduleRequestList!=null && !oneTimeScheduleRequestList.isEmpty()){
            parentLayout.removeAllViews();
            int stt = 1;
            for(OneTimeScheduleRequest oneTimeScheduleRequest: oneTimeScheduleRequestList){
                try {
                    Date date = FormatDateUtils.StringToDate(oneTimeScheduleRequest.getDate());
                    String strDate = FormatDateUtils.DateToString(date);
                    View childView = getLayoutInflater().inflate(R.layout.item_chedule_vaccine,null,false);
                    TextView title = childView.findViewById(R.id.title);
                    String strTile = "Lịch tiêm vaccine "+stt;
                    stt+=1;
                    title.setText(strTile);
                    TextView dateInject = childView.findViewById(R.id.date_inject);
                    TextView hourInject = childView.findViewById(R.id.hour);
                    dateInject.setText(strDate);
                    hourInject.setText(oneTimeScheduleRequest.getTime());
                    parentLayout.addView(childView);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void getVaccine(){
        ApiService.apiService.getVaccineByPet(pet.getId()).enqueue(new Callback<ListVaccineResponse>() {
            @Override
            public void onResponse(Call<ListVaccineResponse> call, Response<ListVaccineResponse> response) {
                if(response.isSuccessful()){
                    ListVaccineResponse vaccineResponse = response.body();
                    if(vaccineResponse!=null && vaccineResponse.getData()!=null){
                        for(Vaccine vaccine: vaccineResponse.getData()){
                            vaccineMap.put(vaccine.getName(), vaccine);
                        }
                        vaccineAdapter = new ArrayAdapter<>(VaccineScheduleDetailActivity.this, R.layout.list_item_dropdown, new ArrayList<>(vaccineMap.keySet()));
                        vaccineView.setAdapter(vaccineAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ListVaccineResponse> call, Throwable t) {
                Toast.makeText(VaccineScheduleDetailActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpOneTimeSchedule();
    }
}