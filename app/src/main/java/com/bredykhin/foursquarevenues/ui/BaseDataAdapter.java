package com.bredykhin.foursquarevenues.ui;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseDataAdapter<T, VH extends androidx.recyclerview.widget.RecyclerView.ViewHolder> extends androidx.recyclerview.widget.RecyclerView.Adapter<VH> {
    protected ArrayList<T> mData = new ArrayList<>();
    protected OnDataItemClickListener<T> mItemClickListener;

    public BaseDataAdapter(OnDataItemClickListener<T> clickListener) {
        mItemClickListener = clickListener;
    }

    public void setData(List<T> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public interface OnDataItemClickListener<T> {
        OnDataItemClickListener EMPTY = (view, data) -> {};

        void onClick(View view, T data);
    }

}
