package com.davidlutta.filamu.database.tv;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface SeriesDao {
    @Query("SELECT * FROM savedShowsTable ORDER BY timestamp")
    LiveData<List<Series>> loadAllSavedSeries();

    @Query("SELECT * FROM savedShowsTable WHERE tvId =:tvId")
    Series loadSavedSeries(String tvId);

    @Insert
    void insertSeries(Series series);

    @Query("DELETE FROM savedShowsTable WHERE tvId = :tvId")
    void deleteMovieById(String tvId);
}
