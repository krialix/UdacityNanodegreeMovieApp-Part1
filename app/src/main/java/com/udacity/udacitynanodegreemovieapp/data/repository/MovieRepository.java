package com.udacity.udacitynanodegreemovieapp.data.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.ContentValues;
import android.support.annotation.NonNull;

import com.udacity.udacitynanodegreemovieapp.data.db.MovieContract;
import com.udacity.udacitynanodegreemovieapp.data.model.MovieDetail;
import com.udacity.udacitynanodegreemovieapp.data.model.MovieResponse;
import com.udacity.udacitynanodegreemovieapp.data.model.ReviewResponse;
import com.udacity.udacitynanodegreemovieapp.data.model.TrailerResponse;
import com.udacity.udacitynanodegreemovieapp.data.network.MovieDbClient;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

  private static final ExecutorService EXECUTOR = Executors.newCachedThreadPool();

  private static MovieRepository instance = null;

  private final MutableLiveData<MovieDetail> detailLiveData = new MutableLiveData<>();

  private final MutableLiveData<MovieResponse> listLiveData = new MutableLiveData<>();

  private final MutableLiveData<ReviewResponse> reviewsLiveData = new MutableLiveData<>();

  private final MutableLiveData<TrailerResponse> trailersLiveData = new MutableLiveData<>();

  private final MovieDbClient movieDbClient;

  private MovieRepository(MovieDbClient movieDbClient) {
    this.movieDbClient = movieDbClient;
  }

  public static MovieRepository getInstance(MovieDbClient movieDbClient) {
    if (instance == null) {
      instance = new MovieRepository(movieDbClient);
    }

    return instance;
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
              public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {}
            });

    return listLiveData;
  }

  public LiveData<ReviewResponse> getReviews(int movieId) {
    movieDbClient
        .getMovieDbService()
        .getReviews(movieId)
        .enqueue(
            new Callback<ReviewResponse>() {
              @Override
              public void onResponse(
                  @NonNull Call<ReviewResponse> call, @NonNull Response<ReviewResponse> response) {
                reviewsLiveData.setValue(response.body());
              }

              @Override
              public void onFailure(@NonNull Call<ReviewResponse> call, @NonNull Throwable t) {}
            });

    return reviewsLiveData;
  }

  public LiveData<TrailerResponse> getTrailers(int movieId) {
    movieDbClient
        .getMovieDbService()
        .getTrailers(movieId)
        .enqueue(
            new Callback<TrailerResponse>() {
              @Override
              public void onResponse(
                  @NonNull Call<TrailerResponse> call,
                  @NonNull Response<TrailerResponse> response) {
                trailersLiveData.setValue(response.body());
              }

              @Override
              public void onFailure(@NonNull Call<TrailerResponse> call, @NonNull Throwable t) {}
            });

    return trailersLiveData;
  }

  public void toggleFavoriteMovie(Application application, MovieDetail movie, boolean favored) {
    EXECUTOR.submit(
        () -> {
          if (favored) {
            ContentValues values = new ContentValues();
            values.put(MovieContract.MovieEntry.COLUMN_ID, String.valueOf(movie.getId()));
            values.put(MovieContract.MovieEntry.COLUMN_TITLE, movie.getTitle());
            application.getContentResolver().insert(MovieContract.MovieEntry.CONTENT_URI, values);
          } else {
            application
                .getContentResolver()
                .delete(
                    MovieContract.MovieEntry.CONTENT_URI,
                    MovieContract.MovieEntry.COLUMN_ID + " = ?",
                    new String[] {String.valueOf(movie.getId())});
          }
          application.getContentResolver().notifyChange(MovieContract.MovieEntry.CONTENT_URI, null);
        });
  }
}
