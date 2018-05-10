package com.bredykhin.foursquarevenues.ui;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.bredykhin.foursquarevenues.data.VenueRepo;
import com.bredykhin.foursquarevenues.ui.city.CityViewModel;
import com.bredykhin.foursquarevenues.ui.photos.VenuePhotoViewModel;
import com.bredykhin.foursquarevenues.ui.venue.VenueViewModel;

import javax.inject.Inject;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private VenueRepo mVenueRepo;

    @Inject
    public ViewModelFactory(VenueRepo venueRepo) {
        mVenueRepo = venueRepo;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(CityViewModel.class)) {
            return (T) new CityViewModel(mVenueRepo);
        } else if(modelClass.isAssignableFrom(VenueViewModel.class)) {
            return (T) new VenueViewModel(mVenueRepo);
        } else if(modelClass.isAssignableFrom(VenuePhotoViewModel.class)) {
            return (T) new VenuePhotoViewModel(mVenueRepo);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
