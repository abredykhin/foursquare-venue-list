package com.bredykhin.foursquarevenues.ui.photos;

import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bredykhin.foursquarevenues.R;
import com.bredykhin.foursquarevenues.data.model.Venue;
import com.bredykhin.foursquarevenues.data.model.VenuePhoto;
import com.bredykhin.foursquarevenues.di.ActivityScoped;
import com.bredykhin.foursquarevenues.ui.BaseDataAdapter;
import com.bredykhin.foursquarevenues.ui.BaseFragment;

@ActivityScoped
public class VenuePhotoFragment extends BaseFragment<VenuePhoto, VenuePhotosAdapter.ViewHolder> {
    private static final int GRID_COLUMNS = 3;
    private static final String ARG_VENUE = "venue";
    private static final String ARG_CITY = "city";

    private VenuePhotoViewModel mVenuePhotoViewModel;
    private Venue mVenue;
    private String mCity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        setRetainInstance(false);

        mVenue = (Venue) getArguments().getSerializable(ARG_VENUE);
        mCity = getArguments().getString(ARG_CITY);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView.setLayoutManager(new androidx.recyclerview.widget.GridLayoutManager(getContext(), GRID_COLUMNS
                , GridLayoutManager.VERTICAL, false));
        androidx.recyclerview.widget.DividerItemDecoration itemDecoration =
                new DividerItemDecoration(requireContext(), androidx.recyclerview.widget.DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setAdapter(mDataAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mVenuePhotoViewModel = ViewModelProviders.of(this, mViewModelFactory).get(VenuePhotoViewModel.class);
        mVenuePhotoViewModel.getVenuePhotos(mCity, mVenue).observe(this, this::processResponse);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.photos_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bookmark:
                mVenue.isBookmarked = !mVenue.isBookmarked;
                getActivity().invalidateOptionsMenu();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.bookmark).setIcon(mVenue.isBookmarked
                ? android.R.drawable.star_big_on : android.R.drawable.star_big_off);
        super.onPrepareOptionsMenu(menu);
    }


    @Override
    protected BaseDataAdapter<VenuePhoto, VenuePhotosAdapter.ViewHolder> createAdapter() {
        RequestManager requestManager = Glide.with(this);
        return new VenuePhotosAdapter((view, data) -> {
        }, requestManager);
    }

    public static VenuePhotoFragment newInstance(String city, Venue venue) {
        VenuePhotoFragment fragment = new VenuePhotoFragment();
        Bundle arguments = new Bundle();
        arguments.putSerializable(ARG_VENUE, venue);
        arguments.putString(ARG_CITY, city);
        fragment.setArguments(arguments);
        return fragment;
    }
}
