package com.davidlutta.filamu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.davidlutta.filamu.models.show.Genre;
import com.davidlutta.filamu.models.show.Show;
import com.davidlutta.filamu.util.Constants;
import com.davidlutta.filamu.viewmodels.TvViewModel;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class SeriesActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private ImageView backgroundImageView;
    private TextView titleTextView;
    private TextView genreTextView;
    private TextView ratingTextView;
    private TextView overviewTextView;
    private SwipeRefreshLayout swipeRefreshLayout;

    private Show currentSeries;
    private TvViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series);
        backgroundImageView = findViewById(R.id.seriesActivityBackgroundImageView);
        titleTextView = findViewById(R.id.seriesTitleTextView);
        genreTextView = findViewById(R.id.seriesGenreTextView);
        ratingTextView = findViewById(R.id.seriesRatingTextView);
        overviewTextView = findViewById(R.id.seriesOverviewTextView);
        swipeRefreshLayout = findViewById(R.id.seriesActivitySwipeRefreshLayout);

        swipeRefreshLayout.setOnRefreshListener(this);
        mViewModel = ViewModelProviders.of(this).get(TvViewModel.class);
        subscribeViewModel();
    }

    private void subscribeViewModel() {
        if (getIntent().hasExtra("id")) {
            String id = Objects.requireNonNull(getIntent().getExtras()).getString("id");
            mViewModel.getSeries(id).observe(this, new Observer<Show>() {
                @Override
                public void onChanged(Show show) {
                    swipeRefreshLayout.setRefreshing(false);
                    currentSeries = show;
                    populateData();
                }
            });
        }
    }

    private void populateData() {
        if (currentSeries != null) {
            titleTextView.setText(currentSeries.getOriginalName());
            String rating = currentSeries.getVoteAverage().toString() + " / 10";
            ratingTextView.setText(rating);
            overviewTextView.setText(currentSeries.getOverview());
            String poster = Constants.IMAGE_BASE_URL + currentSeries.getPosterPath();
            String genre = generateGenreString(currentSeries.getGenres());
            genreTextView.setText(genre);
            backgroundImageView.setVisibility(View.VISIBLE);
            Glide.with(this)
                    .load(poster)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .placeholder(R.drawable.ic_launcher)
                    .into(backgroundImageView);
        }
    }

    private String generateGenreString(List<Genre> genres) {
        String joinedString = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            StringJoiner joiner = new StringJoiner(" | ");
            for (int i = 0; i < genres.size(); i++) {
                joiner.add(genres.get(i).getName());
            }
            joinedString = joiner.toString();
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            String delim = " | ";
            String loopDelim = "";
            for (int i = 0; i < genres.size(); i++) {
                stringBuilder.append(loopDelim);
                stringBuilder.append(genres.get(i).getName());
                loopDelim = delim;
            }
            joinedString = stringBuilder.toString();
        }
        return joinedString;
    }


    @Override
    public void onRefresh() {
        subscribeViewModel();
    }
}
