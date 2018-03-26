package com.udacity.udacitynanodegreemovieapp.presentation.feature.moviedetail;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.udacity.udacitynanodegreemovieapp.data.model.MovieDetail;
import com.udacity.udacitynanodegreemovieapp.data.model.ReviewResponse;
import com.udacity.udacitynanodegreemovieapp.data.model.TrailerResponse;
import com.udacity.udacitynanodegreemovieapp.data.repository.MovieRepository;

class MovieDetailViewModel extends AndroidViewModel {

  private final MovieRepository movieRepository;
  private final int movieId;

  private final MutableLiveData<Boolean> favorite;

  MovieDetailViewModel(Application application, MovieRepository movieRepository, int movieId) {
    super(application);
    this.movieRepository = movieRepository;
    this.movieId = movieId;
    favorite = new MutableLiveData<>();
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
    return favorite;
  }

  void toggleFavoriteMovie(MovieDetail movieDetail) {
    Boolean value = favorite.getValue();
    if (value != null) {
      boolean favored = !value;
      favorite.setValue(false);
      movieRepository.toggleFavoriteMovie(getApplication(), movieDetail, favored);
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
