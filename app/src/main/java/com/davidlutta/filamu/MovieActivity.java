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
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.davidlutta.filamu.adapters.cast.CastAdapter;
import com.davidlutta.filamu.adapters.cast.OnCastClickListener;
import com.davidlutta.filamu.models.cast.Cast;
import com.davidlutta.filamu.models.movie.Movie;
import com.davidlutta.filamu.util.Constants;
import com.davidlutta.filamu.viewmodels.MoviesViewModel;

import java.util.List;
import java.util.StringJoiner;

public class MovieActivity extends AppCompatActivity implements OnCastClickListener {

    private Toolbar toolbar;
    private MoviesViewModel moviesViewModel;

    private TextView titleTextView;
    private TextView genreTextView;
    private TextView ratingTextView;
    private TextView overviewTextView;
    private ImageView backgroundImageView;

    private RecyclerView castRecyclerView;
    private List<Cast> castList;
    private CastAdapter castAdapter;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        setTitle("");
        titleTextView = findViewById(R.id.titleTextView);
        genreTextView = findViewById(R.id.genreTextView);
        ratingTextView = findViewById(R.id.ratingTextView);
        overviewTextView = findViewById(R.id.overviewTextView);
        backgroundImageView = findViewById(R.id.backgroundImageView);
        castRecyclerView = findViewById(R.id.castRecyclerView);
        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        subscribeObservers();
    }

    private void subscribeObservers() {
        if (getIntent().hasExtra("id")) {
//            String id = Objects.requireNonNull(getIntent().getExtras()).getString("id");
            String id = getIntent().getExtras().getString("id");
            moviesViewModel.getMovieDetails(id).observe(this, new Observer<Movie>() {
                @Override
                public void onChanged(Movie movie) {
                    populateData(movie);
                }
            });
            moviesViewModel.getCastDetails(id).observe(this, new Observer<List<Cast>>() {
                @Override
                public void onChanged(List<Cast> casts) {
                    castList = casts;
                    setUpCastAdapter();
                }
            });
        }
    }

    @SuppressLint("SetTextI18n")
    private void populateData(Movie movie) {
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

    private void setUpCastAdapter() {
        if (castAdapter == null) {
            castAdapter = new CastAdapter(this, castList, this);
            castRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            castRecyclerView.setAdapter(castAdapter);
            castRecyclerView.setNestedScrollingEnabled(false);
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

    @Override
    public void onCastClicked(int position) {
        Cast selectedCast = castAdapter.getSelectedCast(position);
        Toast.makeText(this, selectedCast.getName(), Toast.LENGTH_SHORT).show();
    }
}
