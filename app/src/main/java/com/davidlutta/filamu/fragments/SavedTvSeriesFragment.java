package com.davidlutta.filamu.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.davidlutta.filamu.R;
import com.davidlutta.filamu.adapters.favourite.SavedSeriesAdapter;
import com.davidlutta.filamu.database.tv.Series;
import com.davidlutta.filamu.viewmodels.SavedTvSeriesViewModel;

import java.util.List;
import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class SavedTvSeriesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private SavedTvSeriesViewModel mViewModel;
    private SavedSeriesAdapter adapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    public static SavedTvSeriesFragment newInstance() {
        return new SavedTvSeriesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.saved_tv_series_fragment, container, false);
        recyclerView = view.findViewById(R.id.savedSeriesRecyclerView);
        swipeRefreshLayout = view.findViewById(R.id.savedSeriesSwipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
                //for drag and drop functionality
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                mViewModel.deleteSeries(adapter.getSelectedSeries(viewHolder.getAdapterPosition()).getTvId());
                Toasty.warning(Objects.requireNonNull(getContext()), "Deleted Movie", Toasty.LENGTH_SHORT, true).show();
            }
        }).attachToRecyclerView(recyclerView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SavedTvSeriesViewModel.class);
        adapter = new SavedSeriesAdapter(getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        subscribeViewModels();
    }

    private void subscribeViewModels() {
        mViewModel.getSavedSeries().observe(getViewLifecycleOwner(), new Observer<List<Series>>() {
            @Override
            public void onChanged(List<Series> series) {
                swipeRefreshLayout.setRefreshing(false);
                adapter.submitList(series);
            }
        });
    }

    @Override
    public void onRefresh() {
        subscribeViewModels();
    }
}
