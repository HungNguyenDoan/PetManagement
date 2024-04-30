package com.project.petmanagement.activity.schedule.careactivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.petmanagement.R;

public class SetActivityNotificationActivity extends AppCompatActivity {


    private TextView frequency;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_activity_notification);
        LinearLayout changeFrequency = findViewById(R.id.change_frequency);
        frequency = findViewById(R.id.frequency);
        Button saveBtn = findViewById(R.id.save_btn);
        saveBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SetActivityScheduleActivity.class);
            startActivity(intent);
        });
        final int[] checkedItem = {-1};
        changeFrequency.setOnClickListener(v -> {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(SetActivityNotificationActivity.this);
            final String[] listItems = new String[]{"Không lặp lại", "Lặp theo ngày", "Lặp theo tuần", "Lặp theo tháng"};
            alertDialog.setSingleChoiceItems(listItems, checkedItem[0], (dialog, which) -> {
                checkedItem[0] = which;
                frequency.setText(listItems[which]);
                dialog.dismiss();
            });
            alertDialog.setNegativeButton("Cancel", (dialog, which) -> {
            });
            AlertDialog customAlertDialog = alertDialog.create();
            customAlertDialog.show();
        });
        ImageView returnArrow = findViewById(R.id.return_arrow);
        returnArrow.setOnClickListener(v -> finish());
    }
}