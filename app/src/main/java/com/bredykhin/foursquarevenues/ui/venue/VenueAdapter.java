package com.bredykhin.foursquarevenues.ui.venue;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.bredykhin.foursquarevenues.R;
import com.bredykhin.foursquarevenues.data.model.Venue;
import com.bredykhin.foursquarevenues.ui.BaseDataAdapter;

public class VenueAdapter extends BaseDataAdapter<Venue, VenueAdapter.ViewHolder> {

    private final RequestManager mGlideRequestManager;

    public VenueAdapter(OnDataItemClickListener<Venue> clickListener, RequestManager requestManager) {
        super(clickListener);
        mGlideRequestManager = requestManager;
    }

    @NonNull
    @Override
    public VenueAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_venue, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VenueAdapter.ViewHolder holder, int position) {
        Venue venue = mData.get(position);
        holder.bind(venue);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView ivVenue;
        final ImageView ivBookmark;
        final TextView tvName;
        final TextView tvLocation;
        final TextView tvCategories;

        ViewHolder(View view) {
            super(view);
            ivVenue = view.findViewById(R.id.venue_image);
            ivBookmark = view.findViewById(R.id.bookmark);
            tvName = view.findViewById(R.id.venue_name);
            tvLocation = view.findViewById(R.id.venue_location);
            tvCategories = view.findViewById(R.id.venue_categories);
        }

        void bind(Venue venue) {
            itemView.setOnClickListener(v -> mItemClickListener.onClick(itemView, venue));
            if(venue.photos.size() > 0) {
                mGlideRequestManager
                        .load(venue.photos.get(0).url)
                        .apply(RequestOptions.centerCropTransform())
                        .into(ivVenue);
            }

            ivBookmark.setImageResource(venue.isBookmarked ?
                    android.R.drawable.star_big_on : android.R.drawable.star_big_off);
            tvName.setText(venue.name);
            tvLocation.setText(venue.location.format());
            tvCategories.setText(venue.formatCategories());
        }
    }
}
