package com.davidlutta.filamu.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.davidlutta.filamu.models.series.Series;
import com.davidlutta.filamu.models.show.Show;
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
}
