package com.davidlutta.filamu.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.davidlutta.filamu.R;
import com.davidlutta.filamu.viewmodels.SavedTvSeriesViewModel;

public class SavedTvSeriesFragment extends Fragment {

    private SavedTvSeriesViewModel mViewModel;

    public static SavedTvSeriesFragment newInstance() {
        return new SavedTvSeriesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.saved_tv_series_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SavedTvSeriesViewModel.class);
        // TODO: Use the ViewModel
    }

}
