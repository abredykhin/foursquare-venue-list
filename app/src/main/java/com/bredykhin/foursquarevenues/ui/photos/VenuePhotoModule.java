package com.bredykhin.foursquarevenues.ui.photos;

import com.bredykhin.foursquarevenues.di.FragmentScoped;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class VenuePhotoModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract VenuePhotoFragment venuePhotoFragment(); //provide injector for the fragment
}
