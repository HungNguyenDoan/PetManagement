package com.project.petmanagement.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.project.petmanagement.R;
import com.project.petmanagement.activity.ManagePetActivity;
import com.project.petmanagement.model.ProfileHorizontalItem;

import java.util.List;

public class ProfileHorizontalRecyclerViewAdapter extends RecyclerView.Adapter<ProfileHorizontalRecyclerViewAdapter.ProfileHorizontalViewHolder> {

    private final Context context;
    private final List<ProfileHorizontalItem> itemList;

    public ProfileHorizontalRecyclerViewAdapter(Context context, List<ProfileHorizontalItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    public List<ProfileHorizontalItem> getItemList() {
        return itemList;
    }

    public ProfileHorizontalItem getItem(int position) {
        return itemList.get(position);
    }

    @NonNull
    @Override
    public ProfileHorizontalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_horizontal_recycler_view_item, parent, false);
        return new ProfileHorizontalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileHorizontalViewHolder holder, int position) {
        ProfileHorizontalItem item = itemList.get(position);
        holder.image.setImageResource(item.getImage());
        holder.title.setText(item.getTitle());
        shapeRadius(holder, item.getColorBackgroud());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, item.getCls());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
    @SuppressLint("ResourceAsColor")
    private void shapeRadius(ProfileHorizontalViewHolder holder, int color){
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setCornerRadii(new float[]{20, 20, 20, 20, 0, 0, 0, 0});
        gradientDrawable.setColor(ContextCompat.getColor(context, color));
        holder.linearLayout.setBackground(gradientDrawable);
    }

    public static class ProfileHorizontalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView image;
        TextView title;
        LinearLayout linearLayout;
        RelativeLayout relativeLayout;
        public ProfileHorizontalViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.horizontal_item_image);
            title = itemView.findViewById(R.id.horizontal_item_title);
            linearLayout = itemView.findViewById(R.id.linear_layout);
            relativeLayout = itemView.findViewById(R.id.relative_layout);
        }

        @Override
        public void onClick(View v) {

        }
    }

}
