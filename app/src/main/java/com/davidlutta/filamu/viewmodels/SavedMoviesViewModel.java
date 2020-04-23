package com.davidlutta.filamu.viewmodels;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.davidlutta.filamu.database.AppDatabase;
import com.davidlutta.filamu.database.movies.Movie;
import com.davidlutta.filamu.database.movies.MovieDao;
import com.davidlutta.filamu.repository.SavedMoviesRepository;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class SavedMoviesViewModel extends AndroidViewModel {
    private LiveData<List<Movie>> moviesList;
    private SavedMoviesRepository savedMoviesRepository;

    public SavedMoviesViewModel(@NonNull Application application) {
        super(application);
        savedMoviesRepository = new SavedMoviesRepository(application);
        moviesList = savedMoviesRepository.getAllSavedMovies();
    }

    public LiveData<List<Movie>> getSavedMovies() {
        return moviesList;
    }

    public Movie getSavedMovie(int movieId) throws ExecutionException, InterruptedException {
        return savedMoviesRepository.getSavedMovie(movieId);
    }

    public void saveMovie(Movie movie) { savedMoviesRepository.saveMovie(movie); }

    public void deleteSavedMovie(int movieId){
        savedMoviesRepository.deleteMovie(movieId);
    }
}
