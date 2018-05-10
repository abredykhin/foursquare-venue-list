package com.bredykhin.foursquarevenues.ui.city;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bredykhin.foursquarevenues.R;
import com.bredykhin.foursquarevenues.ui.BaseDataAdapter;

public class CityAdapter extends BaseDataAdapter<String, CityAdapter.ViewHolder> {

    public CityAdapter(OnDataItemClickListener<String> clickListener) {
        super(clickListener);
    }

    @NonNull
    @Override
    public CityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_city, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityAdapter.ViewHolder holder, int position) {
        String city = mData.get(position);
        holder.bind(city);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView mNameTextView;

        ViewHolder(View view) {
            super(view);
            mNameTextView = (TextView) view;
        }

        void bind(String city) {
            mNameTextView.setText(city);
            mNameTextView.setOnClickListener(v -> mItemClickListener.onClick(v, city));
        }
    }
}
