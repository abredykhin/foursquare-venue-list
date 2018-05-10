package com.bredykhin.foursquarevenues.ui.venue;

import com.bredykhin.foursquarevenues.di.FragmentScoped;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class VenueModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract VenueListFragment venueListFragment(); //provide injector for the fragment
}
