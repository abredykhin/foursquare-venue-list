package com.bredykhin.foursquarevenues.di;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

/**
 * Used to bind our Application class as a Context in the {@link AppComponent}
 */
@Module
public abstract class ApplicationModule {
    //expose Application as an injectable context
    @Binds
    abstract Context bindContext(Application application);
}

