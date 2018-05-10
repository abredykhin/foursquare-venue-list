package com.bredykhin.foursquarevenues.di;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.bredykhin.foursquarevenues.data.model.VenuePhoto;
import com.bredykhin.foursquarevenues.data.source.remote.VenueService;
import com.bredykhin.foursquarevenues.network.FoursquareAuthInterceptor;
import com.bredykhin.foursquarevenues.network.FoursquareResponseConverterFactory;
import com.bredykhin.foursquarevenues.network.VenuePhotoDeserializer;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Dagger module for network stuff, allows building mock/debug versions of Retrofit/Gson and
 * other objects
 */
@Module
public class NetworkModule {

    @Singleton
    @Provides
    VenueService provideVenueService(Context context, Retrofit retrofit) {
        return retrofit.create(VenueService.class);
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(@Named("baseUrl") String baseUrl, OkHttpClient client,
                             GsonConverterFactory gsonConverterFactory) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(new FoursquareResponseConverterFactory())
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .build();
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient().newBuilder()
                .addInterceptor(new FoursquareAuthInterceptor()).build();
    }

    @Singleton
    @Provides
    GsonConverterFactory provideGsonConverterFactory() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(VenuePhoto.class, new VenuePhotoDeserializer());
        return GsonConverterFactory.create(gsonBuilder.create());
    }

    @Provides
    @Named("baseUrl")
    String provideBaseUrl() {
        return "https://api.foursquare.com/v2/venues/";
    }
}
