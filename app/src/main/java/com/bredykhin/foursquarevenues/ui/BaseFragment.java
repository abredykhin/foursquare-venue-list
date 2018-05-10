package com.bredykhin.foursquarevenues.ui;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.bredykhin.foursquarevenues.R;
import com.bredykhin.foursquarevenues.di.ActivityScoped;
import com.bredykhin.foursquarevenues.util.Response;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

@ActivityScoped
public abstract class BaseFragment<T, VH extends RecyclerView.ViewHolder> extends DaggerFragment {

    @Inject
    protected ViewModelFactory mViewModelFactory; // Required because ViewModel has non-default constructor
    protected View mLoadingView;
    protected View mErrorView;
    protected RecyclerView mRecyclerView;
    protected BaseDataAdapter<T, VH> mDataAdapter;

    public BaseFragment() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mLoadingView = view.findViewById(R.id.loading);
        mErrorView = view.findViewById(R.id.error);
        mRecyclerView = view.findViewById(R.id.list);

        mDataAdapter = createAdapter();
    }

    protected void processResponse(Response<List<T>> listResponse) {
        switch (listResponse.status) {
            case LOADING:
                mLoadingView.setVisibility(View.VISIBLE);
                mErrorView.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
                break;
            case SUCCESS:
                mLoadingView.setVisibility(View.GONE);
                mErrorView.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
                mDataAdapter.setData(listResponse.data);
                break;
            case ERROR:
                mLoadingView.setVisibility(View.GONE);
                mErrorView.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);
                break;
        }
    }

    abstract protected BaseDataAdapter<T, VH> createAdapter();
}
