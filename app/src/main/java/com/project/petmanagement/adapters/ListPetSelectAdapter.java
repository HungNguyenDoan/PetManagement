package com.project.petmanagement.adapters;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.petmanagement.R;
import com.project.petmanagement.activity.schedule.inject.SetVaccineScheduleActivity;
import com.project.petmanagement.activity.schedule.walk.SetWalkScheduleActivity;
import com.project.petmanagement.models.entity.Pet;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListPetSelectAdapter extends RecyclerView.Adapter<ListPetSelectAdapter.PetViewHolder> {
    private List<Pet> petList;
    private Context context;

    public ListPetSelectAdapter(List<Pet> petList, Context context) {
        this.petList = petList;
        this.context = context;
    }

    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_set_pet_schedule,parent,false);
        return new PetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetViewHolder holder, int position) {
        final Pet pet = petList.get(position);
        Glide.with(context)
                .load(pet.getImage())
                .error(R.drawable.default_pet_no_image)
                .into(holder.imagePet);
        holder.namePet.setText(pet.getFullName());
        holder.cardView.setOnClickListener(v -> {
            Intent intent = new Intent(context, SetVaccineScheduleActivity.class);
            intent.putExtra("pet", pet);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if(petList !=null){
            return petList.size();
        }
        return 0;
    }

    public static class PetViewHolder extends RecyclerView.ViewHolder{
        private final CircleImageView imagePet;
        private final TextView namePet;
        private final CardView cardView;

        public PetViewHolder(@NonNull View itemView) {
            super(itemView);
            imagePet = itemView.findViewById(R.id.image_pet);
            namePet = itemView.findViewById(R.id.name_pet);
            cardView = itemView.findViewById(R.id.pet_card_view);
        }
    }
}
