package com.udacity.udacitynanodegreemovieapp.data.model;

import com.squareup.moshi.Json;

public class GenresItem {

  @Json(name = "name")
  private String name;

  @Json(name = "id")
  private int id;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "GenresItem{" + "name = '" + name + '\'' + ",id = '" + id + '\'' + "}";
  }
}
