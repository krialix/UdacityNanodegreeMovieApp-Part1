package com.udacity.udacitynanodegreemovieapp.presentation.feature.movielist;

import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.udacity.udacitynanodegreemovieapp.R;
import com.udacity.udacitynanodegreemovieapp.data.network.MovieDbClient;
import com.udacity.udacitynanodegreemovieapp.data.repository.MovieRepository;
import com.udacity.udacitynanodegreemovieapp.presentation.ui.recyclerview.GridItemDecoration;

import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieListActivity extends AppCompatActivity {

  public static final String SORT_TYPE_POPULAR = "popular";
  public static final String SORT_TYPE_TOP_RATED = "top_rated";
  public static final String SORT_TYPE_FAVORED = "favored";
  private static final String KEY_SORT_TYPE = "SORT_TYPE";

  @BindView(R.id.toolbar)
  Toolbar toolbar;

  @BindView(R.id.movie_list)
  RecyclerView recyclerView;

  @Nullable
  @BindView(R.id.movie_detail_container)
  View movieDetailContainer;

  @BindView(R.id.progressBar)
  ProgressBar progressBar;

  @BindView(R.id.fl_moview_list_container)
  ViewGroup movieListContainer;

  private boolean twoPane;

  private MovieListAdapter adapter;

  private MovieListViewModel viewModel;

  private String sortType = SORT_TYPE_POPULAR;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_movie_list);
    ButterKnife.bind(this);

    twoPane = movieDetailContainer != null;

    setupToolbar();
    setupRecyclerView(recyclerView);

    // set default visibility GONE until loading is completed
    movieListContainer.setVisibility(View.GONE);

    ViewModelProvider.Factory factory =
        new MovieListViewModel.Factory(
            MovieRepository.getInstance(MovieDbClient.getInstance()), getApplication());

    viewModel = new ViewModelProvider(this, factory).get(MovieListViewModel.class);

    if (savedInstanceState != null) {
      sortType = savedInstanceState.getString(KEY_SORT_TYPE);
    }

    viewModel
        .getMovies(sortType)
        .observe(
            this,
            movies -> {
              progressBar.setVisibility(View.GONE);
              movieListContainer.setVisibility(View.VISIBLE);
              adapter.submitList(movies != null ? movies : Collections.emptyList());
            });
  }

  private void setupToolbar() {
    setSupportActionBar(toolbar);
    toolbar.setTitle(getTitle());
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putString(KEY_SORT_TYPE, sortType);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_movie_list, menu);
    return true;
  }

  @Override
  public boolean onPrepareOptionsMenu(Menu menu) {
    setCheckedSortType(menu);
    return super.onPrepareOptionsMenu(menu);
  }

  private void setCheckedSortType(Menu menu) {
    switch (sortType) {
      case SORT_TYPE_POPULAR:
        menu.getItem(0).setChecked(true);
        break;
      case SORT_TYPE_TOP_RATED:
        menu.getItem(1).setChecked(true);
        break;
      case SORT_TYPE_FAVORED:
        menu.getItem(2).setChecked(true);
        break;
    }
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    final int itemId = item.getItemId();

    progressBar.setVisibility(View.VISIBLE);

    item.setChecked(true);

    switch (itemId) {
      case R.id.action_popular:
        sortType = SORT_TYPE_POPULAR;
        viewModel.getMovies(sortType);
        break;
      case R.id.action_top_rated:
        sortType = SORT_TYPE_TOP_RATED;
        viewModel.getMovies(sortType);
        break;
      case R.id.action_favored:
        sortType = SORT_TYPE_FAVORED;
        viewModel.getMovies(sortType);
        break;
    }
    return super.onOptionsItemSelected(item);
  }

  private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
    int columns = getResources().getInteger(R.integer.movie_list_columns);

    RecyclerView.LayoutManager lm = new GridLayoutManager(this, columns);
    recyclerView.setLayoutManager(lm);
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    recyclerView.addItemDecoration(new GridItemDecoration(this, R.dimen.grid_space, columns));

    RequestManager glide = Glide.with(this);

    adapter = new MovieListAdapter(this, glide, twoPane);
    recyclerView.setAdapter(adapter);
  }
}
