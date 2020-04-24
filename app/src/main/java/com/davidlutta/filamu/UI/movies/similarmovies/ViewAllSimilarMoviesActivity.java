package com.davidlutta.filamu.UI.movies.similarmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.davidlutta.filamu.R;
import com.davidlutta.filamu.UI.movies.similarmovies.viewmodel.SimilarViewModel;
import com.davidlutta.filamu.UI.movies.similarmovies.viewmodel.SimilarViewModelFactory;
import com.davidlutta.filamu.adapters.movies.ViewAllMoviesAdapter;
import com.davidlutta.filamu.models.movies.Movies;

public class ViewAllSimilarMoviesActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView moviesRecyclerView;
    private ViewAllMoviesAdapter moviesAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_similar_movies);
        moviesRecyclerView = findViewById(R.id.viewAllSimilarMoviesRecyclerView);
        swipeRefreshLayout = findViewById(R.id.viewAllSimilarMoviesActivitySwipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        setTitle("Similar Movies");
        subscribeViewModels();
        setUpAdapter();
    }

    private void subscribeViewModels() {
        if (getIntent().hasExtra("movieId")) {
            String movieId = getIntent().getExtras().getString("movieId");
            SimilarViewModel mViewModel = ViewModelProviders.of(this, new SimilarViewModelFactory(this.getApplication(), movieId)).get(SimilarViewModel.class);
            mViewModel.getPagedListLiveData().observe(this, new Observer<PagedList<Movies>>() {
                @Override
                public void onChanged(PagedList<Movies> movies) {
                    swipeRefreshLayout.setRefreshing(false);
                    moviesAdapter.submitList(movies);
                }
            });
        }
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
