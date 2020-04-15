package com.davidlutta.filamu.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.davidlutta.filamu.R;
import com.davidlutta.filamu.models.Movies;
import com.davidlutta.filamu.viewmodels.MoviesViewModel;

import java.util.List;

public class MoviesFragment extends Fragment {

    private MoviesViewModel mViewModel;

    public static MoviesFragment newInstance() {
        return new MoviesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.movies_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        mViewModel.getMovies().observe(getViewLifecycleOwner(), new Observer<List<Movies>>() {
            @Override
            public void onChanged(List<Movies> movies) {
                // TODO: 4/16/20 Add Horizontal Adapter to post Values
                System.out.println(movies);
            }
        });
    }

}
