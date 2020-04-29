package com.davidlutta.filamu.adapters.series;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.davidlutta.filamu.R;
import com.davidlutta.filamu.adapters.BaseViewHolder;
import com.davidlutta.filamu.models.series.Series;
import com.davidlutta.filamu.util.Constants;

public class ViewAllSeriesAdapter extends PagedListAdapter<Series, ViewAllSeriesAdapter.SeriesViewHolder> {
    private Context mContext;

    public ViewAllSeriesAdapter(Context context) {
        super(Series.CALLBACK);
        mContext = context;
    }

    @NonNull
    @Override
    public SeriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewAllSeriesAdapter.SeriesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_two, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SeriesViewHolder holder, int position) {
        holder.onBind(getItem(position));
    }

    private Series getSelectedSeries(int position) {
        if (getCurrentList().size() > 0) {
            return getItem(position);
        }
        return null;
    }

    public class SeriesViewHolder extends BaseViewHolder {
        private TextView titleTextView;
        private TextView ratingTextView;
        private ImageView backgroundImageView;

        public SeriesViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.itemTitleTextView);
            ratingTextView = itemView.findViewById(R.id.itemRatingTextView);
            backgroundImageView = itemView.findViewById(R.id.itemBackgroundImageView);
        }

        public void onBind(Series series) {
            if (series != null) {
                String rating = "Rating: " + series.getVoteAverage().toString() + " / 10";
                titleTextView.setText(series.getOriginalName());
                ratingTextView.setText(rating);
                String poster = Constants.IMAGE_BASE_URL + series.getPosterPath();
                Glide.with(mContext)
                        .load(poster)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .placeholder(R.drawable.poster)
                        .into(backgroundImageView);
            }
        }

        @Override
        protected void onClickItem() {
            int position = getAdapterPosition();
            Series selectedSeries = getSelectedSeries(position);
            String id = selectedSeries.getId().toString();
            /*Intent intent = new Intent(itemView.getContext(), MovieActivity.class);
            intent.putExtra(mContext.getString(R.string.movieIntentExtraName), id);
            itemView.getContext().startActivity(intent);*/
        }
    }
}
