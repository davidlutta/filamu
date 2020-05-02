package com.davidlutta.filamu.UI.series.similarSeries.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class SimilarSeriesViewModelFactory implements ViewModelProvider.Factory {
    private Application application;
    private String tvId;

    public SimilarSeriesViewModelFactory(Application application, String tvId) {
        this.application = application;
        this.tvId = tvId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SimilarSeriesViewModel(application, tvId);
    }
}
