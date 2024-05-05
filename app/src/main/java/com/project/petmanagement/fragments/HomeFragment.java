package com.project.petmanagement.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.petmanagement.R;
import com.project.petmanagement.activity.pet.AddNewPetActivity;
import com.project.petmanagement.activity.schedule.ScheduleActivity;
import com.project.petmanagement.activity.schedule.careactivity.ManageCareActivityScheduleInfoActivity;
import com.project.petmanagement.activity.schedule.vaccine.ManageVaccineInjectionScheduleActivity;
import com.project.petmanagement.adapters.DatesOfMonthRecyclerAdapter;
import com.project.petmanagement.adapters.PetHomeAdapter;
import com.project.petmanagement.models.entity.Pet;
import com.project.petmanagement.payloads.responses.ListPetResponse;
import com.project.petmanagement.services.ApiService;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    private RecyclerView petRecyclerView, datesOfMonthRecyclerView, vaccinationNotificationRecyclerView, careNotificationRecyclerView;
    private List<Pet> petList;
    private LinearLayout existedPet;
    private Button addPetBtn;
    private RelativeLayout noPet;
    private List<Button> months;
    private ImageView noVaccinationNotificationImage, noCareNotificationImage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        getAllPetsByUser();
        getDatesRecyclerView();
        addPetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), AddNewPetActivity.class);
                startActivity(intent);
            }
        });
        noPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), AddNewPetActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout activityRedirect = view.findViewById(R.id.activity_redirect);
        activityRedirect.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ScheduleActivity.class);
            startActivity(intent);
        });
        LinearLayout injectActivity = view.findViewById(R.id.inject_activity);
        injectActivity.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ManageVaccineInjectionScheduleActivity.class);
            startActivity(intent);
        });
        LinearLayout careActivity = view.findViewById(R.id.care_activity);
        careActivity.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ManageCareActivityScheduleInfoActivity.class);
            startActivity(intent);
        });
    }

    private void initView(View view) {
        petRecyclerView = view.findViewById(R.id.pet_list_recycler_view);
        existedPet = view.findViewById(R.id.existed_pet_list);
        addPetBtn = view.findViewById(R.id.add_pet_btn);
        noPet = view.findViewById(R.id.no_pet);
        months = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            Button month;
            RecyclerView datesOfMonth;
            switch (i) {
                case 1:
                    month = view.findViewById(R.id.month_1);
                    months.add(month);
                    break;
                case 2:
                    month = view.findViewById(R.id.month_2);
                    months.add(month);
                    break;
                case 3:
                    month = view.findViewById(R.id.month_3);
                    months.add(month);
                    break;
                case 4:
                    month = view.findViewById(R.id.month_4);
                    months.add(month);
                    break;
                case 5:
                    month = view.findViewById(R.id.month_5);
                    months.add(month);
                    break;
                case 6:
                    month = view.findViewById(R.id.month_6);
                    months.add(month);
                    break;
                case 7:
                    month = view.findViewById(R.id.month_7);
                    months.add(month);
                    break;
                case 8:
                    month = view.findViewById(R.id.month_8);
                    months.add(month);
                    break;
                case 9:
                    month = view.findViewById(R.id.month_9);
                    months.add(month);
                    break;
                case 10:
                    month = view.findViewById(R.id.month_10);
                    months.add(month);
                    break;
                case 11:
                    month = view.findViewById(R.id.month_11);
                    months.add(month);
                    break;
                case 12:
                    month = view.findViewById(R.id.month_12);
                    months.add(month);
                    break;
            }
        }
        datesOfMonthRecyclerView = view.findViewById(R.id.dates_month_recycler_view);
        vaccinationNotificationRecyclerView = view.findViewById(R.id.vaccination_notification_recycler_view);
        noVaccinationNotificationImage = view.findViewById(R.id.no_vaccination_notification_image);
        careNotificationRecyclerView = view.findViewById(R.id.care_notification_recycler_view);
        noCareNotificationImage = view.findViewById(R.id.no_care_notification_image);
    }

    private List<LocalDate> getDatesOfMonth(int year, int month) {
        List<LocalDate> dates = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            YearMonth yearMonth = YearMonth.of(year, month);
            for (int day = 1; day <= yearMonth.lengthOfMonth(); day++) {
                dates.add(LocalDate.of(year, month, day));
            }
        }
        return dates;
    }

    private void getAllPetsByUser() {
        ApiService.apiService.getAllPetUser().enqueue(new Callback<ListPetResponse>() {
            @Override
            public void onResponse(Call<ListPetResponse> call, Response<ListPetResponse> response) {
                if (response.isSuccessful()) {
                    ListPetResponse listPetResponse = response.body();
                    if (listPetResponse != null) {
                        petList = listPetResponse.getData();
                        if (petList.isEmpty()) {
                            noPet.setVisibility(View.VISIBLE);
                            existedPet.setVisibility(View.GONE);
                        } else {
                            noPet.setVisibility(View.GONE);
                            existedPet.setVisibility(View.VISIBLE);
                            PetHomeAdapter petHomeAdapter = new PetHomeAdapter(requireContext(), petList);
                            LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
                            petRecyclerView.setAdapter(petHomeAdapter);
                            petRecyclerView.setLayoutManager(layoutManager);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ListPetResponse> call, Throwable t) {

            }
        });
    }

    private void getDatesRecyclerView() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDate currentDate = LocalDate.now();
            int year = currentDate.getYear();
            int currentMonth = currentDate.getMonth().getValue();
            for (int i = 0; i < months.size(); i++) {
                if (i + 1 == currentMonth) {
                    months.get(i).setBackgroundColor(Color.parseColor("#FFF5EB"));
                    months.get(i).setTextColor(Color.parseColor("#EDA33D"));
                    months.get(i).setAlpha(1);
                    List<LocalDate> localDateList = getDatesOfMonth(year, currentMonth);
                    DatesOfMonthRecyclerAdapter datesOfMonthRecyclerAdapter = new DatesOfMonthRecyclerAdapter(requireContext(), localDateList, vaccinationNotificationRecyclerView, noVaccinationNotificationImage, careNotificationRecyclerView, noCareNotificationImage);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
                    datesOfMonthRecyclerView.setAdapter(datesOfMonthRecyclerAdapter);
                    datesOfMonthRecyclerView.setLayoutManager(layoutManager);
                } else {
                    months.get(i).setBackgroundColor(Color.parseColor("#D8DAE7"));
                    months.get(i).setTextColor(Color.BLACK);
                    months.get(i).setAlpha(0.5F);
                }
                int position = i + 1;
                months.get(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int j = 0; j < months.size(); j++) {
                            if (j + 1 == position) {
                                months.get(j).setBackgroundColor(Color.parseColor("#FFF5EB"));
                                months.get(j).setTextColor(Color.parseColor("#EDA33D"));
                                months.get(j).setAlpha(1);
                                DatesOfMonthRecyclerAdapter datesOfMonthRecyclerAdapter = new DatesOfMonthRecyclerAdapter(requireContext(), getDatesOfMonth(year, position), vaccinationNotificationRecyclerView, noVaccinationNotificationImage, careNotificationRecyclerView, noCareNotificationImage);
                                LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
                                datesOfMonthRecyclerView.setAdapter(datesOfMonthRecyclerAdapter);
                                datesOfMonthRecyclerView.setLayoutManager(layoutManager);
                                continue;
                            }
                            months.get(j).setBackgroundColor(Color.parseColor("#D8DAE7"));
                            months.get(j).setTextColor(Color.BLACK);
                            months.get(j).setAlpha(0.5F);
                        }
                    }
                });
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getAllPetsByUser();
        getDatesRecyclerView();
    }
}