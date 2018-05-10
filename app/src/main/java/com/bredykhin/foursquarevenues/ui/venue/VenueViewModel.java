package com.bredykhin.foursquarevenues.ui.venue;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.ViewModel;

import com.bredykhin.foursquarevenues.data.VenueRepo;
import com.bredykhin.foursquarevenues.data.model.Venue;
import com.bredykhin.foursquarevenues.util.Response;

import java.util.List;

public class VenueViewModel extends ViewModel {

    private VenueRepo mVenueRepo;

    public VenueViewModel(VenueRepo repo) {
        mVenueRepo = repo;
    }

    public LiveData<Response<List<Venue>>> getVenueList(String city) {
        return LiveDataReactiveStreams.fromPublisher(mVenueRepo.getVenueList(city));
    }
}
