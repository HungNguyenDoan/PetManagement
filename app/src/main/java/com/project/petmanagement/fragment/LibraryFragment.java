package com.project.petmanagement.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.petmanagement.R;
import com.project.petmanagement.adapter.LibraryRecyclerViewAdapter;
import com.project.petmanagement.model.Diseases;
import com.project.petmanagement.model.Species;
import com.project.petmanagement.response.SpeciesResponse;
import com.project.petmanagement.services.ApiService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LibraryFragment extends Fragment {

    private RecyclerView recyclerView;
    private Map<String, Species> speciesMap;
    private ArrayAdapter<String> speciesAdapter;
    private AutoCompleteTextView speciesDropdown;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_library, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerview);
        speciesDropdown = view.findViewById(R.id.species);
        LibraryRecyclerViewAdapter recyclerViewAdapter = new LibraryRecyclerViewAdapter(getContext(),getDiseases());
        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL);
        recyclerView.setAdapter(recyclerViewAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(decoration);
        speciesMap = new LinkedHashMap<>();
        ApiService.apiService.getSpecies().enqueue(new Callback<SpeciesResponse.SpeciesResponse1>() {
            @Override
            public void onResponse(Call<SpeciesResponse.SpeciesResponse1> call, Response<SpeciesResponse.SpeciesResponse1> response) {
                if (response.isSuccessful()){
                    List<Species> speciesList = response.body().getData();
                    for(Species species: speciesList){
                        speciesMap.put(species.getName(), species);
                    }
                    speciesAdapter = new ArrayAdapter<>(requireContext(),R.layout.list_item_dropdown,new ArrayList<>(speciesMap.keySet()));
                    speciesDropdown.setAdapter(speciesAdapter);
                }
            }

            @Override
            public void onFailure(Call<SpeciesResponse.SpeciesResponse1> call, Throwable t) {

            }
        });
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