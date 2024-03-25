package com.project.petmanagement.utils;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import com.project.petmanagement.activity.pet.AddNewPetActivity;

public class DialogUtils {
    public static void setUpDialog(Context context, String message){
        AlertDialog.Builder arlertDialog = new AlertDialog.Builder(context);
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
