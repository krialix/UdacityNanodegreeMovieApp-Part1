package com.udacity.udacitynanodegreemovieapp.presentation.feature.movielist;

import android.support.annotation.NonNull;
import android.support.v7.graphics.Palette;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.udacity.udacitynanodegreemovieapp.R;
import com.udacity.udacitynanodegreemovieapp.data.model.Movie;
import com.udacity.udacitynanodegreemovieapp.presentation.feature.moviedetail.MovieDetailActivity;
import com.udacity.udacitynanodegreemovieapp.presentation.feature.moviedetail.MovieDetailFragment;
import com.udacity.udacitynanodegreemovieapp.presentation.util.GlidePaletteListener;
import com.udacity.udacitynanodegreemovieapp.presentation.util.ImageUrlResolver;

import butterknife.BindView;
import butterknife.ButterKnife;

class MovieListAdapter extends ListAdapter<Movie, MovieListAdapter.MovieViewHolder> {

  private static final DiffUtil.ItemCallback<Movie> DIFF_CALLBACK =
      new DiffUtil.ItemCallback<Movie>() {
        @Override
        public boolean areItemsTheSame(Movie oldItem, Movie newItem) {
          return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(Movie oldItem, Movie newItem) {
          return oldItem.equals(newItem);
        }
      };

  private final MovieListActivity parent;
  private final RequestManager glide;
  private final boolean twoPane;

  private final MovieViewHolder.OnMovieClickListener onMovieClickListener =
      new MovieViewHolder.OnMovieClickListener() {
        @Override
        public void onMovieClick(View view, int movieId) {
          if (twoPane) {
            MovieDetailFragment fragment = MovieDetailFragment.newInstance(movieId);
            parent
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.movie_detail_container, fragment)
                .commit();
          } else {
            MovieDetailActivity.start(view.getContext(), movieId);
          }
        }
      };

  /**
   * Instantiates a new Movie list adapter.
   *
   * @param parent the parent
   * @param glide the glide
   * @param twoPane the two pane
   */
  MovieListAdapter(MovieListActivity parent, RequestManager glide, boolean twoPane) {
    super(DIFF_CALLBACK);
    this.parent = parent;
    this.glide = glide;
    this.twoPane = twoPane;
  }

  @NonNull
  @Override
  public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_poster_grid, parent, false);
    return new MovieViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull final MovieViewHolder holder, int position) {
    holder.bind(getItem(position), glide, onMovieClickListener);
  }

  static class MovieViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.root_view)
    ViewGroup rootView;

    @BindView(R.id.view_poster_grid_background)
    View viewBackground;

    @BindView(R.id.iv_poster_grid_poster)
    ImageView ivPoster;

    @BindView(R.id.tv_poster_grid_title)
    TextView tvTitle;

    @BindView(R.id.tv_poster_grid_rating)
    TextView tvRating;

    MovieViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }

    void bind(Movie movie, RequestManager glide, OnMovieClickListener onMovieClickListener) {
      rootView.setOnClickListener(v -> onMovieClickListener.onMovieClick(v, movie.getId()));

      final String url =
          ImageUrlResolver.getTdmbImageUrl(movie.getPosterPath(), ImageUrlResolver.MODIFIER_W342);
      glide
          .load(url)
          .listener(
              new GlidePaletteListener(
                  palette -> {
                    Palette.Swatch swatch = palette.getMutedSwatch();
                    if (swatch != null) {
                      viewBackground.setBackgroundColor(swatch.getRgb());
                      tvTitle.setTextColor(swatch.getTitleTextColor());
                      tvRating.setTextColor(swatch.getBodyTextColor());
                    }
                  }))
          .into(ivPoster);

      tvTitle.setText(movie.getTitle());
      tvRating.setText(String.valueOf(movie.getVoteAverage()));
    }

    interface OnMovieClickListener {
      void onMovieClick(View view, int movieId);
    }
  }
}
