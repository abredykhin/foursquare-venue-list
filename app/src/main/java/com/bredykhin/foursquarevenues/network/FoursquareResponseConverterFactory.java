package com.bredykhin.foursquarevenues.network;

import androidx.annotation.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class FoursquareResponseConverterFactory extends Converter.Factory {

    @Nullable
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        Type responseType = new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return new Type[] {type};
            }

            @Override
            public Type getRawType() {
                return FoursquareResponse.class;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        };

        Converter<ResponseBody, FoursquareResponse> delegate =
                retrofit.nextResponseBodyConverter(this, responseType, annotations);
        return (Converter<ResponseBody, Object>) body -> {
            FoursquareResponse<?> response = delegate.convert(body);
            return response.response;
        };
    }
}
