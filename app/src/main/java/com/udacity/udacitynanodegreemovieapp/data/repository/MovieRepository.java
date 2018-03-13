package com.udacity.udacitynanodegreemovieapp.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.udacity.udacitynanodegreemovieapp.data.model.MovieDetail;
import com.udacity.udacitynanodegreemovieapp.data.model.MovieResponse;
import com.udacity.udacitynanodegreemovieapp.data.network.MovieDbClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

  private static MovieRepository instance = null;

  private final MutableLiveData<MovieDetail> detailLiveData = new MutableLiveData<>();

  private final MutableLiveData<MovieResponse> listLiveData = new MutableLiveData<>();

  public static MovieRepository getInstance(MovieDbClient movieDbClient) {
    if (instance == null) {
      instance = new MovieRepository(movieDbClient);
    }

    return instance;
  }

  private final MovieDbClient movieDbClient;

  private MovieRepository(MovieDbClient movieDbClient) {
    this.movieDbClient = movieDbClient;
  }

  public LiveData<MovieDetail> getMovie(int movieId) {
    movieDbClient
        .getMovieDbService()
        .getMovie(movieId)
        .enqueue(
            new Callback<MovieDetail>() {
              @Override
              public void onResponse(
                  @NonNull Call<MovieDetail> call, @NonNull Response<MovieDetail> response) {
                detailLiveData.setValue(response.body());
              }

              @Override
              public void onFailure(@NonNull Call<MovieDetail> call, @NonNull Throwable t) {}
            });

    return detailLiveData;
  }

  public LiveData<MovieResponse> getMovies(String sort) {
    movieDbClient
        .getMovieDbService()
        .getMovies(sort)
        .enqueue(
            new Callback<MovieResponse>() {
              @Override
              public void onResponse(
                  @NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                listLiveData.setValue(response.body());
              }

              @Override
              public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
              }
            });

    return listLiveData;
  }
}
