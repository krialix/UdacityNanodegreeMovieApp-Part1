package com.udacity.udacitynanodegreemovieapp.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MovieDbHelper extends SQLiteOpenHelper {
  public static final String DATABASE_NAME = "movie.db";
  private static final int DATABASE_VERSION = 1;

  public MovieDbHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    final String SQL_CREATE_MOVIE_TABLE =
        "CREATE TABLE "
            + MovieContract.MovieEntry.TABLE_NAME
            + " ("
            + MovieContract.MovieEntry.COLUMN_ID
            + " TEXT PRIMARY KEY, "
            + MovieContract.MovieEntry.COLUMN_TITLE
            + " TEXT, "
            + MovieContract.MovieEntry.COLUMN_POSTER_PATH
            + " INT, "
            + MovieContract.MovieEntry.COLUMN_VOTE_AVERAGE
            + " TEXT"
            + ")";
    db.execSQL(SQL_CREATE_MOVIE_TABLE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + MovieContract.MovieEntry.TABLE_NAME);
    onCreate(db);
  }
}
