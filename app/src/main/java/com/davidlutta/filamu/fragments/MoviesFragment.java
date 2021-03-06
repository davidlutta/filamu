package com.davidlutta.filamu.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.davidlutta.filamu.R;
import com.davidlutta.filamu.UI.movies.playingnowmovies.ViewAllPlayingMoviesActivity;
import com.davidlutta.filamu.UI.movies.popularmovies.ViewAllPopularMoviesActivity;
import com.davidlutta.filamu.UI.movies.upcomingmovies.ViewAllUpcomingMoviesActivity;
import com.davidlutta.filamu.adapters.movies.MoviesAdapter;
import com.davidlutta.filamu.models.movies.Movies;
import com.davidlutta.filamu.viewmodels.MoviesViewModel;
import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView;

import java.util.List;

public class MoviesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private MoviesViewModel mViewModel;

    private MultiSnapRecyclerView popularMoviesRecyclerView;
    private List<Movies> popularMoviesList;

    private MultiSnapRecyclerView moviesPlayingNowRecyclerView;
    private List<Movies> moviesPlayingNowList;

    private MultiSnapRecyclerView upcomingMoviesRecyclerView;
    private List<Movies> upcomingMoviesList;

    private TextView popularTitleTextView;
    private Button viewAllPopularMoviesTextView;
    private TextView discoverTitleTextView;
    private Button viewAllMoviesPlayingNowTextView;
    private TextView upcomingMoviesTitle;
    private Button viewAllUpcomingMoviesTextView;

    private SwipeRefreshLayout swipeRefreshLayout;

    private MoviesAdapter moviesAdapter;

    private ProgressBar popularMoviesPb;
    private ProgressBar playingNowMoviesPb;
    private ProgressBar upcomingMoviesPb;

    public static MoviesFragment newInstance() {
        return new MoviesFragment();
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // FIXME: 4/19/20 Make Unnecessary Text disappear until data is loaded
        View view = inflater.inflate(R.layout.movies_fragment, container, false);

        swipeRefreshLayout = view.findViewById(R.id.moviesFragmentSwipeRefreshLayout);

        popularMoviesPb = view.findViewById(R.id.popularMoviesProgressBar);
        playingNowMoviesPb = view.findViewById(R.id.playingNowProgressBar);
        upcomingMoviesPb = view.findViewById(R.id.upcomingMoviesProgressBar);

        swipeRefreshLayout.setOnRefreshListener(this);

        popularMoviesRecyclerView = view.findViewById(R.id.popular_movies_recyclerView);
        moviesPlayingNowRecyclerView = view.findViewById(R.id.now_playing_recyclerView);
        upcomingMoviesRecyclerView = view.findViewById(R.id.upcomingMoviesRecyclerView);

        popularTitleTextView = view.findViewById(R.id.popularTitleTextView);
        discoverTitleTextView = view.findViewById(R.id.discoverTitleTextView);
        upcomingMoviesTitle = view.findViewById(R.id.upcomingMoviesTitle);
        viewAllMoviesPlayingNowTextView = view.findViewById(R.id.viewAllMoviesPlayingNowTextView);
        viewAllPopularMoviesTextView = view.findViewById(R.id.viewAllPopularMoviesTextView);
        viewAllUpcomingMoviesTextView = view.findViewById(R.id.viewAllUpcomingMoviesTextView);

        viewAllPopularMoviesTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ViewAllPopularMoviesActivity.class);
                startActivity(intent);
            }
        });
        viewAllUpcomingMoviesTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ViewAllUpcomingMoviesActivity.class);
                startActivity(intent);
            }
        });
        viewAllMoviesPlayingNowTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ViewAllPlayingMoviesActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        subscribeObservers();
    }

    private void setUpPopularMoviesAdapter() {
        if (moviesAdapter == null) {
            moviesAdapter = new MoviesAdapter(getContext(), popularMoviesList);
            popularMoviesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            popularMoviesRecyclerView.setAdapter(moviesAdapter);
            popularMoviesRecyclerView.setNestedScrollingEnabled(false);
            popularMoviesRecyclerView.setVisibility(View.VISIBLE);
            popularMoviesPb.setVisibility(View.INVISIBLE);
            moviesAdapter = null;
        }

    }

    private void setUpMoviesPlayingNowAdapter() {
        if (moviesAdapter == null) {
            moviesAdapter = new MoviesAdapter(getContext(), moviesPlayingNowList);
            moviesPlayingNowRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            moviesPlayingNowRecyclerView.setAdapter(moviesAdapter);
            moviesPlayingNowRecyclerView.setNestedScrollingEnabled(false);
            moviesPlayingNowRecyclerView.setVisibility(View.VISIBLE);
            playingNowMoviesPb.setVisibility(View.INVISIBLE);
            moviesAdapter = null;
        }
    }

    private void setUpUpcomingMoviesAdapter() {
        if (moviesAdapter == null) {
            moviesAdapter = new MoviesAdapter(getContext(), upcomingMoviesList);
            upcomingMoviesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            upcomingMoviesRecyclerView.setAdapter(moviesAdapter);
            upcomingMoviesRecyclerView.setNestedScrollingEnabled(false);
            upcomingMoviesRecyclerView.setVisibility(View.VISIBLE);
            upcomingMoviesPb.setVisibility(View.INVISIBLE);
            moviesAdapter = null;
        }
    }

    private void subscribeObservers() {
        mViewModel.getPopularMovies().observe(getViewLifecycleOwner(), new Observer<List<Movies>>() {
            @Override
            public void onChanged(List<Movies> movies) {
                swipeRefreshLayout.setRefreshing(false);
                popularMoviesList = movies;
                setUpPopularMoviesAdapter();
            }
        });
        mViewModel.getMoviesPlayingNow().observe(getViewLifecycleOwner(), new Observer<List<Movies>>() {
            @Override
            public void onChanged(List<Movies> movies) {
                swipeRefreshLayout.setRefreshing(false);
                moviesPlayingNowList = movies;
                setUpMoviesPlayingNowAdapter();
            }
        });
        mViewModel.getUpcomingMovies().observe(getViewLifecycleOwner(), new Observer<List<Movies>>() {
            @Override
            public void onChanged(List<Movies> movies) {
                swipeRefreshLayout.setRefreshing(false);
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

    @Override
    public void onRefresh() {
        subscribeObservers();
    }
}
