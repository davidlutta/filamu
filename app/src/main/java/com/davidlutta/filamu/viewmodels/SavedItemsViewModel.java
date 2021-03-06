package com.davidlutta.filamu.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.davidlutta.filamu.database.AppDatabase;
import com.davidlutta.filamu.database.movies.Movie;
import com.davidlutta.filamu.database.movies.MovieDao;

import java.util.List;

public class SavedItemsViewModel extends AndroidViewModel {
    private LiveData<List<Movie>> moviesList;
    private MovieDao movieDao;

    public SavedItemsViewModel(@NonNull Application application) {
        super(application);
        movieDao = AppDatabase.getInstance(application).movieDao();
        moviesList = movieDao.loadAllSavedMovies();
    }
    // TODO: Implement the ViewModel

    public LiveData<List<Movie>> getMovies() {
        return moviesList;
    }

    public void insert(Movie movie) {
        movieDao.insertMovie(movie);
    }

    public void deleteMovie(Movie movie) {
        movieDao.deleteMovieById(movie.getMovieId());
    }
}
