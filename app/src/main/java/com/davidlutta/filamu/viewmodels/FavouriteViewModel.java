package com.davidlutta.filamu.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.davidlutta.filamu.database.AppDatabase;
import com.davidlutta.filamu.database.movies.Movie;
import com.davidlutta.filamu.database.movies.MovieDao;

import java.util.List;

public class FavouriteViewModel extends AndroidViewModel {
    private MovieDao movieDao;

    public FavouriteViewModel(@NonNull Application application) {
        super(application);
        movieDao = AppDatabase.getInstance(application).movieDao();
        movieDao.loadAllSavedMovies();
    }


    public void insert(Movie movie) {
        movieDao.insertMovie(movie);
    }

    public void deleteMovie(Movie movie) {
        movieDao.deleteMovieById(movie.getMovieId());
    }
}
