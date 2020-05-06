package com.davidlutta.filamu.repository.search;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.davidlutta.filamu.api.RetrofitService;
import com.davidlutta.filamu.api.SearchApi;
import com.davidlutta.filamu.models.movie.Movie;
import com.davidlutta.filamu.models.movies.Movies;
import com.davidlutta.filamu.models.search.Result;
import com.davidlutta.filamu.models.search.SearchResponse;
import com.davidlutta.filamu.util.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchRepository {
    public static final String TAG = "SearchRepository";
    private SearchApi searchApi;
    private static SearchRepository instance;

    public SearchRepository() {
        searchApi = RetrofitService.getSearchApi();
    }

    public static SearchRepository getInstance() {
        if (instance == null) {
            instance = new SearchRepository();
        }
        return instance;
    }

    public MutableLiveData<List<Movies>> search(String query) {
        final MutableLiveData<List<Movies>> searchResults = new MutableLiveData<>();
        searchApi.search(Constants.API_KEY, query).enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<Movies> results = response.body().getMovies();
                        searchResults.postValue(results);
                    }
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: FAILED TO RETRIEVE RESULTS");
            }
        });
        return searchResults;
    }
}
