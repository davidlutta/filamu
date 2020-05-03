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
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.davidlutta.filamu.R;
import com.davidlutta.filamu.adapters.favourite.SavedMoviesAdapter;
import com.davidlutta.filamu.database.movies.Movie;
import com.davidlutta.filamu.viewmodels.SavedMoviesViewModel;

import java.util.List;
import java.util.Objects;

import es.dmoral.toasty.Toasty;

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
        final View view = inflater.inflate(R.layout.saved_movies_fragment, container, false);
        savedMoviesRecyclerView = view.findViewById(R.id.savedMoviesRecyclerView);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
                //for drag and drop functionality
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                mViewModel.deleteSavedMovie(moviesAdapter.getSelectedSavedMovie(viewHolder.getAdapterPosition()).getMovieId());
                Toasty.error(Objects.requireNonNull(getContext()), "Removed Movie", Toasty.LENGTH_SHORT, true).show();
            }
        }).attachToRecyclerView(savedMoviesRecyclerView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SavedMoviesViewModel.class);
        moviesAdapter = new SavedMoviesAdapter(getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        savedMoviesRecyclerView.setLayoutManager(layoutManager);
        savedMoviesRecyclerView.setAdapter(moviesAdapter);
        savedMoviesRecyclerView.setNestedScrollingEnabled(false);
        subscribeViewModels();
    }

    private void subscribeViewModels() {
        mViewModel.getSavedMovies().observe(getViewLifecycleOwner(), new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                moviesAdapter.submitList(movies);
            }
        });
    }
}
