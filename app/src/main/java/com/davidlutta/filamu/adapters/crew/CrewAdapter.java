package com.davidlutta.filamu.adapters.crew;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.davidlutta.filamu.R;
import com.davidlutta.filamu.adapters.BaseViewHolder;
import com.davidlutta.filamu.models.cast.Crew;
import com.davidlutta.filamu.util.Constants;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CrewAdapter extends RecyclerView.Adapter<CrewAdapter.CrewViewHolder> {
    private Context mContext;
    private List<Crew> CrewList;
    private OnCrewClickListener CrewListener;

    public CrewAdapter(Context mContext, List<Crew> CrewList, OnCrewClickListener CrewListener) {
        this.mContext = mContext;
        this.CrewList = CrewList;
        this.CrewListener = CrewListener;
    }

    @NonNull
    @Override
    public CrewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cast_item, parent, false);
        return new CrewViewHolder(view, CrewListener);
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
        return CrewList.size();
    }

    public Crew getSelectedCrew(int position) {
        if (CrewList.size() > 0) {
            return CrewList.get(position);
        }
        return null;
    }

    public class CrewViewHolder extends BaseViewHolder implements View.OnClickListener {
        OnCrewClickListener CrewListener;
        private TextView CrewNameTextView;
        private TextView CrewRoleTextView;
        private CircleImageView CrewImageView;

        public CrewViewHolder(@NonNull View itemView, OnCrewClickListener onCrewListener) {
            super(itemView);
            this.CrewListener = onCrewListener;
            CrewNameTextView = itemView.findViewById(R.id.castNameTextView);
            CrewRoleTextView = itemView.findViewById(R.id.castRoleTextView);
            CrewImageView = itemView.findViewById(R.id.castImageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public int getCurrentPosition() {
            return super.getCurrentPosition();
        }

        @Override
        public void onClick(View v) {
            CrewListener.onCrewClicked(getAdapterPosition());
        }
    }
}