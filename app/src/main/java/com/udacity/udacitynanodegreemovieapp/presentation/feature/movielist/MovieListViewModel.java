package com.udacity.udacitynanodegreemovieapp.presentation.feature.movielist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.udacity.udacitynanodegreemovieapp.data.model.Movie;
import com.udacity.udacitynanodegreemovieapp.data.repository.MovieRepository;

import java.util.List;

class MovieListViewModel extends AndroidViewModel {

  private final MovieRepository movieRepository;

  MovieListViewModel(Application application, MovieRepository movieRepository) {
    super(application);
    this.movieRepository = movieRepository;
  }

  LiveData<List<Movie>> getMovies(String sortType) {
    return movieRepository.getMovies(sortType, getApplication());
  }

  static class Factory extends ViewModelProvider.NewInstanceFactory {
    private final MovieRepository movieRepository;
    private final Application application;

    Factory(MovieRepository movieRepository, Application application) {
      this.movieRepository = movieRepository;
      this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
      //noinspection unchecked
      return (T) new MovieListViewModel(application, movieRepository);
    }
  }
}
