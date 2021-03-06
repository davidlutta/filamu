
package com.davidlutta.filamu.models.show;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreatedBy implements Serializable, Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("credit_id")
    @Expose
    private String creditId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("gender")
    @Expose
    private Integer gender;
    @SerializedName("profile_path")
    @Expose
    private Object profilePath;
    public final static Creator<CreatedBy> CREATOR = new Creator<CreatedBy>() {


        @SuppressWarnings({
            "unchecked"
        })
        public CreatedBy createFromParcel(Parcel in) {
            return new CreatedBy(in);
        }

        public CreatedBy[] newArray(int size) {
            return (new CreatedBy[size]);
        }

    }
    ;
    private final static long serialVersionUID = -5360925358836471464L;

    protected CreatedBy(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.creditId = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.gender = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.profilePath = ((Object) in.readValue((Object.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public CreatedBy() {
    }

    /**
     * 
     * @param creditId
     * @param gender
     * @param name
     * @param id
     * @param profilePath
     */
    public CreatedBy(Integer id, String creditId, String name, Integer gender, Object profilePath) {
        super();
        this.id = id;
        this.creditId = creditId;
        this.name = name;
        this.gender = gender;
        this.profilePath = profilePath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreditId() {
        return creditId;
    }

    public void setCreditId(String creditId) {
        this.creditId = creditId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Object getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(Object profilePath) {
        this.profilePath = profilePath;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(creditId);
        dest.writeValue(name);
        dest.writeValue(gender);
        dest.writeValue(profilePath);
    }

    public int describeContents() {
        return  0;
    }

}
