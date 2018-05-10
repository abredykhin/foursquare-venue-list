package com.bredykhin.foursquarevenues.data.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Venue implements Serializable {
    public String id;
    public String name;
    public VenueLocation location;
    public List<VenueCategory> categories;
    public List<VenuePhoto> photos = new ArrayList<>();
    public boolean isBookmarked;

    public String formatCategories() {
        if(categories == null || categories.isEmpty()) {
            return "";
        } else {
            StringBuilder builder = new StringBuilder();
            for (VenueCategory category : categories) {
                builder.append(category.name);
                builder.append(", ");
            }

            builder.delete(builder.lastIndexOf(","), builder.length());
            return builder.toString();
        }
    }
}
