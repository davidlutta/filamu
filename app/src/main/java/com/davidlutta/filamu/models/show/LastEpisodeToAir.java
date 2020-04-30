
package com.davidlutta.filamu.models.show;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LastEpisodeToAir implements Serializable, Parcelable
{

    @SerializedName("air_date")
    @Expose
    private String airDate;
    @SerializedName("episode_number")
    @Expose
    private Integer episodeNumber;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("production_code")
    @Expose
    private String productionCode;
    @SerializedName("season_number")
    @Expose
    private Integer seasonNumber;
    @SerializedName("show_id")
    @Expose
    private Integer showId;
    @SerializedName("still_path")
    @Expose
    private String stillPath;
    @SerializedName("vote_average")
    @Expose
    private Float voteAverage;
    @SerializedName("vote_count")
    @Expose
    private Integer voteCount;
    public final static Creator<LastEpisodeToAir> CREATOR = new Creator<LastEpisodeToAir>() {


        @SuppressWarnings({
            "unchecked"
        })
        public LastEpisodeToAir createFromParcel(Parcel in) {
            return new LastEpisodeToAir(in);
        }

        public LastEpisodeToAir[] newArray(int size) {
            return (new LastEpisodeToAir[size]);
        }

    }
    ;
    private final static long serialVersionUID = -2417756965105156274L;

    protected LastEpisodeToAir(Parcel in) {
        this.airDate = ((String) in.readValue((String.class.getClassLoader())));
        this.episodeNumber = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.overview = ((String) in.readValue((String.class.getClassLoader())));
        this.productionCode = ((String) in.readValue((String.class.getClassLoader())));
        this.seasonNumber = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.showId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.stillPath = ((String) in.readValue((String.class.getClassLoader())));
        this.voteAverage = ((Float) in.readValue((Float.class.getClassLoader())));
        this.voteCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public LastEpisodeToAir() {
    }

    /**
     * 
     * @param overview
     * @param productionCode
     * @param showId
     * @param voteAverage
     * @param airDate
     * @param name
     * @param stillPath
     * @param id
     * @param seasonNumber
     * @param voteCount
     * @param episodeNumber
     */
    public LastEpisodeToAir(String airDate, Integer episodeNumber, Integer id, String name, String overview, String productionCode, Integer seasonNumber, Integer showId, String stillPath, Float voteAverage, Integer voteCount) {
        super();
        this.airDate = airDate;
        this.episodeNumber = episodeNumber;
        this.id = id;
        this.name = name;
        this.overview = overview;
        this.productionCode = productionCode;
        this.seasonNumber = seasonNumber;
        this.showId = showId;
        this.stillPath = stillPath;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
    }

    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

    public Integer getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(Integer episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getProductionCode() {
        return productionCode;
    }

    public void setProductionCode(String productionCode) {
        this.productionCode = productionCode;
    }

    public Integer getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(Integer seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public Integer getShowId() {
        return showId;
    }

    public void setShowId(Integer showId) {
        this.showId = showId;
    }

    public String getStillPath() {
        return stillPath;
    }

    public void setStillPath(String stillPath) {
        this.stillPath = stillPath;
    }

    public Float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(airDate);
        dest.writeValue(episodeNumber);
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(overview);
        dest.writeValue(productionCode);
        dest.writeValue(seasonNumber);
        dest.writeValue(showId);
        dest.writeValue(stillPath);
        dest.writeValue(voteAverage);
        dest.writeValue(voteCount);
    }

    public int describeContents() {
        return  0;
    }

}
