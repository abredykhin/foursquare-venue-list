package com.bredykhin.foursquarevenues.data.source.local;

import com.bredykhin.foursquarevenues.data.model.Venue;
import com.bredykhin.foursquarevenues.data.model.VenuePhoto;
import com.bredykhin.foursquarevenues.data.source.VenueDataSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Flowable;

/**
 * Local data source. Should be either Room or SQLite database. Simple in-memory cache for brevity
 */
public class LocalVenueDataSource implements VenueDataSource {
    private final HashMap<String, List<Venue>> mVenueMap = new HashMap<>();
    private final List<String> mCities =
            Arrays.asList("San Francisco, CA", "New York, NY", "Seattle, WA", "Los Angeles, CA",
                    "Chicago, IL", "Washington, DC", "Las Vegas, NV", "New Orleans, LA",
                    "Oakland, CA", "Miami, FL");

    public LocalVenueDataSource() {
    }

    @Override
    public Flowable<List<Venue>> getVenues(String city) {
        if(!mVenueMap.containsKey(city)) {
            mVenueMap.put(city, new ArrayList<>());
        }

        return Flowable.just(mVenueMap.get(city));
    }

    @Override
    public void setVenues(String city, List<Venue> venueList) {
        if(!mVenueMap.containsKey(city)) {
            mVenueMap.put(city, Collections.emptyList());
        }

        mVenueMap.get(city).clear();
        mVenueMap.get(city).addAll(venueList);
    }

    @Override
    public Flowable<List<String>> getCities() {
        return Flowable.just(mCities);
    }

    @Override
    public void setCities(List<String> cities) {
        mCities.clear();;
        mCities.addAll(cities);
    }

    @Override
    public Flowable<List<VenuePhoto>> getPhotos(String city, Venue venue) {
        for(Venue v : mVenueMap.get(city)) {
            if(v == venue) {
                return Flowable.just(venue.photos);
            }
        }

        return Flowable.just(Collections.emptyList());
    }
}
