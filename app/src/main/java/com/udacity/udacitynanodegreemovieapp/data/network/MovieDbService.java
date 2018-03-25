package com.udacity.udacitynanodegreemovieapp.data.network;

import android.support.annotation.NonNull;

import com.udacity.udacitynanodegreemovieapp.data.model.MovieDetail;
import com.udacity.udacitynanodegreemovieapp.data.model.MovieResponse;
import com.udacity.udacitynanodegreemovieapp.data.model.ReviewResponse;
import com.udacity.udacitynanodegreemovieapp.data.model.TrailerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/** The interface Movie db service. */
public interface MovieDbService {

  /**
   * Gets movies.
   *
   * @param sort the sort
   * @return the movies
   */
  @GET("3/movie/{sort}")
  Call<MovieResponse> getMovies(@Path("sort") String sort);

  /**
   * Gets movie.
   *
   * @param movieId the movie id
   * @return the movie
   */
  @GET("3/movie/{movie_id}")
  Call<MovieDetail> getMovie(@Path("movie_id") int movieId);

  /**
   * Gets trailers.
   *
   * @param movieId the movie id
   * @return the trailers
   */
  @NonNull
  @GET("3/movie/{movie_id}/videos")
  Call<TrailerResponse> getTrailers(@Path("movie_id") int movieId);

  /**
   * Gets reviews.
   *
   * @param movieId the movie id
   * @return the reviews
   */
  @GET("3/movie/{movie_id}/reviews")
  Call<ReviewResponse> getReviews(@Path("movie_id") int movieId);
}
