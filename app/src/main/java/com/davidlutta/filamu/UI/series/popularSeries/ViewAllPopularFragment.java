package com.davidlutta.filamu.UI.series.popularSeries;

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
import com.davidlutta.filamu.UI.series.popularSeries.viewmodel.ViewAllPopularViewModel;
import com.davidlutta.filamu.adapters.series.ViewAllSeriesAdapter;
import com.davidlutta.filamu.models.series.Series;

import java.util.Objects;

public class ViewAllPopularFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private ViewAllPopularViewModel mViewModel;

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private ViewAllSeriesAdapter seriesAdapter;

    public static ViewAllPopularFragment newInstance() {
        return new ViewAllPopularFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_all_popular_fragment, container, false);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.viewAllPopularSeriesFragmentToolbar);
        toolbar.setTitle("Popular Series");
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setTitle("Popular Series");
        swipeRefreshLayout = view.findViewById(R.id.viewAllPopularSeriesSwipeRefreshLayout);
        recyclerView = view.findViewById(R.id.viewAllPopularSeriesRecyclerView);
        swipeRefreshLayout.setOnRefreshListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ViewAllPopularViewModel.class);
        subscribeViewModel();
        setUpAdapter();
    }

    private void subscribeViewModel() {
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
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onRefresh() {
        subscribeViewModel();
    }
}
