package com.project.petmanagement.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.project.petmanagement.R;
import com.project.petmanagement.activity.medical.MedicalDocumentActivity;
import com.project.petmanagement.activity.statichealth.StaticHealthActivity;
import com.project.petmanagement.models.entity.HealthRecord;
import com.project.petmanagement.models.entity.Pet;
import com.project.petmanagement.payloads.responses.ListHealthRecordResponse;
import com.project.petmanagement.payloads.responses.PetResponse;
import com.project.petmanagement.services.ApiService;
import com.project.petmanagement.utils.FormatDateUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class InforPetFragment extends Fragment {
    private LineChart lineChart;
    private long idPet;
    private ImageView gender;
    private TextView age;
    private TextView seeMoreStatic, seeMoreMedical;
    private List<HealthRecord> healthRecords;
    private Pet pet;
    public InforPetFragment(long idPet) {
        this.idPet = idPet;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_infor_pet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViewById(view);
        healthRecords = new ArrayList<>();
        getHealthRecord();
        getPet();
        seeMoreStatic.setOnClickListener(v -> {
            Intent intent = new Intent(requireActivity(), StaticHealthActivity.class);
            intent.putExtra("petId", idPet);
            startActivity(intent);
        });
        seeMoreMedical.setOnClickListener(v -> {
            Intent intent = new Intent(requireActivity(), MedicalDocumentActivity.class);
            intent.putExtra("petId", idPet);
            startActivity(intent);
        });
    }
    private void getPet(){
        ApiService.apiService.getPetDetail(idPet).enqueue(new Callback<PetResponse>() {
            @Override
            public void onResponse(Call<PetResponse> call, Response<PetResponse> response) {
                if (response.isSuccessful()){
                    PetResponse petResponse = response.body();
                    if (petResponse!=null){
                        pet = petResponse.getData();
                        if(pet.getGender() == 0){
                            gender.setImageDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.baseline_female_24));
                        }else{
                            gender.setImageDrawable(ContextCompat.getDrawable(requireActivity(),R.drawable.baseline_male_24));
                        }
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            age.setText(FormatDateUtils.calculate(pet.getDateOfBirth()));
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<PetResponse> call, Throwable t) {
            }
        });
    }
    private void findViewById(View view){
        lineChart = view.findViewById(R.id.line_chart);
        seeMoreStatic = view.findViewById(R.id.see_more_static);
        seeMoreMedical = view.findViewById(R.id.see_more_medical);
        gender = view.findViewById(R.id.gender);
        age = view.findViewById(R.id.age);
    }
    private void customChart(){
        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();
        for (int i = 0; i < healthRecords.size(); i++){
            HealthRecord healthRecord = healthRecords.get(i);
            float x = i;
            float y = healthRecord.getWeight().floatValue();
            entries.add(new Entry(x, y));
            String date = FormatDateUtils.DateToString(healthRecord.getCheckUpDate());
            // Add date string to labels list
            labels.add(date);
        }

        // Tạo DataSet và cấu hình nó
        LineDataSet dataSet = new LineDataSet(entries, "Weight");
        dataSet.setColor(ContextCompat.getColor(requireContext(), R.color.green));
        dataSet.setValueTextColor(ContextCompat.getColor(requireContext(), R.color.green));
        dataSet.setLineWidth(1f);
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        // Tạo LineData và thêm DataSet vào đó
        LineData lineData = new LineData(dataSet);

        // Cấu hình trục X và trục Y
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(8f);
        xAxis.setGranularity(1f); // Đơn vị giữa các giá trị trên trục X
        xAxis.setValueFormatter(new CustomXAxisValueFormatter(labels)); // Định dạng giá trị trục X
        xAxis.setDrawGridLines(false);
        YAxis yAxis = lineChart.getAxisLeft();
        yAxis.setGranularity(1f); // Đơn vị giữa các giá trị trên trục Y
        YAxis yAxisRight = lineChart.getAxisRight();
        yAxisRight.setEnabled(false);
        yAxis.setDrawGridLines(false);
        YAxis yAxisLeft = lineChart.getAxisLeft();
        yAxisLeft.setEnabled(true);
        // Đặt dữ liệu vào biểu đồ
        lineChart.setData(lineData);
        lineChart.setDragEnabled(true);
        lineChart.setExtraRightOffset(20f);
        lineChart.setPinchZoom(true);
        // Cập nhật biểu đồ
        lineChart.invalidate();
    }
    public static class CustomXAxisValueFormatter extends ValueFormatter {

        private final List<String> labels;

        public CustomXAxisValueFormatter(List<String> labels) {
            this.labels = labels;
        }

        @Override
        public String getAxisLabel(float value, AxisBase axis) {
            // Ensure the value is within the index range
            int index = (int) value;
            if (index >= 0 && index < labels.size()) {
                return labels.get(index);
            } else {
                return "";
            }
        }
    }
    private void getHealthRecord(){
        ApiService.apiService.getHealthRecordByPet(idPet).enqueue(new Callback<ListHealthRecordResponse>() {
            @Override
            public void onResponse(Call<ListHealthRecordResponse> call, Response<ListHealthRecordResponse> response) {
                if(response.isSuccessful()){
                    ListHealthRecordResponse healthRecordResponse = response.body();
                    if(healthRecordResponse!=null&& healthRecordResponse.getData()!=null){
                        List<HealthRecord> healthRecordList = healthRecordResponse.getData();
                        if(healthRecordList.size()<=5){
                            healthRecords.addAll(healthRecordList);
                        }else{
                            for(int i = healthRecordList.size()-5; i < healthRecordList.size();i++){
                                healthRecords.add(healthRecordList.get(i));
                            }
                        }
                        customChart();

                    }
                }
            }

            @Override
            public void onFailure(Call<ListHealthRecordResponse> call, Throwable t) {

            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        getPet();
    }
}