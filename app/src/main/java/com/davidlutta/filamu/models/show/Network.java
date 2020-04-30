
package com.davidlutta.filamu.models.show;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Network implements Serializable, Parcelable
{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("logo_path")
    @Expose
    private String logoPath;
    @SerializedName("origin_country")
    @Expose
    private String originCountry;
    public final static Creator<Network> CREATOR = new Creator<Network>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Network createFromParcel(Parcel in) {
            return new Network(in);
        }

        public Network[] newArray(int size) {
            return (new Network[size]);
        }

    }
    ;
    private final static long serialVersionUID = -4241517037759649916L;

    protected Network(Parcel in) {
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.logoPath = ((String) in.readValue((String.class.getClassLoader())));
        this.originCountry = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Network() {
    }

    /**
     * 
     * @param logoPath
     * @param name
     * @param originCountry
     * @param id
     */
    public Network(String name, Integer id, String logoPath, String originCountry) {
        super();
        this.name = name;
        this.id = id;
        this.logoPath = logoPath;
        this.originCountry = originCountry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeValue(id);
        dest.writeValue(logoPath);
        dest.writeValue(originCountry);
    }

    public int describeContents() {
        return  0;
    }

}
