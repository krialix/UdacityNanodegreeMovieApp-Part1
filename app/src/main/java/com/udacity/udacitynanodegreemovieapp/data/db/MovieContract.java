package com.udacity.udacitynanodegreemovieapp.data.db;

import android.net.Uri;
import android.provider.BaseColumns;

public class MovieContract {
  public static final String CONTENT_AUTHORITY = "com.udacity.udacitynanodegreemovieapp";
  public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
  public static final String PATH_MOVIE = "movie";

  public static final class MovieEntry implements BaseColumns {
    public static final Uri CONTENT_URI =
        BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIE).build();

    public static final String TABLE_NAME = "movie";

    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_POSTER_PATH = "poster_path";
    public static final String COLUMN_VOTE_AVERAGE = "vote_average";
  }
}
