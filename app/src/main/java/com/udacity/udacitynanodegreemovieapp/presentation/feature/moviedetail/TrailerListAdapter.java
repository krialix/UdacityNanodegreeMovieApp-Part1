package com.udacity.udacitynanodegreemovieapp.presentation.feature.moviedetail;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.udacity.udacitynanodegreemovieapp.R;
import com.udacity.udacitynanodegreemovieapp.data.model.Trailer;
import com.udacity.udacitynanodegreemovieapp.presentation.util.ImageUrlResolver;

import butterknife.BindView;
import butterknife.ButterKnife;

class TrailerListAdapter extends ListAdapter<Trailer, TrailerListAdapter.TrailerViewHolder> {

  private static final DiffUtil.ItemCallback<Trailer> DIFF_CALLBACK =
      new DiffUtil.ItemCallback<Trailer>() {
        @Override
        public boolean areItemsTheSame(Trailer oldItem, Trailer newItem) {
          return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(Trailer oldItem, Trailer newItem) {
          return oldItem.equals(newItem);
        }
      };

  private final RequestManager glide;
  private final TrailerViewHolder.OnTrailerClickListener onTrailerClickListener;

  TrailerListAdapter(
      RequestManager glide, TrailerViewHolder.OnTrailerClickListener onTrailerClickListener) {
    super(DIFF_CALLBACK);
    this.glide = glide;
    this.onTrailerClickListener = onTrailerClickListener;
  }

  @NonNull
  @Override
  public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trailer, parent, false);
    return new TrailerViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull final TrailerViewHolder holder, int position) {
    holder.bind(getItem(position), glide, onTrailerClickListener);
  }

  static class TrailerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.iv_trailer)
    ImageView ivTrailer;

    private OnTrailerClickListener onTrailerClickListener;
    private Trailer trailer;

    TrailerViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
      view.setOnClickListener(this);
    }

    void bind(
        Trailer trailer, RequestManager glide, OnTrailerClickListener onTrailerClickListener) {
      this.onTrailerClickListener = onTrailerClickListener;
      this.trailer = trailer;
      glide.load(ImageUrlResolver.getYoutubeThumbUrl(trailer.getKey())).into(ivTrailer);
    }

    @Override
    public void onClick(View v) {
      onTrailerClickListener.onTrailerClick(trailer);
    }

    public interface OnTrailerClickListener {
      void onTrailerClick(Trailer trailer);
    }
  }
}
