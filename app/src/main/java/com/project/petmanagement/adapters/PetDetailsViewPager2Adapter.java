package com.project.petmanagement.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.project.petmanagement.fragments.InforPetFragment;
import com.project.petmanagement.fragments.PetActivityFragment;
import com.project.petmanagement.models.Pet;

public class PetDetailsViewPager2Adapter extends FragmentStateAdapter {

    private long idPet;

    public PetDetailsViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }
    public void setData(long idPet){
        this.idPet = idPet;
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new InforPetFragment(idPet);
            default:
                return new PetActivityFragment(idPet);
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
