package com.udacity.udacitynanodegreemovieapp.presentation.feature.moviedetail;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;

import com.udacity.udacitynanodegreemovieapp.data.db.MovieContract;
import com.udacity.udacitynanodegreemovieapp.data.model.MovieDetail;
import com.udacity.udacitynanodegreemovieapp.data.model.ReviewResponse;
import com.udacity.udacitynanodegreemovieapp.data.model.TrailerResponse;
import com.udacity.udacitynanodegreemovieapp.data.repository.MovieRepository;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class MovieDetailViewModel extends AndroidViewModel {

  private static final ExecutorService EXECUTOR = Executors.newCachedThreadPool();

  private final MovieRepository movieRepository;
  private final int movieId;

  private final MutableLiveData<Boolean> favorite;

  MovieDetailViewModel(Application application, MovieRepository movieRepository, int movieId) {
    super(application);
    this.movieRepository = movieRepository;
    this.movieId = movieId;
    this.favorite = new MutableLiveData<>();
    favorite.setValue(false);
  }

  LiveData<MovieDetail> getMovie() {
    return movieRepository.getMovie(movieId);
  }

  LiveData<ReviewResponse> getReviews() {
    return movieRepository.getReviews(movieId);
  }

  LiveData<TrailerResponse> getTrailers() {
    return movieRepository.getTrailers(movieId);
  }

  MutableLiveData<Boolean> getFavorite() {
    EXECUTOR.submit(
        () -> {
          Cursor cursor =
              getApplication()
                  .getContentResolver()
                  .query(
                      MovieContract.MovieEntry.CONTENT_URI,
                      new String[] {MovieContract.MovieEntry.COLUMN_ID},
                      MovieContract.MovieEntry.COLUMN_ID + " = ?",
                      new String[] {String.valueOf(movieId)},
                      null);
          if (cursor != null) {
            favorite.postValue(cursor.getCount() > 0);
            cursor.close();
          }
        });

    return favorite;
  }

  void toggleFavoriteMovie(MovieDetail movieDetail) {
    Boolean value = favorite.getValue();
    if (value != null) {
      boolean favored = !value;
      favorite.setValue(favored);

      Application application = getApplication();

      EXECUTOR.submit(
          () -> {
            if (favored) {
              ContentValues values = new ContentValues();
              values.put(MovieContract.MovieEntry.COLUMN_ID, movieDetail.getId());
              values.put(MovieContract.MovieEntry.COLUMN_TITLE, movieDetail.getTitle());
              values.put(MovieContract.MovieEntry.COLUMN_POSTER_PATH, movieDetail.getPosterPath());
              values.put(
                  MovieContract.MovieEntry.COLUMN_VOTE_AVERAGE,
                  String.valueOf(movieDetail.getVoteAverage()));
              application.getContentResolver().insert(MovieContract.MovieEntry.CONTENT_URI, values);
            } else {
              application
                  .getContentResolver()
                  .delete(
                      MovieContract.MovieEntry.CONTENT_URI,
                      MovieContract.MovieEntry.COLUMN_ID + " = ?",
                      new String[] {String.valueOf(movieDetail.getId())});
            }
            application
                .getContentResolver()
                .notifyChange(MovieContract.MovieEntry.CONTENT_URI, null);
          });
    }
  }

  static class Factory extends ViewModelProvider.NewInstanceFactory {
    private final Application application;
    private final MovieRepository movieRepository;
    private final int movieId;

    Factory(Application application, MovieRepository movieRepository, int movieId) {
      this.application = application;
      this.movieRepository = movieRepository;
      this.movieId = movieId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
      //noinspection unchecked
      return (T) new MovieDetailViewModel(application, movieRepository, movieId);
    }
  }
}
