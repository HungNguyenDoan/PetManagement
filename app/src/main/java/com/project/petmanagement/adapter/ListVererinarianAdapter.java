package com.project.petmanagement.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.project.petmanagement.R;
import com.project.petmanagement.activity.VeterinarianDetailActivity;
import com.project.petmanagement.model.Veterinatian;

import java.util.List;

public class ListVererinarianAdapter extends RecyclerView.Adapter<ListVererinarianAdapter.VererinarianViewHolder> {
    private List<Veterinatian> veterinatianList;
    private Context context;

    public ListVererinarianAdapter(List<Veterinatian> veterinatianList, Context context) {
        this.veterinatianList = veterinatianList;
        this.context = context;
    }
    public void setVeterinatianList(List<Veterinatian> veterinatianList){
        this.veterinatianList = veterinatianList;
    }
    @NonNull
    @Override
    public VererinarianViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vetenarian, parent, false);
        return  new VererinarianViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VererinarianViewHolder holder, int position) {
        Veterinatian veterinatian = veterinatianList.get(position);
        holder.avatar.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.bs2));
        holder.name.setText(veterinatian.getFullName());
        String rate1 = veterinatian.getRate()+" ("+veterinatian.getPeopleRate()+" đánh giá)";
        holder.rate.setText(rate1);
        String experience1 = veterinatian.getExperience()+" năm";
        holder.experience.setText(experience1);
        holder.itemVeterinarian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VeterinarianDetailActivity.class);
                Log.d("ddddddddd", veterinatian.getEmail());
                intent.putExtra("veterinatian", veterinatian);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (veterinatianList!=null)
            return  veterinatianList.size();
        return 0;
    }

    public static class VererinarianViewHolder extends RecyclerView.ViewHolder {
        private final ImageView avatar;
        private final TextView name,rate, experience;
        private final LinearLayout itemVeterinarian;
        public VererinarianViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.image_doctor);
            name = itemView.findViewById(R.id.name_doctor);
            rate = itemView.findViewById(R.id.rate);
            experience = itemView.findViewById(R.id.experience);
            itemVeterinarian = itemView.findViewById(R.id.item_veterinarian);
        }
    }
}
