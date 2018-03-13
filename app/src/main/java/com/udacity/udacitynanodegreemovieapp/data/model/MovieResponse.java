package com.udacity.udacitynanodegreemovieapp.data.model;

import com.squareup.moshi.Json;

import java.util.List;

public class MovieResponse {

  @Json(name = "page")
  private int page;

  @Json(name = "total_pages")
  private int totalPages;

  @Json(name = "results")
  private List<Movie> results;

  @Json(name = "total_results")
  private int totalResults;

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public int getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(int totalPages) {
    this.totalPages = totalPages;
  }

  public List<Movie> getResults() {
    return results;
  }

  public void setResults(List<Movie> results) {
    this.results = results;
  }

  public int getTotalResults() {
    return totalResults;
  }

  public void setTotalResults(int totalResults) {
    this.totalResults = totalResults;
  }

  @Override
  public String toString() {
    return "MovieResponse{"
        + "page = '"
        + page
        + '\''
        + ",total_pages = '"
        + totalPages
        + '\''
        + ",results = '"
        + results
        + '\''
        + ",total_results = '"
        + totalResults
        + '\''
        + "}";
  }
}
