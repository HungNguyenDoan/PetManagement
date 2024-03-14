package com.project.petmanagement.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.project.petmanagement.R;
import com.project.petmanagement.adapter.ListOrderDetailAdapter;
import com.project.petmanagement.model.Order;
import com.project.petmanagement.model.OrderDetail;
import com.project.petmanagement.model.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailActivity extends AppCompatActivity {
    private RecyclerView productRecyclerView;
    private ListOrderDetailAdapter orderDetailAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        productRecyclerView = findViewById(R.id.product_recyclerview);
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