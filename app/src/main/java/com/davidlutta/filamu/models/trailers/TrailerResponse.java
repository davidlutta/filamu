
package com.davidlutta.filamu.models.trailers;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TrailerResponse implements Serializable, Parcelable {

    public final static Creator<TrailerResponse> CREATOR = new Creator<TrailerResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public TrailerResponse createFromParcel(Parcel in) {
            return new TrailerResponse(in);
        }

        public TrailerResponse[] newArray(int size) {
            return (new TrailerResponse[size]);
        }

    };
    private final static long serialVersionUID = 6337272080688260281L;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("results")
    @Expose
    private List<Trailer> trailers = null;

    protected TrailerResponse(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.trailers, (Trailer.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     */
    public TrailerResponse() {
    }

    /**
     * @param id
     * @param trailers
     */
    public TrailerResponse(Integer id, List<Trailer> trailers) {
        super();
        this.id = id;
        this.trailers = trailers;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Trailer> getTrailers() {
        return trailers;
    }

    public void setTrailers(List<Trailer> trailers) {
        this.trailers = trailers;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeList(trailers);
    }

    public int describeContents() {
        return 0;
    }

}
