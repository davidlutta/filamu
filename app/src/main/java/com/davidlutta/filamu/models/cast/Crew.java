
package com.davidlutta.filamu.models.cast;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Crew implements Serializable, Parcelable {

    public final static Creator<Crew> CREATOR = new Creator<Crew>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Crew createFromParcel(Parcel in) {
            return new Crew(in);
        }

        public Crew[] newArray(int size) {
            return (new Crew[size]);
        }

    };
    private final static long serialVersionUID = -1027755042301212418L;
    @SerializedName("credit_id")
    @Expose
    private String creditId;
    @SerializedName("department")
    @Expose
    private String department;
    @SerializedName("gender")
    @Expose
    private Integer gender;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("job")
    @Expose
    private String job;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("profile_path")
    @Expose
    private Object profilePath;

    protected Crew(Parcel in) {
        this.creditId = ((String) in.readValue((String.class.getClassLoader())));
        this.department = ((String) in.readValue((String.class.getClassLoader())));
        this.gender = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.job = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.profilePath = in.readValue((Object.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     */
    public Crew() {
    }

    /**
     * @param creditId
     * @param gender
     * @param name
     * @param id
     * @param profilePath
     * @param department
     * @param job
     */
    public Crew(String creditId, String department, Integer gender, Integer id, String job, String name, Object profilePath) {
        super();
        this.creditId = creditId;
        this.department = department;
        this.gender = gender;
        this.id = id;
        this.job = job;
        this.name = name;
        this.profilePath = profilePath;
    }

    public String getCreditId() {
        return creditId;
    }

    public void setCreditId(String creditId) {
        this.creditId = creditId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(Object profilePath) {
        this.profilePath = profilePath;
    }

    @Override
    public String toString() {
        return "Crew{" +
                "creditId='" + creditId + '\'' +
                ", department='" + department + '\'' +
                ", gender=" + gender +
                ", id=" + id +
                ", job='" + job + '\'' +
                ", name='" + name + '\'' +
                ", profilePath=" + profilePath +
                '}';
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(creditId);
        dest.writeValue(department);
        dest.writeValue(gender);
        dest.writeValue(id);
        dest.writeValue(job);
        dest.writeValue(name);
        dest.writeValue(profilePath);
    }

    public int describeContents() {
        return 0;
    }

}
