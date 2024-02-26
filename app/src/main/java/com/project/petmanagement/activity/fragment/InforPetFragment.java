package com.project.petmanagement.activity.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.project.petmanagement.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


public class InforPetFragment extends Fragment {
    private LineChart lineChart;
    String[] axisData = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept",
            "Oct", "Nov", "Dec"};
    int[] yAxisData = {50, 20, 15, 30, 20, 60, 15, 40, 45, 10, 90, 18};
    public InforPetFragment() {
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
        List<Float> weightList = new ArrayList<>();
        weightList.add(70.5f);
        weightList.add(71.2f);
        weightList.add(69.8f);
        weightList.add(72.0f);
        weightList.add(70.2f);

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

        // Tạo LineData và thêm DataSet vào đó
        LineData lineData = new LineData(dataSet);

        // Cấu hình trục X và trục Y
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f); // Đơn vị giữa các giá trị trên trục X
        xAxis.setValueFormatter(new IndexAxisValueFormatter()); // Định dạng giá trị trục X

        YAxis yAxis = lineChart.getAxisLeft();
        yAxis.setGranularity(1f); // Đơn vị giữa các giá trị trên trục Y

        // Đặt dữ liệu vào biểu đồ
        lineChart.setData(lineData);

        // Cập nhật biểu đồ
        lineChart.invalidate();
    }

    private void findViewById(View view){
        lineChart = view.findViewById(R.id.line_chart);
    }
    private class IndexAxisValueFormatter extends ValueFormatter {
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
}