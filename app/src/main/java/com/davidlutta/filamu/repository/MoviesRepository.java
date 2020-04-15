package com.davidlutta.filamu.repository;

import androidx.lifecycle.LiveData;

import com.davidlutta.filamu.api.MoviesApi;
import com.davidlutta.filamu.api.MoviesApiClient;
import com.davidlutta.filamu.api.RetrofitService;
import com.davidlutta.filamu.models.Movies;

import java.util.List;

public class MoviesRepository {
    private MoviesApi moviesApi;
    private MoviesApiClient moviesApiClient;
    private int mPageNumber;

    private MoviesRepository() {
        moviesApi = RetrofitService.getMoviesApi();
    }

    public LiveData<List<Movies>> getMovies() {
        return moviesApiClient.getMovies();
    }

    public void getMoviesApi(int pageNumber) {
        if (pageNumber == 0) {
            pageNumber = 1;
        }
        mPageNumber = pageNumber;
        moviesApiClient.getMoviesApi(pageNumber);
    }

    public void searchNextPage() {
        getMoviesApi(mPageNumber + 1);
    }

}
