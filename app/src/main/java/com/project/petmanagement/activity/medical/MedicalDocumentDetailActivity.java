package com.project.petmanagement.activity.medical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.petmanagement.R;
import com.project.petmanagement.models.entity.MedicalDocument;
import com.project.petmanagement.payloads.responses.MedicalDocumentResponse;
import com.project.petmanagement.services.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedicalDocumentDetailActivity extends AppCompatActivity {
    private TextView titleDocument;
    private TextView noteDocument;
    private ImageView openFile;
    private ImageView btnUpdate, btnBack;
    private Long medicalId;
    private MedicalDocument medicalDocument;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_document_detail);
        titleDocument = findViewById(R.id.title_document);
        noteDocument = findViewById(R.id.note);
        openFile = findViewById(R.id.file_open);
        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        medicalId = getIntent().getLongExtra("medicalId",0);
        getMedicalDocument();
        openFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MedicalDocumentDetailActivity.this, ViewPdfActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });
    }
    private void getMedicalDocument(){
        ApiService.apiService.getMedicalDocumentByid(medicalId).enqueue(new Callback<MedicalDocumentResponse>() {
            @Override
            public void onResponse(Call<MedicalDocumentResponse> call, Response<MedicalDocumentResponse> response) {
                if (response.isSuccessful()){
                    MedicalDocumentResponse medicalDocumentResponse = response.body();
                    if (medicalDocumentResponse!=null && medicalDocumentResponse.getData()!=null){
                        medicalDocument = medicalDocumentResponse.getData();
                        titleDocument.setText(medicalDocument.getTitle());
                        noteDocument.setText(medicalDocument.getNote());
                        url = medicalDocument.getUrl();
                    }
                }
            }

            @Override
            public void onFailure(Call<MedicalDocumentResponse> call, Throwable t) {

            }
        });
    }
}