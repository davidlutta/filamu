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
import com.davidlutta.filamu.viewmodels.TvViewModel;

public class TvFragment extends Fragment {

    private TvViewModel mViewModel;

    public static TvFragment newInstance() {
        return new TvFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tv_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TvViewModel.class);
        // TODO: Use the ViewModel
    }

}
