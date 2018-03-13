package com.udacity.udacitynanodegreemovieapp.data.network;

import android.support.annotation.NonNull;

import com.udacity.udacitynanodegreemovieapp.BuildConfig;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/** Adds api_key to each request */
final class TokenInterceptor implements Interceptor {

  private TokenInterceptor() {}

  static TokenInterceptor create() {
    return new TokenInterceptor();
  }

  @Override
  public Response intercept(@NonNull Chain chain) throws IOException {
    Request original = chain.request();
    HttpUrl originalHttpUrl = original.url();

    HttpUrl url =
        originalHttpUrl.newBuilder().addQueryParameter("api_key", BuildConfig.API_KEY).build();

    Request.Builder requestBuilder = original.newBuilder().url(url);

    Request request = requestBuilder.build();
    return chain.proceed(request);
  }
}
