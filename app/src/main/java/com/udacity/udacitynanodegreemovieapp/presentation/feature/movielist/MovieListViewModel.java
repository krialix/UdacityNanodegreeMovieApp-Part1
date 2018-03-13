package com.udacity.udacitynanodegreemovieapp.presentation.feature.movielist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.udacity.udacitynanodegreemovieapp.data.model.MovieResponse;
import com.udacity.udacitynanodegreemovieapp.data.repository.MovieRepository;

class MovieListViewModel extends ViewModel {

  private final MovieRepository movieRepository;

  MovieListViewModel(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
  }

  LiveData<MovieResponse> getMovies(String sort) {
    return movieRepository.getMovies(sort);
  }

  static class Factory extends ViewModelProvider.NewInstanceFactory {
    private final MovieRepository movieRepository;

    Factory(MovieRepository movieRepository) {
      this.movieRepository = movieRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
      //noinspection unchecked
      return (T) new MovieListViewModel(movieRepository);
    }
  }
}
