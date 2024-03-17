package com.project.petmanagement.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;
import com.project.petmanagement.R;
import com.project.petmanagement.model.Image;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UpdatePetActivity extends AppCompatActivity {

    private ImageView btnBack;
    private TextInputEditText dob;

    private String[] species = {"Chó", "Mèo"};
    private String[] breed1 = {"Chó 1", "Chó2"};
    private String[] breed2 = {"Mèo 1", "Mèo 2"};
    private AutoCompleteTextView speciesView;
    private AutoCompleteTextView breedView;
    private ArrayAdapter<String> speciesAdapter;
    private ArrayAdapter<String> breedAdapter;
    private DatePickerDialog datePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pet);
        btnBack = findViewById(R.id.btn_back);
        speciesView = findViewById(R.id.species);
        breedView = findViewById(R.id.breed);
        dob = findViewById(R.id.dob);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        speciesAdapter = new ArrayAdapter<>(this, R.layout.list_item_dropdown, species);
        speciesView.setAdapter(speciesAdapter);
        speciesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String speciesSelect = parent.getItemAtPosition(position).toString();
                if(speciesSelect.equals("Chó")){
                    breedAdapter = new ArrayAdapter<>(UpdatePetActivity.this, R.layout.list_item_dropdown, breed1);
                } else if (speciesSelect.equals("Mèo")) {
                    breedAdapter = new ArrayAdapter<>(UpdatePetActivity.this, R.layout.list_item_dropdown, breed2);
                }
                breedView.setAdapter(breedAdapter);
            }
        });
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDob();
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
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            Date date1 = sdf.parse(date);
                            String date2 = sdf.format(date1);
                            dob.setText(date2);

                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }
    private  void setUpDialog(String message){
        AlertDialog.Builder arlertDialog = new AlertDialog.Builder(UpdatePetActivity.this);
        arlertDialog.setTitle("Thông báo")
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }
}