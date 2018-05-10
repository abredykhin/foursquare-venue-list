package com.bredykhin.foursquarevenues.data;

import com.bredykhin.foursquarevenues.data.source.VenueDataSource;
import com.bredykhin.foursquarevenues.data.source.local.Local;
import com.bredykhin.foursquarevenues.data.source.local.LocalVenueDataSource;
import com.bredykhin.foursquarevenues.data.source.remote.Remote;
import com.bredykhin.foursquarevenues.data.source.remote.RemoteVenueDataSource;
import com.bredykhin.foursquarevenues.data.source.remote.VenueService;
import com.bredykhin.foursquarevenues.di.NetworkModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module for {@link VenueRepo}
 */
@Module(includes = {
        NetworkModule.class,
})
public class VenueRepoModule {

    @Singleton
    @Provides
    @Local
    VenueDataSource provideVenueLocalDataSource() {
        return new LocalVenueDataSource();
    }

    @Singleton
    @Provides
    @Remote
    VenueDataSource provideVenueRemoteDataSource(VenueService venueService) {
        return new RemoteVenueDataSource(venueService);
    }
}
