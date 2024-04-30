package com.project.petmanagement.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.project.petmanagement.R;
import com.project.petmanagement.activity.schedule.inject.VaccineScheduleDetailActivity;
import com.project.petmanagement.models.entity.OneTimeSchedule;
import com.project.petmanagement.models.entity.VaccinationNotification;
import com.project.petmanagement.payloads.responses.Response;
import com.project.petmanagement.services.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class ListScheduleVaccineAdapter extends RecyclerView.Adapter<ListScheduleVaccineAdapter.ScheduleViewHolder> {
    private List<VaccinationNotification> vaccinationNotificationList;
    private Context context;

    public ListScheduleVaccineAdapter(List<VaccinationNotification> vaccinationNotificationList, Context context) {
        this.vaccinationNotificationList = vaccinationNotificationList;
        this.context = context;
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_vaccine_item, parent, false);
        return new ScheduleViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if(vaccinationNotificationList != null){
            return vaccinationNotificationList.size();
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        final VaccinationNotification vaccinationNotification = vaccinationNotificationList.get(position);
        boolean check=false;
        OneTimeSchedule oneTimeSchedule1 = null;
        for(OneTimeSchedule oneTimeSchedule: vaccinationNotification.getSchedules()){
            if(!oneTimeSchedule.getVaccinationStatus()){
                holder.hour.setText(oneTimeSchedule.getTime());
                holder.title.setText(vaccinationNotification.getTitle());
                holder.status.setText("Chưa tiêm");
                holder.status.setTextColor(ContextCompat.getColor(context,R.color.red));
                oneTimeSchedule1 = oneTimeSchedule;
                holder.statusCheckBox.setChecked(false);
                check = true;
                break;
            }
        }
        if(!check){
            OneTimeSchedule oneTimeSchedule = vaccinationNotification.getSchedules().get(vaccinationNotification.getSchedules().size()-1);
            holder.hour.setText(oneTimeSchedule.getTime());
            holder.title.setText(vaccinationNotification.getTitle());
            holder.status.setText("Đã tiêm");
            holder.status.setTextColor(ContextCompat.getColor(context,R.color.green));
            holder.statusCheckBox.setChecked(true);
        }
//        holder.statusCheckBox.setOnClickListener(v -> {
//            if(oneTimeSchedule1!=null && holder.statusCheckBox.isChecked()){
//                oneTimeSchedule1.setVaccinationStatus(true);
//
//            }
//        });
        holder.layout.setOnClickListener(v -> {
            Intent intent = new Intent(context, VaccineScheduleDetailActivity.class);
            intent.putExtra("vaccineNotification", vaccinationNotification);
            context.startActivity(intent);
        });
        holder.layout.setOnLongClickListener(v -> {
            AlertDialog.Builder arlertDialog = new AlertDialog.Builder(context);
            arlertDialog.setTitle("Thông báo")
                    .setMessage("Bạn chắc chán muốn xóa thông báo")
                    .setPositiveButton("Có", (dialog, which) -> {
                        ApiService.apiService.deleteVaccineNotification(vaccinationNotification.getId()).enqueue(new Callback<Response>() {
                            @Override
                            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                                if(response.isSuccessful()){
                                    Toast.makeText(context, "Xóa thông báo thành công.", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Response> call, Throwable t) {
                                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    })
                    .setNegativeButton("Không", (dialog, which) -> dialog.cancel())
                    .show();
            return true;
        });
    }

    public static class ScheduleViewHolder extends RecyclerView.ViewHolder{
        private final Button hour;
        private final TextView title;
        private final TextView status;
        private final CheckBox statusCheckBox;
        private final LinearLayout layout;

        public ScheduleViewHolder(@NonNull View itemView) {
            super(itemView);
            hour = itemView.findViewById(R.id.hour);
            title = itemView.findViewById(R.id.title);
            status = itemView.findViewById(R.id.status);
            statusCheckBox = itemView.findViewById(R.id.status_checked);
            layout = itemView.findViewById(R.id.layout);
        }
    }
}
