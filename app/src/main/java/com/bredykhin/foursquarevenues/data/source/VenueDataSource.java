package com.bredykhin.foursquarevenues.data.source;

import com.bredykhin.foursquarevenues.data.model.Venue;
import com.bredykhin.foursquarevenues.data.model.VenuePhoto;

import java.util.List;

import io.reactivex.Flowable;

public interface VenueDataSource {
    Flowable<List<Venue>> getVenues(String city);
    void setVenues(String city, List<Venue> venueList);

    Flowable<List<String>> getCities();
    void setCities(List<String> cities);

    Flowable<List<VenuePhoto>> getPhotos(String city, Venue venue);
}
