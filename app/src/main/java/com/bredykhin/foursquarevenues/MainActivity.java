package com.bredykhin.foursquarevenues;

import android.os.Bundle;

import com.bredykhin.foursquarevenues.data.model.Venue;
import com.bredykhin.foursquarevenues.ui.city.CityListFragment;
import com.bredykhin.foursquarevenues.ui.photos.VenuePhotoFragment;
import com.bredykhin.foursquarevenues.ui.venue.VenueListFragment;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity
        implements CityListFragment.CitySelectionListener,
                   VenueListFragment.VenueSelectionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, new CityListFragment())
                    .commit();
        }

    }

    @Override
    public void onCitySelected(String city) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, VenueListFragment.newInstance(city))
                .addToBackStack("venue")
                .commit();
    }

    @Override
    public void onVenueSelected(String city, Venue venue) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, VenuePhotoFragment.newInstance(city, venue))
                .addToBackStack("venue_photo")
                .commit();
    }
}
