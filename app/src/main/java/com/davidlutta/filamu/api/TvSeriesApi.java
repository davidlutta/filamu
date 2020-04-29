package com.davidlutta.filamu.api;

import com.davidlutta.filamu.models.series.SeriesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TvSeriesApi {
    @GET("/3/tv/airing_today")
    Call<SeriesResponse> getSeriesAiringToday(@Query("api_key") String key, @Query("page") String page);

    @GET("/3/tv/on_the_air")
    Call<SeriesResponse> getSeriesOnAir(@Query("api_key") String key, @Query("page") String page);

    @GET("/3/tv/popular")
    Call<SeriesResponse> getPopularSeries(@Query("api_key") String key, @Query("page") String page);
}
