package com.project.petmanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.petmanagement.R;
import com.project.petmanagement.model.Diseases;

import java.util.List;

public class LibraryRecyclerViewAdapter extends RecyclerView.Adapter<LibraryRecyclerViewAdapter.LibraryViewholder>{
    private List<Diseases> listDiseases;
    Context context;
    public LibraryRecyclerViewAdapter(Context context, List<Diseases> listDiseases){
        this.context = context;
        this.listDiseases = listDiseases;
    }
    @NonNull
    @Override
    public LibraryViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_library_rycv, parent, false);
        return new LibraryViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LibraryViewholder holder, int position) {
        Diseases diseases = listDiseases.get(position);
        holder.nameDiseases.setText(diseases.getName());
    }

    @Override
    public int getItemCount() {
        if(listDiseases!=null){
            return listDiseases.size();
        }
        return 0;
    }

    public static class LibraryViewholder extends RecyclerView.ViewHolder{
        private TextView nameDiseases;
        public LibraryViewholder(@NonNull View itemView) {
            super(itemView);
            nameDiseases = itemView.findViewById(R.id.name_diseases);
        }
    }
}