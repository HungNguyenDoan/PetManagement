package com.project.petmanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.petmanagement.R;
import com.project.petmanagement.model.Pet;

import java.util.List;

public class HomePetRecycleViewAdapter extends RecyclerView.Adapter<HomePetRecycleViewAdapter.HomePetViewHolder> {
    private List<Pet> petList;
    public void setDate(List<Pet> petList){
        this.petList = petList;
    }
    @NonNull
    @Override
    public HomePetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pet,parent, false);
        return new HomePetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomePetViewHolder holder, int position) {
        Pet pet = petList.get(position);
        holder.breed.setText(pet.getBreed());;
        holder.namePet.setText(pet.getFullName());
    }

    @Override
    public int getItemCount() {
        if (petList!=null)
            return petList.size();
        return  0;
    }

    public class HomePetViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView namePet;
        private TextView breed;
        public HomePetViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_pet);
            namePet = itemView.findViewById(R.id.namePet);
            breed = itemView.findViewById(R.id.breed);
        }
    }
}
