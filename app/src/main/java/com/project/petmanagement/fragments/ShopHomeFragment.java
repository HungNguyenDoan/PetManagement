package com.project.petmanagement.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.petmanagement.R;
import com.project.petmanagement.adapters.CategoryAdapter;
import com.project.petmanagement.adapters.ListProductAdapter;
import com.project.petmanagement.models.Category;
import com.project.petmanagement.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ShopHomeFragment extends Fragment {

    private RecyclerView recyclerViewProduct;
    private RecyclerView recyclerViewCategory;
    private TextView all;
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
        ListProductAdapter listProductAdapter = new ListProductAdapter(getContext(), getAll());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerViewProduct.setLayoutManager(layoutManager);
        recyclerViewProduct.setAdapter(listProductAdapter);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategory.setLayoutManager(layoutManager1);
        CategoryAdapter categoryAdapter = new CategoryAdapter(requireContext(), getList());
        recyclerViewCategory.setAdapter(categoryAdapter);
    }
    private List<Category> getList(){
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Đồ ăn"));
        categories.add(new Category("Đồ chơi"));
        return categories;
    }
    private List<Product> getAll(){
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Thức ăn cho chó con cỡ nhỏ ROYAL CANIN Mini Puppy",210000d,3,"aaa","a"));
        products.add(new Product(1L, "Thức ăn cho chó con cỡ nhỏ ROYAL CANIN Mini Puppy",210000d,3,"aaa","a"));
        products.add(new Product(1L, "Thức ăn cho chó con cỡ nhỏ ROYAL CANIN Mini Puppy",210000d,3,"aaa","a"));
        products.add(new Product(1L, "Thức ăn cho chó con cỡ nhỏ ROYAL CANIN Mini Puppy",210000d,3,"aaa","a"));
        products.add(new Product(1L, "Thức ăn cho chó con cỡ nhỏ ROYAL CANIN Mini Puppy",210000d,3,"aaa","a"));
        products.add(new Product(1L, "Thức ăn cho chó con cỡ nhỏ ROYAL CANIN Mini Puppy",210000d,3,"aaa","a"));
        products.add(new Product(1L, "Thức ăn cho chó con cỡ nhỏ ROYAL CANIN Mini Puppy",210000d,3,"aaa","a"));
        return products;
    }

}