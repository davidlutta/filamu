
package com.davidlutta.filamu.models.credits;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CreditsCrew implements Serializable, Parcelable {

    public final static Creator<CreditsCrew> CREATOR = new Creator<CreditsCrew>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CreditsCrew createFromParcel(Parcel in) {
            return new CreditsCrew(in);
        }

        public CreditsCrew[] newArray(int size) {
            return (new CreditsCrew[size]);
        }

    };
    private final static long serialVersionUID = -1624387471270226352L;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("department")
    @Expose
    private String department;
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;
    @SerializedName("original_title")
    @Expose
    private String originalTitle;
    @SerializedName("job")
    @Expose
    private String job;
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
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("vote_average")
    @Expose
    private Float voteAverage;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("popularity")
    @Expose
    private Float popularity;
    @SerializedName("genre_ids")
    @Expose
    private List<Integer> genreIds = null;
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("adult")
    @Expose
    private Boolean adult;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("credit_id")
    @Expose
    private String creditId;

    protected CreditsCrew(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.department = ((String) in.readValue((String.class.getClassLoader())));
        this.originalLanguage = ((String) in.readValue((String.class.getClassLoader())));
        this.originalTitle = ((String) in.readValue((String.class.getClassLoader())));
        this.job = ((String) in.readValue((String.class.getClassLoader())));
        this.overview = ((String) in.readValue((String.class.getClassLoader())));
        this.voteCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.video = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.mediaType = ((String) in.readValue((String.class.getClassLoader())));
        this.releaseDate = ((String) in.readValue((String.class.getClassLoader())));
        this.voteAverage = ((Float) in.readValue((Float.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.popularity = ((Float) in.readValue((Float.class.getClassLoader())));
        in.readList(this.genreIds, (Integer.class.getClassLoader()));
        this.backdropPath = ((String) in.readValue((String.class.getClassLoader())));
        this.adult = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.posterPath = ((String) in.readValue((String.class.getClassLoader())));
        this.creditId = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public CreditsCrew() {
    }

    /**
     * @param overview
     * @param voteAverage
     * @param releaseDate
     * @param mediaType
     * @param video
     * @param originalLanguage
     * @param title
     * @param genreIds
     * @param creditId
     * @param originalTitle
     * @param popularity
     * @param id
     * @param voteCount
     * @param backdropPath
     * @param department
     * @param job
     * @param adult
     * @param posterPath
     */
    public CreditsCrew(Integer id, String department, String originalLanguage, String originalTitle, String job, String overview, Integer voteCount, Boolean video, String mediaType, String releaseDate, Float voteAverage, String title, Float popularity, List<Integer> genreIds, String backdropPath, Boolean adult, String posterPath, String creditId) {
        super();
        this.id = id;
        this.department = department;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.job = job;
        this.overview = overview;
        this.voteCount = voteCount;
        this.video = video;
        this.mediaType = mediaType;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
        this.title = title;
        this.popularity = popularity;
        this.genreIds = genreIds;
        this.backdropPath = backdropPath;
        this.adult = adult;
        this.posterPath = posterPath;
        this.creditId = creditId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
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

    public Float getPopularity() {
        return popularity;
    }

    public void setPopularity(Float popularity) {
        this.popularity = popularity;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getCreditId() {
        return creditId;
    }

    public void setCreditId(String creditId) {
        this.creditId = creditId;
    }

    @Override
    public String toString() {
        return "CreditsCrew{" +
                "id=" + id +
                ", department='" + department + '\'' +
                ", originalLanguage='" + originalLanguage + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                ", job='" + job + '\'' +
                ", overview='" + overview + '\'' +
                ", voteCount=" + voteCount +
                ", video=" + video +
                ", mediaType='" + mediaType + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", voteAverage=" + voteAverage +
                ", title='" + title + '\'' +
                ", popularity=" + popularity +
                ", genreIds=" + genreIds +
                ", backdropPath='" + backdropPath + '\'' +
                ", adult=" + adult +
                ", posterPath='" + posterPath + '\'' +
                ", creditId='" + creditId + '\'' +
                '}';
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(department);
        dest.writeValue(originalLanguage);
        dest.writeValue(originalTitle);
        dest.writeValue(job);
        dest.writeValue(overview);
        dest.writeValue(voteCount);
        dest.writeValue(video);
        dest.writeValue(mediaType);
        dest.writeValue(releaseDate);
        dest.writeValue(voteAverage);
        dest.writeValue(title);
        dest.writeValue(popularity);
        dest.writeList(genreIds);
        dest.writeValue(backdropPath);
        dest.writeValue(adult);
        dest.writeValue(posterPath);
        dest.writeValue(creditId);
    }

    public int describeContents() {
        return 0;
    }

}
