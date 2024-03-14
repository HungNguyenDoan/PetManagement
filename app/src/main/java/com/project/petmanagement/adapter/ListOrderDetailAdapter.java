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
import com.project.petmanagement.model.OrderDetail;
import com.project.petmanagement.utils.FormatNumberUtils;

import java.util.List;

public class ListOrderDetailAdapter extends RecyclerView.Adapter<ListOrderDetailAdapter.ProductDetailViewHolder> {
    private Context context;
    private List<OrderDetail> orderDetails;

    public ListOrderDetailAdapter(Context context, List<OrderDetail> orderDetails) {
        this.context = context;
        this.orderDetails = orderDetails;
    }

    @NonNull
    @Override
    public ProductDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductDetailViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_detail,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductDetailViewHolder holder, int position) {
        OrderDetail orderDetail = orderDetails.get(position);
        holder.nameProduct.setText(orderDetail.getProduct().getName());
        holder.priceProduct.setText(FormatNumberUtils.formatFloat(orderDetail.getPrice()));
        holder.quantity.setText("x"+String.valueOf(orderDetail.getQuantity()));
    }

    @Override
    public int getItemCount() {
        if(orderDetails!=null){
            return orderDetails.size();
        }
        return 0;
    }

    public static class ProductDetailViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageProduct;
        private final TextView nameProduct, priceProduct, quantity;

        public ProductDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            imageProduct = itemView.findViewById(R.id.image_product);
            nameProduct = itemView.findViewById(R.id.name_product);
            priceProduct = itemView.findViewById(R.id.price_product);
            quantity = itemView.findViewById(R.id.quantity_product);
        }
    }
}
