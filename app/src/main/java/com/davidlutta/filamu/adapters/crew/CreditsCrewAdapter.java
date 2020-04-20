package com.davidlutta.filamu.adapters.crew;

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
import com.davidlutta.filamu.models.credits.CreditsCrew;
import com.davidlutta.filamu.util.Constants;

import java.util.List;

public class CreditsCrewAdapter extends RecyclerView.Adapter<CreditsCrewAdapter.CreditsCrewViewHolder> {
    private final int limit = 10;
    private Context mContext;
    private List<CreditsCrew> creditsCrewList;

    public CreditsCrewAdapter(Context mContext, List<CreditsCrew> creditsCrewList) {
        this.mContext = mContext;
        this.creditsCrewList = creditsCrewList;
    }

    @NonNull
    @Override
    public CreditsCrewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new CreditsCrewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CreditsCrewViewHolder holder, int position) {
        String title = creditsCrewList.get(position).getTitle();
        String job = creditsCrewList.get(position).getJob();
        String poster = Constants.IMAGE_BASE_URL + creditsCrewList.get(position).getPosterPath();
        holder.titleTextView.setText(title);
        holder.characterTextView.setText(job);
        Glide.with(mContext)
                .load(poster)
                .placeholder(R.drawable.poster)
                .into(holder.backgroundImage);
    }

    @Override
    public int getItemCount() {
        if (creditsCrewList.size() > 10) {
            return limit;
        } else {
            return creditsCrewList.size();
        }
    }

    private CreditsCrew getCreditsCrew(int position) {
        if (creditsCrewList.size() > 0) {
            return creditsCrewList.get(position);
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
            CreditsCrew selectedCreditsCrew = getCreditsCrew(position);
            String id = selectedCreditsCrew.getId().toString();
            Intent intent = new Intent(itemView.getContext(), MovieActivity.class);
            intent.putExtra(mContext.getString(R.string.movieIntentExtraName), id);
            itemView.getContext().startActivity(intent);
        }
    }
}
