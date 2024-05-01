package com.project.petmanagement.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.project.petmanagement.R;
import com.project.petmanagement.activity.schedule.vaccine.VaccineScheduleDetailActivity;
import com.project.petmanagement.activity.statichealth.UpdateStaticHealthActivity;
import com.project.petmanagement.models.entity.HealthRecord;
import com.project.petmanagement.payloads.responses.Response;
import com.project.petmanagement.services.ApiService;
import com.project.petmanagement.utils.FormatDateUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class StaticHealthAdapter extends RecyclerView.Adapter<StaticHealthAdapter.StaticHealViewHolder>{
    private Context context;
    private List<HealthRecord> healthRecords;

    public StaticHealthAdapter(Context context, List<HealthRecord> healthRecords) {
        this.context = context;
        this.healthRecords = healthRecords;
    }

    @NonNull
    @Override
    public StaticHealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StaticHealViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.static_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StaticHealViewHolder holder, int position) {
        final HealthRecord healthRecord = healthRecords.get(position);
        holder.imageStatic.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.up));
        if(position!=0){
            if(healthRecords.get(position-1).getWeight()>healthRecord.getWeight()){
                holder.imageStatic.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.down));
            }else{
                holder.imageStatic.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.up));
            }
        }else{
            holder.imageStatic.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.up));
        }
        holder.weight.setText(String.valueOf(healthRecord.getWeight())+" kg");
        holder.checkupDate.setText(FormatDateUtils.DateToString(healthRecord.getCheckUpDate()));
        holder.itemStaticHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateStaticHealthActivity.class);
                intent.putExtra("healthRecord", healthRecord);
                context.startActivity(intent);
            }
        });
        holder.itemStaticHealth.setOnLongClickListener(v -> {
            AlertDialog.Builder arlertDialog = new AlertDialog.Builder(context);
            arlertDialog.setTitle("Thông báo")
                    .setMessage("Bạn chắc chán muốn xóa lịch này")
                    .setPositiveButton("Có", (dialog, which) -> {
                        ApiService.apiService.deleteHealthRecord(healthRecord.getId()).enqueue(new Callback<Response>() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(context, "Xóa thông báo thành công.", Toast.LENGTH_SHORT).show();
                                    healthRecords.remove(healthRecord);
                                    notifyDataSetChanged();
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

    @Override
    public int getItemCount() {
        if (healthRecords!=null){
            return healthRecords.size();
        }
        return 0;
    }

    public static class StaticHealViewHolder extends RecyclerView.ViewHolder{
        private final ImageView imageStatic;
        private final TextView weight;
        private final TextView checkupDate;
        private final RelativeLayout itemStaticHealth;
        public StaticHealViewHolder(@NonNull View itemView) {
            super(itemView);
            imageStatic = itemView.findViewById(R.id.image_static);
            weight = itemView.findViewById(R.id.static_weight);
            checkupDate = itemView.findViewById(R.id.static_checkup_date);
            itemStaticHealth = itemView.findViewById(R.id.item_static);
        }
    }
}
