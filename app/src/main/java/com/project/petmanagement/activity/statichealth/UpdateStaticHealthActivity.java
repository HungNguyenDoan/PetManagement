package com.project.petmanagement.activity.statichealth;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.project.petmanagement.R;
import com.project.petmanagement.models.entity.HealthRecord;
import com.project.petmanagement.payloads.requests.HealRecordRequest;
import com.project.petmanagement.payloads.responses.HealRecordResponse;
import com.project.petmanagement.payloads.responses.HealthRecordErrorResponse;
import com.project.petmanagement.services.ApiService;
import com.project.petmanagement.utils.DialogUtils;
import com.project.petmanagement.utils.FormatDateUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateStaticHealthActivity extends AppCompatActivity {
    private final String[] exerciseLevel = {"1","2", "3", "4", "5"};
    private TextInputEditText checkUpdate;
    private EditText editWeight;
    private TextInputEditText editSymptoms, editDiagnosis, editNote;
    private HealthRecord healthRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_health_detail);
        Spinner spinner = findViewById(R.id.exercise_level);
        Button btnUpdate = findViewById(R.id.btn_update);
        editWeight = findViewById(R.id.weight);
        checkUpdate = findViewById(R.id.check_update);
        editSymptoms = findViewById(R.id.symptoms);
        editDiagnosis = findViewById(R.id.diagnosis);
        editNote = findViewById(R.id.note);
        ImageView btnEdit = findViewById(R.id.btn_edit);
        spinner.setEnabled(false);
        ArrayAdapter<String> exerciseAdapter = new ArrayAdapter<>(this, R.layout.list_item_dropdown, Arrays.asList(exerciseLevel));
        spinner.setAdapter(exerciseAdapter);
        ImageView btnBack = findViewById(R.id.btn_back);
        healthRecord = (HealthRecord) getIntent().getSerializableExtra("healthRecord");
        if(healthRecord!=null){
            editWeight.setText(String.valueOf(healthRecord.getWeight()));
            String strCheckUpdate = FormatDateUtils.DateToString(healthRecord.getCheckUpDate());
            checkUpdate.setText(strCheckUpdate);
            editSymptoms.setText(healthRecord.getSymptoms());
            editDiagnosis.setText(healthRecord.getDiagnosis());
            editNote.setText(healthRecord.getNote());
        }
        btnBack.setOnClickListener(v -> finish());
        btnEdit.setOnClickListener(v -> {
            spinner.setEnabled(true);
            editWeight.setEnabled(true);
            editSymptoms.setEnabled(true);
            editDiagnosis.setEnabled(true);
            editNote.setEnabled(true);
            btnUpdate.setVisibility(View.VISIBLE);
        });
        btnUpdate.setOnClickListener(v -> {
            if(validate()){
                String strCheckupDate = Objects.requireNonNull(checkUpdate.getText()).toString().trim();
                Date checkUpdate1 = null;
                try {
                    checkUpdate1 = FormatDateUtils.StringToDate1(strCheckupDate);
                    strCheckupDate = FormatDateUtils.DateToString1(checkUpdate1);
                    HealRecordRequest healRecordRequest = new HealRecordRequest(strCheckupDate, Double.parseDouble(editWeight.getText().toString()) ,Integer.parseInt(spinner.getSelectedItem().toString()), editSymptoms.getText().toString(), editDiagnosis.getText().toString(), editNote.getText().toString(), healthRecord.getPet().getId());
                    ApiService.apiService.updateHealthRecord(healthRecord.getId(), healRecordRequest).enqueue(new Callback<HealRecordResponse>() {
                        @Override
                        public void onResponse(Call<HealRecordResponse> call, Response<HealRecordResponse> response) {
                            if(response.isSuccessful()){
                                Toast.makeText(UpdateStaticHealthActivity.this, "Cập nhập báo cáo sức khỏe thành công", Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Gson gson = new Gson();
                                HealthRecordErrorResponse healthRecordErrorResponse = null;
                                try {
                                    healthRecordErrorResponse = gson.fromJson(response.errorBody().string(), HealthRecordErrorResponse.class);
                                    String message = "";
                                    if (healthRecordErrorResponse.getMessage().getCheckUpDate() != null) {
                                        message += healthRecordErrorResponse.getMessage().getCheckUpDate() + "\n";
                                    }
                                    if (healthRecordErrorResponse.getMessage().getDiagnosis() != null) {
                                        message += healthRecordErrorResponse.getMessage().getDiagnosis() + "\n";
                                    }
                                    if (healthRecordErrorResponse.getMessage().getNote() != null) {
                                        message += healthRecordErrorResponse.getMessage().getNote() + "\n";
                                    }
                                    if (healthRecordErrorResponse.getMessage().getWeight() != null) {
                                        message += healthRecordErrorResponse.getMessage().getWeight() + "\n";
                                    }
                                    if(healthRecordErrorResponse.getMessage().getSymptoms() !=null) {
                                        message += healthRecordErrorResponse.getMessage().getSymptoms() + "\n";
                                    }
                                    if(healthRecordErrorResponse.getMessage().getExerciseLevel() !=null) {
                                        message += healthRecordErrorResponse.getMessage().getExerciseLevel() + "\n";
                                    }
                                    if(healthRecordErrorResponse.getMessage().getPetId() !=null) {
                                        message += healthRecordErrorResponse.getMessage().getPetId() + "\n";
                                    }
                                    DialogUtils.setUpDialog(UpdateStaticHealthActivity.this, message);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<HealRecordResponse> call, Throwable t) {
                            Toast.makeText(UpdateStaticHealthActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    private boolean validate(){
        if(checkUpdate.length()==0){
            checkUpdate.setError("Ngày kiểm tra không được để trống.");
            return false;
        }
        if(editWeight.length() == 0){
            editWeight.setError("Cân nặng không được để trống");
            return false;
        }
        return true;
    }
}