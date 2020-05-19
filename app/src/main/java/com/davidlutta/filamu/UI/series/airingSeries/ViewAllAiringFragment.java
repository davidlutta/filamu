package com.davidlutta.filamu.UI.series.airingSeries;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.davidlutta.filamu.R;
import com.davidlutta.filamu.UI.series.airingSeries.viewmodel.ViewAllAiringViewModel;
import com.davidlutta.filamu.adapters.series.ViewAllSeriesAdapter;
import com.davidlutta.filamu.models.series.Series;

import java.util.Objects;

public class ViewAllAiringFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView seriesRecyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ViewAllAiringViewModel mViewModel;
    private ViewAllSeriesAdapter seriesAdapter;

    public static ViewAllAiringFragment newInstance() {
        return new ViewAllAiringFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_all_airing_fragment, container, false);
        swipeRefreshLayout = view.findViewById(R.id.airingSwipeRefreshLayout);
        seriesRecyclerView = view.findViewById(R.id.allAiringRecyclerView);
        swipeRefreshLayout.setOnRefreshListener(this);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.viewAllAiringFragmentToolbar);
        toolbar.setTitle("Airing Series");
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setTitle("Airing Series");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ViewAllAiringViewModel.class);
        subscribeViewModels();
        setUpAdapter();
    }

    private void subscribeViewModels() {
        mViewModel.getPagedListLiveData().observe(getViewLifecycleOwner(), new Observer<PagedList<Series>>() {
            @Override
            public void onChanged(PagedList<Series> series) {
                swipeRefreshLayout.setRefreshing(false);
                seriesAdapter.submitList(series);
            }
        });
    }

    private void setUpAdapter() {
        if (seriesAdapter == null) {
            seriesAdapter = new ViewAllSeriesAdapter(getContext());
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
            seriesRecyclerView.setLayoutManager(layoutManager);
            seriesRecyclerView.setAdapter(seriesAdapter);
            seriesRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onRefresh() {
        subscribeViewModels();
    }
}
