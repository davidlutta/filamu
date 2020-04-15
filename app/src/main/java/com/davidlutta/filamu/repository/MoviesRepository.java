package com.davidlutta.filamu.repository;

import androidx.lifecycle.MutableLiveData;

import com.davidlutta.filamu.api.MoviesApi;
import com.davidlutta.filamu.api.RetrofitService;
import com.davidlutta.filamu.models.MovieResponse;
import com.davidlutta.filamu.models.Movies;
import com.davidlutta.filamu.util.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesRepository {
    private static MoviesRepository instance;
    private MoviesApi moviesApi;
    private int mPageNumber;

    private MoviesRepository() {
        moviesApi = RetrofitService.getMoviesApi();
    }

    public static MoviesRepository getInstance() {
        if (instance == null) {
            instance = new MoviesRepository();
        }
        return instance;
    }

    public MutableLiveData<List<Movies>> getMovies() {
        final MutableLiveData<List<Movies>> moviesData = new MutableLiveData<>();
        moviesApi.getNowPlayingMovies(Constants.API_KEY, "1").enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<Movies> movies = response.body().getMovies();
                        moviesData.postValue(movies);
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                System.out.println("NOTHING WAS RETURNED BRO !!");
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
