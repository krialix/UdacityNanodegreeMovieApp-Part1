package com.udacity.udacitynanodegreemovieapp.presentation.feature.moviedetail;

import android.arch.lifecycle.ViewModelProvider;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.udacity.udacitynanodegreemovieapp.R;
import com.udacity.udacitynanodegreemovieapp.data.model.GenresItem;
import com.udacity.udacitynanodegreemovieapp.data.model.MovieDetail;
import com.udacity.udacitynanodegreemovieapp.data.model.ReviewResponse;
import com.udacity.udacitynanodegreemovieapp.data.model.Trailer;
import com.udacity.udacitynanodegreemovieapp.data.model.TrailerResponse;
import com.udacity.udacitynanodegreemovieapp.data.network.MovieDbClient;
import com.udacity.udacitynanodegreemovieapp.data.repository.MovieRepository;
import com.udacity.udacitynanodegreemovieapp.presentation.feature.movielist.MovieListActivity;
import com.udacity.udacitynanodegreemovieapp.presentation.util.ImageUrlResolver;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MovieDetailActivity extends AppCompatActivity {

  private static final String ARG_MOVIE_ID = "ARG_MOVIE_ID";

  @BindView(R.id.detail_toolbar)
  Toolbar toolbar;

  @BindView(R.id.app_bar)
  AppBarLayout appBarLayout;

  @BindView(R.id.toolbar_layout)
  CollapsingToolbarLayout collapsingToolbarLayout;

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

  @BindView(R.id.rv_movie_detail_trailers)
  RecyclerView rvTrailers;

  @BindView(R.id.rv_movie_detail_reviews)
  RecyclerView rvReviews;

  @BindView(R.id.fab_movie_detail_favorite)
  FloatingActionButton fabFavorite;

  private ReviewListAdapter reviewListAdapter;
  private TrailerListAdapter trailerListAdapter;

  private MovieDetailViewModel viewModel;

  private MovieDetail movieDetail;

  public static void start(Context context, int movieId) {
    Intent starter = new Intent(context, MovieDetailActivity.class);
    starter.putExtra(ARG_MOVIE_ID, movieId);
    context.startActivity(starter);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_movie_detail);
    ButterKnife.bind(this);
    setupToolbar();

    setupReviewsRecyclerView();
    setupTrailersRecyclerView();

    int movieId = getIntent().getIntExtra(ARG_MOVIE_ID, 0);

    ViewModelProvider.Factory factory =
        new MovieDetailViewModel.Factory(
            getApplication(), MovieRepository.getInstance(MovieDbClient.getInstance()), movieId);

    viewModel = new ViewModelProvider(this, factory).get(MovieDetailViewModel.class);

    viewModel.getMovie().observe(this, this::setMovieDetails);

    viewModel.getReviews().observe(this, this::setMovieReviews);

    viewModel.getTrailers().observe(this, this::setMovieTrailers);

    viewModel
        .getFavorite()
        .observe(
            this,
            selected -> {
              Log.d("MovieDetail", "onCreate: " + selected);
              fabFavorite.setSelected(selected);
            });
  }

  private void setupToolbar() {
    setSupportActionBar(toolbar);

    if (collapsingToolbarLayout != null) {
      collapsingToolbarLayout.setTitle("");
    }

    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
      actionBar.setTitle("");
    }
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == android.R.id.home) {
      navigateUpTo(new Intent(this, MovieListActivity.class));
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  private void setMovieDetails(MovieDetail movieDetail) {
    this.movieDetail = movieDetail;

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

  private void setMovieReviews(ReviewResponse reviewResponse) {
    reviewListAdapter.submitList(reviewResponse.getResults());
  }

  private void setMovieTrailers(TrailerResponse trailerResponse) {
    trailerListAdapter.submitList(trailerResponse.getResults());
  }

  private void setupReviewsRecyclerView() {
    rvReviews.setItemAnimator(new DefaultItemAnimator());
    rvReviews.addItemDecoration(
        new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));

    reviewListAdapter = new ReviewListAdapter(getResources());
    rvReviews.setAdapter(reviewListAdapter);
  }

  private void setupTrailersRecyclerView() {
    RecyclerView.LayoutManager lm =
        new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
    rvTrailers.setLayoutManager(lm);

    rvTrailers.setItemAnimator(new DefaultItemAnimator());

    RequestManager glide = Glide.with(this);
    trailerListAdapter = new TrailerListAdapter(glide, this::openTrailer);

    rvTrailers.setAdapter(trailerListAdapter);
  }

  @OnClick(R.id.fab_movie_detail_favorite)
  void toggleFavoriteMovie(View view) {
    if (movieDetail != null) {
      viewModel.toggleFavoriteMovie(movieDetail);
    }
  }

  private void openTrailer(@Nullable Trailer trailer) {
    if (trailer != null) {
      Uri uriApp = Uri.parse("vnd.youtube:" + trailer.getKey());
      Uri uriWeb = Uri.parse("http://www.youtube.com/watch?v=" + trailer.getKey());
      Intent intent = null;
      try {
        intent = new Intent(Intent.ACTION_VIEW, uriApp);
      } catch (ActivityNotFoundException e) {
        intent = new Intent(Intent.ACTION_VIEW, uriWeb);
      } finally {
        startActivity(intent);
      }
    }
  }
}
