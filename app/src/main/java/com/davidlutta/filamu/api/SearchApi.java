package com.davidlutta.filamu.api;

import com.davidlutta.filamu.models.movies.MovieResponse;
import com.davidlutta.filamu.models.search.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchApi {
    @GET("/3/search/multi")
    Call<SearchResponse> search(@Query("api_key") String apiKey, @Query("query") String query);
}
