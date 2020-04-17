
package com.davidlutta.filamu.models.cast;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Cast implements Serializable, Parcelable {

    public final static Creator<Cast> CREATOR = new Creator<Cast>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Cast createFromParcel(Parcel in) {
            return new Cast(in);
        }

        public Cast[] newArray(int size) {
            return (new Cast[size]);
        }

    };
    private final static long serialVersionUID = 3185009614731629846L;
    @SerializedName("cast_id")
    @Expose
    private Integer castId;
    @SerializedName("character")
    @Expose
    private String character;
    @SerializedName("credit_id")
    @Expose
    private String creditId;
    @SerializedName("gender")
    @Expose
    private Integer gender;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("order")
    @Expose
    private Integer order;
    @SerializedName("profile_path")
    @Expose
    private Object profilePath;

    protected Cast(Parcel in) {
        this.castId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.character = ((String) in.readValue((String.class.getClassLoader())));
        this.creditId = ((String) in.readValue((String.class.getClassLoader())));
        this.gender = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.order = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.profilePath = in.readValue((Object.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     */
    public Cast() {
    }

    /**
     * @param character
     * @param creditId
     * @param gender
     * @param castId
     * @param name
     * @param id
     * @param profilePath
     * @param order
     */
    public Cast(Integer castId, String character, String creditId, Integer gender, Integer id, String name, Integer order, Object profilePath) {
        super();
        this.castId = castId;
        this.character = character;
        this.creditId = creditId;
        this.gender = gender;
        this.id = id;
        this.name = name;
        this.order = order;
        this.profilePath = profilePath;
    }

    public Integer getCastId() {
        return castId;
    }

    public void setCastId(Integer castId) {
        this.castId = castId;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getCreditId() {
        return creditId;
    }

    public void setCreditId(String creditId) {
        this.creditId = creditId;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Object getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(Object profilePath) {
        this.profilePath = profilePath;
    }


    @Override
    public String toString() {
        return "Cast{" +
                "castId=" + castId +
                ", character='" + character + '\'' +
                ", creditId='" + creditId + '\'' +
                ", gender=" + gender +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", order=" + order +
                ", profilePath=" + profilePath +
                '}';
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(castId);
        dest.writeValue(character);
        dest.writeValue(creditId);
        dest.writeValue(gender);
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(order);
        dest.writeValue(profilePath);
    }

    public int describeContents() {
        return 0;
    }

}
