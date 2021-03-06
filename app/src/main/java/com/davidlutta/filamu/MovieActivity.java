package com.davidlutta.filamu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.davidlutta.filamu.UI.credits.CreditsViewHolderActivity;
import com.davidlutta.filamu.UI.movies.similarmovies.ViewAllSimilarMoviesActivity;
import com.davidlutta.filamu.adapters.cast.CastAdapter;
import com.davidlutta.filamu.adapters.crew.CrewAdapter;
import com.davidlutta.filamu.adapters.movies.MoviesAdapter;
import com.davidlutta.filamu.adapters.trailers.TrailersAdapter;
import com.davidlutta.filamu.models.cast.Cast;
import com.davidlutta.filamu.models.cast.Crew;
import com.davidlutta.filamu.models.movie.Genre;
import com.davidlutta.filamu.models.movie.Movie;
import com.davidlutta.filamu.models.movies.Movies;
import com.davidlutta.filamu.models.trailers.Trailer;
import com.davidlutta.filamu.util.Constants;
import com.davidlutta.filamu.viewmodels.MoviesViewModel;
import com.davidlutta.filamu.viewmodels.SavedMoviesViewModel;

import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.ExecutionException;

import es.dmoral.toasty.Toasty;

public class MovieActivity extends AppCompatActivity implements View.OnClickListener {

    private MoviesViewModel moviesViewModel;

    private TextView titleTextView;
    private TextView genreTextView;
    private TextView ratingTextView;
    private TextView overviewTextView;
    private ImageView backgroundImageView;
    private Button viewAllSimilarMoviesTextView;

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

    private SavedMoviesViewModel favouriteViewModel;

    private SwipeRefreshLayout swipeRefreshLayout;

    private Button saveButton;
    private Button viewAllCastButton;
    private Button viewAllCrewButton;

    private ProgressBar castProgressBar;
    private ProgressBar crewProgressBar;
    private ProgressBar trailerProgressBar;
    private ProgressBar similarProgressBar;

    private Movie currentMovie;

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
        saveButton = findViewById(R.id.saveButton);
        viewAllSimilarMoviesTextView = findViewById(R.id.viewAllSimilarMoviesTextView);
        viewAllCastButton = findViewById(R.id.viewAllCastTextView);
        viewAllCrewButton = findViewById(R.id.viewAllCrewTextView);
        castProgressBar = findViewById(R.id.movieCastProgressBar);
        crewProgressBar = findViewById(R.id.crewProgressBar);
        trailerProgressBar = findViewById(R.id.trailersProgressBar);
        similarProgressBar = findViewById(R.id.similarMoviesProgressBar);
        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        favouriteViewModel = ViewModelProviders.of(this).get(SavedMoviesViewModel.class);
        subscribeObservers();
        saveButton.setOnClickListener(this);

        viewAllSimilarMoviesTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ViewAllSimilarMoviesActivity.class);
                intent.putExtra("movieId", currentMovie.getId().toString());
                startActivity(intent);
            }
        });

        viewAllCastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CreditsViewHolderActivity.class);
                intent.putExtra("Credits", "cast");
                intent.putExtra("Category", "Movies");
                intent.putExtra("id", currentMovie.getId().toString());
                startActivity(intent);
            }
        });

        viewAllCrewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CreditsViewHolderActivity.class);
                intent.putExtra("Credits", "crew");
                intent.putExtra("Category", "Movies");
                intent.putExtra("id", currentMovie.getId().toString());
                startActivity(intent);
            }
        });
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
                    currentMovie = movie;
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
            String genreString = generateGenreString(movie.getGenres());
            genreTextView.setText(genreString);

            backgroundImageView.setVisibility(View.VISIBLE);
            if (movie.getPosterPath()!=null) {
                Glide.with(this)
                        .load(poster)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .placeholder(R.drawable.poster)
                        .into(backgroundImageView);
            }else {
                Glide.with(this)
                        .load(R.drawable.poster)
                        .into(backgroundImageView);
            }
        }
        try {
            if (checkIfSaved()) {
                saveButton.setText(R.string.saved);
                saveButton.setBackgroundResource(R.drawable.green_rounded_button_background);
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
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

    private void setUpCastAdapter() {
        if (castAdapter == null) {
            castAdapter = new CastAdapter(this, castList);
            castRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            castRecyclerView.setAdapter(castAdapter);
            castRecyclerView.setNestedScrollingEnabled(false);
            castRecyclerView.setVisibility(View.VISIBLE);
            castProgressBar.setVisibility(View.INVISIBLE);
        }
    }

    private void setUpCrewAdapter() {
        if (crewAdapter == null) {
            crewAdapter = new CrewAdapter(this, crewList);
            crewRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            crewRecyclerView.setAdapter(crewAdapter);
            crewRecyclerView.setNestedScrollingEnabled(false);
            crewRecyclerView.setVisibility(View.VISIBLE);
            crewProgressBar.setVisibility(View.INVISIBLE);
        }
    }

    private void setUpTrailersAdapter() {
        if (trailersAdapter == null) {
            trailersAdapter = new TrailersAdapter(this, trailerList);
            trailersRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            trailersRecyclerView.setAdapter(trailersAdapter);
            trailersRecyclerView.setNestedScrollingEnabled(false);
            trailersRecyclerView.setVisibility(View.VISIBLE);
            trailerProgressBar.setVisibility(View.INVISIBLE);
        }
    }

    private void setUpSimilarMoviesAdapter() {
        if (similarMoviesAdapter == null) {
            similarMoviesAdapter = new MoviesAdapter(this, similarMoviesList);
            similarMoviesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            similarMoviesRecyclerView.setAdapter(similarMoviesAdapter);
            similarMoviesRecyclerView.setNestedScrollingEnabled(false);
            similarMoviesRecyclerView.setVisibility(View.VISIBLE);
            similarProgressBar.setVisibility(View.INVISIBLE);
        }
    }

    private void saveMovie(Movie movie) {
        String poster = Constants.IMAGE_BASE_URL + movie.getPosterPath();
        com.davidlutta.filamu.database.movies.Movie movieToSave =
                new com.davidlutta.filamu.database.movies.Movie(movie.getId().intValue(), movie.getTitle(), movie.getVoteAverage().toString(), generateGenreString(movie.getGenres()), movie.getOverview(), poster);
        favouriteViewModel.saveMovie(movieToSave);
    }

    private boolean checkIfSaved() throws ExecutionException, InterruptedException {
        com.davidlutta.filamu.database.movies.Movie movie = favouriteViewModel.getSavedMovie(currentMovie.getId().intValue());
        boolean bool = false;
        if (movie!=null){
            bool = true;
        }
        return bool;
    }


    @Override
    public void onClick(View v) {
        try {
            if (checkIfSaved()) {
                saveButton.setText(R.string.saved);
                saveButton.setBackgroundResource(R.drawable.green_rounded_button_background);
                favouriteViewModel.deleteSavedMovie(currentMovie.getId().intValue());
                Toasty.error(this,"Removed Movie",Toasty.LENGTH_SHORT,true).show();
                saveButton.setText(R.string.save);
                saveButton.setBackgroundResource(R.drawable.red_rounded_button_background);
            } else {
                saveMovie(currentMovie);
                saveButton.setText(R.string.saved);
                saveButton.setBackgroundResource(R.drawable.green_rounded_button_background);
                Toasty.success(this, "Movie Saved", Toasty.LENGTH_SHORT, true).show();
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
