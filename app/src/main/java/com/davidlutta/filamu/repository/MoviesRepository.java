package com.davidlutta.filamu.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.davidlutta.filamu.api.MoviesApi;
import com.davidlutta.filamu.api.RetrofitService;
import com.davidlutta.filamu.models.MovieResponse;
import com.davidlutta.filamu.models.Movies;
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
