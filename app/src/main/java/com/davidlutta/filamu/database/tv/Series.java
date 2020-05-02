package com.davidlutta.filamu.database.tv;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "savedShowsTable")
public class Series {
    @PrimaryKey
    @NonNull
    private String tvId;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "genre")
    private String genre;

    @ColumnInfo(name = "rating")
    private String rating;

    @ColumnInfo(name = "overview")
    private String overView;

    @ColumnInfo(name = "firstAirDate")
    private String firstAirDate;

    @ColumnInfo(name = "lastAirDate")
    private String lastAirDate;

    @ColumnInfo(name = "numOfSeasons")
    private String numOfSeasons;

    @ColumnInfo(name = "status")
    private String status;

    @ColumnInfo(name = "timestamp")
    private long timestamp;

    public Series(@NonNull String tvId, String title, String genre, String rating, String overView, String firstAirDate, String lastAirDate, String numOfSeasons, String status) {
        this.tvId = tvId;
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.overView = overView;
        this.firstAirDate = firstAirDate;
        this.lastAirDate = lastAirDate;
        this.numOfSeasons = numOfSeasons;
        this.status = status;
        this.timestamp = System.currentTimeMillis();
    }

    @NonNull
    public String getTvId() {
        return tvId;
    }

    public void setTvId(@NonNull String tvId) {
        this.tvId = tvId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getOverView() {
        return overView;
    }

    public void setOverView(String overView) {
        this.overView = overView;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public String getLastAirDate() {
        return lastAirDate;
    }

    public void setLastAirDate(String lastAirDate) {
        this.lastAirDate = lastAirDate;
    }

    public String getNumOfSeasons() {
        return numOfSeasons;
    }

    public void setNumOfSeasons(String numOfSeasons) {
        this.numOfSeasons = numOfSeasons;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Series{" +
                "tvId='" + tvId + '\'' +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", rating='" + rating + '\'' +
                ", overView='" + overView + '\'' +
                ", firstAirDate='" + firstAirDate + '\'' +
                ", lastAirDate='" + lastAirDate + '\'' +
                ", numOfSeasons='" + numOfSeasons + '\'' +
                ", status='" + status + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
