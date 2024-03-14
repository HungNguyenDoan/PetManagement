package com.project.petmanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.project.petmanagement.R;
import com.project.petmanagement.model.Image;
import com.project.petmanagement.model.OrderDetail;

import java.util.List;

public class ListImageOrderDetailAdapter extends RecyclerView.Adapter<ListImageOrderDetailAdapter.ImageOrderDetailViewHolder> {
    private List<OrderDetail> orderDetailList;
    private Context context;

    public ListImageOrderDetailAdapter(Context context,List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
        this.context = context;
    }

    @NonNull
    @Override
    public ImageOrderDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_order_detail,parent,false);
        return new ImageOrderDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageOrderDetailViewHolder holder, int position) {
        OrderDetail orderDetail = orderDetailList.get(position);
        holder.imageProductDetail.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.thucan1));
    }

    @Override
    public int getItemCount() {
        if(orderDetailList!=null){
            return orderDetailList.size();
        }
        return 0;
    }

    public static class ImageOrderDetailViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageProductDetail;
        public ImageOrderDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            imageProductDetail = itemView.findViewById(R.id.image_product_detail);
        }
    }
}
