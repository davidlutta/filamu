
package com.davidlutta.filamu.models.credits;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CreditsResponse implements Serializable, Parcelable {

    public final static Creator<CreditsResponse> CREATOR = new Creator<CreditsResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CreditsResponse createFromParcel(Parcel in) {
            return new CreditsResponse(in);
        }

        public CreditsResponse[] newArray(int size) {
            return (new CreditsResponse[size]);
        }

    };
    private final static long serialVersionUID = 1489680821529383759L;
    @SerializedName("cast")
    @Expose
    private List<CreditsCast> creditsCast = null;
    @SerializedName("crew")
    @Expose
    private List<CreditsCrew> creditsCrew = null;
    @SerializedName("id")
    @Expose
    private Integer id;

    protected CreditsResponse(Parcel in) {
        in.readList(this.creditsCast, (CreditsCast.class.getClassLoader()));
        in.readList(this.creditsCrew, (CreditsCrew.class.getClassLoader()));
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public CreditsResponse() {
    }

    /**
     * @param creditsCast
     * @param id
     * @param creditsCrew
     */
    public CreditsResponse(List<CreditsCast> creditsCast, List<CreditsCrew> creditsCrew, Integer id) {
        super();
        this.creditsCast = creditsCast;
        this.creditsCrew = creditsCrew;
        this.id = id;
    }

    public List<CreditsCast> getCreditsCast() {
        return creditsCast;
    }

    public void setCreditsCast(List<CreditsCast> creditsCast) {
        this.creditsCast = creditsCast;
    }

    public List<CreditsCrew> getCreditsCrew() {
        return creditsCrew;
    }

    public void setCreditsCrew(List<CreditsCrew> creditsCrew) {
        this.creditsCrew = creditsCrew;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(creditsCast);
        dest.writeList(creditsCrew);
        dest.writeValue(id);
    }

    public int describeContents() {
        return 0;
    }

}
