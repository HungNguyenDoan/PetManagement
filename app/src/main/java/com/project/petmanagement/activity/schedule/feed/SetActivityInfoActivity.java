package com.project.petmanagement.activity.schedule.feed;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.textfield.TextInputEditText;
import com.project.petmanagement.R;
import com.project.petmanagement.models.entity.DailyActivity;
import com.project.petmanagement.payloads.responses.ListDaiLyActivityResponse;
import com.project.petmanagement.services.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetActivityInfoActivity extends AppCompatActivity {

    private int stt=1;
    private LinearLayout parentLayout;
    private Button saveBtn;
    private ArrayAdapter<String> dailyActivityAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_activity_info);
        ImageView returnArrow = findViewById(R.id.return_arrow);
        CardView btnAddView = findViewById(R.id.add_activity_btn);
        parentLayout = findViewById(R.id.parent_layout);
        saveBtn = findViewById(R.id.save_btn);
        btnAddView.setOnClickListener(v -> {
            addView();
        });
        returnArrow.setOnClickListener(v -> finish());

        Button saveBtn = findViewById(R.id.save_btn);
        saveBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SetActivityScheduleActivity.class);
            startActivity(intent);
        });
        setUpButton();
    }
    private void addView() {
        final View childView = getLayoutInflater().inflate(R.layout.item_schedule_atv,null,false);
        TextView title = childView.findViewById(R.id.title);
        String strTile = "Hoạt động "+stt;
        stt+=1;
        title.setText(strTile);
        AutoCompleteTextView activityType = childView.findViewById(R.id.activity_type);
        ApiService.apiService.getAllDaiLyActivity().enqueue(new Callback<ListDaiLyActivityResponse>() {
            @Override
            public void onResponse(Call<ListDaiLyActivityResponse> call, Response<ListDaiLyActivityResponse> response) {
                if(response.isSuccessful()){
                    List<String> listStr = new ArrayList<>();
                    if(response.body()!=null && response.body().getData()!=null){
                        for(DailyActivity dailyActivity: response.body().getData()){
                            listStr.add(dailyActivity.getName());
                        }
                        dailyActivityAdapter = new ArrayAdapter<>(SetActivityInfoActivity.this, R.layout.list_item_dropdown, listStr);
                        activityType.setAdapter(dailyActivityAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ListDaiLyActivityResponse> call, Throwable t) {

            }
        });
        ImageView deleteLayout = childView.findViewById(R.id.delete_layout);
        deleteLayout.setOnClickListener(v -> {
            int position = parentLayout.indexOfChild(childView);
            parentLayout.removeView(childView);
            for(int i=position;i<parentLayout.getChildCount();i++){
                TextView title1 = parentLayout.getChildAt(i).findViewById(R.id.title);
                String strTile1 = "Hoạt động "+(i+1);
                title1.setText(strTile1);
            }
            stt = parentLayout.getChildCount()+1;
            if(parentLayout.getChildCount()==0){
                stt = 1;
            }
            setUpButton();
        });
        parentLayout.addView(childView);
        setUpButton();
    }
    private void setUpButton(){
        if(parentLayout.getChildCount()==0){
            saveBtn.setEnabled(false);
            saveBtn.setAlpha(0.4f);
        }else{
            saveBtn.setEnabled(true);
            saveBtn.setAlpha(1);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpButton();
    }
}