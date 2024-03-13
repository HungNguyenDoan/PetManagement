package com.project.petmanagement.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.project.petmanagement.fragment.CommunityFragment;
import com.project.petmanagement.fragment.HomeFragment;
import com.project.petmanagement.fragment.LibraryFragment;
import com.project.petmanagement.fragment.ProfileFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                return new LibraryFragment();
            case 2:
                return new CommunityFragment();
            default:
                return new ProfileFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
