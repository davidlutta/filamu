package com.davidlutta.filamu.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.davidlutta.filamu.database.AppDatabase;
import com.davidlutta.filamu.database.movies.Movie;
import com.davidlutta.filamu.database.movies.MovieDao;

import java.util.List;

public class SavedMoviesViewModel extends AndroidViewModel {
    private LiveData<List<Movie>> moviesList;
    private MovieDao movieDao;

    public SavedMoviesViewModel(@NonNull Application application) {
        super(application);
        movieDao = AppDatabase.getInstance(application).movieDao();
        moviesList = movieDao.loadAllSavedMovies();
    }

    public LiveData<List<Movie>> getSavedMovies() {
        return moviesList;
    }

    public Movie getSavedMovie(int movieId) {
        return movieDao.loadSavedMovie(movieId);
    }
}
