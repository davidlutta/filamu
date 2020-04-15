package com.davidlutta.filamu.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.davidlutta.filamu.R;
import com.davidlutta.filamu.adapters.NowPlayingMoviesAdapter;
import com.davidlutta.filamu.adapters.OnMovieListener;
import com.davidlutta.filamu.models.Movies;
import com.davidlutta.filamu.viewmodels.MoviesViewModel;

import java.util.List;

public class MoviesFragment extends Fragment implements OnMovieListener {

    private MoviesViewModel mViewModel;
    private RecyclerView nowPlayingRecyclerView;
    private NowPlayingMoviesAdapter nowPlayingMoviesAdapter;
    private List<Movies> moviesList;
    private TextView nowPlayingTitle;

    public static MoviesFragment newInstance() {
        return new MoviesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movies_fragment, container, false);
        nowPlayingRecyclerView = view.findViewById(R.id.now_playing_recyclerView);
        nowPlayingTitle = view.findViewById(R.id.nowPlayingTitleTextView);
        return view;
    }

    private void setUpAdapters() {
        if (nowPlayingMoviesAdapter == null) {
            nowPlayingMoviesAdapter = new NowPlayingMoviesAdapter(getContext(), moviesList, this);
            nowPlayingRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            nowPlayingRecyclerView.setAdapter(nowPlayingMoviesAdapter);
            nowPlayingRecyclerView.setNestedScrollingEnabled(true);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        mViewModel.getMovies().observe(getViewLifecycleOwner(), new Observer<List<Movies>>() {
            @Override
            public void onChanged(List<Movies> movies) {
                // TODO: 4/16/20 Add Horizontal Adapter to post Values
                moviesList = movies;
                setUpAdapters();
            }
        });
    }

    @Override
    public void OnMovieClick(int position) {

    }
}
