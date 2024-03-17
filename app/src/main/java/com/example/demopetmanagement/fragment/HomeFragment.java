package com.example.demopetmanagement.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.demopetmanagement.activity.feed.FeedScheduleInfo;
import com.example.demopetmanagement.activity.injection.SelectInjectionType;
import com.example.demopetmanagement.R;
import com.example.demopetmanagement.activity.Schedule;
import com.example.demopetmanagement.activity.walk.WalkScheduleInfo;

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
                Intent intent = new Intent(getContext(), Schedule.class);
                startActivity(intent);
            }
        });
        LinearLayout injectActivity = view.findViewById(R.id.inject_activity);
        injectActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SelectInjectionType.class);
                startActivity(intent);
            }
        });

        LinearLayout feedActivity = view.findViewById(R.id.feed_activity);
        feedActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FeedScheduleInfo.class);
                startActivity(intent);
            }
        });


        LinearLayout walkActivity = view.findViewById(R.id.walk_activity);
        walkActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WalkScheduleInfo.class);
                startActivity(intent);
            }
        });
    }
}