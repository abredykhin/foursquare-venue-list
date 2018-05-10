package com.bredykhin.foursquarevenues.ui.photos;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.bredykhin.foursquarevenues.R;
import com.bredykhin.foursquarevenues.data.model.VenuePhoto;
import com.bredykhin.foursquarevenues.ui.BaseDataAdapter;

public class VenuePhotosAdapter extends BaseDataAdapter<VenuePhoto, VenuePhotosAdapter.ViewHolder> {

    private final RequestManager mGlideRequestManager;

    public VenuePhotosAdapter(OnDataItemClickListener<VenuePhoto> clickListener, RequestManager requestManager) {
        super(clickListener);
        mGlideRequestManager = requestManager;
    }

    @NonNull
    @Override
    public VenuePhotosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_photo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VenuePhotosAdapter.ViewHolder holder, int position) {
        VenuePhoto photo = mData.get(position);
        holder.bind(photo);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView ivPhoto;

        ViewHolder(View view) {
            super(view);
            ivPhoto = (ImageView) view;
        }

        void bind(VenuePhoto photo) {
            mGlideRequestManager
                    .load(photo.url)
                    .apply(RequestOptions.centerCropTransform())
                    .into(ivPhoto);
        }
    }

}
