package com.davidlutta.filamu.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.davidlutta.filamu.models.cast.Cast;
import com.davidlutta.filamu.models.cast.Crew;
import com.davidlutta.filamu.models.series.Series;
import com.davidlutta.filamu.models.show.Show;
import com.davidlutta.filamu.models.trailers.Trailer;
import com.davidlutta.filamu.repository.series.SeriesRepository;

import java.util.List;

public class TvViewModel extends ViewModel {
    private SeriesRepository mSeriesRepository;

    public TvViewModel() {
        mSeriesRepository = SeriesRepository.getInstance();
    }

    public LiveData<List<Series>> getSeriesAiringToday() {
        return mSeriesRepository.getSeriesAiringToday();
    }

    public LiveData<List<Series>> getSeriesOnAir() {
        return mSeriesRepository.getSeriesOnAir();
    }

    public LiveData<List<Series>> getPopularSeries() {
        return mSeriesRepository.getPopularSeries();
    }

    public LiveData<Show> getSeries(String tvId) {
        return mSeriesRepository.getSeries(tvId);
    }

    public LiveData<List<Cast>> getSeriesCast(String tvId){
        return mSeriesRepository.getSeriesCast(tvId);
    }

    public LiveData<List<Crew>> getSeriesCrew(String tvId) {
        return mSeriesRepository.getSeriesCrew(tvId);
    }

    public LiveData<List<Trailer>> getSeriesTrailers(String tvId) {
        return mSeriesRepository.getSeriesTrailer(tvId);
    }

    public LiveData<List<Series>> getSimilarShows(String tvId) {
        return mSeriesRepository.getSimilarShows(tvId);
    }
}
