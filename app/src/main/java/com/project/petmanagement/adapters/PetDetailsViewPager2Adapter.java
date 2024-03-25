package com.project.petmanagement.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.project.petmanagement.fragments.InforPetFragment;
import com.project.petmanagement.fragments.PetActivityFragment;

public class PetDetailsViewPager2Adapter extends FragmentStateAdapter {


    public PetDetailsViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new InforPetFragment();
            default:
                return new PetActivityFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
