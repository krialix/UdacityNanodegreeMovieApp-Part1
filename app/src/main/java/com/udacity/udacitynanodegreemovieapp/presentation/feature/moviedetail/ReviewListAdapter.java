package com.udacity.udacitynanodegreemovieapp.presentation.feature.moviedetail;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.udacitynanodegreemovieapp.R;
import com.udacity.udacitynanodegreemovieapp.data.model.Review;

import butterknife.BindView;
import butterknife.ButterKnife;

class ReviewListAdapter extends ListAdapter<Review, ReviewListAdapter.ReviewViewHolder> {

  private static final DiffUtil.ItemCallback<Review> DIFF_CALLBACK =
      new DiffUtil.ItemCallback<Review>() {
        @Override
        public boolean areItemsTheSame(Review oldItem, Review newItem) {
          return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(Review oldItem, Review newItem) {
          return oldItem.equals(newItem);
        }
      };
  private final Resources resources;

  ReviewListAdapter(Resources resources) {
    super(DIFF_CALLBACK);
    this.resources = resources;
  }

  @NonNull
  @Override
  public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false);
    return new ReviewViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull final ReviewViewHolder holder, int position) {
    holder.bind(getItem(position), resources);
  }

  static class ReviewViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_review_content)
    TextView tvContent;

    ReviewViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }

    void bind(Review review, Resources resources) {
      String content =
          resources.getString(R.string.review_content, review.getContent(), review.getAuthor());
      tvContent.setText(content);
    }
  }
}
