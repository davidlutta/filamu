package com.davidlutta.filamu.adapters.cast;


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
import com.davidlutta.filamu.models.cast.Cast;
import com.davidlutta.filamu.util.Constants;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastViewHolder> {
    private Context mContext;
    private List<Cast> CastList;
    private OnCastClickListener castListener;

    public CastAdapter(Context mContext, List<Cast> CastList, OnCastClickListener castListener) {
        this.mContext = mContext;
        this.CastList = CastList;
        this.castListener = castListener;
    }

    @NonNull
    @Override
    public CastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cast_item, parent, false);
        return new CastViewHolder(view, castListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CastViewHolder holder, int position) {
        String name = CastList.get(position).getName();
        String role = CastList.get(position).getCharacter();
        String poster = Constants.IMAGE_BASE_URL + CastList.get(position).getProfilePath();
        holder.castNameTextView.setText(name);
        holder.castRoleTextView.setText(role);
        Glide.with(mContext)
                .load(poster)
                .placeholder(R.drawable.ic_launcher)
                .into(holder.castImageView);
    }

    @Override
    public int getItemCount() {
        return CastList.size();
    }

    public Cast getSelectedCast(int position) {
        if (CastList.size() > 0) {
            return CastList.get(position);
        }
        return null;
    }

    public class CastViewHolder extends BaseViewHolder implements View.OnClickListener {
        OnCastClickListener castListener;
        private TextView castNameTextView;
        private TextView castRoleTextView;
        private CircleImageView castImageView;

        public CastViewHolder(@NonNull View itemView, OnCastClickListener oncastListener) {
            super(itemView);
            this.castListener = oncastListener;
            castNameTextView = itemView.findViewById(R.id.castNameTextView);
            castRoleTextView = itemView.findViewById(R.id.castRoleTextView);
            castImageView = itemView.findViewById(R.id.castImageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public int getCurrentPosition() {
            return super.getCurrentPosition();
        }

        @Override
        public void onClick(View v) {
            castListener.onCastClicked(getAdapterPosition());
        }
    }
}