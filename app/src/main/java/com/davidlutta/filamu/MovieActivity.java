package com.davidlutta.filamu;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.davidlutta.filamu.adapters.cast.CastAdapter;
import com.davidlutta.filamu.adapters.crew.CrewAdapter;
import com.davidlutta.filamu.adapters.movies.MoviesAdapter;
import com.davidlutta.filamu.adapters.trailers.TrailersAdapter;
import com.davidlutta.filamu.models.cast.Cast;
import com.davidlutta.filamu.models.cast.Crew;
import com.davidlutta.filamu.models.movie.Movie;
import com.davidlutta.filamu.models.movies.Movies;
import com.davidlutta.filamu.models.trailers.Trailer;
import com.davidlutta.filamu.util.Constants;
import com.davidlutta.filamu.viewmodels.MoviesViewModel;

import java.util.List;
import java.util.StringJoiner;

public class MovieActivity extends AppCompatActivity {

    private MoviesViewModel moviesViewModel;

    private TextView titleTextView;
    private TextView genreTextView;
    private TextView ratingTextView;
    private TextView overviewTextView;
    private ImageView backgroundImageView;

    private RecyclerView castRecyclerView;
    private List<Cast> castList;
    private CastAdapter castAdapter;

    private RecyclerView crewRecyclerView;
    private List<Crew> crewList;
    private CrewAdapter crewAdapter;

    private RecyclerView trailersRecyclerView;
    private List<Trailer> trailerList;
    private TrailersAdapter trailersAdapter;

    private RecyclerView similarMoviesRecyclerView;
    private List<Movies> similarMoviesList;
    private MoviesAdapter similarMoviesAdapter;

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        setTitle("");
        // FIXME: 4/19/20 Make Uneccessary Text disappear until data is loaded
        swipeRefreshLayout = findViewById(R.id.moviesActivitySwipeRefreshLayout);
        titleTextView = findViewById(R.id.titleTextView);
        genreTextView = findViewById(R.id.genreTextView);
        ratingTextView = findViewById(R.id.ratingTextView);
        overviewTextView = findViewById(R.id.overviewTextView);
        backgroundImageView = findViewById(R.id.backgroundImageView);
        castRecyclerView = findViewById(R.id.castRecyclerView);
        crewRecyclerView = findViewById(R.id.crewRecyclerView);
        trailersRecyclerView = findViewById(R.id.trailersRecyclerView);
        similarMoviesRecyclerView = findViewById(R.id.similarMoviesRecyclerView);
        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        subscribeObservers();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                subscribeObservers();
            }
        });
    }

    private void subscribeObservers() {
        if (getIntent().hasExtra("id")) {
            String id = getIntent().getExtras().getString(getString(R.string.movieIntentExtraName));
            moviesViewModel.getMovieDetails(id).observe(this, new Observer<Movie>() {
                @Override
                public void onChanged(Movie movie) {
                    swipeRefreshLayout.setRefreshing(false);
                    populateData(movie);
                }
            });
            moviesViewModel.getCastDetails(id).observe(this, new Observer<List<Cast>>() {
                @Override
                public void onChanged(List<Cast> casts) {
                    swipeRefreshLayout.setRefreshing(false);
                    castList = casts;
                    setUpCastAdapter();
                }
            });
            moviesViewModel.getCrewDetails(id).observe(this, new Observer<List<Crew>>() {
                @Override
                public void onChanged(List<Crew> crews) {
                    swipeRefreshLayout.setRefreshing(false);
                    crewList = crews;
                    setUpCrewAdapter();
                }
            });
            moviesViewModel.getTrailers(id).observe(this, new Observer<List<Trailer>>() {
                @Override
                public void onChanged(List<Trailer> trailers) {
                    swipeRefreshLayout.setRefreshing(false);
                    trailerList = trailers;
                    setUpTrailersAdapter();
                }
            });
            moviesViewModel.getSimilarMovies(id).observe(this, new Observer<List<Movies>>() {
                @Override
                public void onChanged(List<Movies> movies) {
                    swipeRefreshLayout.setRefreshing(false);
                    similarMoviesList = movies;
                    setUpSimilarMoviesAdapter();
                }
            });
        }
    }

    @SuppressLint("SetTextI18n")
    private void populateData(Movie movie) {
        if (movie != null) {
            titleTextView.setText(movie.getTitle());
            String rating = movie.getVoteAverage().toString() + " / 10";
            ratingTextView.setText(rating);
            overviewTextView.setText(movie.getOverview());
            String poster = Constants.IMAGE_BASE_URL + movie.getPosterPath();
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                StringJoiner joiner = new StringJoiner(" | ");
                for (int i = 0; i < movie.getGenres().size(); i++) {
                    joiner.add(movie.getGenres().get(i).getName());
                }
                String joinedString = joiner.toString();
                genreTextView.setText(joinedString);
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                String delim = " | ";
                String loopDelim = "";
                for (int i = 0; i < movie.getGenres().size(); i++) {
                    stringBuilder.append(loopDelim);
                    stringBuilder.append(movie.getGenres().get(i).getName());
                    loopDelim = delim;
                }
                String joinedString = stringBuilder.toString();
                genreTextView.setText(joinedString);
            }
            backgroundImageView.setVisibility(View.VISIBLE);
            Glide.with(this)
                    .load(poster)
                    .placeholder(R.drawable.ic_launcher)
                    .into(backgroundImageView);
        }
    }

    private void setUpCastAdapter() {
        if (castAdapter == null) {
            castAdapter = new CastAdapter(this, castList);
            castRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            castRecyclerView.setAdapter(castAdapter);
            castRecyclerView.setNestedScrollingEnabled(false);
        }
    }

    private void setUpCrewAdapter() {
        if (crewAdapter == null) {
            crewAdapter = new CrewAdapter(this, crewList);
            crewRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            crewRecyclerView.setAdapter(crewAdapter);
            crewRecyclerView.setNestedScrollingEnabled(false);
        }
    }

    private void setUpTrailersAdapter() {
        if (trailersAdapter == null) {
            trailersAdapter = new TrailersAdapter(this, trailerList);
            trailersRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            trailersRecyclerView.setAdapter(trailersAdapter);
            trailersRecyclerView.setNestedScrollingEnabled(false);
        }
    }

    private void setUpSimilarMoviesAdapter() {
        if (similarMoviesAdapter == null) {
            similarMoviesAdapter = new MoviesAdapter(this, similarMoviesList);
            similarMoviesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            similarMoviesRecyclerView.setAdapter(similarMoviesAdapter);
            similarMoviesRecyclerView.setNestedScrollingEnabled(false);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movie_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save) {
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
