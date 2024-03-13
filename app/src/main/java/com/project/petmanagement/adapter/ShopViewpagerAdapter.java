package com.project.petmanagement.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.project.petmanagement.fragment.ShopCartFragment;
import com.project.petmanagement.fragment.ShopHomeFragment;
import com.project.petmanagement.fragment.ShopMoreFragment;

public class ShopViewpagerAdapter extends FragmentStateAdapter {
    public ShopViewpagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new ShopHomeFragment();
            case 1:
                return new ShopCartFragment();
            default:
                return new ShopMoreFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
