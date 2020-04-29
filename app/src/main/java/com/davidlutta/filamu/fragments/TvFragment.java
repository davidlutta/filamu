package com.davidlutta.filamu.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.davidlutta.filamu.R;
import com.davidlutta.filamu.adapters.series.SeriesAdapter;
import com.davidlutta.filamu.models.series.Series;
import com.davidlutta.filamu.viewmodels.TvViewModel;
import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView;

import java.util.List;

public class TvFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private TvViewModel mViewModel;
    private MultiSnapRecyclerView airingTodayRecyclerView;
    private MultiSnapRecyclerView onAirRecyclerView;
    private MultiSnapRecyclerView popularSeriesRecyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    private Button viewAllAiringTodayButton;
    private Button viewOnAirButton;
    private Button viewAllPopularButton;

    private SeriesAdapter seriesAdapter;
    private List<Series> seriesAiringTodayList;
    private List<Series> seriesOnAirList;
    private List<Series> popularSeriesList;


    public static TvFragment newInstance() {
        return new TvFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tv_fragment, container, false);
        airingTodayRecyclerView = view.findViewById(R.id.airing_today_recyclerView);
        onAirRecyclerView = view.findViewById(R.id.on_air_recyclerView);
        popularSeriesRecyclerView = view.findViewById(R.id.popular_series_RecyclerView);

        viewAllAiringTodayButton = view.findViewById(R.id.viewAllAiringTodayButton);
        viewOnAirButton = view.findViewById(R.id.viewAllOnAirButton);
        viewAllPopularButton = view.findViewById(R.id.viewAllPopularSeriesButton);
        swipeRefreshLayout = view.findViewById(R.id.tvFragmentSwipeRefreshLayout);

        swipeRefreshLayout.setOnRefreshListener(this);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TvViewModel.class);
        subscribeViewModels();
    }

    private void subscribeViewModels() {
        mViewModel.getSeriesAiringToday().observe(getViewLifecycleOwner(), new Observer<List<Series>>() {
            @Override
            public void onChanged(List<Series> series) {
                swipeRefreshLayout.setRefreshing(false);
                seriesAiringTodayList = series;
                setUpSeriesAiringTodayAdapter();
            }
        });
        mViewModel.getSeriesOnAir().observe(getViewLifecycleOwner(), new Observer<List<Series>>() {
            @Override
            public void onChanged(List<Series> series) {
                swipeRefreshLayout.setRefreshing(false);
                seriesOnAirList = series;
                setUpSeriesOnAirAdapter();
            }
        });
        mViewModel.getPopularSeries().observe(getViewLifecycleOwner(), new Observer<List<Series>>() {
            @Override
            public void onChanged(List<Series> series) {
                swipeRefreshLayout.setRefreshing(false);
                popularSeriesList = series;
                setUpPopularSeriesAdapter();
            }
        });
    }

    private void setUpSeriesAiringTodayAdapter() {
        if (seriesAdapter == null) {
            seriesAdapter = new SeriesAdapter(getContext(), seriesAiringTodayList);
            airingTodayRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            airingTodayRecyclerView.setAdapter(seriesAdapter);
            airingTodayRecyclerView.setNestedScrollingEnabled(false);
            seriesAdapter = null;
        }
    }

    private void setUpSeriesOnAirAdapter() {
        if (seriesAdapter == null) {
            seriesAdapter = new SeriesAdapter(getContext(), seriesOnAirList);
            onAirRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            onAirRecyclerView.setAdapter(seriesAdapter);
            onAirRecyclerView.setNestedScrollingEnabled(false);
            seriesAdapter = null;
        }
    }

    private void setUpPopularSeriesAdapter() {
        if (seriesAdapter == null) {
            seriesAdapter = new SeriesAdapter(getContext(), popularSeriesList);
            popularSeriesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            popularSeriesRecyclerView.setAdapter(seriesAdapter);
            popularSeriesRecyclerView.setNestedScrollingEnabled(false);
            seriesAdapter = null;
        }
    }

    @Override
    public void onRefresh() {
        subscribeViewModels();
    }
}
