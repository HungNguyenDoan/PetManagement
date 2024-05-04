package com.project.petmanagement.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.petmanagement.R;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class DatesOfMonthRecyclerAdapter extends RecyclerView.Adapter<DatesOfMonthRecyclerAdapter.DatesOfMonthViewHolder> {
    private Context context;

    private List<LocalDate> datesOfMonth;

    public DatesOfMonthRecyclerAdapter(Context context, List<LocalDate> datesOfMonth) {
        this.context = context;
        this.datesOfMonth = datesOfMonth;
    }

    @NonNull
    @Override
    public DatesOfMonthViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_date_of_month, parent, false);
        return new DatesOfMonthViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull DatesOfMonthViewHolder holder, @SuppressLint("RecyclerView") int position) {
        LocalDate localDate = datesOfMonth.get(position);
        System.out.println(localDate);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            holder.dateOfMonth.setText(covertDayOfWeek(localDate.getDayOfWeek()) + "\n" + localDate.getDayOfMonth());
            if (localDate.equals(LocalDate.now())) {
                holder.dateOfMonth.setBackgroundColor(Color.parseColor("#EFF7EA"));
                holder.dateOfMonth.setTextColor(Color.parseColor("#FF61B93C"));
            } else {
                holder.dateOfMonth.setBackgroundColor(Color.parseColor("#D8DAE7"));
                holder.dateOfMonth.setTextColor(Color.BLACK);
                holder.dateOfMonth.setAlpha(0.6F);
            }
        }
        holder.dateOfMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < datesOfMonth.size(); i++) {
                    if (i == position) {
                        holder.dateOfMonth.setBackgroundColor(Color.parseColor("#EFF7EA"));
                        holder.dateOfMonth.setTextColor(Color.parseColor("#FF61B93C"));
                        continue;
                    }
                    holder.dateOfMonth.setBackgroundColor(Color.parseColor("#D8DAE7"));
                    holder.dateOfMonth.setTextColor(Color.BLACK);
                    holder.dateOfMonth.setAlpha(0.6F);

                }

            }
        });
    }

    @Override
    public int getItemCount() {
        if (datesOfMonth != null) {
            return datesOfMonth.size();
        }
        return 0;
    }

    public static class DatesOfMonthViewHolder extends RecyclerView.ViewHolder {
        private Button dateOfMonth;

        public DatesOfMonthViewHolder(@NonNull View itemView) {
            super(itemView);
            dateOfMonth = itemView.findViewById(R.id.date_of_month_item);
        }
    }

    private String covertDayOfWeek(DayOfWeek dayOfWeek) {
        String day = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            switch (dayOfWeek) {
                case MONDAY:
                    day = "Thứ 2";
                    break;
                case TUESDAY:
                    day = "Thứ 3";
                    break;
                case WEDNESDAY:
                    day = "Thứ 4";
                    break;
                case THURSDAY:
                    day = "Thứ 5";
                    break;
                case FRIDAY:
                    day = "Thứ 6";
                    break;
                case SATURDAY:
                    day = "Thứ 7";
                    break;
                case SUNDAY:
                    day = "CN";
                    break;
            }
        }
        return day;
    }
}
