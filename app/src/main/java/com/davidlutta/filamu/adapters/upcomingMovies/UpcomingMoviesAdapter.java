package com.davidlutta.filamu.adapters.upcomingMovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.davidlutta.filamu.R;
import com.davidlutta.filamu.adapters.BaseViewHolder;
import com.davidlutta.filamu.models.Movies;

import java.util.List;

public class UpcomingMoviesAdapter extends RecyclerView.Adapter<UpcomingMoviesAdapter.MovieViewHolder> {
    private Context mContext;
    private List<Movies> moviesList;
    private OnUpcomingMovieListener movieListener;

    public UpcomingMoviesAdapter(Context mContext, List<Movies> moviesList, OnUpcomingMovieListener movieListener) {
        this.mContext = mContext;
        this.moviesList = moviesList;
        this.movieListener = movieListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MovieViewHolder(view, movieListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        String title = moviesList.get(position).getTitle();
        String rating = "Rating: " + moviesList.get(position).getVoteAverage().toString() + " / 10";
        String poster = "https://image.tmdb.org/t/p/w500" + moviesList.get(position).getPosterPath();
        holder.titleTextView.setText(title);
        holder.ratingTextView.setText(rating);
        Glide.with(mContext)
                .load(poster)
                .placeholder(R.drawable.poster)
                .into(holder.backgroundImageview);
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public Movies getSelectedMovie(int position) {
        if (moviesList.size() > 0) {
            return moviesList.get(position);
        }
        return null;
    }

    public class MovieViewHolder extends BaseViewHolder implements View.OnClickListener {
        OnUpcomingMovieListener movieListener;
        private TextView titleTextView;
        private TextView ratingTextView;
        private ImageView backgroundImageview;

        public MovieViewHolder(@NonNull View itemView, OnUpcomingMovieListener onMovieListener) {
            super(itemView);
            this.movieListener = onMovieListener;
            titleTextView = itemView.findViewById(R.id.titleTextView);
            ratingTextView = itemView.findViewById(R.id.ratingTextView);
            backgroundImageview = itemView.findViewById(R.id.backgroundImageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public int getCurrentPosition() {
            return super.getCurrentPosition();
        }

        @Override
        public void onClick(View v) {
            movieListener.onUpcomingMovieClick(getAdapterPosition());
        }
    }
}
