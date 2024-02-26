package com.project.petmanagement.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.petmanagement.R;
import com.project.petmanagement.activity.LoginActivity;
import com.project.petmanagement.activity.ManagePetActivity;
import com.project.petmanagement.adapter.ProfileHorizontalRecyclerViewAdapter;
import com.project.petmanagement.model.ProfileHorizontalItem;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {
    private RecyclerView horizontalRecyclerView, verticalRecyclerView;
    private ProfileHorizontalRecyclerViewAdapter horizontalRecyclerViewAdapter;
    private Button btnLogin;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        horizontalRecyclerView = view.findViewById(R.id.profile_horizontal_recycler_view);
        btnLogin = view.findViewById(R.id.btn_login);
        List<ProfileHorizontalItem> list = getItemListHorizontal();
        horizontalRecyclerViewAdapter = new ProfileHorizontalRecyclerViewAdapter(getContext(), list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        horizontalRecyclerView.setLayoutManager(manager);
        horizontalRecyclerView.setAdapter(horizontalRecyclerViewAdapter);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
    private List<ProfileHorizontalItem> getItemListHorizontal(){
        List<ProfileHorizontalItem> itemList = new ArrayList<>();
        itemList.add(new ProfileHorizontalItem(R.drawable.baseline_pets_24, "Thú cưng",R.color.green_background, ManagePetActivity.class));
        itemList.add(new ProfileHorizontalItem(R.drawable.baseline_pets_24, "Thú cưng",R.color.green_background, ManagePetActivity.class));
        itemList.add(new ProfileHorizontalItem(R.drawable.baseline_pets_24, "Thú cưng",R.color.green_background, ManagePetActivity.class));
        itemList.add(new ProfileHorizontalItem(R.drawable.baseline_pets_24, "Thú cưng",R.color.green_background,ManagePetActivity.class));
        itemList.add(new ProfileHorizontalItem(R.drawable.baseline_pets_24, "Thú cưng",R.color.green_background, ManagePetActivity.class));
        itemList.add(new ProfileHorizontalItem(R.drawable.baseline_pets_24, "Thú cưng",R.color.green_background, ManagePetActivity.class));
        itemList.add(new ProfileHorizontalItem(R.drawable.baseline_pets_24, "Thú cưng",R.color.green_background, ManagePetActivity.class));
        itemList.add(new ProfileHorizontalItem(R.drawable.baseline_pets_24, "Thú cưng",R.color.green_background, ManagePetActivity.class));
        return itemList;
    }
}