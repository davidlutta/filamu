package com.davidlutta.filamu.UI.popularmovies.datasource;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.davidlutta.filamu.api.MoviesApi;
import com.davidlutta.filamu.api.RetrofitService;
import com.davidlutta.filamu.models.movies.MovieResponse;
import com.davidlutta.filamu.models.movies.Movies;
import com.davidlutta.filamu.util.Constants;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesDataSource extends PageKeyedDataSource<Integer, Movies> {
    public static final String TAG = "MoviesDataSource";
    private MoviesApi moviesApi;

    public MoviesDataSource() {
        moviesApi = RetrofitService.getMoviesApi();
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Movies> callback) {
        final int page = 1;
        moviesApi.getPopularMovies(Constants.API_KEY, String.valueOf(page))
        .enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<Movies> moviesList = response.body().getMovies();
                        callback.onResult(moviesList, null, page + 1);
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d(TAG, "LoadInitial: onFailure: FAILED TO RETRIEVE MOVIES");
            }
        });

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movies> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Movies> callback) {
        final int page = params.key;
        moviesApi.getPopularMovies(Constants.API_KEY, String.valueOf(page))
                .enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                List<Movies> moviesList = response.body().getMovies();
                                callback.onResult(moviesList, page + 1);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieResponse> call, Throwable t) {
                        Log.d(TAG, "LoadAfter: onFailure: FAILED TO RETRIEVE MOVIES");
                    }
                });
    }
}
