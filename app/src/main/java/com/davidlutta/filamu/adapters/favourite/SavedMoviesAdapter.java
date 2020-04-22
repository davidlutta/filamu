package com.davidlutta.filamu.adapters.favourite;

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
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.davidlutta.filamu.R;
import com.davidlutta.filamu.SavedMovieActivity;
import com.davidlutta.filamu.adapters.BaseViewHolder;
import com.davidlutta.filamu.database.movies.Movie;

import java.util.List;

public class SavedMoviesAdapter extends RecyclerView.Adapter<SavedMoviesAdapter.SavedMoviesViewHolder> {
    private Context mContext;
    private List<Movie> movieList;

    public SavedMoviesAdapter(Context mContext, List<Movie> movieList) {
        this.mContext = mContext;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public SavedMoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.saved_item, parent, false);
        return new SavedMoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SavedMoviesViewHolder holder, int position) {
        Movie movie = getSelectedSavedMovie(position);
        String title = movie.getTitle();
        String rating = "Rating: " + movie.getRating() + " / 10";
        String genre = movie.getGenres();
        String poster = movie.getPoster();
        holder.title.setText(title);
        holder.genre.setText(genre);
        holder.rating.setText(rating);
        Glide.with(mContext)
                .load(poster)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(R.drawable.poster)
                .into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public Movie getSelectedSavedMovie(int position) {
        if (movieList.size() > 0) {
            return movieList.get(position);
        }
        return null;
    }

    public class SavedMoviesViewHolder extends BaseViewHolder {
        private TextView title;
        private ImageView poster;
        private TextView genre;
        private TextView rating;

        public SavedMoviesViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.savedTitleTextView);
            poster = itemView.findViewById(R.id.posterImageView);
            genre = itemView.findViewById(R.id.savedGenreTextView);
            rating = itemView.findViewById(R.id.savedRatingTextView);
        }

        @Override
        protected void onClickItem() {
            int position = getAdapterPosition();
            Movie movie = getSelectedSavedMovie(position);
            String id = String.valueOf(movie.getMovieId());
            Intent intent = new Intent(itemView.getContext(), SavedMovieActivity.class);
            intent.putExtra("id", id);
            itemView.getContext().startActivity(intent);
        }
    }
}
