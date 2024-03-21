package com.project.petmanagement.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.petmanagement.R;
import com.project.petmanagement.activity.pet.PetDetailsActivity;
import com.project.petmanagement.model.Pet;

import java.util.List;

public class ManagePetRecyclerViewAdapter extends RecyclerView.Adapter<ManagePetRecyclerViewAdapter.PetViewHolder>{
    private final List<Pet> petList;
    private final Context context;

    public ManagePetRecyclerViewAdapter(Context context, List<Pet> petList) {
        this.petList = petList;
        this.context = context;
    }
    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.manage_pet_recyclerview_item,parent,false);
        return new PetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetViewHolder holder, int position) {
        Pet pet = petList.get(position);
        holder.imagePet.setImageResource(R.drawable.backgroud_login);
        holder.namePet.setText(pet.getFullname());
//        if(pet.getGender().equals("Male")){
//            holder.imageGenderPet.setImageResource(R.drawable.baseline_male_24);
//        }else{
//            holder.imageGenderPet.setImageResource(R.drawable.baseline_female_24);
//        }
//        holder.ageAndWeight.setText(pet.ge());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PetDetailsActivity.class);
                context.startActivity(intent);
            }
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
        private ImageView imagePet;
        private TextView namePet;
        private ImageView imageGenderPet;
        private TextView ageAndWeight;
        private RelativeLayout relativeLayout;
        public PetViewHolder(@NonNull View itemView) {
            super(itemView);
            imagePet = itemView.findViewById(R.id.image_pet);
            namePet = itemView.findViewById(R.id.name_pet);
            imageGenderPet = itemView.findViewById(R.id.image_gender);
            ageAndWeight = itemView.findViewById(R.id.age_and_weight);
            relativeLayout = itemView.findViewById(R.id.relative_layout);
        }
    }
}
