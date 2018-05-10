package com.bredykhin.foursquarevenues.ui.city;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.ViewModel;

import com.bredykhin.foursquarevenues.data.VenueRepo;
import com.bredykhin.foursquarevenues.util.Response;

import java.util.List;

public class CityViewModel extends ViewModel {

    private VenueRepo mVenueRepo;

    public CityViewModel(VenueRepo repo) {
        mVenueRepo = repo;
    }

    public LiveData<Response<List<String>>> getCityList() {
        return LiveDataReactiveStreams.fromPublisher(mVenueRepo.getCityList());
    }
}
