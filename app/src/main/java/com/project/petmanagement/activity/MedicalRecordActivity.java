package com.project.petmanagement.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.project.petmanagement.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MedicalRecordActivity extends AppCompatActivity {
    private ImageView btnAdd, btnBack;
    private Bitmap image;
    private Dialog dialog;
    private LinearLayout empty;
    private RelativeLayout occupied;
    private TextView nameMedical;
    private ImageView imageMedical;
    String name;
    private int check = 0;

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_PICK = 2;
    private ActivityResultLauncher<Intent> galleryLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode()==RESULT_OK && result.getData()!=null){
                Uri uri = result.getData().getData();
                if (uri!=null){
                    try {
                        image = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                        imageMedical.setImageBitmap(image);
                        nameMedical.setText(name);
                        check =1;
                        dialog.cancel();
                        checkEmpty();

                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_record);
        btnAdd = findViewById(R.id.add);
        btnBack = findViewById(R.id.btn_back);
        empty = findViewById(R.id.linear_empty);
        occupied = findViewById(R.id.occupied);
        imageMedical = findViewById(R.id.image_medical);
        nameMedical = findViewById(R.id.name_medical);
        checkEmpty();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFeedBackDialog(Gravity.CENTER);
            }
        });
    }
    private void checkEmpty(){
        if(check == 0){
            empty.setVisibility(View.VISIBLE);
            occupied.setVisibility(View.GONE);
        }else{
            empty.setVisibility(View.GONE);
            occupied.setVisibility(View.VISIBLE);
        }
    }
    private void openFeedBackDialog(int gravity){
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_dialog);
        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttribute = window.getAttributes();
        windowAttribute.gravity = Gravity.CENTER;
        window.setAttributes(windowAttribute);
        if(Gravity.BOTTOM == gravity){
            dialog.setCancelable(false);
        } else {
            dialog.setCancelable(true);
        }
        EditText nameMedical = dialog.findViewById(R.id.name_medical);
        TextView btnAccept = dialog.findViewById(R.id.btn_accept);
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameMedical.getText().toString();
                requestPermission();

            }
        });
        dialog.show();
    }
    private void startGallery(){
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryLauncher.launch(pickPhoto);
    }
    private void requestPermission(){
        androidx.appcompat.app.AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Chọn một ảnh");
        alertDialog.setMessage("Chọn ảnh từ");
        alertDialog.setPositiveButton("Bộ sưu tập", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startGallery();
            }
        });
        alertDialog.setNegativeButton("Máy ảnh", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(ContextCompat.checkSelfPermission(MedicalRecordActivity.this,Manifest.permission.CAMERA)==PackageManager.PERMISSION_GRANTED) {
//                    startCamera();
                }else {
                    ActivityCompat.requestPermissions(MedicalRecordActivity.this, new String[]{Manifest.permission.CAMERA}, REQUEST_IMAGE_CAPTURE);

                }
            }
        });
        alertDialog.setNeutralButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alertDialog.show();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_IMAGE_PICK) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startGallery();
            } else {
                Toast.makeText(this, "Không được chấp nhận", Toast.LENGTH_SHORT).show();
            }
        }
    }

}