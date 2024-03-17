package com.project.petmanagement.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.project.petmanagement.R;
import com.project.petmanagement.activity.NotificationActivity;
import com.project.petmanagement.activity.PetInjectionActivity;
import com.project.petmanagement.activity.ScheduleActivity;
//import com.project.petmanagement.activity.PetInjection;
//import com.project.petmanagement.activity.Schedule;

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayout activityRedirect = view.findViewById(R.id.activity_redirect);
        activityRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ScheduleActivity.class);
                startActivity(intent);
            }
        });
        LinearLayout injectActivity = view.findViewById(R.id.inject_activity);
        injectActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PetInjectionActivity.class);
                startActivity(intent);
            }
        });
    }
}