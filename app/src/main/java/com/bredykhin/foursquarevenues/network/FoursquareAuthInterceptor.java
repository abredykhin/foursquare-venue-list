package com.bredykhin.foursquarevenues.network;

import androidx.annotation.NonNull;

import com.bredykhin.foursquarevenues.AppConfig;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class FoursquareAuthInterceptor implements Interceptor {

    private static final String PARAM_CLIENT_KEY = "client_id";
    private static final String PARAM_CLIENT_SECRET = "client_secret";
    private static final String PARAM_VERSION = "v";
    private static final String PARAM_VERSION_VALUE = "20180505";

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url().newBuilder()
                .addQueryParameter(PARAM_CLIENT_KEY, AppConfig.FOURSQUARE_CLIENT_KEY)
                .addQueryParameter(PARAM_CLIENT_SECRET, AppConfig.FOURSQUARE_CLIENT_SECRET)
                .addQueryParameter(PARAM_VERSION, PARAM_VERSION_VALUE)
                .build();
        return chain.proceed(request.newBuilder().url(url).build());
    }

}
