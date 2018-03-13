package com.udacity.udacitynanodegreemovieapp.data.network;

import com.udacity.udacitynanodegreemovieapp.BuildConfig;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class MovieDbClient {

  private static MovieDbClient instance = null;

  private MovieDbService movieDbService;

  private MovieDbClient() {
    OkHttpClient.Builder builder = new OkHttpClient.Builder();
    builder.addInterceptor(TokenInterceptor.create());

    if (BuildConfig.DEBUG) {
      builder.addInterceptor(getLoggingInterceptor());
    }

    OkHttpClient client = builder.build();

    Retrofit retrofit =
        new Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_API_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build();

    movieDbService = retrofit.create(MovieDbService.class);
  }

  /**
   * Gets instance.
   *
   * @return the instance
   */
  public static MovieDbClient getInstance() {
    if (instance == null) {
      instance = new MovieDbClient();
    }
    return instance;
  }

  private static Interceptor getLoggingInterceptor() {
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
    return loggingInterceptor;
  }

  /**
   * Gets movie db service.
   *
   * @return the movie db service
   */
  public MovieDbService getMovieDbService() {
    return movieDbService;
  }
}
