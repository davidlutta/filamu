package com.davidlutta.filamu.adapters.movies;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.davidlutta.filamu.MovieActivity;
import com.davidlutta.filamu.R;
import com.davidlutta.filamu.adapters.BaseViewHolder;
import com.davidlutta.filamu.models.movies.Movies;
import com.davidlutta.filamu.util.Constants;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {

    private Context mContext;
    private List<Movies> moviesList;

    public MoviesAdapter(Context mContext, List<Movies> moviesList) {
        this.mContext = mContext;
        this.moviesList = moviesList;
    }

    @NonNull
    @Override
    public MoviesAdapter.MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MoviesAdapter.MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.MoviesViewHolder holder, int position) {
        String title = moviesList.get(position).getTitle();
        String rating = "Rating: " + moviesList.get(position).getVoteAverage().toString() + " / 10";
        String poster = Constants.IMAGE_BASE_URL + moviesList.get(position).getPosterPath();
        holder.titleTextView.setText(title);
        holder.ratingTextView.setText(rating);
        Glide.with(mContext)
                .load(poster)
                .placeholder(R.drawable.poster)
                .into(holder.backgroundImageView);
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    private Movies getSelectedMovie(int position) {
        if (moviesList.size() > 0) {
            return moviesList.get(position);
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
