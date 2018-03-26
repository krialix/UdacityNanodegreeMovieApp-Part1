package com.udacity.udacitynanodegreemovieapp.presentation.feature.moviedetail;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.udacity.udacitynanodegreemovieapp.R;
import com.udacity.udacitynanodegreemovieapp.data.model.GenresItem;
import com.udacity.udacitynanodegreemovieapp.data.model.MovieDetail;
import com.udacity.udacitynanodegreemovieapp.data.network.MovieDbClient;
import com.udacity.udacitynanodegreemovieapp.data.repository.MovieRepository;
import com.udacity.udacitynanodegreemovieapp.presentation.util.ImageUrlResolver;

import butterknife.BindView;

public class MovieDetailFragment extends Fragment {

  private static final String ARG_MOVIE_ID = "ARG_MOVIE_ID";

  @BindView(R.id.iv_movie_detail_backdrop)
  KenBurnsView ivBackdrop;

  @BindView(R.id.iv_movie_detail_poster)
  ImageView ivPoster;

  @BindView(R.id.tv_movie_detail_title)
  TextView tvTitle;

  @BindView(R.id.tv_movie_detail_release_date)
  TextView tvReleaseDate;

  @BindView(R.id.tv_movie_detail_genres)
  TextView tvGenres;

  @BindView(R.id.ratingBar_movie_details_rating)
  RatingBar ratingBar;

  @BindView(R.id.tv_movie_details_voter_count)
  TextView tvVoterCount;

  @BindView(R.id.tv_movie_detail_description)
  TextView tvDescription;

  private MovieDetailViewModel viewModel;

  public static MovieDetailFragment newInstance(int movieId) {
    Bundle args = new Bundle();
    args.putInt(ARG_MOVIE_ID, movieId);
    MovieDetailFragment fragment = new MovieDetailFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Bundle bundle = getArguments();

    if (bundle.containsKey(ARG_MOVIE_ID)) {
      int movieId = bundle.getInt(ARG_MOVIE_ID);

      ViewModelProvider.Factory factory =
          new MovieDetailViewModel.Factory(
              (Application) getContext().getApplicationContext(),
              MovieRepository.getInstance(MovieDbClient.getInstance()),
              movieId);

      viewModel = new ViewModelProvider(this, factory).get(MovieDetailViewModel.class);

      Activity activity = getActivity();
      CollapsingToolbarLayout appBarLayout = activity.findViewById(R.id.toolbar_layout);
      if (appBarLayout != null) {
        appBarLayout.setTitle("");
      }
    }
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    viewModel.getMovie().observe(this, this::setMovieDetails);
  }

  private void setMovieDetails(MovieDetail movieDetail) {
    RequestManager glide = Glide.with(this);

    glide
        .load(
            ImageUrlResolver.getTdmbImageUrl(
                movieDetail.getBackdropPath(), ImageUrlResolver.MODIFIER_W780))
        .into(ivBackdrop);

    glide
        .load(
            ImageUrlResolver.getTdmbImageUrl(
                movieDetail.getPosterPath(), ImageUrlResolver.MODIFIER_W185))
        .into(ivPoster);

    tvTitle.setText(movieDetail.getTitle());
    tvReleaseDate.setText(movieDetail.getReleaseDate());

    StringBuilder sb = new StringBuilder();
    for (GenresItem item : movieDetail.getGenres()) {
      sb.append(", ").append(item.getName());
    }
    // remove first comma
    sb.deleteCharAt(0);
    tvGenres.setText(sb.toString());

    ratingBar.setRating(movieDetail.getVoteAverage() / 2.0f);
    tvVoterCount.setText(
        getResources()
            .getQuantityString(
                R.plurals.movie_detail_vote_count,
                movieDetail.getVoteCount(),
                movieDetail.getVoteCount()));

    tvDescription.setText(movieDetail.getOverview());
  }

  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_movie_detail, container, false);
  }
}
