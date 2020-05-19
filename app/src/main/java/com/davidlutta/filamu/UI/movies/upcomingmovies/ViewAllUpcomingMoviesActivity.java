package com.davidlutta.filamu.UI.movies.upcomingmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.davidlutta.filamu.R;
import com.davidlutta.filamu.UI.movies.upcomingmovies.viewmodel.UpcomingViewModel;
import com.davidlutta.filamu.adapters.movies.ViewAllMoviesAdapter;
import com.davidlutta.filamu.models.movies.Movies;

public class ViewAllUpcomingMoviesActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView moviesRecyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ViewAllMoviesAdapter moviesAdapter;
    private UpcomingViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_upcoming_movies);
        moviesRecyclerView = findViewById(R.id.ViewAllUpcomingMoviesActivityRecyclerView);
        swipeRefreshLayout = findViewById(R.id.ViewAllUpcomingMoviesActivitySwipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        setTitle("Upcoming Movies");
        mViewModel = ViewModelProviders.of(this).get(UpcomingViewModel.class);
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
            moviesRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onRefresh() {
        subscribeViewModels();
    }
}
