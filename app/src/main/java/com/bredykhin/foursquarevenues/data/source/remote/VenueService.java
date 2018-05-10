package com.bredykhin.foursquarevenues.data.source.remote;

import com.bredykhin.foursquarevenues.data.model.api.VenueListResponse;
import com.bredykhin.foursquarevenues.data.model.api.VenuePhotosResponse;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface VenueService {
    int SEARCH_LIMIT = 25;

    @GET("search?limit=" + SEARCH_LIMIT)
    Flowable<VenueListResponse> getVenues(@Query("near") String city);

    @GET("cities")
    Flowable<List<String>> getCities();

    @GET("{venue}/photos?limit=50")
    Flowable<VenuePhotosResponse> getPhotos(@Path("venue") String venueId);
}
