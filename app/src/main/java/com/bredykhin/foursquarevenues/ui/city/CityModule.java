package com.bredykhin.foursquarevenues.ui.city;

import com.bredykhin.foursquarevenues.data.VenueRepo;
import com.bredykhin.foursquarevenues.di.FragmentScoped;
import com.bredykhin.foursquarevenues.ui.ViewModelFactory;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class CityModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract CityListFragment cityListFragment(); //provide injector for the fragment

    @Provides
    static ViewModelFactory provideViewModelFactory(VenueRepo venueRepo) {
        return new ViewModelFactory(venueRepo); // inject repository into ViewModel
    }

}
