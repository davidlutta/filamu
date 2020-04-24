package com.davidlutta.filamu.UI.movies.similarmovies.datasource;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class MoviesDataSourceFactory extends DataSource.Factory {
    public static final String TAG = "MoviesDataSourceFactory";
    MoviesDataSource moviesDataSource;
    MutableLiveData<MoviesDataSource> mutableLiveData;
    private String movieId;

    public MoviesDataSourceFactory(String movieId) {
        this.movieId = movieId;
        mutableLiveData = new MutableLiveData<>();
    }

    @Override
    public DataSource create() {
        moviesDataSource = new MoviesDataSource(movieId);
        Log.d(TAG, "create: " + moviesDataSource);
        mutableLiveData.postValue(moviesDataSource);
        return moviesDataSource;
    }

    public MutableLiveData<MoviesDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
