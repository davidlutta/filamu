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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.davidlutta.filamu.R;
import com.davidlutta.filamu.adapters.favourite.SavedMoviesAdapter;
import com.davidlutta.filamu.database.movies.Movie;
import com.davidlutta.filamu.viewmodels.SavedMoviesViewModel;

import java.util.List;

public class SavedMoviesFragment extends Fragment {

    private SavedMoviesViewModel mViewModel;
    private RecyclerView savedMoviesRecyclerView;
    private SavedMoviesAdapter moviesAdapter;

    public static SavedMoviesFragment newInstance() {
        return new SavedMoviesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.saved_movies_fragment, container, false);
        savedMoviesRecyclerView = view.findViewById(R.id.savedMoviesRecyclerView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SavedMoviesViewModel.class);
        subscribeViewModels();
    }

    private void subscribeViewModels() {
        mViewModel.getSavedMovies().observe(getViewLifecycleOwner(), new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                if (moviesAdapter == null) {
                    moviesAdapter = new SavedMoviesAdapter(getContext(), movies);
                    savedMoviesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    savedMoviesRecyclerView.setAdapter(moviesAdapter);
                    savedMoviesRecyclerView.setNestedScrollingEnabled(true);
                }
            }
        });
    }

}
