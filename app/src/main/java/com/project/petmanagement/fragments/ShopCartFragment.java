package com.project.petmanagement.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.petmanagement.R;
import com.project.petmanagement.activity.shop.PaymentActivity;
import com.project.petmanagement.activity.shop.ShopActivity;
import com.project.petmanagement.adapter.ListCartItemAdapter;
import com.project.petmanagement.model.CartItem;
import com.project.petmanagement.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ShopCartFragment extends Fragment {

    private RecyclerView carItemRecyclerView;
    private ListCartItemAdapter cartItemAdapter;
    private ImageView btnBack;
    private TextView btnPayment;
    public ShopCartFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shop_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        carItemRecyclerView = view.findViewById(R.id.cart_item_recyclerview);
        btnBack = view.findViewById(R.id.btn_back);
        btnPayment = view.findViewById(R.id.btn_payment);
        cartItemAdapter = new ListCartItemAdapter(getContext(), getList());
        carItemRecyclerView.setAdapter(cartItemAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        carItemRecyclerView.setLayoutManager(layoutManager);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopActivity shopActivity = (ShopActivity) getActivity();
                shopActivity.getHomePage();
            }
        });
        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), PaymentActivity.class);
                startActivity(intent);
            }
        });
    }
    private List<CartItem> getList(){
        List<CartItem> cartItems = new ArrayList<>();
        Product product1 = new Product(1L, "Thức ăn cho chó con cỡ nhỏ ROYAL CANIN Mini Puppy",210000d,3,"aaa","a");
        cartItems.add(new CartItem(2L,product1,3,645000d));
        Product product2 = new Product(2L, "Thức ăn cho chó con cỡ nhỏ ROYAL CANIN Mini Puppy 2",210000d,1,"aaa","a");
        cartItems.add(new CartItem(2L,product2,3,645000d));
        Product product3 = new Product(2L, "Thức ăn cho chó con cỡ nhỏ ROYAL CANIN Mini Puppy 2",210000d,1,"aaa","a");
        cartItems.add(new CartItem(2L,product3,3,645000d));
        return cartItems;
    }
}