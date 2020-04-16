package com.davidlutta.filamu.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.davidlutta.filamu.api.MoviesApi;
import com.davidlutta.filamu.api.RetrofitService;
import com.davidlutta.filamu.models.movie.Movie;
import com.davidlutta.filamu.models.movies.MovieResponse;
import com.davidlutta.filamu.models.movies.Movies;
import com.davidlutta.filamu.util.Constants;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesRepository {
    private static MoviesRepository instance;
    private MoviesApi moviesApi;
    private int mPageNumber;
    private static final String TAG = "MoviesRepository";

    private MoviesRepository() {
        moviesApi = RetrofitService.getMoviesApi();
    }

    public static MoviesRepository getInstance() {
        if (instance == null) {
            instance = new MoviesRepository();
        }
        return instance;
    }

    public MutableLiveData<List<Movies>> getPopularMovies() {
        final MutableLiveData<List<Movies>> moviesData = new MutableLiveData<>();
        moviesApi.getPopularMovies(Constants.API_KEY, "1").enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<Movies> movies = response.body().getMovies();
                        Collections.sort(movies, Movies.BY_RATING);
                        Collections.reverse(movies);
                        moviesData.postValue(movies);
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d(TAG, "GetPopularMovies onFailure: FAILED TO FETCH MOVIES FROM API");
            }
        });
        return moviesData;
    }

    public MutableLiveData<List<Movies>> getMoviesPlayingNow() {
        final MutableLiveData<List<Movies>> moviesData = new MutableLiveData<>();
        moviesApi.getMoviesPlayingNow(Constants.API_KEY,"1").enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<Movies> movies = response.body().getMovies();
                        Collections.sort(movies, Movies.BY_RATING);
                        Collections.reverse(movies);
                        moviesData.postValue(movies);
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d(TAG, "GetPopularMovies onFailure: FAILED TO FETCH MOVIES FROM API");
            }
        });
        return moviesData;
    }

    public MutableLiveData<List<Movies>> getUpcomingMovies() {
        final MutableLiveData<List<Movies>> moviesData = new MutableLiveData<>();
        moviesApi.getUpcomingMovies(Constants.API_KEY,"1").enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<Movies> movies = response.body().getMovies();
                        Collections.sort(movies, Movies.BY_RATING);
                        Collections.reverse(movies);
                        moviesData.postValue(movies);
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d(TAG, "GetUpcomingMovies onFailure: FAILURE TO FETCH MOVIES");
            }
        });
        return moviesData;
    }

    public MutableLiveData<Movie> getMovieDetails(String movieId) {
        final MutableLiveData<Movie> movieData = new MutableLiveData<>();
        moviesApi.getMovieDetails(movieId, Constants.API_KEY).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        movieData.postValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });
        return movieData;
    }

    // TODO: 4/16/20 LOOK AT MEE TOO PLEASE !!!
    public void getMoviesApi(int pageNumber) {
        if (pageNumber == 0) {
            pageNumber = 1;
        }
        mPageNumber = pageNumber;
//        moviesApiClient.getMoviesApi(pageNumber);
    }

    public void searchNextPage() {
        getMoviesApi(mPageNumber + 1);
    }


}
