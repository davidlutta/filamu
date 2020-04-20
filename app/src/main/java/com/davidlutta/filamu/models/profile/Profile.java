
package com.davidlutta.filamu.models.profile;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Profile implements Serializable, Parcelable {

    public final static Creator<Profile> CREATOR = new Creator<Profile>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Profile createFromParcel(Parcel in) {
            return new Profile(in);
        }

        public Profile[] newArray(int size) {
            return (new Profile[size]);
        }

    };
    private final static long serialVersionUID = 7662846102493023062L;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("known_for_department")
    @Expose
    private String knownForDepartment;
    @SerializedName("deathday")
    @Expose
    private Object deathday;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("also_known_as")
    @Expose
    private List<String> alsoKnownAs = null;
    @SerializedName("gender")
    @Expose
    private Integer gender;
    @SerializedName("biography")
    @Expose
    private String biography;
    @SerializedName("popularity")
    @Expose
    private Float popularity;
    @SerializedName("place_of_birth")
    @Expose
    private String placeOfBirth;
    @SerializedName("profile_path")
    @Expose
    private String profilePath;
    @SerializedName("adult")
    @Expose
    private Boolean adult;
    @SerializedName("imdb_id")
    @Expose
    private String imdbId;
    @SerializedName("homepage")
    @Expose
    private Object homepage;

    protected Profile(Parcel in) {
        this.birthday = ((String) in.readValue((String.class.getClassLoader())));
        this.knownForDepartment = ((String) in.readValue((String.class.getClassLoader())));
        this.deathday = in.readValue((Object.class.getClassLoader()));
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.alsoKnownAs, (String.class.getClassLoader()));
        this.gender = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.biography = ((String) in.readValue((String.class.getClassLoader())));
        this.popularity = ((Float) in.readValue((Float.class.getClassLoader())));
        this.placeOfBirth = ((String) in.readValue((String.class.getClassLoader())));
        this.profilePath = ((String) in.readValue((String.class.getClassLoader())));
        this.adult = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.imdbId = ((String) in.readValue((String.class.getClassLoader())));
        this.homepage = in.readValue((Object.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     */
    public Profile() {
    }

    /**
     * @param birthday
     * @param placeOfBirth
     * @param gender
     * @param imdbId
     * @param knownForDepartment
     * @param biography
     * @param deathday
     * @param alsoKnownAs
     * @param popularity
     * @param name
     * @param id
     * @param profilePath
     * @param adult
     * @param homepage
     */
    public Profile(String birthday, String knownForDepartment, Object deathday, Integer id, String name, List<String> alsoKnownAs, Integer gender, String biography, Float popularity, String placeOfBirth, String profilePath, Boolean adult, String imdbId, Object homepage) {
        super();
        this.birthday = birthday;
        this.knownForDepartment = knownForDepartment;
        this.deathday = deathday;
        this.id = id;
        this.name = name;
        this.alsoKnownAs = alsoKnownAs;
        this.gender = gender;
        this.biography = biography;
        this.popularity = popularity;
        this.placeOfBirth = placeOfBirth;
        this.profilePath = profilePath;
        this.adult = adult;
        this.imdbId = imdbId;
        this.homepage = homepage;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getKnownForDepartment() {
        return knownForDepartment;
    }

    public void setKnownForDepartment(String knownForDepartment) {
        this.knownForDepartment = knownForDepartment;
    }

    public Object getDeathday() {
        return deathday;
    }

    public void setDeathday(Object deathday) {
        this.deathday = deathday;
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

    public List<String> getAlsoKnownAs() {
        return alsoKnownAs;
    }

    public void setAlsoKnownAs(List<String> alsoKnownAs) {
        this.alsoKnownAs = alsoKnownAs;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Float getPopularity() {
        return popularity;
    }

    public void setPopularity(Float popularity) {
        this.popularity = popularity;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public Object getHomepage() {
        return homepage;
    }

    public void setHomepage(Object homepage) {
        this.homepage = homepage;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(birthday);
        dest.writeValue(knownForDepartment);
        dest.writeValue(deathday);
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeList(alsoKnownAs);
        dest.writeValue(gender);
        dest.writeValue(biography);
        dest.writeValue(popularity);
        dest.writeValue(placeOfBirth);
        dest.writeValue(profilePath);
        dest.writeValue(adult);
        dest.writeValue(imdbId);
        dest.writeValue(homepage);
    }

    public int describeContents() {
        return 0;
    }

}
