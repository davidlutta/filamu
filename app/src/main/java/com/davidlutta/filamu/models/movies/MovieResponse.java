
package com.davidlutta.filamu.models.movies;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MovieResponse implements Serializable, Parcelable
{

    @SerializedName("page")
    @Expose
    private Long page;
    @SerializedName("total_results")
    @Expose
    private Long totalResults;
    @SerializedName("total_pages")
    @Expose
    private Long totalPages;
    @SerializedName("results")
    @Expose
    private List<Movies> movies = null;
    public final static Parcelable.Creator<MovieResponse> CREATOR = new Creator<MovieResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public MovieResponse createFromParcel(Parcel in) {
            return new MovieResponse(in);
        }

        public MovieResponse[] newArray(int size) {
            return (new MovieResponse[size]);
        }

    }
    ;
    private final static long serialVersionUID = 6363815541159170065L;

    protected MovieResponse(Parcel in) {
        this.page = ((Long) in.readValue((Long.class.getClassLoader())));
        this.totalResults = ((Long) in.readValue((Long.class.getClassLoader())));
        this.totalPages = ((Long) in.readValue((Long.class.getClassLoader())));
        in.readList(this.movies, (Movies.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public MovieResponse() {
    }

    /**
     * 
     * @param totalResults
     * @param totalPages
     * @param page
     * @param movies
     */
    public MovieResponse(Long page, Long totalResults, Long totalPages, List<Movies> movies) {
        super();
        this.page = page;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
        this.movies = movies;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Long totalResults) {
        this.totalResults = totalResults;
    }

    public Long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
    }

    public List<Movies> getMovies() {
        return movies;
    }

    public void setMovies(List<Movies> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "Movies{" +
                "page=" + page +
                ", totalResults=" + totalResults +
                ", totalPages=" + totalPages +
                ", results=" + movies +
                '}';
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(page);
        dest.writeValue(totalResults);
        dest.writeValue(totalPages);
        dest.writeList(movies);
    }

    public int describeContents() {
        return  0;
    }

}
