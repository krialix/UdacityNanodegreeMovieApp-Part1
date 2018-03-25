package com.udacity.udacitynanodegreemovieapp.data.model;

import com.squareup.moshi.Json;

import java.util.Objects;

public class Trailer {

  @Json(name = "id")
  private String id;

  @Json(name = "name")
  private String name;

  @Json(name = "key")
  private String key;

  @Json(name = "site")
  private String site;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getSite() {
    return site;
  }

  public void setSite(String site) {
    this.site = site;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Trailer trailer = (Trailer) o;
    return Objects.equals(id, trailer.id)
        && Objects.equals(name, trailer.name)
        && Objects.equals(key, trailer.key)
        && Objects.equals(site, trailer.site);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, key, site);
  }

  @Override
  public String toString() {
    return "Trailer{"
        + "id='"
        + id
        + '\''
        + ", name='"
        + name
        + '\''
        + ", key='"
        + key
        + '\''
        + ", site='"
        + site
        + '\''
        + '}';
  }
}
