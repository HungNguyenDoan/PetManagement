package com.project.petmanagement.activity.shop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.petmanagement.R;
import com.project.petmanagement.adapters.ListOrderAdapter;
import com.project.petmanagement.models.entity.Order;
import com.project.petmanagement.payloads.responses.ListOrderResponse;
import com.project.petmanagement.services.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity {
    private RecyclerView orderRecyclerView;
    private ListOrderAdapter orderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        orderRecyclerView = findViewById(R.id.order_recyclerview);
        ImageView btnBack = findViewById(R.id.btn_back);
        ImageView btnCart = findViewById(R.id.btn_cart);
        getList();
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
                finish();
            }
        });

    }

    private void getList() {
        ApiService.apiService.getOrderUser().enqueue(new Callback<ListOrderResponse>() {
            @Override
            public void onResponse(Call<ListOrderResponse> call, Response<ListOrderResponse> response) {
                if (response.isSuccessful()) {
                    ListOrderResponse orderResponse = response.body();
                    if (orderResponse != null) {
                        List<Order> orderList = orderResponse.getData();
                        if (orderList != null) {
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(OrderActivity.this, LinearLayoutManager.VERTICAL, false);
                            orderRecyclerView.setLayoutManager(layoutManager);
                            orderAdapter = new ListOrderAdapter(OrderActivity.this, orderList);
                            orderRecyclerView.setAdapter(orderAdapter);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ListOrderResponse> call, Throwable t) {
                Log.d("ddddd", t.getMessage());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getList();
    }
}