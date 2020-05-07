package com.davidlutta.filamu.adapters.trailers;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.davidlutta.filamu.R;
import com.davidlutta.filamu.models.trailers.Trailer;
import com.davidlutta.filamu.util.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.TrailersViewHolder> {
    private Context mContext;
    private List<Trailer> trailerList;
    private final int limit = 3;

    public TrailersAdapter(Context mContext, List<Trailer> trailerList) {
        this.mContext = mContext;
        this.trailerList = trailerList;
    }

    @NonNull
    @Override
    public TrailersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_item, parent, false);
        return new TrailersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailersViewHolder holder, int position) {
        Trailer trailer = trailerList.get(position);
        String thumbnail = Constants.THUMBNAIL_BASE_URL + trailer.getKey() + Constants.THUMBNAIL_END;
        Glide.with(mContext)
                .load(thumbnail)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(R.drawable.poster)
                .into(holder.trailersImageView);
    }

    private Trailer getSelectedTrailer(int position) {
        if (trailerList.size() > 0) {
            return trailerList.get(position);
        }
        return null;
    }

    @Override
    public int getItemCount() {
        if (trailerList.size() > limit) {
            return limit;
        } else {
            return trailerList.size();
        }
    }

    public class TrailersViewHolder extends RecyclerView.ViewHolder {
        private ImageView trailersImageView;
        private FloatingActionButton playButton;

        public TrailersViewHolder(@NonNull final View itemView) {
            super(itemView);
            trailersImageView = itemView.findViewById(R.id.trailerImageView);
            playButton = itemView.findViewById(R.id.playTrailerButton);
            playButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Trailer selectedTrailer = trailerList.get(position);
                    String videoId = selectedTrailer.getKey();
                    Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.YOUTUBE_WEB_BASE_URL + videoId));
                    Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.YOUTUBE_APP_BASE_URL + videoId));
                    webIntent.putExtra("VIDEO_ID", videoId);
                    webIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    try {
                        mContext.startActivity(appIntent);
                    } catch (ActivityNotFoundException ex) {
                        mContext.startActivity(webIntent);
                    }
                }
            });
        }
    }
}