package com.davidlutta.filamu.adapters.favourite;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.davidlutta.filamu.R;
import com.davidlutta.filamu.adapters.BaseViewHolder;
import com.davidlutta.filamu.database.tv.Series;

import java.util.Objects;

public class SavedSeriesAdapter extends ListAdapter<Series, SavedSeriesAdapter.SavedSeriesViewHolder> {
    private Context mContext;

    public SavedSeriesAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.mContext = context;
    }

    private static final DiffUtil.ItemCallback<Series> DIFF_CALLBACK = new DiffUtil.ItemCallback<Series>() {
        @Override
        public boolean areItemsTheSame(@NonNull Series oldItem, @NonNull Series newItem) {
            return oldItem.getTvId() == newItem.getTvId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Series oldItem, @NonNull Series newItem) {
            return Objects.equals(oldItem, newItem);
        }
    };

    @NonNull
    @Override
    public SavedSeriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SavedSeriesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.saved_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SavedSeriesViewHolder holder, int position) {
        Series series = getSelectedSeries(position);
        String title = series.getTitle();
        String rating = "Rating: " + series.getRating() + " / 10";
        String genre = series.getGenre();
        String poster = series.getPoster();
        holder.title.setText(title);
        holder.genre.setText(genre);
        holder.rating.setText(rating);
        Glide.with(mContext)
                .load(poster)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(R.drawable.poster)
                .into(holder.poster);
    }

    public Series getSelectedSeries(int position) {
        if (getCurrentList().size() > 0) {
            return getItem(position);
        }
        return null;
    }

    public class SavedSeriesViewHolder extends BaseViewHolder {
        private TextView title;
        private ImageView poster;
        private TextView genre;
        private TextView rating;

        public SavedSeriesViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.savedTitleTextView);
            poster = itemView.findViewById(R.id.posterImageView);
            genre = itemView.findViewById(R.id.savedGenreTextView);
            rating = itemView.findViewById(R.id.savedRatingTextView);
        }

        @Override
        protected void onClickItem() {
            int position = getAdapterPosition();
            Series series = getSelectedSeries(position);
            String id = String.valueOf(series.getTvId());
            Toast.makeText(mContext, series.getTitle(), Toast.LENGTH_SHORT).show();
            /*Intent intent = new Intent(itemView.getContext(), );
            intent.putExtra("id", id);
            itemView.getContext().startActivity(intent);*/
        }
    }
}
