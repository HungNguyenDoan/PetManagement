package com.project.petmanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.project.petmanagement.R;
import com.project.petmanagement.model.Image;
import com.project.petmanagement.model.Product;

import java.text.DecimalFormat;
import java.util.List;

public class ListProductAdapter extends RecyclerView.Adapter<ListProductAdapter.ProductViewHoler>{
    private List<Product> productList;
    private Context context;
    public ListProductAdapter(Context context, List<Product> productList){
        this.context = context;
        this.productList = productList;
    }
    @NonNull
    @Override
    public ProductViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHoler holder, int position) {
        Product product = productList.get(position);
        holder.imageProduct.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.thucan1));
        holder.nameProduct.setText(product.getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        String priceFormat = decimalFormat.format(product.getPrice());
        holder.priceProduct.setText(priceFormat.replace(",", "."));
    }

    @Override
    public int getItemCount() {
        if(productList!=null){
            return productList.size();
        }
        return 0;
    }

    public static class ProductViewHoler extends RecyclerView.ViewHolder{

        private final ImageView imageProduct;
        private final TextView nameProduct, priceProduct;
        public ProductViewHoler(@NonNull View itemView) {
            super(itemView);
            imageProduct = itemView.findViewById(R.id.image_product);
            nameProduct = itemView.findViewById(R.id.name_product);
            priceProduct = itemView.findViewById(R.id.price);
        }
    }
}
