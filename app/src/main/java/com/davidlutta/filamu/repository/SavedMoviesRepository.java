package com.davidlutta.filamu.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.davidlutta.filamu.database.AppDatabase;
import com.davidlutta.filamu.database.movies.Movie;
import com.davidlutta.filamu.database.movies.MovieDao;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class SavedMoviesRepository {
    private MovieDao movieDao;
    private LiveData<List<Movie>> movieList;

    public SavedMoviesRepository(Application application) {
        movieDao = AppDatabase.getInstance(application).movieDao();
        movieList = movieDao.loadAllSavedMovies();
    }

    public LiveData<List<Movie>> getAllSavedMovies() {
        return movieList;
    }
    public Movie getSavedMovie(int movieId) throws ExecutionException, InterruptedException {
        return new GetMoveByIdAsyncTask(movieDao).execute(movieId).get();
    }

    public void saveMovie(Movie movie) {
        new InsertMovieAsyncTask(movieDao).execute(movie);
    }

    public void deleteMovie(int movieId) {
        new DeleteMovieAsyncTask(movieDao).execute(movieId);
    }

    private static class InsertMovieAsyncTask extends AsyncTask<Movie, Void, Void> {
        private MovieDao movieDao;

        public InsertMovieAsyncTask(MovieDao movieDao) {
            this.movieDao = movieDao;
        }

        @Override
        protected Void doInBackground(Movie... movies) {
            movieDao.insertMovie(movies[0]);
            return null;
        }
    }

    private static class DeleteMovieAsyncTask extends AsyncTask<Integer, Void, Void> {
        private MovieDao movieDao;

        public DeleteMovieAsyncTask(MovieDao movieDao) {
            this.movieDao = movieDao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            movieDao.deleteMovieById(integers[0]);
            return null;
        }
    }

    private static class GetMoveByIdAsyncTask extends AsyncTask<Integer, Void, Movie> {
        private MovieDao movieDao;

        public GetMoveByIdAsyncTask(MovieDao movieDao) {
            this.movieDao = movieDao;
        }

        @Override
        protected Movie doInBackground(Integer... integers) {
            return movieDao.loadSavedMovie(integers[0]);
        }
    }

}
