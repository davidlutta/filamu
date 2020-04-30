package com.davidlutta.filamu.api;

import com.davidlutta.filamu.models.series.SeriesResponse;
import com.davidlutta.filamu.models.show.Show;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TvSeriesApi {
    @GET("/3/tv/airing_today")
    Call<SeriesResponse> getSeriesAiringToday(@Query("api_key") String key, @Query("page") String page);

    @GET("/3/tv/on_the_air")
    Call<SeriesResponse> getSeriesOnAir(@Query("api_key") String key, @Query("page") String page);

    @GET("/3/tv/popular")
    Call<SeriesResponse> getPopularSeries(@Query("api_key") String key, @Query("page") String page);

    @GET("/3/tv/{tv_id}")
    Call<Show> getSeries(@Path("tv_id") String tvId, @Query("api_key") String key);
}
