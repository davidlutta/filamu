
package com.davidlutta.filamu.models.search;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KnownFor implements Serializable, Parcelable
{

    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("vote_count")
    @Expose
    private Integer voteCount;
    @SerializedName("video")
    @Expose
    private Boolean video;
    @SerializedName("media_type")
    @Expose
    private String mediaType;
    @SerializedName("vote_average")
    @Expose
    private Float voteAverage;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("genre_ids")
    @Expose
    private List<Integer> genreIds = null;
    @SerializedName("original_title")
    @Expose
    private String originalTitle;
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;
    @SerializedName("adult")
    @Expose
    private Boolean adult;
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("original_name")
    @Expose
    private String originalName;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("first_air_date")
    @Expose
    private String firstAirDate;
    @SerializedName("origin_country")
    @Expose
    private List<String> originCountry = null;
    public final static Creator<KnownFor> CREATOR = new Creator<KnownFor>() {


        @SuppressWarnings({
            "unchecked"
        })
        public KnownFor createFromParcel(Parcel in) {
            return new KnownFor(in);
        }

        public KnownFor[] newArray(int size) {
            return (new KnownFor[size]);
        }

    }
    ;
    private final static long serialVersionUID = 7374062273310265445L;

    protected KnownFor(Parcel in) {
        this.releaseDate = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.voteCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.video = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.mediaType = ((String) in.readValue((String.class.getClassLoader())));
        this.voteAverage = ((Float) in.readValue((Float.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.genreIds, (Integer.class.getClassLoader()));
        this.originalTitle = ((String) in.readValue((String.class.getClassLoader())));
        this.originalLanguage = ((String) in.readValue((String.class.getClassLoader())));
        this.adult = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.backdropPath = ((String) in.readValue((String.class.getClassLoader())));
        this.overview = ((String) in.readValue((String.class.getClassLoader())));
        this.posterPath = ((String) in.readValue((String.class.getClassLoader())));
        this.originalName = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.firstAirDate = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.originCountry, (String.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public KnownFor() {
    }

    /**
     * 
     * @param overview
     * @param voteAverage
     * @param releaseDate
     * @param mediaType
     * @param video
     * @param title
     * @param genreIds
     * @param originalLanguage
     * @param originalName
     * @param firstAirDate
     * @param originalTitle
     * @param name
     * @param originCountry
     * @param id
     * @param voteCount
     * @param backdropPath
     * @param adult
     * @param posterPath
     */
    public KnownFor(String releaseDate, Integer id, Integer voteCount, Boolean video, String mediaType, Float voteAverage, String title, List<Integer> genreIds, String originalTitle, String originalLanguage, Boolean adult, String backdropPath, String overview, String posterPath, String originalName, String name, String firstAirDate, List<String> originCountry) {
        super();
        this.releaseDate = releaseDate;
        this.id = id;
        this.voteCount = voteCount;
        this.video = video;
        this.mediaType = mediaType;
        this.voteAverage = voteAverage;
        this.title = title;
        this.genreIds = genreIds;
        this.originalTitle = originalTitle;
        this.originalLanguage = originalLanguage;
        this.adult = adult;
        this.backdropPath = backdropPath;
        this.overview = overview;
        this.posterPath = posterPath;
        this.originalName = originalName;
        this.name = name;
        this.firstAirDate = firstAirDate;
        this.originCountry = originCountry;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
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

    public List<String> getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(List<String> originCountry) {
        this.originCountry = originCountry;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(releaseDate);
        dest.writeValue(id);
        dest.writeValue(voteCount);
        dest.writeValue(video);
        dest.writeValue(mediaType);
        dest.writeValue(voteAverage);
        dest.writeValue(title);
        dest.writeList(genreIds);
        dest.writeValue(originalTitle);
        dest.writeValue(originalLanguage);
        dest.writeValue(adult);
        dest.writeValue(backdropPath);
        dest.writeValue(overview);
        dest.writeValue(posterPath);
        dest.writeValue(originalName);
        dest.writeValue(name);
        dest.writeValue(firstAirDate);
        dest.writeList(originCountry);
    }

    public int describeContents() {
        return  0;
    }

}
