
package com.davidlutta.filamu.models.series;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Series implements Serializable, Parcelable
{

    @SerializedName("original_name")
    @Expose
    private String originalName;
    @SerializedName("genre_ids")
    @Expose
    private List<Integer> genreIds = null;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("popularity")
    @Expose
    private Float popularity;
    @SerializedName("origin_country")
    @Expose
    private List<String> originCountry = null;
    @SerializedName("vote_count")
    @Expose
    private Integer voteCount;
    @SerializedName("first_air_date")
    @Expose
    private String firstAirDate;
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("vote_average")
    @Expose
    private Float voteAverage;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    public final static Creator<Series> CREATOR = new Creator<Series>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Series createFromParcel(Parcel in) {
            return new Series(in);
        }

        public Series[] newArray(int size) {
            return (new Series[size]);
        }

    }
    ;
    private final static long serialVersionUID = -857177772041230521L;

    protected Series(Parcel in) {
        this.originalName = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.genreIds, (Integer.class.getClassLoader()));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.popularity = ((Float) in.readValue((Float.class.getClassLoader())));
        in.readList(this.originCountry, (String.class.getClassLoader()));
        this.voteCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.firstAirDate = ((String) in.readValue((String.class.getClassLoader())));
        this.backdropPath = ((String) in.readValue((String.class.getClassLoader())));
        this.originalLanguage = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.voteAverage = ((Float) in.readValue((Float.class.getClassLoader())));
        this.overview = ((String) in.readValue((String.class.getClassLoader())));
        this.posterPath = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Series() {
    }

    /**
     * 
     * @param overview
     * @param voteAverage
     * @param genreIds
     * @param originalLanguage
     * @param originalName
     * @param firstAirDate
     * @param popularity
     * @param name
     * @param originCountry
     * @param voteCount
     * @param backdropPath
     * @param id
     * @param posterPath
     */
    public Series(String originalName, List<Integer> genreIds, String name, Float popularity, List<String> originCountry, Integer voteCount, String firstAirDate, String backdropPath, String originalLanguage, Integer id, Float voteAverage, String overview, String posterPath) {
        super();
        this.originalName = originalName;
        this.genreIds = genreIds;
        this.name = name;
        this.popularity = popularity;
        this.originCountry = originCountry;
        this.voteCount = voteCount;
        this.firstAirDate = firstAirDate;
        this.backdropPath = backdropPath;
        this.originalLanguage = originalLanguage;
        this.id = id;
        this.voteAverage = voteAverage;
        this.overview = overview;
        this.posterPath = posterPath;
    }

    public static final Comparator<Series> BY_RATING = new Comparator<Series>() {
        @Override
        public int compare(Series o1, Series o2) {
            return o1.voteAverage.compareTo(o2.voteAverage);
        }
    };

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPopularity() {
        return popularity;
    }

    public void setPopularity(Float popularity) {
        this.popularity = popularity;
    }

    public List<String> getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(List<String> originCountry) {
        this.originCountry = originCountry;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Float voteAverage) {
        this.voteAverage = voteAverage;
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

    public static final DiffUtil.ItemCallback<Series> CALLBACK = new DiffUtil.ItemCallback<Series>() {
        @Override
        public boolean areItemsTheSame(@NonNull Series oldItem, @NonNull Series newItem) {
            return Objects.equals(oldItem.id, newItem.id);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Series oldItem, @NonNull Series newItem) {
            return Objects.equals(oldItem, newItem);
        }
    };


    @Override
    public String toString() {
        return "Series{" +
                "originalName='" + originalName + '\'' +
                ", genreIds=" + genreIds +
                ", name='" + name + '\'' +
                ", popularity=" + popularity +
                ", originCountry=" + originCountry +
                ", voteCount=" + voteCount +
                ", firstAirDate='" + firstAirDate + '\'' +
                ", backdropPath='" + backdropPath + '\'' +
                ", originalLanguage='" + originalLanguage + '\'' +
                ", id=" + id +
                ", voteAverage=" + voteAverage +
                ", overview='" + overview + '\'' +
                ", posterPath='" + posterPath + '\'' +
                '}';
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(originalName);
        dest.writeList(genreIds);
        dest.writeValue(name);
        dest.writeValue(popularity);
        dest.writeList(originCountry);
        dest.writeValue(voteCount);
        dest.writeValue(firstAirDate);
        dest.writeValue(backdropPath);
        dest.writeValue(originalLanguage);
        dest.writeValue(id);
        dest.writeValue(voteAverage);
        dest.writeValue(overview);
        dest.writeValue(posterPath);
    }

    public int describeContents() {
        return  0;
    }

}
