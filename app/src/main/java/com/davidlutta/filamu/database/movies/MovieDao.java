package com.davidlutta.filamu.database.movies;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieDao {
    @Query("SELECT * FROM savedMoviesTable ORDER BY timestamp")
    LiveData<List<Movie>> loadAllSavedMovies();

    @Query("SELECT * FROM savedMoviesTable WHERE movieId = :movieId")
    Movie loadSavedMovie(int movieId);

    @Insert
    void insertMovie(Movie movie);

    @Delete
    void deleteMovie(Movie movie);

    @Query("DELETE FROM savedMoviesTable WHERE movieId =:movieId")
    void deleteMovieById(int movieId);

    @Query("DELETE FROM savedMoviesTable")
    void deleteTable();
}
