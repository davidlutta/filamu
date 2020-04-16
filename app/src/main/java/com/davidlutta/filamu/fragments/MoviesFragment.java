package com.davidlutta.filamu.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.davidlutta.filamu.MovieActivity;
import com.davidlutta.filamu.R;
import com.davidlutta.filamu.adapters.moviesPlayingNow.MoviesPlayingNowAdapter;
import com.davidlutta.filamu.adapters.moviesPlayingNow.OnMoviesNowPlayingListener;
import com.davidlutta.filamu.adapters.popularMovies.OnPopularMovieListener;
import com.davidlutta.filamu.adapters.popularMovies.PopularMoviesAdapter;
import com.davidlutta.filamu.adapters.upcomingMovies.OnUpcomingMovieListener;
import com.davidlutta.filamu.adapters.upcomingMovies.UpcomingMoviesAdapter;
import com.davidlutta.filamu.models.movies.Movies;
import com.davidlutta.filamu.viewmodels.MoviesViewModel;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MoviesFragment extends Fragment implements OnPopularMovieListener, OnUpcomingMovieListener, OnMoviesNowPlayingListener {

    private MoviesViewModel mViewModel;

    private PopularMoviesAdapter popularMoviesAdapter;
    private RecyclerView popularMoviesRecyclerView;
    private List<Movies> popularMoviesList;

    private MoviesPlayingNowAdapter moviesPlayingNowAdapter;
    private RecyclerView moviesPlayingNowRecyclerView;
    private List<Movies> moviesPlayingNowList;

    private UpcomingMoviesAdapter upcomingMoviesAdapter;
    private RecyclerView upcomingMoviesRecyclerView;
    private List<Movies> upcomingMoviesList;

    SweetAlertDialog sweetAlertDialog;
    private ProgressBar progressBar;
    private ProgressBar moviesNowPlayingProgressBar;
    private ProgressBar upcomingMoviesProgressBar;
    private TextView popularTitleTextView;
    private TextView viewAllPopularMoviesTextView;
    private TextView discoverTitleTextView;
    private TextView viewAllMoviesPlayingNowTextView;
    private TextView upcomingMoviesTitle;
    private TextView viewAllUpcomingMoviesTextView;

    public static MoviesFragment newInstance() {
        return new MoviesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movies_fragment, container, false);
        popularMoviesRecyclerView = view.findViewById(R.id.popular_movies_recyclerView);
        moviesPlayingNowRecyclerView = view.findViewById(R.id.now_playing_recyclerView);
        upcomingMoviesRecyclerView = view.findViewById(R.id.upcomingMoviesRecyclerView);
        progressBar = view.findViewById(R.id.popularMoviesFragmentProgressBar);
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
        if (popularMoviesAdapter == null) {
            popularMoviesAdapter = new PopularMoviesAdapter(getContext(), popularMoviesList, this);
            popularMoviesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            popularMoviesRecyclerView.setAdapter(popularMoviesAdapter);
            popularMoviesRecyclerView.setNestedScrollingEnabled(false);
        }
    }

    private void setUpMoviesPlayingNowAdapter() {
        if (moviesPlayingNowAdapter == null) {
            moviesPlayingNowAdapter = new MoviesPlayingNowAdapter(getContext(), moviesPlayingNowList, this);
            moviesPlayingNowRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            moviesPlayingNowRecyclerView.setAdapter(moviesPlayingNowAdapter);
            moviesPlayingNowRecyclerView.setNestedScrollingEnabled(false);
        }
    }

    private void setUpUpcomingMoviesAdapter() {
        if (upcomingMoviesAdapter == null) {
            upcomingMoviesAdapter = new UpcomingMoviesAdapter(getContext(), upcomingMoviesList, this);
            upcomingMoviesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            upcomingMoviesRecyclerView.setAdapter(upcomingMoviesAdapter);
            upcomingMoviesRecyclerView.setNestedScrollingEnabled(false);
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

    @Override
    public void OnPopularMovieClick(int position) {
        Movies selectedMovie = popularMoviesAdapter.getSelectedMovie(position);
        Intent intent = new Intent(getContext(), MovieActivity.class);
        String id = selectedMovie.getId().toString();
        intent.putExtra("id", id);
        startActivity(intent);
    }

    @Override
    public void onUpcomingMovieClick(int position) {
        Movies selectedMovie = upcomingMoviesAdapter.getSelectedMovie(position);
        Intent intent = new Intent(getContext(), MovieActivity.class);
        String id = selectedMovie.getId().toString();
        intent.putExtra("id", id);
        startActivity(intent);
    }

    @Override
    public void onMovieNowPlayingListener(int position) {
        Movies selectedMovie = moviesPlayingNowAdapter.getSelectedMovie(position);
        Intent intent = new Intent(getContext(), MovieActivity.class);
        String id = selectedMovie.getId().toString();
        intent.putExtra("id", id);
        startActivity(intent);
    }

    private void showProgressBar() {
        /*SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();*/
        progressBar.setVisibility(View.VISIBLE);
        popularTitleTextView.setVisibility(View.INVISIBLE);
        discoverTitleTextView.setVisibility(View.INVISIBLE);
        upcomingMoviesTitle.setVisibility(View.INVISIBLE);
        viewAllUpcomingMoviesTextView.setVisibility(View.INVISIBLE);
        viewAllPopularMoviesTextView.setVisibility(View.INVISIBLE);
        viewAllMoviesPlayingNowTextView.setVisibility(View.INVISIBLE);
    }

    private void hideProgressBar() {
        if (!popularMoviesList.isEmpty() && !moviesPlayingNowList.isEmpty() && !upcomingMoviesList.isEmpty()) {
            progressBar.setVisibility(View.INVISIBLE);
            popularTitleTextView.setVisibility(View.VISIBLE);
            discoverTitleTextView.setVisibility(View.VISIBLE);
            upcomingMoviesTitle.setVisibility(View.VISIBLE);
            viewAllUpcomingMoviesTextView.setVisibility(View.VISIBLE);
            viewAllPopularMoviesTextView.setVisibility(View.VISIBLE);
            viewAllMoviesPlayingNowTextView.setVisibility(View.VISIBLE);
        }
    }
}
