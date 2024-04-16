//package com.project.petmanagement.adapters;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.project.petmanagement.R;
//
//import java.util.List;
//
//public class NutritionsRecyclerViewAdapter extends  RecyclerView.Adapter<NutritionsRecyclerViewAdapter.NutritionsViewHolder>{
//    private List<NutritionInfo> nutritionsInforList;
//    private Context context;
//    public NutritionsRecyclerViewAdapter(Context context, List<NutritionInfo> nutritionsInforList){
//        this.context = context;
//        this.nutritionsInforList = nutritionsInforList;
//    }
//    @NonNull
//    @Override
//    public NutritionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_nutritions, parent, false);
//        return new NutritionsViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull NutritionsViewHolder holder, int position) {
//        NutritionInfo nutritionsInfor = nutritionsInforList.get(position);
//        holder.nameNutritions.setText(nutritionsInfor.getNutritionName());
//        holder.description.setText(nutritionsInfor.getDescription());
//    }
//
//    @Override
//    public int getItemCount() {
//        if(nutritionsInforList!=null){
//            return nutritionsInforList.size();
//        }
//        return 0;
//    }
//
//    public static class NutritionsViewHolder extends RecyclerView.ViewHolder {
//        private TextView nameNutritions;
//        private TextView description;
//        public NutritionsViewHolder(@NonNull View itemView) {
//            super(itemView);
//            nameNutritions = itemView.findViewById(R.id.name_nutritions);
//            description = itemView.findViewById(R.id.desciption);
//        }
//    }
//}
