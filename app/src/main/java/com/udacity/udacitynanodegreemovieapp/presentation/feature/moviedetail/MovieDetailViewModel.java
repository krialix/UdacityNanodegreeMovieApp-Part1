package com.udacity.udacitynanodegreemovieapp.presentation.feature.moviedetail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.udacity.udacitynanodegreemovieapp.data.model.MovieDetail;
import com.udacity.udacitynanodegreemovieapp.data.repository.MovieRepository;

class MovieDetailViewModel extends ViewModel {

  private final MovieRepository movieRepository;
  private final int movieId;

  MovieDetailViewModel(MovieRepository movieRepository, int movieId) {
    this.movieRepository = movieRepository;
    this.movieId = movieId;
  }

  LiveData<MovieDetail> getMovie() {
    return movieRepository.getMovie(movieId);
  }

  static class Factory extends ViewModelProvider.NewInstanceFactory {
    private final MovieRepository movieRepository;
    private final int movieId;

    Factory(MovieRepository movieRepository, int movieId) {
      this.movieRepository = movieRepository;
      this.movieId = movieId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
      //noinspection unchecked
      return (T) new MovieDetailViewModel(movieRepository, movieId);
    }
  }
}
