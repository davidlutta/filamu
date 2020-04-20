package com.davidlutta.filamu.adapters.cast;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.davidlutta.filamu.ProfileActivity;
import com.davidlutta.filamu.R;
import com.davidlutta.filamu.adapters.BaseViewHolder;
import com.davidlutta.filamu.models.cast.Cast;
import com.davidlutta.filamu.util.Constants;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastViewHolder> {
    private Context mContext;
    private List<Cast> CastList;
    private final int limit = 10;

    public CastAdapter(Context mContext, List<Cast> CastList) {
        this.mContext = mContext;
        this.CastList = CastList;
    }

    @NonNull
    @Override
    public CastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cast_item, parent, false);
        return new CastViewHolder(view);
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
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(R.drawable.ic_launcher)
                .into(holder.castImageView);
    }

    @Override
    public int getItemCount() {
        if (CastList.size() > limit) {
            return limit;
        } else {
            return CastList.size();
        }
    }

    private Cast getSelectedCast(int position) {
        if (CastList.size() > 0) {
            return CastList.get(position);
        }
        return null;
    }

    public class CastViewHolder extends BaseViewHolder {
        private TextView castNameTextView;
        private TextView castRoleTextView;
        private CircleImageView castImageView;

        public CastViewHolder(@NonNull View itemView) {
            super(itemView);
            castNameTextView = itemView.findViewById(R.id.castNameTextView);
            castRoleTextView = itemView.findViewById(R.id.castRoleTextView);
            castImageView = itemView.findViewById(R.id.castImageView);
        }
        @Override
        protected void onClickItem() {
            int position = getAdapterPosition();
            Cast selectedCast = getSelectedCast(position);
            Intent intent = new Intent(itemView.getContext(), ProfileActivity.class);
            String personId = selectedCast.getId().toString();
            intent.putExtra(mContext.getString(R.string.personIntentExtraName), personId);
            intent.putExtra("Credits", "Cast");
            itemView.getContext().startActivity(intent);
        }
    }
}