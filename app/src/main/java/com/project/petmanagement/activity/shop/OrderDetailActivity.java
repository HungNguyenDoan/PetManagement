package com.project.petmanagement.activity.shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.project.petmanagement.R;
import com.project.petmanagement.adapters.ListOrderDetailAdapter;
import com.project.petmanagement.models.OrderDetail;
import com.project.petmanagement.models.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailActivity extends AppCompatActivity {
    private RecyclerView productRecyclerView;
    private ListOrderDetailAdapter orderDetailAdapter;
    private ImageView btnBack, btnCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        productRecyclerView = findViewById(R.id.product_recyclerview);
        btnBack = findViewById(R.id.btn_back);
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
                Intent intent = new Intent(OrderDetailActivity.this, ShopActivity.class);
                intent.putExtra("key", "cart");
                startActivity(intent);

            }
        });
        orderDetailAdapter = new ListOrderDetailAdapter(this, getList());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        productRecyclerView.setLayoutManager(layoutManager);
        productRecyclerView.setAdapter(orderDetailAdapter);
    }
    private List<OrderDetail> getList(){
        Product product1 = new Product(1L, "Thức ăn cho chó con cỡ nhỏ ROYAL CANIN Mini Puppy",210000d,3,"aaa","a");
        List<OrderDetail> orderDetails2 = new ArrayList<>();
        Product product2 = new Product(1L, "Thức ăn cho chó con cỡ nhỏ ROYAL CANIN Mini Puppy",210000d,3,"aaa","a");
        orderDetails2.add(new OrderDetail(1L,1,product1,215000d,215000d));
        orderDetails2.add(new OrderDetail(1L,1,product2,215000d,215000d));
        orderDetails2.add(new OrderDetail(1L,1,product1,215000d,215000d));
        return orderDetails2;
    }
}