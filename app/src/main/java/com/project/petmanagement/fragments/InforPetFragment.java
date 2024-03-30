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
import com.google.android.material.tabs.TabLayoutMediator;
import com.project.petmanagement.R;
import com.project.petmanagement.activity.medical.MedicalRecordActivity;
import com.project.petmanagement.activity.pet.PetDetailsActivity;
import com.project.petmanagement.activity.statichealth.StaticHealthActivity;
import com.project.petmanagement.adapters.PetDetailsViewPager2Adapter;
import com.project.petmanagement.models.Pet;
import com.project.petmanagement.payload.response.PetResponse;
import com.project.petmanagement.services.ApiService;
import com.project.petmanagement.utils.FormatDateUtils;
import com.project.petmanagement.utils.ImageUtils;

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
        customChart();
        getPet();
        seeMoreStatic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), StaticHealthActivity.class);
                startActivity(intent);
            }
        });
        seeMoreMedical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), MedicalRecordActivity.class);
                startActivity(intent);
            }
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
                            gender.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.baseline_female_24));
                        }else{
                            gender.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.baseline_male_24));
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
        List<Float> weightList = new ArrayList<>();
        weightList.add(20f);
        weightList.add(21f);
        weightList.add(22f);
        weightList.add(23f);

        // Chuyển đổi danh sách cân nặng thành danh sách Entry
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < weightList.size(); i++) {
            entries.add(new Entry(i, weightList.get(i)));
        }

        // Tạo DataSet và cấu hình nó
        LineDataSet dataSet = new LineDataSet(entries, "Weight");
        dataSet.setColor(ContextCompat.getColor(requireContext(), R.color.green));
        dataSet.setValueTextColor(ContextCompat.getColor(requireContext(), R.color.green));
        dataSet.setLineWidth(2f);
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        // Tạo LineData và thêm DataSet vào đó
        LineData lineData = new LineData(dataSet);

        // Cấu hình trục X và trục Y
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f); // Đơn vị giữa các giá trị trên trục X
        xAxis.setValueFormatter(new IndexAxisValueFormatter()); // Định dạng giá trị trục X
        xAxis.setDrawGridLines(false);
        YAxis yAxis = lineChart.getAxisLeft();
        yAxis.setGranularity(1f); // Đơn vị giữa các giá trị trên trục Y
        YAxis yAxisRight = lineChart.getAxisRight();
        yAxisRight.setEnabled(false);
        yAxis.setDrawGridLines(false);
        YAxis yAxisLeft = lineChart.getAxisLeft();
        yAxisLeft.setEnabled(false);
        // Đặt dữ liệu vào biểu đồ
        lineChart.setData(lineData);

        // Cập nhật biểu đồ
        lineChart.invalidate();
    }
    private static class IndexAxisValueFormatter extends ValueFormatter {
        @Override
        public String getAxisLabel(float value, AxisBase axis) {
            int monthIndex = (int) value;
            String[] monthNames = new SimpleDateFormat("MMMM", Locale.getDefault()).getDateFormatSymbols().getMonths();

            if (monthIndex >= 0 && monthIndex < monthNames.length) {
                return monthNames[monthIndex];
            } else {
                return "";
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getPet();
    }
}