package com.bredykhin.foursquarevenues.ui.venue;

import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bredykhin.foursquarevenues.R;
import com.bredykhin.foursquarevenues.data.model.Venue;
import com.bredykhin.foursquarevenues.di.ActivityScoped;
import com.bredykhin.foursquarevenues.ui.BaseDataAdapter;
import com.bredykhin.foursquarevenues.ui.BaseFragment;

@ActivityScoped
public class VenueListFragment extends BaseFragment<Venue, VenueAdapter.ViewHolder> {

    private static final String ARG_CITY = "city";

    private VenueViewModel mVenueViewModel;
    private VenueSelectionListener mVenueSelectionListener;
    private String mCity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        mRecyclerView.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(getContext()));
        DividerItemDecoration itemDecoration =
                new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setAdapter(mDataAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mVenueSelectionListener = (VenueSelectionListener) getActivity();
        mVenueViewModel = ViewModelProviders.of(this, mViewModelFactory).get(VenueViewModel.class);
        mVenueViewModel.getVenueList(mCity).observe(this, this::processResponse);
    }

    @Override
    protected BaseDataAdapter<Venue, VenueAdapter.ViewHolder> createAdapter() {
        RequestManager requestManager = Glide.with(this);
        return new VenueAdapter((view, data) -> mVenueSelectionListener.onVenueSelected(mCity, data),
                requestManager);
    }

    public interface VenueSelectionListener {
        void onVenueSelected(String city, Venue venue);
    }

    public static VenueListFragment newInstance(String city) {
        VenueListFragment fragment = new VenueListFragment();
        Bundle arguments = new Bundle();
        arguments.putString(ARG_CITY, city);
        fragment.setArguments(arguments);
        return fragment;
    }
}
