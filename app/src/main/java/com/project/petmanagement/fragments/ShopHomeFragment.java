package com.project.petmanagement.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.project.petmanagement.R;
import com.project.petmanagement.adapters.CategoryAdapter;
import com.project.petmanagement.adapters.ListProductAdapter;
import com.project.petmanagement.models.entity.Product;
import com.project.petmanagement.payloads.responses.ListCategoryResponse;
import com.project.petmanagement.payloads.responses.ListProductResponse;
import com.project.petmanagement.services.ApiService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopHomeFragment extends Fragment {

    private RecyclerView recyclerViewProduct;
    private RecyclerView recyclerViewCategory;
    private CategoryAdapter categoryAdapter;
    private TextView all;
    private ListProductAdapter listProductAdapter;
    private List<Product> products;
    private TextInputEditText searchInput;
    public ShopHomeFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shop_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewProduct = view.findViewById(R.id.list_product);
        all = view.findViewById(R.id.all);
        recyclerViewCategory = view.findViewById(R.id.category);
        searchInput = view.findViewById(R.id.search);
        getCategories();
        getAllProduct();
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                all.setTextColor(ContextCompat.getColor(requireContext(), R.color.green));
                getAllProduct();
                categoryAdapter.resetSelection();
                recyclerViewCategory.setAdapter(categoryAdapter);
            }
        });
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()==0){
                    listProductAdapter.setProductList(products);
                    Objects.requireNonNull(recyclerViewProduct.getAdapter()).notifyDataSetChanged();
                    all.setTextColor(ContextCompat.getColor(requireContext(), R.color.green));
                }else{
                    List<Product> productList = new ArrayList<>();
                    for (Product product: products){
                        if(product.getName().toLowerCase().contains(s.toString().toLowerCase())){
                            productList.add(product);
                        }
                    }
                    listProductAdapter.setProductList(productList);
                    Objects.requireNonNull(recyclerViewProduct.getAdapter()).notifyDataSetChanged();
                    all.setTextColor(ContextCompat.getColor(requireContext(), R.color.text_default));
                }
                categoryAdapter.resetSelection();
                recyclerViewCategory.setAdapter(categoryAdapter);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
    private void getCategories(){
        ApiService.apiService.getAllCategory().enqueue(new Callback<ListCategoryResponse>() {
            @Override
            public void onResponse(Call<ListCategoryResponse> call, Response<ListCategoryResponse> response) {
                if(response.isSuccessful()){
                    ListCategoryResponse listCategoryResponse = response.body();
                    if(listCategoryResponse!=null){
                        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                        recyclerViewCategory.setLayoutManager(layoutManager1);
                        categoryAdapter = new CategoryAdapter(requireContext(), listCategoryResponse.getData(), recyclerViewProduct, all);
                        recyclerViewCategory.setAdapter(categoryAdapter);
                    }
                }
            }
            @Override
            public void onFailure(Call<ListCategoryResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Kiểm tra lại kết nối của bạn", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getAllProduct(){
        ApiService.apiService.getAllProduct().enqueue(new Callback<ListProductResponse>() {
            @Override
            public void onResponse(Call<ListProductResponse> call, Response<ListProductResponse> response) {
                if(response.isSuccessful()){
                    ListProductResponse listProductResponse = response.body();
                    if (listProductResponse != null) {
                        products = listProductResponse.getData();
                        listProductAdapter = new ListProductAdapter(getContext(), products);
                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
                        recyclerViewProduct.setLayoutManager(layoutManager);
                        recyclerViewProduct.setAdapter(listProductAdapter);
                    }
                }
            }
            @Override
            public void onFailure(Call<ListProductResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Kiểm tra lại kết nối của bạn", Toast.LENGTH_SHORT).show();
            }
        });
    }

}