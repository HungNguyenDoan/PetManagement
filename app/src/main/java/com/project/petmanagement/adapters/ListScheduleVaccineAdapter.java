package com.project.petmanagement.adapters;

import android.content.Context;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.project.petmanagement.R;
import com.project.petmanagement.models.entity.OneTimeSchedule;
import com.project.petmanagement.models.entity.VaccinationNotification;

import java.util.List;

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
                oneTimeSchedule = oneTimeSchedule;
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
    }

    public static class ScheduleViewHolder extends RecyclerView.ViewHolder{
        private final Button hour;
        private final TextView title;
        private final TextView status;
        private final CheckBox statusCheckBox;

        public ScheduleViewHolder(@NonNull View itemView) {
            super(itemView);
            hour = itemView.findViewById(R.id.hour);
            title = itemView.findViewById(R.id.title);
            status = itemView.findViewById(R.id.status);
            statusCheckBox = itemView.findViewById(R.id.status_checked);
        }
    }
}
