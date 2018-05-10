package com.bredykhin.foursquarevenues.network;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.bredykhin.foursquarevenues.data.model.VenuePhoto;

import java.lang.reflect.Type;

public class VenuePhotoDeserializer implements JsonDeserializer {

    private static final String PHOTO_DIMENSION = "300";
    private static final String PHOTO_SIZE = "width" + PHOTO_DIMENSION;

    @Override
    public Object deserialize(JsonElement json, Type typeOfT,
                              JsonDeserializationContext context) throws JsonParseException {
        JsonObject photoObject = json.getAsJsonObject();
        String prefix = photoObject.getAsJsonPrimitive("prefix").getAsString();
        String suffix = photoObject.getAsJsonPrimitive("suffix").getAsString();
        VenuePhoto venuePhoto = new VenuePhoto();
        venuePhoto.url = prefix + PHOTO_SIZE + suffix;
        return venuePhoto;
    }
}
