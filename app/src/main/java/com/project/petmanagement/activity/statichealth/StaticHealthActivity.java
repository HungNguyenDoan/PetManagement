package com.project.petmanagement.activity.statichealth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.project.petmanagement.R;
import com.project.petmanagement.adapters.StaticHealthAdapter;
import com.project.petmanagement.models.HealthRecord;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class StaticHealthActivity extends AppCompatActivity {
    private LineChart lineChart;
    private RecyclerView staticRecyclerView;
    private FloatingActionButton btnAdd;
    private ImageView btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_health);
        findViewById();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        try {
            customChart();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            staticRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
            StaticHealthAdapter staticHealthAdapter = new StaticHealthAdapter(this,getList());
            staticRecyclerView.setAdapter(staticHealthAdapter);
            staticRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StaticHealthActivity.this, AddStaticHealthActity.class);
                startActivity(intent);
            }
        });
    }

    private void findViewById(){
        lineChart = findViewById(R.id.line_chart);
        staticRecyclerView = findViewById(R.id.static_recycler_view);
        btnAdd = findViewById(R.id.btn_add);
        btnBack = findViewById(R.id.btn_back);

    }
    private List<HealthRecord> getList() throws ParseException {
        List<HealthRecord> healthRecords = new ArrayList<>();
        String dateString = "2002-01-01";
        String dateString1 = "2002-02-01";
        String dateString2 = "2002-03-01";
        String dateString3 = "2002-04-01";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(dateString);
        Date date1 = dateFormat.parse(dateString1);
        Date date2 = dateFormat.parse(dateString2);
        Date date3 = dateFormat.parse(dateString3);
        healthRecords.add(new HealthRecord(date,20f));
        healthRecords.add(new HealthRecord(date1,21f));
        healthRecords.add(new HealthRecord(date2,22f));
        healthRecords.add(new HealthRecord(date3,23f));
        return healthRecords;
    }
    private void customChart() throws ParseException {
        List<Float> weightList = new ArrayList<>();
        for(HealthRecord healthRecord: getList()){
            weightList.add(healthRecord.getWeight());

        }

        // Chuyển đổi danh sách cân nặng thành danh sách Entry
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < weightList.size(); i++) {
            entries.add(new Entry(i, weightList.get(i)));
        }

        // Tạo DataSet và cấu hình nó
        LineDataSet dataSet = new LineDataSet(entries, "Weight");
        dataSet.setColor(ContextCompat.getColor(this, R.color.green));
        dataSet.setValueTextColor(ContextCompat.getColor(this, R.color.green));
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
}