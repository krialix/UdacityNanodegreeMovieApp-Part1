package com.udacity.udacitynanodegreemovieapp.presentation.util;

import android.support.annotation.NonNull;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class ImageUrlResolver {

  public static final String MODIFIER_W92 = "w92";
  public static final String MODIFIER_W154 = "w154";
  public static final String MODIFIER_W185 = "w185";
  public static final String MODIFIER_W342 = "w342";
  public static final String MODIFIER_W500 = "w500";
  public static final String MODIFIER_W780 = "w780";

  private ImageUrlResolver() {}

  @NonNull
  public static String getTdmbImageUrl(@NonNull String path, @ModifierType String modifier) {
    return "http://image.tmdb.org/t/p/" + modifier + "/" + path;
  }

  public static String getYoutubeThumbUrl(@NonNull String videoId) {
    return "https://img.youtube.com/vi/" + videoId + "/default.jpg";
  }

  @Retention(RetentionPolicy.SOURCE)
  @StringDef({
    MODIFIER_W92,
    MODIFIER_W154,
    MODIFIER_W185,
    MODIFIER_W342,
    MODIFIER_W500,
    MODIFIER_W780
  })
  public @interface ModifierType {}
}
