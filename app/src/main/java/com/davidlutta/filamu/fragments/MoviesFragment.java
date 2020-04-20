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

import com.davidlutta.filamu.R;
import com.davidlutta.filamu.adapters.movies.MoviesAdapter;
import com.davidlutta.filamu.models.movies.Movies;
import com.davidlutta.filamu.viewmodels.MoviesViewModel;
import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MoviesFragment extends Fragment {

    private MoviesViewModel mViewModel;

    private MultiSnapRecyclerView popularMoviesRecyclerView;
    private List<Movies> popularMoviesList;

    private MultiSnapRecyclerView moviesPlayingNowRecyclerView;
    private List<Movies> moviesPlayingNowList;

    private MultiSnapRecyclerView upcomingMoviesRecyclerView;
    private List<Movies> upcomingMoviesList;

    SweetAlertDialog sweetAlertDialog;
    private TextView popularTitleTextView;
    private TextView viewAllPopularMoviesTextView;
    private TextView discoverTitleTextView;
    private TextView viewAllMoviesPlayingNowTextView;
    private TextView upcomingMoviesTitle;
    private TextView viewAllUpcomingMoviesTextView;

    private MoviesAdapter moviesAdapter;

    public static MoviesFragment newInstance() {
        return new MoviesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // FIXME: 4/19/20 Make Unnecessary Text disappear until data is loaded
        View view = inflater.inflate(R.layout.movies_fragment, container, false);
        popularMoviesRecyclerView = view.findViewById(R.id.popular_movies_recyclerView);
        moviesPlayingNowRecyclerView = view.findViewById(R.id.now_playing_recyclerView);
        upcomingMoviesRecyclerView = view.findViewById(R.id.upcomingMoviesRecyclerView);
        popularTitleTextView = view.findViewById(R.id.popularTitleTextView);
        discoverTitleTextView = view.findViewById(R.id.discoverTitleTextView);
        upcomingMoviesTitle = view.findViewById(R.id.upcomingMoviesTitle);
        viewAllMoviesPlayingNowTextView = view.findViewById(R.id.viewAllMoviesPlayingNowTextView);
        viewAllPopularMoviesTextView = view.findViewById(R.id.viewAllPopularMoviesTextView);
        viewAllUpcomingMoviesTextView = view.findViewById(R.id.viewAllUpcomingMoviesTextView);
        final float scale = getContext().getResources().getDisplayMetrics().density;
        int pixels = (int) (350 * scale + 0.5f);
        upcomingMoviesRecyclerView.getLayoutParams().height = pixels;
        return view;
    }

    private void setUpPopularMoviesAdapter() {
        if (moviesAdapter == null) {
            moviesAdapter = new MoviesAdapter(getContext(), popularMoviesList);
            popularMoviesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            popularMoviesRecyclerView.setAdapter(moviesAdapter);
            popularMoviesRecyclerView.setNestedScrollingEnabled(false);
            moviesAdapter = null;
        }

    }

    private void setUpMoviesPlayingNowAdapter() {
        if (moviesAdapter == null) {
            moviesAdapter = new MoviesAdapter(getContext(), moviesPlayingNowList);
            moviesPlayingNowRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            moviesPlayingNowRecyclerView.setAdapter(moviesAdapter);
            moviesPlayingNowRecyclerView.setNestedScrollingEnabled(false);
            moviesAdapter = null;
        }
    }

    private void setUpUpcomingMoviesAdapter() {
        if (moviesAdapter == null) {
            moviesAdapter = new MoviesAdapter(getContext(), upcomingMoviesList);
            upcomingMoviesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            upcomingMoviesRecyclerView.setAdapter(moviesAdapter);
            upcomingMoviesRecyclerView.setNestedScrollingEnabled(false);
            moviesAdapter = null;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        subscribeObservers();
    }

    private void subscribeObservers() {
        mViewModel.getPopularMovies().observe(getViewLifecycleOwner(), new Observer<List<Movies>>() {
            @Override
            public void onChanged(List<Movies> movies) {
                popularMoviesList = movies;
                setUpPopularMoviesAdapter();
            }
        });
        mViewModel.getMoviesPlayingNow().observe(getViewLifecycleOwner(), new Observer<List<Movies>>() {
            @Override
            public void onChanged(List<Movies> movies) {
                moviesPlayingNowList = movies;
                setUpMoviesPlayingNowAdapter();
            }
        });
        mViewModel.getUpcomingMovies().observe(getViewLifecycleOwner(), new Observer<List<Movies>>() {
            @Override
            public void onChanged(List<Movies> movies) {
                upcomingMoviesList = movies;
                setUpUpcomingMoviesAdapter();
            }
        });
    }

    private void showProgressBar() {
        /*SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();*/
        popularTitleTextView.setVisibility(View.INVISIBLE);
        discoverTitleTextView.setVisibility(View.INVISIBLE);
        upcomingMoviesTitle.setVisibility(View.INVISIBLE);
        viewAllUpcomingMoviesTextView.setVisibility(View.INVISIBLE);
        viewAllPopularMoviesTextView.setVisibility(View.INVISIBLE);
        viewAllMoviesPlayingNowTextView.setVisibility(View.INVISIBLE);
    }

    private void hideProgressBar() {
        if (!popularMoviesList.isEmpty() && !moviesPlayingNowList.isEmpty() && !upcomingMoviesList.isEmpty()) {
            popularTitleTextView.setVisibility(View.VISIBLE);
            discoverTitleTextView.setVisibility(View.VISIBLE);
            upcomingMoviesTitle.setVisibility(View.VISIBLE);
            viewAllUpcomingMoviesTextView.setVisibility(View.VISIBLE);
            viewAllPopularMoviesTextView.setVisibility(View.VISIBLE);
            viewAllMoviesPlayingNowTextView.setVisibility(View.VISIBLE);
        }
    }
}
