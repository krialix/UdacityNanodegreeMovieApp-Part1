package com.udacity.udacitynanodegreemovieapp.data.model;

import com.squareup.moshi.Json;

import java.util.List;

public class ReviewResponse {

  @Json(name = "id")
  private int id;

  @Json(name = "page")
  private int page;

  @Json(name = "total_pages")
  private int totalPages;

  @Json(name = "results")
  private List<Review> results;

  @Json(name = "total_results")
  private int totalResults;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

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

  public List<Review> getResults() {
    return results;
  }

  public void setResults(List<Review> results) {
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
    return "ReviewResponse{"
        + "id = '"
        + id
        + '\''
        + ",page = '"
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
