package com.davidlutta.filamu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
import com.davidlutta.filamu.UI.series.ViewAllSeriesHolderActivity;
import com.davidlutta.filamu.adapters.cast.CastAdapter;
import com.davidlutta.filamu.adapters.crew.CrewAdapter;
import com.davidlutta.filamu.adapters.productionCompanies.ProductionCompanyAdapter;
import com.davidlutta.filamu.adapters.series.SeriesAdapter;
import com.davidlutta.filamu.adapters.trailers.TrailersAdapter;
import com.davidlutta.filamu.models.cast.Cast;
import com.davidlutta.filamu.models.cast.Crew;
import com.davidlutta.filamu.models.series.Series;
import com.davidlutta.filamu.models.show.Genre;
import com.davidlutta.filamu.models.show.ProductionCompany;
import com.davidlutta.filamu.models.show.Show;
import com.davidlutta.filamu.models.trailers.Trailer;
import com.davidlutta.filamu.util.Constants;
import com.davidlutta.filamu.viewmodels.SavedTvSeriesViewModel;
import com.davidlutta.filamu.viewmodels.TvViewModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.concurrent.ExecutionException;

import es.dmoral.toasty.Toasty;

public class SeriesActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {
    private ImageView backgroundImageView;
    private TextView titleTextView;
    private TextView genreTextView;
    private TextView ratingTextView;
    private TextView overviewTextView;
    private TextView firstEpisodeTextView;
    private TextView numOfSeasonsTextView;
    private TextView runtimeTextView;
    private TextView statusTextView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView productionCompaniesRecyclerView;

    private Show currentSeries;
    private TvViewModel mViewModel;

    private List<ProductionCompany> productionCompaniesList;
    private ProductionCompanyAdapter productionCompanyAdapter;

    private List<Cast> castList;
    private CastAdapter castAdapter;
    private RecyclerView castRecyclerView;

    private List<Crew> crewList;
    private CrewAdapter crewAdapter;
    private RecyclerView crewRecyclerView;

    private List<Trailer> trailerList;
    private TrailersAdapter trailersAdapter;
    private RecyclerView trailersRecyclerView;

    private List<Series> similarSeriesList;
    private RecyclerView similarSeriesRecyclerView;
    private SeriesAdapter seriesAdapter;

    private Button similarShowsButton;
    private Button saveButton;
    private Button viewAllCast;
    private Button viewAllCrew;

    private ProgressBar productionCompaniesProgressBar;
    private ProgressBar castProgressBar;
    private ProgressBar crewProgressBar;
    private ProgressBar trailerProgressBar;
    private ProgressBar similarProgressBar;

    private SavedTvSeriesViewModel savedTvSeriesViewModel;

    @SuppressLint("CutPasteId")
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
        firstEpisodeTextView = findViewById(R.id.firstEpisodeTextView);
        numOfSeasonsTextView = findViewById(R.id.numOfSeasonsTextView);
        runtimeTextView = findViewById(R.id.runtimeTextView);
        statusTextView = findViewById(R.id.statusTextView);
        productionCompaniesRecyclerView = findViewById(R.id.productionCompaniesRecyclerView);
        castRecyclerView = findViewById(R.id.seriesActivityCastRecyclerView);
        crewRecyclerView = findViewById(R.id.seriesActivityCrewRecyclerView);
        trailersRecyclerView = findViewById(R.id.seriesActivityTrailerRecyclerView);
        similarSeriesRecyclerView = findViewById(R.id.seriesActivitySimilarRecyclerView);
        similarShowsButton = findViewById(R.id.seriesActivityViewAllSimilarSeriesButton);
        saveButton = findViewById(R.id.seriesSaveButton);
        viewAllCast = findViewById(R.id.seriesActivityViewAllCastButton);
        viewAllCrew = findViewById(R.id.seriesActivityViewAllCrewButton);
        productionCompaniesProgressBar = findViewById(R.id.prodCompProgressBar);
        castProgressBar = findViewById(R.id.seriesCastProgressBar);
        crewProgressBar = findViewById(R.id.seriesCrewProgressBar);
        trailerProgressBar = findViewById(R.id.seriesTrailerProgressBar);
        similarProgressBar = findViewById(R.id.similarSeriesProgressBar);

        swipeRefreshLayout.setOnRefreshListener(this);
        mViewModel = ViewModelProviders.of(this).get(TvViewModel.class);
        savedTvSeriesViewModel = ViewModelProviders.of(this).get(SavedTvSeriesViewModel.class);
        similarShowsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ViewAllSeriesHolderActivity.class);
                intent.putExtra("Category", "similarSeries");
                intent.putExtra("tvId", currentSeries.getId().toString());
                startActivity(intent);
            }
        });
        saveButton.setOnClickListener(this);
        viewAllCast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CreditsViewHolderActivity.class);
                intent.putExtra("Credits", "cast");
                intent.putExtra("Category", "Series");
                intent.putExtra("id", currentSeries.getId().toString());
                startActivity(intent);
            }
        });
        viewAllCrew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CreditsViewHolderActivity.class);
                intent.putExtra("Credits", "crew");
                intent.putExtra("Category", "Series");
                intent.putExtra("id", currentSeries.getId().toString());
                startActivity(intent);
            }
        });

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
            mViewModel.getSeriesCast(id).observe(this, new Observer<List<Cast>>() {
                @Override
                public void onChanged(List<Cast> casts) {
                    swipeRefreshLayout.setRefreshing(false);
                    castList = casts;
                    setUpCastAdapter();
                }
            });
            mViewModel.getSeriesCrew(id).observe(this, new Observer<List<Crew>>() {
                @Override
                public void onChanged(List<Crew> crews) {
                    swipeRefreshLayout.setRefreshing(false);
                    crewList = crews;
                    setUpCrewAdapter();
                }
            });
            mViewModel.getSeriesTrailers(id).observe(this, new Observer<List<Trailer>>() {
                @Override
                public void onChanged(List<Trailer> trailers) {
                    swipeRefreshLayout.setRefreshing(false);
                    trailerList = trailers;
                    setUpTrailersAdapter();
                }
            });
            mViewModel.getSimilarShows(id).observe(this, new Observer<List<Series>>() {
                @Override
                public void onChanged(List<Series> series) {
                    swipeRefreshLayout.setRefreshing(false);
                    similarSeriesList = series;
                    setUpSimilarShowsAdapter();
                }
            });
        }
    }

    private void populateData() {
        if (currentSeries != null) {
            productionCompaniesList = currentSeries.getProductionCompanies();
            titleTextView.setText(currentSeries.getOriginalName());
            String rating = currentSeries.getVoteAverage().toString() + " / 10";
            String poster = Constants.IMAGE_BASE_URL + currentSeries.getPosterPath();
            String genre = generateGenreString(currentSeries.getGenres());
            String runtime = "Runtime: " + currentSeries.getEpisodeRunTime() + " min";
            String numberOfSeasons = "Number of Seasons: " + currentSeries.getSeasons().size();
            String status = "Status: " + currentSeries.getStatus();
            String lastAirDate = currentSeries.getLastAirDate();
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
                DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);
                LocalDate ld = LocalDate.parse(currentSeries.getFirstAirDate(), dtf);
                LocalDate ld2 = LocalDate.parse(currentSeries.getLastAirDate(), dtf);
                String date = "First Episode: " + dtf2.format(ld) + "\nLast Episode: " + dtf2.format(ld2);
                firstEpisodeTextView.setText(date);
            }
            statusTextView.setText(status);
            firstEpisodeTextView.setText(String.format("First Episode: %s\nLast Episode: %s", currentSeries.getFirstAirDate(), lastAirDate));
            runtimeTextView.setText(runtime);
            numOfSeasonsTextView.setText(numberOfSeasons);
            ratingTextView.setText(rating);
            overviewTextView.setText(currentSeries.getOverview());
            genreTextView.setText(genre);
            backgroundImageView.setVisibility(View.VISIBLE);
            if (currentSeries.getPosterPath() != null) {
            Glide.with(this)
                    .load(poster)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .placeholder(R.drawable.person)
                    .into(backgroundImageView);
            }else {
                Glide.with(this)
                        .load(R.drawable.person)
                        .into(backgroundImageView);
            }
            setUpProductionCompanyAdapter();
            try {
                if (checkIfSaved()) {
                    saveButton.setText(R.string.saved);
                    saveButton.setBackgroundResource(R.drawable.green_rounded_button_background);
                }
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void setUpProductionCompanyAdapter() {
        if (productionCompanyAdapter == null) {
            productionCompanyAdapter = new ProductionCompanyAdapter(this, productionCompaniesList);
            productionCompaniesRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
            productionCompaniesRecyclerView.setAdapter(productionCompanyAdapter);
            productionCompaniesRecyclerView.setNestedScrollingEnabled(false);
            productionCompaniesRecyclerView.setVisibility(View.VISIBLE);
            productionCompaniesProgressBar.setVisibility(View.INVISIBLE);
        }
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

    private void setUpSimilarShowsAdapter() {
        if (seriesAdapter == null) {
            seriesAdapter = new SeriesAdapter(this, similarSeriesList);
            similarSeriesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            similarSeriesRecyclerView.setAdapter(seriesAdapter);
            similarSeriesRecyclerView.setNestedScrollingEnabled(false);
            similarSeriesRecyclerView.setVisibility(View.VISIBLE);
            similarProgressBar.setVisibility(View.INVISIBLE);
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

    private void saveShow() {
        String poster = Constants.IMAGE_BASE_URL + currentSeries.getPosterPath();
        com.davidlutta.filamu.database.tv.Series showToSave = new com.davidlutta.filamu.database.tv.Series(
                currentSeries.getId(),
                currentSeries.getOriginalName(),
                generateGenreString(currentSeries.getGenres()),
                currentSeries.getVoteAverage().toString(),
                poster, currentSeries.getOverview(),
                currentSeries.getFirstAirDate(),
                currentSeries.getLastAirDate(),
                currentSeries.getNumberOfSeasons().toString(),
                currentSeries.getStatus()
        );
        savedTvSeriesViewModel.saveSeries(showToSave);
    }

    private boolean checkIfSaved() throws ExecutionException, InterruptedException {
        com.davidlutta.filamu.database.tv.Series show = savedTvSeriesViewModel.getSavedSeries(currentSeries.getId());
        boolean bool = false;
        if (show != null) {
            bool = true;
        }
        return bool;
    }

    @Override
    public void onRefresh() {
        subscribeViewModel();
    }

    @Override
    public void onClick(View v) {
        try {
            if (checkIfSaved()) {
                saveButton.setText(R.string.saved);
                saveButton.setBackgroundResource(R.drawable.green_rounded_button_background);
                savedTvSeriesViewModel.deleteSeries(currentSeries.getId());
                Toasty.error(this,"Removed Show",Toasty.LENGTH_SHORT,true).show();
                saveButton.setText(R.string.save);
                saveButton.setBackgroundResource(R.drawable.red_rounded_button_background);
            } else {
                saveShow();
                saveButton.setText(R.string.saved);
                saveButton.setBackgroundResource(R.drawable.green_rounded_button_background);
                Toasty.success(this, "Show Saved", Toasty.LENGTH_SHORT, true).show();
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
