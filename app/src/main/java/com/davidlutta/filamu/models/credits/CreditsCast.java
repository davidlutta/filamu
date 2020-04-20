
package com.davidlutta.filamu.models.credits;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CreditsCast implements Serializable, Parcelable {

    public final static Creator<CreditsCast> CREATOR = new Creator<CreditsCast>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CreditsCast createFromParcel(Parcel in) {
            return new CreditsCast(in);
        }

        public CreditsCast[] newArray(int size) {
            return (new CreditsCast[size]);
        }

    };
    private final static long serialVersionUID = -9062185948348404950L;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("character")
    @Expose
    private String character;
    @SerializedName("original_title")
    @Expose
    private String originalTitle;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("vote_count")
    @Expose
    private Integer voteCount;
    @SerializedName("video")
    @Expose
    private Boolean video;
    @SerializedName("media_type")
    @Expose
    private String mediaType;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("popularity")
    @Expose
    private Float popularity;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;
    @SerializedName("genre_ids")
    @Expose
    private List<Integer> genreIds = null;
    @SerializedName("vote_average")
    @Expose
    private Float voteAverage;
    @SerializedName("adult")
    @Expose
    private Boolean adult;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("credit_id")
    @Expose
    private String creditId;
    @SerializedName("episode_count")
    @Expose
    private Integer episodeCount;
    @SerializedName("origin_country")
    @Expose
    private List<String> originCountry = null;
    @SerializedName("original_name")
    @Expose
    private String originalName;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("first_air_date")
    @Expose
    private String firstAirDate;

    protected CreditsCast(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.character = ((String) in.readValue((String.class.getClassLoader())));
        this.originalTitle = ((String) in.readValue((String.class.getClassLoader())));
        this.overview = ((String) in.readValue((String.class.getClassLoader())));
        this.voteCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.video = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.mediaType = ((String) in.readValue((String.class.getClassLoader())));
        this.posterPath = ((String) in.readValue((String.class.getClassLoader())));
        this.backdropPath = ((String) in.readValue((String.class.getClassLoader())));
        this.popularity = ((Float) in.readValue((Float.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.originalLanguage = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.genreIds, (Integer.class.getClassLoader()));
        this.voteAverage = ((Float) in.readValue((Float.class.getClassLoader())));
        this.adult = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.releaseDate = ((String) in.readValue((String.class.getClassLoader())));
        this.creditId = ((String) in.readValue((String.class.getClassLoader())));
        this.episodeCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.originCountry, (String.class.getClassLoader()));
        this.originalName = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.firstAirDate = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public CreditsCast() {
    }

    /**
     * @param overview
     * @param voteAverage
     * @param releaseDate
     * @param episodeCount
     * @param mediaType
     * @param video
     * @param title
     * @param originalLanguage
     * @param genreIds
     * @param originalName
     * @param firstAirDate
     * @param character
     * @param creditId
     * @param originalTitle
     * @param popularity
     * @param originCountry
     * @param name
     * @param id
     * @param voteCount
     * @param backdropPath
     * @param adult
     * @param posterPath
     */
    public CreditsCast(Integer id, String character, String originalTitle, String overview, Integer voteCount, Boolean video, String mediaType, String posterPath, String backdropPath, Float popularity, String title, String originalLanguage, List<Integer> genreIds, Float voteAverage, Boolean adult, String releaseDate, String creditId, Integer episodeCount, List<String> originCountry, String originalName, String name, String firstAirDate) {
        super();
        this.id = id;
        this.character = character;
        this.originalTitle = originalTitle;
        this.overview = overview;
        this.voteCount = voteCount;
        this.video = video;
        this.mediaType = mediaType;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.popularity = popularity;
        this.title = title;
        this.originalLanguage = originalLanguage;
        this.genreIds = genreIds;
        this.voteAverage = voteAverage;
        this.adult = adult;
        this.releaseDate = releaseDate;
        this.creditId = creditId;
        this.episodeCount = episodeCount;
        this.originCountry = originCountry;
        this.originalName = originalName;
        this.name = name;
        this.firstAirDate = firstAirDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Float getPopularity() {
        return popularity;
    }

    public void setPopularity(Float popularity) {
        this.popularity = popularity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public Float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCreditId() {
        return creditId;
    }

    public void setCreditId(String creditId) {
        this.creditId = creditId;
    }

    public Integer getEpisodeCount() {
        return episodeCount;
    }

    public void setEpisodeCount(Integer episodeCount) {
        this.episodeCount = episodeCount;
    }

    public List<String> getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(List<String> originCountry) {
        this.originCountry = originCountry;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    @Override
    public String toString() {
        return "CreditsCast{" +
                "id=" + id +
                ", character='" + character + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                ", overview='" + overview + '\'' +
                ", voteCount=" + voteCount +
                ", video=" + video +
                ", mediaType='" + mediaType + '\'' +
                ", posterPath='" + posterPath + '\'' +
                ", backdropPath='" + backdropPath + '\'' +
                ", popularity=" + popularity +
                ", title='" + title + '\'' +
                ", originalLanguage='" + originalLanguage + '\'' +
                ", genreIds=" + genreIds +
                ", voteAverage=" + voteAverage +
                ", adult=" + adult +
                ", releaseDate='" + releaseDate + '\'' +
                ", creditId='" + creditId + '\'' +
                ", episodeCount=" + episodeCount +
                ", originCountry=" + originCountry +
                ", originalName='" + originalName + '\'' +
                ", name='" + name + '\'' +
                ", firstAirDate='" + firstAirDate + '\'' +
                '}';
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(character);
        dest.writeValue(originalTitle);
        dest.writeValue(overview);
        dest.writeValue(voteCount);
        dest.writeValue(video);
        dest.writeValue(mediaType);
        dest.writeValue(posterPath);
        dest.writeValue(backdropPath);
        dest.writeValue(popularity);
        dest.writeValue(title);
        dest.writeValue(originalLanguage);
        dest.writeList(genreIds);
        dest.writeValue(voteAverage);
        dest.writeValue(adult);
        dest.writeValue(releaseDate);
        dest.writeValue(creditId);
        dest.writeValue(episodeCount);
        dest.writeList(originCountry);
        dest.writeValue(originalName);
        dest.writeValue(name);
        dest.writeValue(firstAirDate);
    }

    public int describeContents() {
        return 0;
    }

}
