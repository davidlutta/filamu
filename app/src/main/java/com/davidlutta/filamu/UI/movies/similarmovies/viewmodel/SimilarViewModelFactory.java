package com.davidlutta.filamu.UI.movies.similarmovies.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class SimilarViewModelFactory implements ViewModelProvider.Factory {
    private Application application;
    private String movieId;

    public SimilarViewModelFactory(Application application, String movieId) {
        this.application = application;
        this.movieId = movieId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SimilarViewModel(application, movieId);
    }
}
