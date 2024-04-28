package com.project.petmanagement.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.project.petmanagement.R;
import com.project.petmanagement.activity.schedule.feed.ActivityScheduleInfoActivity;
import com.project.petmanagement.activity.schedule.ScheduleActivity;
import com.project.petmanagement.activity.schedule.inject.VaccineInjectionScheduleActivity;
import com.project.petmanagement.activity.schedule.walk.WalkScheduleInfoActivity;


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
        activityRedirect.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ScheduleActivity.class);
            startActivity(intent);
        });
        LinearLayout injectActivity = view.findViewById(R.id.inject_activity);
        injectActivity.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), VaccineInjectionScheduleActivity.class);
            startActivity(intent);
        });
        LinearLayout careActivity = view.findViewById(R.id.care_activity);
        careActivity.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ActivityScheduleInfoActivity.class);
            startActivity(intent);
        });

    }
}