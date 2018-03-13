package com.udacity.udacitynanodegreemovieapp.data.model;

import com.squareup.moshi.Json;

import java.util.List;

public class MovieDetail {

  @Json(name = "original_language")
  private String originalLanguage;

  @Json(name = "imdb_id")
  private String imdbId;

  @Json(name = "video")
  private boolean video;

  @Json(name = "title")
  private String title;

  @Json(name = "backdrop_path")
  private String backdropPath;

  @Json(name = "revenue")
  private int revenue;

  @Json(name = "genres")
  private List<GenresItem> genres;

  @Json(name = "popularity")
  private double popularity;

  @Json(name = "production_countries")
  private List<ProductionCountriesItem> productionCountries;

  @Json(name = "id")
  private int id;

  @Json(name = "vote_count")
  private int voteCount;

  @Json(name = "budget")
  private int budget;

  @Json(name = "overview")
  private String overview;

  @Json(name = "original_title")
  private String originalTitle;

  @Json(name = "runtime")
  private int runtime;

  @Json(name = "poster_path")
  private String posterPath;

  @Json(name = "spoken_languages")
  private List<SpokenLanguagesItem> spokenLanguages;

  @Json(name = "production_companies")
  private List<ProductionCompaniesItem> productionCompanies;

  @Json(name = "release_date")
  private String releaseDate;

  @Json(name = "vote_average")
  private float voteAverage;

  @Json(name = "belongs_to_collection")
  private Object belongsToCollection;

  @Json(name = "tagline")
  private String tagline;

  @Json(name = "adult")
  private boolean adult;

  @Json(name = "homepage")
  private String homepage;

  @Json(name = "status")
  private String status;

  public String getOriginalLanguage() {
    return originalLanguage;
  }

  public void setOriginalLanguage(String originalLanguage) {
    this.originalLanguage = originalLanguage;
  }

  public String getImdbId() {
    return imdbId;
  }

  public void setImdbId(String imdbId) {
    this.imdbId = imdbId;
  }

  public boolean isVideo() {
    return video;
  }

  public void setVideo(boolean video) {
    this.video = video;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getBackdropPath() {
    return backdropPath;
  }

  public void setBackdropPath(String backdropPath) {
    this.backdropPath = backdropPath;
  }

  public int getRevenue() {
    return revenue;
  }

  public void setRevenue(int revenue) {
    this.revenue = revenue;
  }

  public List<GenresItem> getGenres() {
    return genres;
  }

  public void setGenres(List<GenresItem> genres) {
    this.genres = genres;
  }

  public double getPopularity() {
    return popularity;
  }

  public void setPopularity(double popularity) {
    this.popularity = popularity;
  }

  public List<ProductionCountriesItem> getProductionCountries() {
    return productionCountries;
  }

  public void setProductionCountries(List<ProductionCountriesItem> productionCountries) {
    this.productionCountries = productionCountries;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getVoteCount() {
    return voteCount;
  }

  public void setVoteCount(int voteCount) {
    this.voteCount = voteCount;
  }

  public int getBudget() {
    return budget;
  }

  public void setBudget(int budget) {
    this.budget = budget;
  }

  public String getOverview() {
    return overview;
  }

  public void setOverview(String overview) {
    this.overview = overview;
  }

  public String getOriginalTitle() {
    return originalTitle;
  }

  public void setOriginalTitle(String originalTitle) {
    this.originalTitle = originalTitle;
  }

  public int getRuntime() {
    return runtime;
  }

  public void setRuntime(int runtime) {
    this.runtime = runtime;
  }

  public String getPosterPath() {
    return posterPath;
  }

  public void setPosterPath(String posterPath) {
    this.posterPath = posterPath;
  }

  public List<SpokenLanguagesItem> getSpokenLanguages() {
    return spokenLanguages;
  }

  public void setSpokenLanguages(List<SpokenLanguagesItem> spokenLanguages) {
    this.spokenLanguages = spokenLanguages;
  }

  public List<ProductionCompaniesItem> getProductionCompanies() {
    return productionCompanies;
  }

  public void setProductionCompanies(List<ProductionCompaniesItem> productionCompanies) {
    this.productionCompanies = productionCompanies;
  }

  public String getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(String releaseDate) {
    this.releaseDate = releaseDate;
  }

  public float getVoteAverage() {
    return voteAverage;
  }

  public void setVoteAverage(float voteAverage) {
    this.voteAverage = voteAverage;
  }

  public Object getBelongsToCollection() {
    return belongsToCollection;
  }

  public void setBelongsToCollection(Object belongsToCollection) {
    this.belongsToCollection = belongsToCollection;
  }

  public String getTagline() {
    return tagline;
  }

  public void setTagline(String tagline) {
    this.tagline = tagline;
  }

  public boolean isAdult() {
    return adult;
  }

  public void setAdult(boolean adult) {
    this.adult = adult;
  }

  public String getHomepage() {
    return homepage;
  }

  public void setHomepage(String homepage) {
    this.homepage = homepage;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "MovieDetail{"
        + "original_language = '"
        + originalLanguage
        + '\''
        + ",imdb_id = '"
        + imdbId
        + '\''
        + ",video = '"
        + video
        + '\''
        + ",title = '"
        + title
        + '\''
        + ",backdrop_path = '"
        + backdropPath
        + '\''
        + ",revenue = '"
        + revenue
        + '\''
        + ",genres = '"
        + genres
        + '\''
        + ",popularity = '"
        + popularity
        + '\''
        + ",production_countries = '"
        + productionCountries
        + '\''
        + ",id = '"
        + id
        + '\''
        + ",vote_count = '"
        + voteCount
        + '\''
        + ",budget = '"
        + budget
        + '\''
        + ",overview = '"
        + overview
        + '\''
        + ",original_title = '"
        + originalTitle
        + '\''
        + ",runtime = '"
        + runtime
        + '\''
        + ",poster_path = '"
        + posterPath
        + '\''
        + ",spoken_languages = '"
        + spokenLanguages
        + '\''
        + ",production_companies = '"
        + productionCompanies
        + '\''
        + ",release_date = '"
        + releaseDate
        + '\''
        + ",vote_average = '"
        + voteAverage
        + '\''
        + ",belongs_to_collection = '"
        + belongsToCollection
        + '\''
        + ",tagline = '"
        + tagline
        + '\''
        + ",adult = '"
        + adult
        + '\''
        + ",homepage = '"
        + homepage
        + '\''
        + ",status = '"
        + status
        + '\''
        + "}";
  }
}
