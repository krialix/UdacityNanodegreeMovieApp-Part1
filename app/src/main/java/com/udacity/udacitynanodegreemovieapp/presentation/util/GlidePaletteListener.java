package com.udacity.udacitynanodegreemovieapp.presentation.util;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public class GlidePaletteListener implements RequestListener<Drawable> {

  private final OnPaletteReadyListener onPaletteReadyListener;

  public GlidePaletteListener(OnPaletteReadyListener onPaletteReadyListener) {
    this.onPaletteReadyListener = onPaletteReadyListener;
  }

  @Override
  public boolean onLoadFailed(
      @Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
    return false;
  }

  @Override
  public boolean onResourceReady(
      Drawable resource,
      Object model,
      Target<Drawable> target,
      DataSource dataSource,
      boolean isFirstResource) {
    if (resource instanceof BitmapDrawable) {
      Bitmap bitmap = ((BitmapDrawable) resource).getBitmap();
      new Palette.Builder(bitmap).generate(onPaletteReadyListener::onPaletteReady);
    }
    return false;
  }

  public interface OnPaletteReadyListener {
    void onPaletteReady(Palette palette);
  }
}
