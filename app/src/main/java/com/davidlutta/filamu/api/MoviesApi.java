package com.davidlutta.filamu.api;

import com.davidlutta.filamu.models.cast.CastResponse;
import com.davidlutta.filamu.models.movie.Movie;
import com.davidlutta.filamu.models.movies.MovieResponse;
import com.davidlutta.filamu.models.trailers.TrailerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviesApi {
    @GET("/3/movie/popular")
    Call<MovieResponse> getPopularMovies(@Query("api_key") String key, @Query("page") String page);

    @GET("/3/movie/now_playing")
    Call<MovieResponse> getMoviesPlayingNow(@Query("api_key") String key, @Query("page") String page);

    @GET("/3/movie/upcoming")
    Call<MovieResponse> getUpcomingMovies(@Query("api_key") String key, @Query("page") String page);

    @GET("/3/movie/{movie_id}")
    Call<Movie> getMovieDetails(@Path("movie_id") String movieId, @Query("api_key") String key);

    @GET("/3/movie/{movie_id}/credits")
    Call<CastResponse> getCredits(@Path("movie_id") String movieId, @Query("api_key") String key);

    @GET("/3/movie/{movie_id}/videos")
    Call<TrailerResponse> getTrailers(@Path("movie_id") String movieId, @Query("api_key") String key);

}
