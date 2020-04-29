
package com.davidlutta.filamu.models.series;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SeriesResponse implements Serializable, Parcelable
{

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("results")
    @Expose
    private List<Series> series = null;
    public final static Creator<SeriesResponse> CREATOR = new Creator<SeriesResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public SeriesResponse createFromParcel(Parcel in) {
            return new SeriesResponse(in);
        }

        public SeriesResponse[] newArray(int size) {
            return (new SeriesResponse[size]);
        }

    }
    ;
    private final static long serialVersionUID = -8864630954082897805L;

    protected SeriesResponse(Parcel in) {
        this.page = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalResults = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalPages = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.series, (Series.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public SeriesResponse() {
    }

    /**
     * 
     * @param totalResults
     * @param totalPages
     * @param page
     * @param series
     */
    public SeriesResponse(Integer page, Integer totalResults, Integer totalPages, List<Series> series) {
        super();
        this.page = page;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
        this.series = series;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<Series> getSeries() {
        return series;
    }

    public void setSeries(List<Series> series) {
        this.series = series;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(page);
        dest.writeValue(totalResults);
        dest.writeValue(totalPages);
        dest.writeList(series);
    }

    public int describeContents() {
        return  0;
    }

}
