<<<<<<<< HEAD:app/src/main/java/com/project/petmanagement/activities/shop/ProductDetailActivity.java
package com.project.petmanagement.activities.shop;
========
package com.project.petmanagement.activity.shop;
>>>>>>>> origin/frontend/shop:app/src/main/java/com/project/petmanagement/activity/shop/ProductDetailActivity.java

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.project.petmanagement.R;

public class ProductDetailActivity extends AppCompatActivity {
    private ImageView btnBack, btnCart;
    private Button btnAddToCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        btnBack = findViewById(R.id.btn_back);
        btnAddToCart = findViewById(R.id.btn_add_to_cart);
        btnCart = findViewById(R.id.btn_cart);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailActivity.this, ShopActivity.class);
                intent.putExtra("key", "cart");
                startActivity(intent);

            }
        });
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProductDetailActivity.this, "Thêm sản phẩm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }
}