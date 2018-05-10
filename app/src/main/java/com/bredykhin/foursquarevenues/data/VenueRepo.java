package com.bredykhin.foursquarevenues.data;

import com.bredykhin.foursquarevenues.data.model.Venue;
import com.bredykhin.foursquarevenues.data.model.VenuePhoto;
import com.bredykhin.foursquarevenues.data.source.VenueDataSource;
import com.bredykhin.foursquarevenues.data.source.local.Local;
import com.bredykhin.foursquarevenues.data.source.remote.Remote;
import com.bredykhin.foursquarevenues.util.Response;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class VenueRepo {

    private @Local
    VenueDataSource mLocalDataSource;
    private @Remote
    VenueDataSource mRemoteDataSource;

    @Inject
    public VenueRepo(@Local VenueDataSource localDataSource,
                     @Remote VenueDataSource remoteDataSource) {
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
    }

    public Flowable<Response<List<String>>> getCityList() {
        return Flowable.just(Response.<List<String>>loading()) // notify that we're loading data
                .concatWith(
                        mLocalDataSource.getCities()
                                .filter(list -> !list.isEmpty())
                                .switchIfEmpty(mRemoteDataSource.getCities()
                                        .doOnNext(list -> mLocalDataSource.setCities(list)))
                                .flatMap(cities -> Flowable.just(Response.success(cities))))
                .onErrorReturn(Response::error) // allow UI to gracefully handle errors
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<Response<List<Venue>>> getVenueList(String city) {
        return Flowable.just(Response.<List<Venue>>loading()) // notify that we're loading data
                .concatWith(mLocalDataSource.getVenues(city)
                        .filter(cityList -> !cityList.isEmpty())
                        .switchIfEmpty(mRemoteDataSource.getVenues(city)
                                .doOnNext(list -> mLocalDataSource.setVenues(city, list))
                                .flatMapIterable(list -> list)
                                .flatMap(venue -> getRemoteVenuePhotos(city, venue))
                                .toList().toFlowable()
                        ).flatMap(venues -> Flowable.just(Response.success(venues))))
                .onErrorReturn(Response::error) // allow UI to gracefully handle errors
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Flowable<Venue> getRemoteVenuePhotos(String city, Venue venue) {
        return mRemoteDataSource.getPhotos(city, venue)
                .flatMap(photos -> {
                    venue.photos.addAll(photos);
                    return Flowable.just(venue);
                });

    }

    public Flowable<Response<List<VenuePhoto>>> getVenuePhotos(String city, Venue venue) {
        return Flowable.just(Response.<List<VenuePhoto>>loading()) // notify that we're loading data
                .concatWith(
                        mLocalDataSource.getPhotos(city, venue)
                                .flatMap(photos -> Flowable.just(Response.success(photos))))
                .onErrorReturn(Response::error) // allow UI to gracefully handle errors
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
}
