package com.project.petmanagement.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.project.petmanagement.R;
import com.project.petmanagement.activity.shop.ProductDetailActivity;
import com.project.petmanagement.models.entity.Product;
import com.project.petmanagement.payloads.responses.CartResponse;
import com.project.petmanagement.services.ApiService;
import com.project.petmanagement.utils.FormatNumberUtils;
import com.project.petmanagement.utils.ImageUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListProductAdapter extends RecyclerView.Adapter<ListProductAdapter.ProductViewHoler>{
    private List<Product> productList;
    private Context context;
    public ListProductAdapter(Context context, List<Product> productList){
        this.context = context;
        this.productList = productList;
    }
    public void setProductList(List<Product> productList){
        this.productList = productList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ProductViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHoler holder, int position) {
        final Product product = productList.get(position);
        holder.imageProduct.setImageBitmap(ImageUtils.decodeBase64(product.getImage()));
        holder.nameProduct.setText(product.getName());
        String priceFormat = FormatNumberUtils.formatFloat(product.getPrice())+"đ";
        holder.priceProduct.setText(priceFormat);
        holder.productItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("product", product);
                context.startActivity(intent);
            }
        });
        holder.btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiService.apiService.addToCart(product.getId(), 1).enqueue(new Callback<CartResponse>() {
                    @Override
                    public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(context, "Thêm sản phẩm vào giỏ hàng thành công.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<CartResponse> call, Throwable t) {

                    }
                });
            }
        });
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
        private final RelativeLayout productItem;
        private final ImageButton btnAddToCart;
        public ProductViewHoler(@NonNull View itemView) {
            super(itemView);
            imageProduct = itemView.findViewById(R.id.image_product);
            nameProduct = itemView.findViewById(R.id.name_product);
            priceProduct = itemView.findViewById(R.id.price);
            productItem = itemView.findViewById(R.id.product_item);
            btnAddToCart = itemView.findViewById(R.id.btn_add_to_cart);
        }
    }
}
