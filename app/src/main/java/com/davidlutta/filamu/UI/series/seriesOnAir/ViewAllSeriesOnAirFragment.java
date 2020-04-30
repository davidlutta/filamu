package com.davidlutta.filamu.UI.series.seriesOnAir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.davidlutta.filamu.R;
import com.davidlutta.filamu.UI.series.seriesOnAir.viewmodel.ViewAllSeriesOnAirViewModel;
import com.davidlutta.filamu.adapters.series.ViewAllSeriesAdapter;
import com.davidlutta.filamu.models.series.Series;

import java.util.Objects;

public class ViewAllSeriesOnAirFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private ViewAllSeriesOnAirViewModel mViewModel;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private ViewAllSeriesAdapter seriesAdapter;

    public static ViewAllSeriesOnAirFragment newInstance() {
        return new ViewAllSeriesOnAirFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_all_series_on_air_fragment, container, false);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.viewAllSeriesOnAirFragmentToolbar);
        toolbar.setTitle("Series On Air");
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setTitle("Series On Air");
        recyclerView = view.findViewById(R.id.viewAllOnAirRecyclerView);
        swipeRefreshLayout = view.findViewById(R.id.onAirSwipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ViewAllSeriesOnAirViewModel.class);
        // TODO: Use the ViewModel
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
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(seriesAdapter);
        }
    }

    @Override
    public void onRefresh() {
        subscribeViewModels();
    }
}
