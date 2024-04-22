package com.project.petmanagement.activity.shop;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.petmanagement.MyApplication;
import com.project.petmanagement.R;
import com.project.petmanagement.adapters.PaymentItemAdapter;
import com.project.petmanagement.models.entity.Cart;
import com.project.petmanagement.models.entity.CartItem;
import com.project.petmanagement.models.entity.User;
import com.project.petmanagement.models.enums.PaymentMethodEnum;
import com.project.petmanagement.payloads.requests.OrderRequest;
import com.project.petmanagement.payloads.responses.OrderResponse;
import com.project.petmanagement.services.ApiService;
import com.project.petmanagement.services.StorageService;
import com.project.petmanagement.utils.FormatNumberUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity {
    private TextView fullName;
    private TextView phoneNumber;
    private TextView address;
    private CharSequence items[] = new CharSequence[] {"Thanh toán khi nhận hàng", "Thẻ tín dụng"};

    private StorageService storageService = MyApplication.getStorageService();
    ActivityResultLauncher<Intent> infoLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult o) {
            if (o.getResultCode() == Activity.RESULT_OK){
                Intent intent = o.getData();
                if(intent!=null){
                    phoneNumber.setText(intent.getStringExtra("phoneNumber"));
                    address.setText(intent.getStringExtra("address"));
                }
            }
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ImageView btnBack = findViewById(R.id.btn_back);
        ImageView btnCart = findViewById(R.id.btn_cart);
        fullName = findViewById(R.id.full_name);
        phoneNumber = findViewById(R.id.phone_number);
        address = findViewById(R.id.address);
        RecyclerView listItemRecyclerView = findViewById(R.id.list_item);
        Button btnConfirmPayment = findViewById(R.id.confirm_payment);
        TextView totalPrice = findViewById(R.id.total_price);
        TextView textChangePaymentMethod = findViewById(R.id.change_payment_method);
        TextView paymentMethod = findViewById(R.id.payment_method);
        TextView textChangeInfo = findViewById(R.id.change_info);
        User user = storageService.getUser("user");
        fullName.setText(user.getFullName());
        phoneNumber.setText(user.getPhoneNumber());
        address.setText(user.getAddress());
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
        textChangeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentActivity.this, ChangeInfoActivity.class);
                intent.putExtra("fullName", fullName.getText().toString());
                intent.putExtra("phoneNumber", phoneNumber.getText().toString());
                intent.putExtra("address", address.getText().toString());
                infoLauncher.launch(intent);
            }
        });
        textChangePaymentMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int[] checkedItem = {-1};
                AlertDialog.Builder builder = new AlertDialog.Builder(PaymentActivity.this);
                builder.setSingleChoiceItems(items, checkedItem[0], new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        checkedItem[0] = which;
                        paymentMethod.setText(items[which]);
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Thoát", null);
                builder.setTitle("Chọn phương thức thanh toán");
                builder.show();
            }
        });
        btnConfirmPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaymentMethodEnum paymentMethodEnum;
                if(paymentMethod.getText().toString().equals(items[0].toString())){
                    paymentMethodEnum = PaymentMethodEnum.CASH_ON_DELIVERY;
                }else{
                    paymentMethodEnum = PaymentMethodEnum.CREDIT_CARD;

                }
                OrderRequest orderRequest = new OrderRequest(address.getText().toString(), phoneNumber.getText().toString(),paymentMethodEnum);
                ApiService.apiService.createOrder(orderRequest).enqueue(new Callback<OrderResponse>() {
                    @Override
                    public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(PaymentActivity.this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(PaymentActivity.this, OrderActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<OrderResponse> call, Throwable t) {

                    }
                });
            }
        });


    }

}