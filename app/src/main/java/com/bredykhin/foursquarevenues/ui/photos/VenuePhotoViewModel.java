package com.bredykhin.foursquarevenues.ui.photos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.ViewModel;

import com.bredykhin.foursquarevenues.data.VenueRepo;
import com.bredykhin.foursquarevenues.data.model.Venue;
import com.bredykhin.foursquarevenues.data.model.VenuePhoto;
import com.bredykhin.foursquarevenues.util.Response;

import java.util.List;

public class VenuePhotoViewModel extends ViewModel {

    private VenueRepo mVenueRepo;

    public VenuePhotoViewModel(VenueRepo repo) {
        mVenueRepo = repo;
    }

    public LiveData<Response<List<VenuePhoto>>> getVenuePhotos(String city, Venue venue) {
        return LiveDataReactiveStreams.fromPublisher(mVenueRepo.getVenuePhotos(city, venue));
    }
}
