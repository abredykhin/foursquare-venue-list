package com.bredykhin.foursquarevenues.di;

import android.app.Application;

import com.bredykhin.foursquarevenues.App;
import com.bredykhin.foursquarevenues.data.VenueRepoModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Main Dagger Component
 */
@Singleton
@Component(modules = {
        VenueRepoModule.class,
        ApplicationModule.class,
        ActivityBindingModule.class,
        AndroidSupportInjectionModule.class
})
public interface AppComponent extends AndroidInjector<App> {

    // This is to be able to do DaggerAppComponent.builder().application(this).build().inject(this);
    // instead of instating individual modules when constructing the graph.
    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
