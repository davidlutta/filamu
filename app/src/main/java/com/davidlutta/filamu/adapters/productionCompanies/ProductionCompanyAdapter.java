package com.davidlutta.filamu.adapters.productionCompanies;

import android.content.Context;
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
import com.davidlutta.filamu.adapters.BaseViewHolder;
import com.davidlutta.filamu.models.show.ProductionCompany;
import com.davidlutta.filamu.util.Constants;

import java.util.List;

public class ProductionCompanyAdapter extends RecyclerView.Adapter<ProductionCompanyAdapter.ProdCompViewHolder> {
    private Context mContext;
    private List<ProductionCompany> companyList;

    public ProductionCompanyAdapter(Context mContext, List<ProductionCompany> companyList) {
        this.mContext = mContext;
        this.companyList = companyList;
    }

    @NonNull
    @Override
    public ProdCompViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_two, parent, false);
        return new ProdCompViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdCompViewHolder holder, int position) {
        String name = companyList.get(position).getName();
        String poster = Constants.IMAGE_BASE_URL + companyList.get(position).getLogoPath();
        holder.castNameTextView.setText(name);
        holder.castNameTextView.setMaxLines(2);
        holder.castRoleTextView.setVisibility(View.INVISIBLE);
        Glide.with(mContext)
                .load(poster)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(R.drawable.ic_launcher)
                .into(holder.castImageView);
    }

    @Override
    public int getItemCount() {
        return companyList.size();
    }

    public class ProdCompViewHolder extends BaseViewHolder {
        private TextView castNameTextView;
        private TextView castRoleTextView;
        private ImageView castImageView;

        public ProdCompViewHolder(@NonNull View itemView) {
            super(itemView);
            castNameTextView = itemView.findViewById(R.id.itemTitleTextView);
            castRoleTextView = itemView.findViewById(R.id.itemRatingTextView);
            castImageView = itemView.findViewById(R.id.itemBackgroundImageView);
        }

        @Override
        protected void onClickItem() {

        }
    }
}
