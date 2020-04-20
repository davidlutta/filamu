package com.davidlutta.filamu.adapters.crew;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.davidlutta.filamu.ProfileActivity;
import com.davidlutta.filamu.R;
import com.davidlutta.filamu.adapters.BaseViewHolder;
import com.davidlutta.filamu.models.cast.Crew;
import com.davidlutta.filamu.util.Constants;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CrewAdapter extends RecyclerView.Adapter<CrewAdapter.CrewViewHolder> {
    private Context mContext;
    private List<Crew> CrewList;
    private final int limit = 10;

    public CrewAdapter(Context mContext, List<Crew> CrewList) {
        this.mContext = mContext;
        this.CrewList = CrewList;
    }

    @NonNull
    @Override
    public CrewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cast_item, parent, false);
        return new CrewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CrewViewHolder holder, int position) {
        String name = CrewList.get(position).getName();
        String job = CrewList.get(position).getJob();
        String poster = Constants.IMAGE_BASE_URL + CrewList.get(position).getProfilePath();
        holder.CrewNameTextView.setText(name);
        holder.CrewRoleTextView.setText(job);
        Glide.with(mContext)
                .load(poster)
                .placeholder(R.drawable.ic_launcher)
                .into(holder.CrewImageView);
    }

    @Override
    public int getItemCount() {
        if (CrewList.size() > limit) {
            return limit;
        } else {
            return CrewList.size();
        }
    }

    private Crew getSelectedCrew(int position) {
        if (CrewList.size() > 0) {
            return CrewList.get(position);
        }
        return null;
    }

    public class CrewViewHolder extends BaseViewHolder {
        private TextView CrewNameTextView;
        private TextView CrewRoleTextView;
        private CircleImageView CrewImageView;

        public CrewViewHolder(@NonNull View itemView) {
            super(itemView);
            CrewNameTextView = itemView.findViewById(R.id.castNameTextView);
            CrewRoleTextView = itemView.findViewById(R.id.castRoleTextView);
            CrewImageView = itemView.findViewById(R.id.castImageView);
        }

        @Override
        protected void onClickItem() {
            int position = getAdapterPosition();
            Crew selectedCrew = getSelectedCrew(position);
            Intent intent = new Intent(itemView.getContext(), ProfileActivity.class);
            String personId = selectedCrew.getId().toString();
            intent.putExtra(mContext.getString(R.string.personIntentExtraName), personId);
            intent.putExtra("Credits", "Crew");
            itemView.getContext().startActivity(intent);
        }
    }
}