package com.davidlutta.filamu.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.davidlutta.filamu.R;
import com.davidlutta.filamu.adapters.SavedItemsPagerAdapter;
import com.davidlutta.filamu.viewmodels.SavedItemsViewModel;
import com.google.android.material.tabs.TabLayout;

import es.dmoral.toasty.Toasty;

public class SavedItemsFragment extends Fragment {

    private SavedItemsViewModel mViewModel;
    private ViewPager mViewpager;
    private SavedItemsPagerAdapter pagerAdapter;
    private TabLayout mTablayout;
    private Toolbar toolbar;

    public static SavedItemsFragment newInstance() {
        return new SavedItemsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.saved_items_fragment, container, false);
        mViewpager = view.findViewById(R.id.savedFragmentViewPager);
        mTablayout = view.findViewById(R.id.savedFragmentTabLayout);
        toolbar = view.findViewById(R.id.savedFragmentToolbar);

        pagerAdapter = new SavedItemsPagerAdapter(getParentFragmentManager(), 1);
        mViewpager.setAdapter(pagerAdapter);
        mTablayout.setupWithViewPager(mViewpager);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.saved_items_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toasty.success(getContext(), "I've been clicked", Toasty.LENGTH_SHORT, true).show();
        return true;
    }
}
