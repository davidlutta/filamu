package com.davidlutta.filamu.UI.popularmovies;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.davidlutta.filamu.R;
import com.davidlutta.filamu.UI.popularmovies.viewmodel.PopularViewModel;
import com.davidlutta.filamu.adapters.movies.ViewAllMoviesAdapter;
import com.davidlutta.filamu.models.movies.Movies;

public class ViewAllPopularMoviesActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView moviesRecyclerView;
    private PopularViewModel mViewModel;
    private ViewAllMoviesAdapter moviesAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_movies);
        moviesRecyclerView = findViewById(R.id.viewAllMoviesRecyclerView);
        swipeRefreshLayout = findViewById(R.id.viewAllMoviesSwipeRefreshLayout);
        setTitle("Popular Movies");
        mViewModel = ViewModelProviders.of(this).get(PopularViewModel.class);
        // TODO: 4/24/20 Add Pagination to this page Please We might have to just create a new adapter reference this https://github.com/davidlutta/retro/tree/c60678bb2afedb78c4a7b253548625d848336c8d/app/src/main/java/com/davidlutta/retro 
        swipeRefreshLayout.setOnRefreshListener(this);
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
