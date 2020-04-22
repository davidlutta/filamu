package com.davidlutta.filamu;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.davidlutta.filamu.database.movies.Movie;
import com.davidlutta.filamu.viewmodels.SavedMoviesViewModel;

import java.util.concurrent.ExecutionException;

public class SavedMovieActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private SavedMoviesViewModel mViewModel;
    private TextView title;
    private TextView genre;
    private TextView rating;
    private TextView overview;
    private ImageView poster;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_movie);
        mViewModel = ViewModelProviders.of(this).get(SavedMoviesViewModel.class);
        title = findViewById(R.id.savedActivityTitleTextView);
        genre = findViewById(R.id.savedActivityGenreTextView);
        rating = findViewById(R.id.savedActivityRatingTextView);
        overview = findViewById(R.id.savedActivityOverviewTextView);
        poster = findViewById(R.id.savedBackgroundImageView);
        swipeRefreshLayout = findViewById(R.id.savedMovieActivitySwipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        try {
            subscribeViewModels();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        // TODO: 4/21/20 Add delete movie functionality by pressing the unlike button
    }

    private void subscribeViewModels() throws ExecutionException, InterruptedException {
        if (getIntent().hasExtra("id")) {
            String id = getIntent().getExtras().getString("id");
            swipeRefreshLayout.setRefreshing(false);
            Movie currentMovie = null;
            currentMovie = mViewModel.getSavedMovie(Integer.parseInt(id));
            assert currentMovie != null;
            populateData(currentMovie);
        }
    }

    private void populateData(Movie currentMovie) {
        title.setText(currentMovie.getTitle());
        genre.setText(currentMovie.getGenres());
        String ratingText = currentMovie.getRating() + " / 10";
        rating.setText(ratingText);
        overview.setText(currentMovie.getOverview());
        poster.setVisibility(View.VISIBLE);
        Glide.with(this)
                .load(currentMovie.getPoster())
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(R.drawable.ic_launcher)
                .into(poster);
    }

    @Override
    public void onRefresh() {
        try {
            subscribeViewModels();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
