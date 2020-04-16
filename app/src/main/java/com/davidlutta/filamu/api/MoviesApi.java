package com.davidlutta.filamu.api;

import com.davidlutta.filamu.models.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesApi {
    @GET("/3/movie/popular")
    Call<MovieResponse> getPopularMovies(@Query("api_key") String key, @Query("page") String page);

    @GET("/3/movie/now_playing")
    Call<MovieResponse> getMoviesPlayingNow(@Query("api_key") String key, @Query("page") String page);

    @GET("/3/movie/upcoming")
    Call<MovieResponse> getUpcomingMovies(@Query("api_key") String key, @Query("page") String page);
}
