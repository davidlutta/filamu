package com.davidlutta.filamu.adapters.cast;

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
import com.davidlutta.filamu.MovieActivity;
import com.davidlutta.filamu.R;
import com.davidlutta.filamu.adapters.BaseViewHolder;
import com.davidlutta.filamu.models.credits.CreditsCast;
import com.davidlutta.filamu.util.Constants;

import java.util.List;

public class CreditsCastAdapter extends RecyclerView.Adapter<CreditsCastAdapter.CreditsCrewViewHolder> {
    private final int limit = 10;
    private Context mContext;
    private List<CreditsCast> creditsCastsList;

    public CreditsCastAdapter(Context mContext, List<CreditsCast> creditsCastsList) {
        this.mContext = mContext;
        this.creditsCastsList = creditsCastsList;
    }

    @NonNull
    @Override
    public CreditsCrewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new CreditsCrewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CreditsCrewViewHolder holder, int position) {
        String title = creditsCastsList.get(position).getTitle();
        String character = creditsCastsList.get(position).getCharacter();
        String poster = Constants.IMAGE_BASE_URL + creditsCastsList.get(position).getPosterPath();
        holder.titleTextView.setText(title);
        holder.characterTextView.setText(character);
        Glide.with(mContext)
                .load(poster)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(R.drawable.poster)
                .into(holder.backgroundImage);
    }

    @Override
    public int getItemCount() {
        if (creditsCastsList.size() > 10) {
            return limit;
        } else {
            return creditsCastsList.size();
        }
    }

    private CreditsCast getCreditsCast(int position) {
        if (creditsCastsList.size() > 0) {
            return creditsCastsList.get(position);
        }
        return null;
    }

    public class CreditsCrewViewHolder extends BaseViewHolder {
        private TextView titleTextView;
        private TextView characterTextView;
        private ImageView backgroundImage;

        public CreditsCrewViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.itemTitleTextView);
            characterTextView = itemView.findViewById(R.id.itemRatingTextView);
            backgroundImage = itemView.findViewById(R.id.itemBackgroundImageView);
        }

        @Override
        protected void onClickItem() {
            int position = getAdapterPosition();
            CreditsCast selectedCreditsCrew = getCreditsCast(position);
            String id = selectedCreditsCrew.getId().toString();
            Intent intent = new Intent(itemView.getContext(), MovieActivity.class);
            intent.putExtra(mContext.getString(R.string.movieIntentExtraName), id);
            itemView.getContext().startActivity(intent);
        }
    }
}
