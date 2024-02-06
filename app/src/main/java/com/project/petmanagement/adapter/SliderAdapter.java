package com.project.petmanagement.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.petmanagement.R;
import com.project.petmanagement.model.Image;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {
    private List<Image> images;

    public SliderAdapter(List<Image> images) {
        this.images = images;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        Image image = images.get(position);
        if(image==null){
            return;
        }
        holder.image.setImageResource(image.getResource());
    }

    @Override
    public int getItemCount() {
        if(images!=null){
            return images.size();
        }
        return 0;
    }

    public class SliderViewHolder extends RecyclerView.ViewHolder{
        private ImageView image;
        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_slide);
        }
    }
}
