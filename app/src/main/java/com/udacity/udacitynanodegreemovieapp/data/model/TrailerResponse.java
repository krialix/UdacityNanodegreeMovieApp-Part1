package com.udacity.udacitynanodegreemovieapp.data.model;

import com.squareup.moshi.Json;

import java.util.List;

public class TrailerResponse {

  @Json(name = "id")
  private int id;

  @Json(name = "results")
  private List<Trailer> trailers;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public List<Trailer> getTrailers() {
    return trailers;
  }

  public void setTrailers(List<Trailer> trailers) {
    this.trailers = trailers;
  }
}
