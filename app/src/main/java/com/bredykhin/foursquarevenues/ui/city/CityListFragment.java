package com.bredykhin.foursquarevenues.ui.city;

import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bredykhin.foursquarevenues.R;
import com.bredykhin.foursquarevenues.di.ActivityScoped;
import com.bredykhin.foursquarevenues.ui.BaseDataAdapter;
import com.bredykhin.foursquarevenues.ui.BaseFragment;

@ActivityScoped
public class CityListFragment extends BaseFragment<String, CityAdapter.ViewHolder> {

    private CityViewModel mCityViewModel;
    private CitySelectionListener mCitySelectionListener;

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
                new androidx.recyclerview.widget.DividerItemDecoration(requireContext(), androidx.recyclerview.widget.DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setAdapter(mDataAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mCitySelectionListener = (CitySelectionListener) getActivity();
        mCityViewModel = ViewModelProviders.of(this, mViewModelFactory).get(CityViewModel.class);
        mCityViewModel.getCityList().observe(this, this::processResponse);
    }

    @Override
    protected BaseDataAdapter<String, CityAdapter.ViewHolder> createAdapter() {
        return new CityAdapter((view, data) -> mCitySelectionListener.onCitySelected(data));
    }

    public interface CitySelectionListener {
        void onCitySelected(String city);
    }
}
