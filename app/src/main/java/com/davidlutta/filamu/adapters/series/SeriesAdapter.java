package com.davidlutta.filamu.adapters.series;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.davidlutta.filamu.R;
import com.davidlutta.filamu.SeriesActivity;
import com.davidlutta.filamu.UI.series.ViewAllSeriesHolderActivity;
import com.davidlutta.filamu.adapters.BaseViewHolder;
import com.davidlutta.filamu.models.series.Series;
import com.davidlutta.filamu.util.Constants;

import java.util.List;

public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder> {
    private Context mContext;
    private List<Series> seriesList;

    public SeriesAdapter(Context mContext, List<Series> seriesList) {
        this.mContext = mContext;
        this.seriesList = seriesList;
    }

    @NonNull
    @Override
    public SeriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new SeriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeriesViewHolder holder, int position) {
        String title = seriesList.get(position).getOriginalName();
        String rating = "Rating: " + seriesList.get(position).getVoteAverage().toString() + " / 10";
        String poster = Constants.IMAGE_BASE_URL + seriesList.get(position).getPosterPath();
        holder.titleTextView.setText(title);
        holder.ratingTextView.setText(rating);
        Glide.with(mContext)
                .load(poster)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(R.drawable.poster)
                .into(holder.backgroundImageView);
    }

    @Override
    public int getItemCount() {
        return seriesList.size();
    }

    private Series getSelectedSeries(int position) {
        if (seriesList.size() > 0) {
            return seriesList.get(position);
        }
        return null;
    }

    public class SeriesViewHolder extends BaseViewHolder{
        private TextView titleTextView;
        private TextView ratingTextView;
        private ImageView backgroundImageView;

        public SeriesViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.itemTitleTextView);
            ratingTextView = itemView.findViewById(R.id.itemRatingTextView);
            backgroundImageView = itemView.findViewById(R.id.itemBackgroundImageView);
        }

        @Override
        protected void onClickItem() {
            int position = getAdapterPosition();
            Series selectedSeries = getSelectedSeries(position);
            String id = selectedSeries.getId().toString();
            Intent intent = new Intent(itemView.getContext(), SeriesActivity.class);
            intent.putExtra(mContext.getString(R.string.movieIntentExtraName), id);
            itemView.getContext().startActivity(intent);
        }

    }
}
