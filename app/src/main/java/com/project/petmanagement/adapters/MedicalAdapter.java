package com.project.petmanagement.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.petmanagement.R;
import com.project.petmanagement.models.entity.MedicalDocument;

import java.util.List;

public class MedicalAdapter extends RecyclerView.Adapter<MedicalAdapter.MedicalDocumentViewHolder> {
    private Context context;
    private List<MedicalDocument> medicalList;

    public MedicalAdapter(Context context, List<MedicalDocument> medicalList) {
        this.context = context;
        this.medicalList = medicalList;
    }
    @SuppressLint("NotifyDataSetChanged")
    public void setMedicalList(List<MedicalDocument> medicalList){
        this.medicalList = medicalList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MedicalDocumentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.medical_document_item,parent, false);
        return new MedicalDocumentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicalDocumentViewHolder holder, int position) {
        final MedicalDocument medicalDocument = medicalList.get(position);
        holder.nameMedical.setText(medicalDocument.getTitle());
    }

    @Override
    public int getItemCount() {
        if(medicalList!=null){
            return medicalList.size();
        }
        return 0;
    }

    public static class MedicalDocumentViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameMedical;
        public MedicalDocumentViewHolder(@NonNull View itemView) {
            super(itemView);
            nameMedical = itemView.findViewById(R.id.name_medical);
        }
    }
}
