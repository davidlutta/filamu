package com.davidlutta.filamu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.davidlutta.filamu.adapters.movies.MoviesAdapter;
import com.davidlutta.filamu.models.movie.Movie;
import com.davidlutta.filamu.models.movies.Movies;
import com.davidlutta.filamu.viewmodels.MoviesViewModel;

import java.util.List;

public class ViewAllMoviesActivity extends AppCompatActivity {
    private RecyclerView moviesRecyclerView;
    private List<Movies> movieList;
    private MoviesAdapter moviesAdapter;
    private MoviesViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_movies);
        moviesRecyclerView = findViewById(R.id.viewAllMoviesRecyclerView);
        mViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        subscribeViewModels();
    }

    private void subscribeViewModels() {
        if (getIntent().hasExtra("id") || getIntent().hasExtra("movieId")) {
            String id = getIntent().getExtras().getString("id");
            String movieId = getIntent().getExtras().getString("movieId");
            assert id != null;
            switch (id) {
                case "popularMovies":
                    mViewModel.getPopularMovies().observe(this, new Observer<List<Movies>>() {
                        @Override
                        public void onChanged(List<Movies> movies) {
                            movieList = movies;
                            setUpAdapter("Popular Movies");
                        }
                    });
                    break;
                case "moviesPlayingNow":
                    mViewModel.getMoviesPlayingNow().observe(this, new Observer<List<Movies>>() {
                        @Override
                        public void onChanged(List<Movies> movies) {
                            movieList = movies;
                            setUpAdapter("Playing Now");
                        }
                    });
                    break;
                case "upcomingMovies":
                    mViewModel.getUpcomingMovies().observe(this, new Observer<List<Movies>>() {
                        @Override
                        public void onChanged(List<Movies> movies) {
                            movieList = movies;
                            setUpAdapter("Upcoming Movies");
                        }
                    });
                    break;
                case "similarMovies":
                    mViewModel.getSimilarMovies(movieId).observe(this, new Observer<List<Movies>>() {
                        @Override
                        public void onChanged(List<Movies> movies) {
                            movieList = movies;
                            setUpAdapter("Similar Movies");
                        }
                    });
            }
        }
    }

    private void setUpAdapter(String title) {
        if (moviesAdapter == null) {
            moviesAdapter = new MoviesAdapter(this, movieList);
            setTitle(title);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
            moviesRecyclerView.setLayoutManager(layoutManager);
            moviesRecyclerView.setAdapter(moviesAdapter);
        }
    }
}
