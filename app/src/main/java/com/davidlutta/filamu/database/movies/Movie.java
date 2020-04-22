package com.davidlutta.filamu.database.movies;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Timestamp;

@Entity(tableName = "savedMoviesTable")
public class Movie {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "movieId")
    private int movieId;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "rating")
    private String rating;

    @ColumnInfo(name = "genres")
    private String genres;

    @ColumnInfo(name = "overview")
    private String overview;

    @ColumnInfo(name = "poster")
    private String poster;

    @ColumnInfo(name = "timestamp")
    private long timestamp;

    public Movie(int movieId, String title, String rating, String genres, String overview, String poster) {
        this.movieId = movieId;
        this.title = title;
        this.rating = rating;
        this.genres = genres;
        this.overview = overview;
        this.poster = poster;
        this.timestamp = System.currentTimeMillis();
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String id) {
        this.poster = poster;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "poster=" + poster +
                ", movieId=" + movieId +
                ", title='" + title + '\'' +
                ", rating='" + rating + '\'' +
                ", genres='" + genres + '\'' +
                ", overview='" + overview + '\'' +
                '}';
    }
}
