package com.udacity.udacitynanodegreemovieapp.data.network;

import com.udacity.udacitynanodegreemovieapp.data.model.MovieDetail;
import com.udacity.udacitynanodegreemovieapp.data.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

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
}
