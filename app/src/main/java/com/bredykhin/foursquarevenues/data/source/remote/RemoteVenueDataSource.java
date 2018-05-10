package com.bredykhin.foursquarevenues.data.source.remote;

import com.bredykhin.foursquarevenues.data.model.Venue;
import com.bredykhin.foursquarevenues.data.model.VenuePhoto;
import com.bredykhin.foursquarevenues.data.source.VenueDataSource;

import java.util.List;

import io.reactivex.Flowable;

public class RemoteVenueDataSource implements VenueDataSource {

    private VenueService mVenueService;

    public RemoteVenueDataSource(VenueService venueService) {
        mVenueService = venueService;
    }

    @Override
    public Flowable<List<Venue>> getVenues(String city) {
        return mVenueService.getVenues(city).map(response -> response.venues);
    }

    @Override
    public void setVenues(String city, List<Venue> venueList) {
    }

    @Override
    public Flowable<List<String>> getCities() {
        return mVenueService.getCities();
    }

    @Override
    public void setCities(List<String> cities) {
    }

    @Override
    public Flowable<List<VenuePhoto>> getPhotos(String city, Venue venue) {
        return mVenueService.getPhotos(venue.id).map(response -> response.photos.venuePhotos);
    }
}
