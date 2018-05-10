package com.bredykhin.foursquarevenues.data.model;

import android.text.TextUtils;

public class VenueLocation {
    public String address;
    public String city;
    public String state;

    public String format() {
        if(!TextUtils.isEmpty(address)) {
            return address;
        } else if (!TextUtils.isEmpty(city)) {
            return city + (!TextUtils.isEmpty(state) ? ", " + state : "");
        } else if(!TextUtils.isEmpty(state)) {
            return state;
        } else {
            return "";
        }
    }
}
