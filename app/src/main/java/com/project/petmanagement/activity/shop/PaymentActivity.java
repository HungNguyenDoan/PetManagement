package com.project.petmanagement.activity.shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.petmanagement.R;
import com.project.petmanagement.adapters.PaymentItemAdapter;
import com.project.petmanagement.models.entity.Cart;
import com.project.petmanagement.models.entity.CartItem;
import com.project.petmanagement.utils.FormatNumberUtils;

import java.util.ArrayList;
import java.util.List;

public class PaymentActivity extends AppCompatActivity {
    private ImageView btnBack, btnCart;
    private RecyclerView listItemRecyclerView;
    private Button btnConfirmPayment;
    private TextView totalPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        btnBack = findViewById(R.id.btn_back);
        btnCart = findViewById(R.id.btn_cart);
        listItemRecyclerView = findViewById(R.id.list_item);
        btnConfirmPayment = findViewById(R.id.confirm_payment);
        totalPrice = findViewById(R.id.total_price);
        Cart cart = (Cart) getIntent().getSerializableExtra("cart");
        if(cart!=null){
            List<CartItem> cartItems = cart.getCartItems();
            PaymentItemAdapter paymentItemAdapter = new PaymentItemAdapter(this,cartItems);
            listItemRecyclerView.setAdapter(paymentItemAdapter);
            listItemRecyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
            String totalPrices = FormatNumberUtils.formatFloat(cart.getTotalPrice())+" VND";
            totalPrice.setText(totalPrices);
        }
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
//                Intent intent = new Intent(PaymentActivity.this, OrderActivity.class);
//                startActivity(intent);
            }
        });


    }

}