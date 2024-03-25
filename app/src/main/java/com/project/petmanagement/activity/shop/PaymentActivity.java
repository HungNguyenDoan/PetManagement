<<<<<<<< HEAD:app/src/main/java/com/project/petmanagement/activities/shop/PaymentActivity.java
package com.project.petmanagement.activities.shop;
========
package com.project.petmanagement.activity.shop;
>>>>>>>> origin/frontend/shop:app/src/main/java/com/project/petmanagement/activity/shop/PaymentActivity.java

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.project.petmanagement.R;
import com.project.petmanagement.adapters.PaymentItemAdapter;
import com.project.petmanagement.models.CartItem;
import com.project.petmanagement.models.Product;

import java.util.ArrayList;
import java.util.List;

public class PaymentActivity extends AppCompatActivity {
    private ImageView btnBack, btnCart;
    private RecyclerView listItemRecyclerView;
    private Button btnConfirmPayment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        btnBack = findViewById(R.id.btn_back);
        btnCart = findViewById(R.id.btn_cart);
        listItemRecyclerView = findViewById(R.id.list_item);
        btnConfirmPayment = findViewById(R.id.confirm_payment);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentActivity.this, ShopActivity.class);
                intent.putExtra("key", "cart");
                startActivity(intent);

            }
        });
        btnConfirmPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PaymentActivity.this, "Xác nhận thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PaymentActivity.this, OrderActivity.class);
                startActivity(intent);
            }
        });
        PaymentItemAdapter paymentItemAdapter = new PaymentItemAdapter(this,getList());
        listItemRecyclerView.setAdapter(paymentItemAdapter);
        listItemRecyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

    }
    private List<CartItem> getList(){
        List<CartItem> cartItems = new ArrayList<>();
        Product product1 = new Product(1L, "Thức ăn cho chó con cỡ nhỏ ROYAL CANIN Mini Puppy",215000d,3,"aaa","a");
        cartItems.add(new CartItem(2L,product1,3,645000d));
        return cartItems;
    }
}