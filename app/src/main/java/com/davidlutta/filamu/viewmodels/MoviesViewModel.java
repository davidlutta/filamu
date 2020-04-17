package com.davidlutta.filamu.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.davidlutta.filamu.models.cast.Cast;
import com.davidlutta.filamu.models.cast.Crew;
import com.davidlutta.filamu.models.movie.Movie;
import com.davidlutta.filamu.models.movies.Movies;
import com.davidlutta.filamu.models.trailers.Trailers;
import com.davidlutta.filamu.repository.MoviesRepository;

import java.util.List;

public class MoviesViewModel extends ViewModel {
    private MoviesRepository mMoviesRepository;
    private boolean mIsViewingMovies;
    private boolean mIsPerformingQuery;

    public MoviesViewModel() {
        this.mMoviesRepository = MoviesRepository.getInstance();
        mIsPerformingQuery = false;
    }

    public LiveData<List<Movies>> getPopularMovies() {
        return mMoviesRepository.getPopularMovies();
    }

    public LiveData<List<Movies>> getMoviesPlayingNow() {
        return mMoviesRepository.getMoviesPlayingNow();
    }

    public LiveData<List<Movies>> getUpcomingMovies() {
        return mMoviesRepository.getUpcomingMovies();
    }

    public LiveData<Movie> getMovieDetails(String movieId) {
        return mMoviesRepository.getMovieDetails(movieId);
    }

    public LiveData<List<Cast>> getCastDetails(String movieId) {
        return mMoviesRepository.getCast(movieId);
    }

    public LiveData<List<Crew>> getCrewDetails(String movieId) {
        return mMoviesRepository.getCrew(movieId);
    }

    public LiveData<List<Trailers>> getTrailers(String movieId) {
        return mMoviesRepository.getMovieTrailers(movieId);
    }

    public void searchNextPage() {
        if (!mIsPerformingQuery && mIsViewingMovies) {
            mMoviesRepository.searchNextPage();
        }
    }

    public boolean isPerformingQuery() {
        return mIsPerformingQuery;
    }

    public void setIsPerformingQuery(boolean mIsPerformingQuery) {
        this.mIsPerformingQuery = mIsPerformingQuery;
    }

    public boolean isViewingMovies() {
        return mIsViewingMovies;
    }

    public void setIsViewingMovies(boolean mIsViewingMovies) {
        this.mIsViewingMovies = mIsViewingMovies;
    }
}
