package com.davidlutta.filamu.api;

import com.davidlutta.filamu.models.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesApi {
    @GET("/now_playing")
    Call<MovieResponse> getNowPlayingMovies(@Query("api_key") String key, @Query("page") String page);

}
