package com.udacity.udacitynanodegreemovieapp.data.model;

import com.squareup.moshi.Json;

import java.util.Objects;

public class Review {

  @Json(name = "id")
  private String id;

  @Json(name = "author")
  private String author;

  @Json(name = "content")
  private String content;

  @Json(name = "url")
  private String url;

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Review review = (Review) o;
    return Objects.equals(id, review.id)
        && Objects.equals(author, review.author)
        && Objects.equals(content, review.content)
        && Objects.equals(url, review.url);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, author, content, url);
  }

  @Override
  public String toString() {
    return "Review{"
        + "author = '"
        + author
        + '\''
        + ",id = '"
        + id
        + '\''
        + ",content = '"
        + content
        + '\''
        + ",url = '"
        + url
        + '\''
        + "}";
  }
}
