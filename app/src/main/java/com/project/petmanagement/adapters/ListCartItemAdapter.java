package com.project.petmanagement.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.project.petmanagement.R;
import com.project.petmanagement.models.CartItem;
import com.project.petmanagement.utils.FormatNumberUtils;

import java.util.List;

public class ListCartItemAdapter extends RecyclerView.Adapter<ListCartItemAdapter.CartItemViewHolder>{
    private List<CartItem> cartItemList;
    private Context context;
    public ListCartItemAdapter(Context context, List<CartItem> cartItemList){
        this.context = context;
        this.cartItemList = cartItemList;
    }
    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new CartItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int position) {
        CartItem cartItem = cartItemList.get(position);
        holder.imageProduct.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.thucan1));
        holder.nameProduct.setText(cartItem.getProduct().getName());
        holder.quantity.setText(String.valueOf(cartItem.getQuantity()));
        holder.price.setText(FormatNumberUtils.formatFloat(cartItem.getProduct().getPrice())+"đ");
        holder.btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(holder.quantity.getText().toString())-1;
                holder.quantity.setText(String.valueOf(quantity));
            }
        });
        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(holder.quantity.getText().toString())+1;
                holder.quantity.setText(String.valueOf(quantity));
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartItemList.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        if(cartItemList!=null){
            return cartItemList.size();
        }
        return 0;
    }

    public static class CartItemViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageProduct, btnAdd, btnSub;
        private final TextView nameProduct, price, quantity;
        private final ImageButton btnDelete;
        public CartItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imageProduct = itemView.findViewById(R.id.image_cart_item);
            nameProduct = itemView.findViewById(R.id.name_cart_item);
            price = itemView.findViewById(R.id.price_cart_item);
            btnAdd = itemView.findViewById(R.id.btn_add);
            btnSub = itemView.findViewById(R.id.btn_sub);
            quantity = itemView.findViewById(R.id.quantity);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }
}
