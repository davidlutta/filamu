
package com.davidlutta.filamu.models.cast;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CastResponse implements Serializable, Parcelable {

    public final static Creator<CastResponse> CREATOR = new Creator<CastResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CastResponse createFromParcel(Parcel in) {
            return new CastResponse(in);
        }

        public CastResponse[] newArray(int size) {
            return (new CastResponse[size]);
        }

    };
    private final static long serialVersionUID = 5543603185413068540L;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("cast")
    @Expose
    private List<Cast> cast = null;
    @SerializedName("crew")
    @Expose
    private List<Crew> crew = null;

    protected CastResponse(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.cast, (Cast.class.getClassLoader()));
        in.readList(this.crew, (com.davidlutta.filamu.models.cast.Crew.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     */
    public CastResponse() {
    }

    /**
     * @param cast
     * @param id
     * @param crew
     */
    public CastResponse(Integer id, List<Cast> cast, List<Crew> crew) {
        super();
        this.id = id;
        this.cast = cast;
        this.crew = crew;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

    public List<Crew> getCrew() {
        return crew;
    }

    public void setCrew(List<Crew> crew) {
        this.crew = crew;
    }

    @Override
    public String toString() {
        return "CastResponse{" +
                "id=" + id +
                ", cast=" + cast +
                ", crew=" + crew +
                '}';
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeList(cast);
        dest.writeList(crew);
    }

    public int describeContents() {
        return 0;
    }

}
