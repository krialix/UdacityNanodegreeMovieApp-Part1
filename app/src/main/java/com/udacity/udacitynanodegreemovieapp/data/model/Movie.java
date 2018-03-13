package com.udacity.udacitynanodegreemovieapp.data.model;

import com.squareup.moshi.Json;

import java.util.List;
import java.util.Objects;

public class Movie {

  @Json(name = "overview")
  private String overview;

  @Json(name = "original_language")
  private String originalLanguage;

  @Json(name = "original_title")
  private String originalTitle;

  @Json(name = "video")
  private boolean video;

  @Json(name = "title")
  private String title;

  @Json(name = "genre_ids")
  private List<Integer> genreIds;

  @Json(name = "poster_path")
  private String posterPath;

  @Json(name = "backdrop_path")
  private String backdropPath;

  @Json(name = "release_date")
  private String releaseDate;

  @Json(name = "vote_average")
  private double voteAverage;

  @Json(name = "popularity")
  private double popularity;

  @Json(name = "id")
  private int id;

  @Json(name = "adult")
  private boolean adult;

  @Json(name = "vote_count")
  private int voteCount;

  public String getOverview() {
    return overview;
  }

  public void setOverview(String overview) {
    this.overview = overview;
  }

  public String getOriginalLanguage() {
    return originalLanguage;
  }

  public void setOriginalLanguage(String originalLanguage) {
    this.originalLanguage = originalLanguage;
  }

  public String getOriginalTitle() {
    return originalTitle;
  }

  public void setOriginalTitle(String originalTitle) {
    this.originalTitle = originalTitle;
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

  public List<Integer> getGenreIds() {
    return genreIds;
  }

  public void setGenreIds(List<Integer> genreIds) {
    this.genreIds = genreIds;
  }

  public String getPosterPath() {
    return posterPath;
  }

  public void setPosterPath(String posterPath) {
    this.posterPath = posterPath;
  }

  public String getBackdropPath() {
    return backdropPath;
  }

  public void setBackdropPath(String backdropPath) {
    this.backdropPath = backdropPath;
  }

  public String getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(String releaseDate) {
    this.releaseDate = releaseDate;
  }

  public double getVoteAverage() {
    return voteAverage;
  }

  public void setVoteAverage(double voteAverage) {
    this.voteAverage = voteAverage;
  }

  public double getPopularity() {
    return popularity;
  }

  public void setPopularity(double popularity) {
    this.popularity = popularity;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public boolean isAdult() {
    return adult;
  }

  public void setAdult(boolean adult) {
    this.adult = adult;
  }

  public int getVoteCount() {
    return voteCount;
  }

  public void setVoteCount(int voteCount) {
    this.voteCount = voteCount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Movie movie = (Movie) o;
    return video == movie.video
        && Double.compare(movie.voteAverage, voteAverage) == 0
        && Double.compare(movie.popularity, popularity) == 0
        && id == movie.id
        && adult == movie.adult
        && voteCount == movie.voteCount
        && Objects.equals(overview, movie.overview)
        && Objects.equals(originalLanguage, movie.originalLanguage)
        && Objects.equals(originalTitle, movie.originalTitle)
        && Objects.equals(title, movie.title)
        && Objects.equals(genreIds, movie.genreIds)
        && Objects.equals(posterPath, movie.posterPath)
        && Objects.equals(backdropPath, movie.backdropPath)
        && Objects.equals(releaseDate, movie.releaseDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        overview,
        originalLanguage,
        originalTitle,
        video,
        title,
        genreIds,
        posterPath,
        backdropPath,
        releaseDate,
        voteAverage,
        popularity,
        id,
        adult,
        voteCount);
  }

  @Override
  public String toString() {
    return "Movie{"
        + "overview = '"
        + overview
        + '\''
        + ",original_language = '"
        + originalLanguage
        + '\''
        + ",original_title = '"
        + originalTitle
        + '\''
        + ",video = '"
        + video
        + '\''
        + ",title = '"
        + title
        + '\''
        + ",genre_ids = '"
        + genreIds
        + '\''
        + ",poster_path = '"
        + posterPath
        + '\''
        + ",backdrop_path = '"
        + backdropPath
        + '\''
        + ",release_date = '"
        + releaseDate
        + '\''
        + ",vote_average = '"
        + voteAverage
        + '\''
        + ",popularity = '"
        + popularity
        + '\''
        + ",id = '"
        + id
        + '\''
        + ",adult = '"
        + adult
        + '\''
        + ",vote_count = '"
        + voteCount
        + '\''
        + "}";
  }
}
