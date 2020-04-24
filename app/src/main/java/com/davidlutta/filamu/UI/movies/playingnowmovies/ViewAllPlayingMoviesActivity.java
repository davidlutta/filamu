package com.davidlutta.filamu.UI.movies.playingnowmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import com.davidlutta.filamu.R;
import com.davidlutta.filamu.UI.movies.playingnowmovies.viewmodel.PlayingViewModel;
import com.davidlutta.filamu.adapters.movies.ViewAllMoviesAdapter;
import com.davidlutta.filamu.models.movies.Movies;

public class ViewAllPlayingMoviesActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView moviesRecyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private PlayingViewModel mViewModel;
    private ViewAllMoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_playing_movies);
        moviesRecyclerView = findViewById(R.id.ViewAllPlayingMoviesActivityRecyclerView);
        swipeRefreshLayout = findViewById(R.id.ViewAllPlayingMoviesActivitySwipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        mViewModel = ViewModelProviders.of(this).get(PlayingViewModel.class);
        setTitle("Now Playing");
        setUpAdapter();
        subscribeViewModels();
    }

    private void subscribeViewModels() {
        mViewModel.getPagedListLiveData().observe(this, new Observer<PagedList<Movies>>() {
            @Override
            public void onChanged(PagedList<Movies> movies) {
                swipeRefreshLayout.setRefreshing(false);
                moviesAdapter.submitList(movies);
            }
        });
    }

    private void setUpAdapter() {
        if (moviesAdapter == null) {
            moviesAdapter = new ViewAllMoviesAdapter(this);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
            moviesRecyclerView.setLayoutManager(layoutManager);
            moviesRecyclerView.setAdapter(moviesAdapter);
        }
    }

    @Override
    public void onRefresh() {
        subscribeViewModels();
    }
}
