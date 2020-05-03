package com.davidlutta.filamu.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.davidlutta.filamu.database.tv.Series;
import com.davidlutta.filamu.repository.series.SavedSeriesRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class SavedTvSeriesViewModel extends AndroidViewModel {
    private LiveData<List<Series>> seriesList;
    private SavedSeriesRepository seriesRepository;

    public SavedTvSeriesViewModel(@NonNull Application application) {
        super(application);
        seriesRepository = new SavedSeriesRepository(application);
        seriesList = seriesRepository.getSavedSeries();
    }

    public LiveData<List<Series>> getSavedSeries() {
        return seriesList;
    }

    public Series getSavedSeries(int tvId) throws ExecutionException, InterruptedException {
        return seriesRepository.getSeries(tvId);
    }

    public void saveSeries(Series show) {
        seriesRepository.saveSeries(show);
    }

    public void deleteSeries(int tvId) {
        seriesRepository.deleteSeries(tvId);
    }
}
