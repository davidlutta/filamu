package com.davidlutta.filamu.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.davidlutta.filamu.R;
import com.davidlutta.filamu.UI.series.ViewAllSeriesHolderActivity;
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

    private ProgressBar airingProgressBar;
    private ProgressBar onAirProgressBar;
    private ProgressBar popularSeriesProgressBar;


    public static TvFragment newInstance() {
        return new TvFragment();
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tv_fragment, container, false);
        airingTodayRecyclerView = view.findViewById(R.id.airing_today_recyclerView);
        onAirRecyclerView = view.findViewById(R.id.on_air_recyclerView);
        popularSeriesRecyclerView = view.findViewById(R.id.popular_series_RecyclerView);

        viewAllAiringTodayButton = view.findViewById(R.id.viewAllAiringTodayButton);
        viewOnAirButton = view.findViewById(R.id.viewAllOnAirButton);
        viewAllPopularButton = view.findViewById(R.id.viewAllPopularSeriesButton);
        swipeRefreshLayout = view.findViewById(R.id.tvFragmentSwipeRefreshLayout);

        airingProgressBar = view.findViewById(R.id.airingTodayProgressBar);
        onAirProgressBar = view.findViewById(R.id.onAirProgressBar);
        popularSeriesProgressBar = view.findViewById(R.id.popularSeriesProgressBar);

        swipeRefreshLayout.setOnRefreshListener(this);
        viewAllAiringTodayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ViewAllSeriesHolderActivity.class);
                intent.putExtra("Category", "AiringToday");
                startActivity(intent);
            }
        });

        viewOnAirButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ViewAllSeriesHolderActivity.class);
                intent.putExtra("Category", "OnTheAir");
                startActivity(intent);
            }
        });

        viewAllPopularButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ViewAllSeriesHolderActivity.class);
                intent.putExtra("Category", "popularSeries");
                startActivity(intent);
            }
        });
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
            airingTodayRecyclerView.setVisibility(View.VISIBLE);
            airingProgressBar.setVisibility(View.INVISIBLE);
            seriesAdapter = null;
        }
    }

    private void setUpSeriesOnAirAdapter() {
        if (seriesAdapter == null) {
            seriesAdapter = new SeriesAdapter(getContext(), seriesOnAirList);
            onAirRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            onAirRecyclerView.setAdapter(seriesAdapter);
            onAirRecyclerView.setNestedScrollingEnabled(false);
            onAirRecyclerView.setVisibility(View.VISIBLE);
            onAirProgressBar.setVisibility(View.INVISIBLE);
            seriesAdapter = null;
        }
    }

    private void setUpPopularSeriesAdapter() {
        if (seriesAdapter == null) {
            seriesAdapter = new SeriesAdapter(getContext(), popularSeriesList);
            popularSeriesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            popularSeriesRecyclerView.setAdapter(seriesAdapter);
            popularSeriesRecyclerView.setNestedScrollingEnabled(false);
            popularSeriesRecyclerView.setVisibility(View.VISIBLE);
            popularSeriesProgressBar.setVisibility(View.INVISIBLE);
            seriesAdapter = null;
        }
    }

    @Override
    public void onRefresh() {
        subscribeViewModels();
    }
}
