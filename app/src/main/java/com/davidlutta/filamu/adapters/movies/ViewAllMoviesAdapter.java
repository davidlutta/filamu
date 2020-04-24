package com.davidlutta.filamu.adapters.movies;

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
import com.davidlutta.filamu.MovieActivity;
import com.davidlutta.filamu.R;
import com.davidlutta.filamu.adapters.BaseViewHolder;
import com.davidlutta.filamu.models.movies.Movies;
import com.davidlutta.filamu.util.Constants;

public class ViewAllMoviesAdapter extends PagedListAdapter<Movies, ViewAllMoviesAdapter.MoviesViewHolder> {
    private Context mContext;

    public ViewAllMoviesAdapter(Context context) {
        super(Movies.CALLBACK);
        this.mContext = context;
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewAllMoviesAdapter.MoviesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_two, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {
        holder.onBind(getItem(position));
    }

    public Movies getSelectedMovie(int position) {
        if (getCurrentList() != null) {
            if (getCurrentList().size() > 0) {
                return getItem(position);
            }
        }
        return null;
    }

    public class MoviesViewHolder extends BaseViewHolder {
        private TextView titleTextView;
        private TextView ratingTextView;
        private ImageView backgroundImageView;

        public MoviesViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.itemTitleTextView);
            ratingTextView = itemView.findViewById(R.id.itemRatingTextView);
            backgroundImageView = itemView.findViewById(R.id.itemBackgroundImageView);
        }

        public void onBind(Movies movies) {
            if (movies != null) {
                String rating = "Rating: " + movies.getVoteAverage().toString() + " / 10";
                titleTextView.setText(movies.getTitle());
                ratingTextView.setText(rating);
                String poster = Constants.IMAGE_BASE_URL + movies.getPosterPath();
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
            Movies selectedMovie = getSelectedMovie(position);
            String id = selectedMovie.getId().toString();
            Intent intent = new Intent(itemView.getContext(), MovieActivity.class);
            intent.putExtra(mContext.getString(R.string.movieIntentExtraName), id);
            itemView.getContext().startActivity(intent);
        }
    }
}
