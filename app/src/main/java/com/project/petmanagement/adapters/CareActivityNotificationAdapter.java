package com.project.petmanagement.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.project.petmanagement.R;
import com.project.petmanagement.models.entity.CareActivityNotification;
import com.project.petmanagement.models.enums.FrequencyEnum;
import com.project.petmanagement.utils.FormatDateUtils;

import java.util.Date;
import java.util.List;

public class CareActivityNotificationAdapter extends RecyclerView.Adapter<CareActivityNotificationAdapter.CareActivityNotificationViewHolder> {
    private List<CareActivityNotification> careActivityNotifications;
    private Context context;

    public CareActivityNotificationAdapter(List<CareActivityNotification> careActivityNotifications, Context context) {
        this.careActivityNotifications = careActivityNotifications;
        this.context = context;
    }

    @NonNull
    @Override
    public CareActivityNotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.care_activity_notification_item, parent,false);
        return new CareActivityNotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CareActivityNotificationViewHolder holder, int position) {
        final CareActivityNotification careActivityNotification = careActivityNotifications.get(position);
        String strTitle = "Thông báo " + (position+1);
        holder.title.setText(strTitle);
        if(careActivityNotification.getNotificationStatus()){
            holder.status.setChecked(true);
        }else {
            holder.status.setChecked(false);
        }
        if(careActivityNotification.getSchedule().getFrequency() == FrequencyEnum.NO_REPEAT){
            holder.frequency.setText("Không lặp lại");
            String date1 = FormatDateUtils.DateToString1(careActivityNotification.getSchedule().getDate());
            holder.date.setText(date1);
        } else {
            if (careActivityNotification.getSchedule().getFrequency() == FrequencyEnum.DAILY)
                holder.frequency.setText("Lặp lại mỗi "+ careActivityNotification.getSchedule().getValue() +" ngày 1 lần");
            else
                holder.frequency.setText("Lặp lại mỗi "+ careActivityNotification.getSchedule().getValue() +" tuần 1 lần");
            String fromDate = FormatDateUtils.DateToString1(careActivityNotification.getSchedule().getFromDate());
            String toDate = FormatDateUtils.DateToString1(careActivityNotification.getSchedule().getToDate());
            String date = "Từ ngày "+fromDate +" đến ngày "+ toDate;
            holder.date.setText(date);
        }
        holder.relativeLayout.setOnClickListener(v -> {
            Intent intent = new Intent();
        });
    }

    @Override
    public int getItemCount() {
        if(careActivityNotifications!=null){
            return careActivityNotifications.size();
        }
        return 0;
    }

    static class CareActivityNotificationViewHolder extends RecyclerView.ViewHolder{
        private final TextView title, frequency, date;
        private final SwitchCompat status;
        private final RelativeLayout relativeLayout;
        public CareActivityNotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            frequency = itemView.findViewById(R.id.frequency);
            date = itemView.findViewById(R.id.date);
            status = itemView.findViewById(R.id.status);
            relativeLayout = itemView.findViewById(R.id.layout);
        }
    }
}
