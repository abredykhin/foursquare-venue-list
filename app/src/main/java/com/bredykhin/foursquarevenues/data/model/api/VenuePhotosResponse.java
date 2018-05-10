package com.bredykhin.foursquarevenues.data.model.api;

import com.google.gson.annotations.SerializedName;
import com.bredykhin.foursquarevenues.data.model.VenuePhoto;

import java.util.ArrayList;
import java.util.List;

public class VenuePhotosResponse {

    public Photos photos;

    public class Photos {
        @SerializedName("items")
        public List<VenuePhoto> venuePhotos = new ArrayList<>();
    }
}
