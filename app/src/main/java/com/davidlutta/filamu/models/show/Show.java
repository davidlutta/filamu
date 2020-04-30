
package com.davidlutta.filamu.models.show;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Show implements Serializable, Parcelable
{

    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("created_by")
    @Expose
    private List<CreatedBy> createdBy = null;
    @SerializedName("episode_run_time")
    @Expose
    private List<Integer> episodeRunTime = null;
    @SerializedName("first_air_date")
    @Expose
    private String firstAirDate;
    @SerializedName("genres")
    @Expose
    private List<Genre> genres = null;
    @SerializedName("homepage")
    @Expose
    private String homepage;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("in_production")
    @Expose
    private Boolean inProduction;
    @SerializedName("languages")
    @Expose
    private List<String> languages = null;
    @SerializedName("last_air_date")
    @Expose
    private String lastAirDate;
    @SerializedName("last_episode_to_air")
    @Expose
    private LastEpisodeToAir lastEpisodeToAir;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("next_episode_to_air")
    @Expose
    private Object nextEpisodeToAir;
    @SerializedName("networks")
    @Expose
    private List<Network> networks = null;
    @SerializedName("number_of_episodes")
    @Expose
    private Integer numberOfEpisodes;
    @SerializedName("number_of_seasons")
    @Expose
    private Integer numberOfSeasons;
    @SerializedName("origin_country")
    @Expose
    private List<String> originCountry = null;
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;
    @SerializedName("original_name")
    @Expose
    private String originalName;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("popularity")
    @Expose
    private Float popularity;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("production_companies")
    @Expose
    private List<ProductionCompany> productionCompanies = null;
    @SerializedName("seasons")
    @Expose
    private List<Season> seasons = null;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("vote_average")
    @Expose
    private Float voteAverage;
    @SerializedName("vote_count")
    @Expose
    private Integer voteCount;
    public final static Creator<Show> CREATOR = new Creator<Show>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Show createFromParcel(Parcel in) {
            return new Show(in);
        }

        public Show[] newArray(int size) {
            return (new Show[size]);
        }

    }
    ;
    private final static long serialVersionUID = -6067070301098010362L;

    protected Show(Parcel in) {
        this.backdropPath = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.createdBy, (com.davidlutta.filamu.models.show.CreatedBy.class.getClassLoader()));
        in.readList(this.episodeRunTime, (Integer.class.getClassLoader()));
        this.firstAirDate = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.genres, (com.davidlutta.filamu.models.show.Genre.class.getClassLoader()));
        this.homepage = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.inProduction = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        in.readList(this.languages, (String.class.getClassLoader()));
        this.lastAirDate = ((String) in.readValue((String.class.getClassLoader())));
        this.lastEpisodeToAir = ((LastEpisodeToAir) in.readValue((LastEpisodeToAir.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.nextEpisodeToAir = ((Object) in.readValue((Object.class.getClassLoader())));
        in.readList(this.networks, (com.davidlutta.filamu.models.show.Network.class.getClassLoader()));
        this.numberOfEpisodes = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.numberOfSeasons = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.originCountry, (String.class.getClassLoader()));
        this.originalLanguage = ((String) in.readValue((String.class.getClassLoader())));
        this.originalName = ((String) in.readValue((String.class.getClassLoader())));
        this.overview = ((String) in.readValue((String.class.getClassLoader())));
        this.popularity = ((Float) in.readValue((Float.class.getClassLoader())));
        this.posterPath = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.productionCompanies, (com.davidlutta.filamu.models.show.ProductionCompany.class.getClassLoader()));
        in.readList(this.seasons, (com.davidlutta.filamu.models.show.Season.class.getClassLoader()));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        this.voteAverage = ((Float) in.readValue((Float.class.getClassLoader())));
        this.voteCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Show() {
    }

    /**
     * 
     * @param networks
     * @param type
     * @param inProduction
     * @param episodeRunTime
     * @param firstAirDate
     * @param numberOfSeasons
     * @param originalName
     * @param genres
     * @param popularity
     * @param id
     * @param posterPath
     * @param overview
     * @param seasons
     * @param voteAverage
     * @param languages
     * @param originalLanguage
     * @param numberOfEpisodes
     * @param lastEpisodeToAir
     * @param createdBy
     * @param name
     * @param originCountry
     * @param nextEpisodeToAir
     * @param backdropPath
     * @param voteCount
     * @param lastAirDate
     * @param productionCompanies
     * @param homepage
     * @param status
     */
    public Show(String backdropPath, List<CreatedBy> createdBy, List<Integer> episodeRunTime, String firstAirDate, List<Genre> genres, String homepage, Integer id, Boolean inProduction, List<String> languages, String lastAirDate, LastEpisodeToAir lastEpisodeToAir, String name, Object nextEpisodeToAir, List<Network> networks, Integer numberOfEpisodes, Integer numberOfSeasons, List<String> originCountry, String originalLanguage, String originalName, String overview, Float popularity, String posterPath, List<ProductionCompany> productionCompanies, List<Season> seasons, String status, String type, Float voteAverage, Integer voteCount) {
        super();
        this.backdropPath = backdropPath;
        this.createdBy = createdBy;
        this.episodeRunTime = episodeRunTime;
        this.firstAirDate = firstAirDate;
        this.genres = genres;
        this.homepage = homepage;
        this.id = id;
        this.inProduction = inProduction;
        this.languages = languages;
        this.lastAirDate = lastAirDate;
        this.lastEpisodeToAir = lastEpisodeToAir;
        this.name = name;
        this.nextEpisodeToAir = nextEpisodeToAir;
        this.networks = networks;
        this.numberOfEpisodes = numberOfEpisodes;
        this.numberOfSeasons = numberOfSeasons;
        this.originCountry = originCountry;
        this.originalLanguage = originalLanguage;
        this.originalName = originalName;
        this.overview = overview;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.productionCompanies = productionCompanies;
        this.seasons = seasons;
        this.status = status;
        this.type = type;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public List<CreatedBy> getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(List<CreatedBy> createdBy) {
        this.createdBy = createdBy;
    }

    public List<Integer> getEpisodeRunTime() {
        return episodeRunTime;
    }

    public void setEpisodeRunTime(List<Integer> episodeRunTime) {
        this.episodeRunTime = episodeRunTime;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getInProduction() {
        return inProduction;
    }

    public void setInProduction(Boolean inProduction) {
        this.inProduction = inProduction;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public String getLastAirDate() {
        return lastAirDate;
    }

    public void setLastAirDate(String lastAirDate) {
        this.lastAirDate = lastAirDate;
    }

    public LastEpisodeToAir getLastEpisodeToAir() {
        return lastEpisodeToAir;
    }

    public void setLastEpisodeToAir(LastEpisodeToAir lastEpisodeToAir) {
        this.lastEpisodeToAir = lastEpisodeToAir;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getNextEpisodeToAir() {
        return nextEpisodeToAir;
    }

    public void setNextEpisodeToAir(Object nextEpisodeToAir) {
        this.nextEpisodeToAir = nextEpisodeToAir;
    }

    public List<Network> getNetworks() {
        return networks;
    }

    public void setNetworks(List<Network> networks) {
        this.networks = networks;
    }

    public Integer getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(Integer numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public Integer getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(Integer numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public List<String> getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(List<String> originCountry) {
        this.originCountry = originCountry;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Float getPopularity() {
        return popularity;
    }

    public void setPopularity(Float popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public List<ProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(List<ProductionCompany> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    @Override
    public String toString() {
        return "Show{" +
                "backdropPath='" + backdropPath + '\'' +
                ", createdBy=" + createdBy +
                ", episodeRunTime=" + episodeRunTime +
                ", firstAirDate='" + firstAirDate + '\'' +
                ", genres=" + genres +
                ", homepage='" + homepage + '\'' +
                ", id=" + id +
                ", inProduction=" + inProduction +
                ", languages=" + languages +
                ", lastAirDate='" + lastAirDate + '\'' +
                ", lastEpisodeToAir=" + lastEpisodeToAir +
                ", name='" + name + '\'' +
                ", nextEpisodeToAir=" + nextEpisodeToAir +
                ", networks=" + networks +
                ", numberOfEpisodes=" + numberOfEpisodes +
                ", numberOfSeasons=" + numberOfSeasons +
                ", originCountry=" + originCountry +
                ", originalLanguage='" + originalLanguage + '\'' +
                ", originalName='" + originalName + '\'' +
                ", overview='" + overview + '\'' +
                ", popularity=" + popularity +
                ", posterPath='" + posterPath + '\'' +
                ", productionCompanies=" + productionCompanies +
                ", seasons=" + seasons +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", voteAverage=" + voteAverage +
                ", voteCount=" + voteCount +
                '}';
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(backdropPath);
        dest.writeList(createdBy);
        dest.writeList(episodeRunTime);
        dest.writeValue(firstAirDate);
        dest.writeList(genres);
        dest.writeValue(homepage);
        dest.writeValue(id);
        dest.writeValue(inProduction);
        dest.writeList(languages);
        dest.writeValue(lastAirDate);
        dest.writeValue(lastEpisodeToAir);
        dest.writeValue(name);
        dest.writeValue(nextEpisodeToAir);
        dest.writeList(networks);
        dest.writeValue(numberOfEpisodes);
        dest.writeValue(numberOfSeasons);
        dest.writeList(originCountry);
        dest.writeValue(originalLanguage);
        dest.writeValue(originalName);
        dest.writeValue(overview);
        dest.writeValue(popularity);
        dest.writeValue(posterPath);
        dest.writeList(productionCompanies);
        dest.writeList(seasons);
        dest.writeValue(status);
        dest.writeValue(type);
        dest.writeValue(voteAverage);
        dest.writeValue(voteCount);
    }

    public int describeContents() {
        return  0;
    }

}
