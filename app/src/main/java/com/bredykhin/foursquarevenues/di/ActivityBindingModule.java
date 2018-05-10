package com.bredykhin.foursquarevenues.di;

import com.bredykhin.foursquarevenues.MainActivity;
import com.bredykhin.foursquarevenues.ui.city.CityModule;
import com.bredykhin.foursquarevenues.ui.photos.VenuePhotoModule;
import com.bredykhin.foursquarevenues.ui.venue.VenueModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Separation of concerns between {@link AppComponent} and sub-components (don't know about each
 * other). New sub-component will be generated for MainActivity
 */
@Module
public abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = {CityModule.class, VenueModule.class, VenuePhotoModule.class})
    abstract MainActivity mainActivity();
}
