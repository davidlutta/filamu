package com.davidlutta.filamu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.davidlutta.filamu.database.tv.Series;
import com.davidlutta.filamu.viewmodels.SavedTvSeriesViewModel;

import java.util.concurrent.ExecutionException;

public class SavedSeriesActivity extends AppCompatActivity {
    private ImageView backgroundImage;
    private TextView titleTextView;
    private TextView genreTextView;
    private TextView overviewTextView;
    private TextView ratingTextView;
    private TextView firstEpisodeTextView;
    private TextView numOfSeasonsTextView;
    private TextView statusTextView;
    private SavedTvSeriesViewModel viewModel;
    private Series currentSeries;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_series);
        backgroundImage = findViewById(R.id.savedSeriesActivityBackgroundImageView);
        titleTextView = findViewById(R.id.savedSeriesActivityTitleTextView);
        genreTextView = findViewById(R.id.savedSeriesActivityGenreTextView);
        overviewTextView = findViewById(R.id.savedSeriesActivityOverviewTextView);
        ratingTextView = findViewById(R.id.savedSeriesActivityRatingTextView);
        firstEpisodeTextView = findViewById(R.id.savedSeriesActivityFirstEpisodeTextView);
        numOfSeasonsTextView = findViewById(R.id.savedSeriesActivityNumOfSeasonsTextView);
        statusTextView = findViewById(R.id.savedSeriesActivityStatus);
        viewModel = ViewModelProviders.of(this).get(SavedTvSeriesViewModel.class);
        try {
            subscribeViewModels();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void subscribeViewModels() throws ExecutionException, InterruptedException {
        if (getIntent().hasExtra("id")) {
            String id = getIntent().getExtras().getString("id");
            currentSeries = viewModel.getSavedSeries(Integer.parseInt(id));
            populateData();
        }
    }

    private void populateData() {
        if (currentSeries != null) {
            titleTextView.setText(currentSeries.getTitle());
            genreTextView.setText(currentSeries.getGenre());
            String ratingText = currentSeries.getRating() + " / 10";
            ratingTextView.setText(ratingText);
            overviewTextView.setText(currentSeries.getOverView());
            backgroundImage.setVisibility(View.VISIBLE);
            firstEpisodeTextView.setText(String.format("First Episode: %s\nLast Episode: %s", currentSeries.getFirstAirDate(), currentSeries.getLastAirDate()));
            numOfSeasonsTextView.setText(String.format("Number of Seasons: %s", currentSeries.getNumOfSeasons()));
            statusTextView.setText(String.format("Status: %s", currentSeries.getStatus()));
            Glide.with(this)
                    .load(currentSeries.getPoster())
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .placeholder(R.drawable.ic_launcher)
                    .into(backgroundImage);
        }
    }
}
