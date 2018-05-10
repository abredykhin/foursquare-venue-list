package com.bredykhin.foursquarevenues.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.Log;

/**
 * Holds data + its loading status. Used to provide updates on progress of loading data from local/remote
*
 * @param <T>
 */
public class Response<T> {

    public final Status status;

    @Nullable
    public final T data;

    @Nullable
    public final Throwable error;

    private Response(Status status, @Nullable T data, @Nullable Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public static <T> Response<T> loading() {
        return new Response<>(Status.LOADING, null, null);
    }

    public static <T> Response<T> success(@NonNull T data) {
        return new Response<>(Status.SUCCESS, data, null);
    }

    public static <T> Response<T> error(@NonNull Throwable error) {
        Log.e("Response", error.getMessage());
        return new Response<>(Status.ERROR, null, error);
    }
}
