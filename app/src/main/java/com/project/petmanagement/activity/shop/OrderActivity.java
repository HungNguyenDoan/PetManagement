<<<<<<<< HEAD:app/src/main/java/com/project/petmanagement/activities/shop/OrderActivity.java
package com.project.petmanagement.activities.shop;
========
package com.project.petmanagement.activity.shop;
>>>>>>>> origin/frontend/shop:app/src/main/java/com/project/petmanagement/activity/shop/OrderActivity.java

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.project.petmanagement.R;
import com.project.petmanagement.adapters.ListOrderAdapter;
import com.project.petmanagement.models.Order;
import com.project.petmanagement.models.OrderDetail;
import com.project.petmanagement.models.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {
    private RecyclerView orderRecyclerView;
    private ListOrderAdapter orderAdapter;
    private ImageView btnBack, btnCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        orderRecyclerView = findViewById(R.id.order_recyclerview);
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
                Intent intent = new Intent(OrderActivity.this, ShopActivity.class);
                intent.putExtra("key", "cart");
                startActivity(intent);

            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        orderRecyclerView.setLayoutManager(layoutManager);
        orderAdapter = new ListOrderAdapter(this, getList());
        orderRecyclerView.setAdapter(orderAdapter);
    }
    private List<Order> getList(){
        List<Order> orders = new ArrayList<>();
        List<OrderDetail> orderDetails = new ArrayList<>();
        Product product1 = new Product(1L, "Thức ăn cho chó con cỡ nhỏ ROYAL CANIN Mini Puppy",210000d,3,"aaa","a");
        orderDetails.add(new OrderDetail(1L,1,product1,215000d,215000d));
        orders.add(new Order(1L,215000d,"Đang xử lý",orderDetails));
        List<OrderDetail> orderDetails2 = new ArrayList<>();
        Product product2 = new Product(1L, "Thức ăn cho chó con cỡ nhỏ ROYAL CANIN Mini Puppy",210000d,3,"aaa","a");
        orderDetails2.add(new OrderDetail(1L,1,product1,215000d,215000d));
        orderDetails2.add(new OrderDetail(1L,1,product2,215000d,215000d));
        orderDetails2.add(new OrderDetail(1L,1,product1,215000d,215000d));
        orders.add(new Order(1L,215000d,"Đang xử lý",orderDetails2));
        return orders;
    }
}