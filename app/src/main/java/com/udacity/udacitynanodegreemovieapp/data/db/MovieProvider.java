package com.udacity.udacitynanodegreemovieapp.data.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Objects;

public class MovieProvider extends ContentProvider {
  public static final int CODE_MOVIE = 100;

  private static final UriMatcher uriMatcher = buildUriMatcher();
  private MovieDbHelper dbHelper;

  public static UriMatcher buildUriMatcher() {
    UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    String authority = MovieContract.CONTENT_AUTHORITY;
    matcher.addURI(authority, MovieContract.PATH_MOVIE, CODE_MOVIE);
    return matcher;
  }

  @Override
  public boolean onCreate() {
    dbHelper = new MovieDbHelper(getContext());
    return true;
  }

  @Nullable
  @Override
  public Cursor query(
      @NonNull Uri uri,
      @Nullable String[] projection,
      @Nullable String selection,
      @Nullable String[] selectionArgs,
      @Nullable String sortOrder) {
    Cursor cursor;
    switch (uriMatcher.match(uri)) {
      case CODE_MOVIE:
        cursor =
            dbHelper
                .getReadableDatabase()
                .query(
                    MovieContract.MovieEntry.TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    sortOrder);
        break;
      default:
        throw new UnsupportedOperationException("Unknown uri: " + uri);
    }
    cursor.setNotificationUri(Objects.requireNonNull(getContext()).getContentResolver(), uri);
    return cursor;
  }

  @Override
  public int delete(
      @NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
    int rowsDeleted;
    if (selection == null) {
      selection = "1";
    }
    switch (uriMatcher.match(uri)) {
      case CODE_MOVIE:
        rowsDeleted =
            dbHelper
                .getWritableDatabase()
                .delete(MovieContract.MovieEntry.TABLE_NAME, selection, selectionArgs);
        break;
      default:
        throw new UnsupportedOperationException("Unknown uri: " + uri);
    }
    return rowsDeleted;
  }

  @Nullable
  @Override
  public String getType(@NonNull Uri uri) {
    return null;
  }

  @NonNull
  @Override
  public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
    SQLiteDatabase db = dbHelper.getWritableDatabase();
    switch (uriMatcher.match(uri)) {
      case CODE_MOVIE:
        db.insert(MovieContract.MovieEntry.TABLE_NAME, null, values);
        return MovieContract.MovieEntry.CONTENT_URI;
      default:
        throw new UnsupportedOperationException("Unknown uri: " + uri);
    }
  }

  @Override
  public int update(
      @NonNull Uri uri,
      @Nullable ContentValues values,
      @Nullable String selection,
      @Nullable String[] selectionArgs) {
    throw new UnsupportedOperationException("Update is not allowed in favorites DB");
  }
}
