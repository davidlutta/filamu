package com.davidlutta.filamu.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.davidlutta.filamu.fragments.SavedMoviesFragment;
import com.davidlutta.filamu.fragments.SavedTvSeriesFragment;

public class SavedItemsPagerAdapter extends FragmentStatePagerAdapter {

    public SavedItemsPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return SavedMoviesFragment.newInstance();
            case 1:
                return SavedTvSeriesFragment.newInstance();
        }
        return SavedTvSeriesFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Movies";
            case 1:
                return "Tv Series";
            default:
                return null;
        }
    }
}
