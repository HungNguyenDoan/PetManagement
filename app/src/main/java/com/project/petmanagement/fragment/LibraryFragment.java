package com.project.petmanagement.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.petmanagement.R;
import com.project.petmanagement.adapter.LibraryRecyclerViewAdapter;
import com.project.petmanagement.model.Diseases;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class LibraryFragment extends Fragment {

    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_library, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerview);
        LibraryRecyclerViewAdapter recyclerViewAdapter = new LibraryRecyclerViewAdapter(getContext(),getDiseases());
        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL);
        recyclerView.setAdapter(recyclerViewAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(decoration);
    }
    private List<Diseases> getDiseases(){
        List<Diseases> diseases = new ArrayList<>();
        diseases.add(new Diseases("Viên ruột truyền nhiễm"));
        diseases.add(new Diseases("Viên ruột truyền nhiễm1"));
        diseases.add(new Diseases("Viên ruột truyền nhiễm2"));
        diseases.add(new Diseases("Viên ruột truyền nhiễm3"));
        diseases.add(new Diseases("Viên ruột truyền nhiễm4"));
        diseases.add(new Diseases("Viên ruột truyền nhiễm5"));
        diseases.add(new Diseases("Viên ruột truyền nhiễm6"));
        return  diseases;
    }
}