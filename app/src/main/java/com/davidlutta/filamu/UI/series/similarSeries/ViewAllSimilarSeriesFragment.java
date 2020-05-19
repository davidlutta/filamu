package com.davidlutta.filamu.UI.series.similarSeries;

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
import android.widget.ProgressBar;

import com.davidlutta.filamu.R;
import com.davidlutta.filamu.UI.series.similarSeries.viewmodel.SimilarSeriesViewModel;
import com.davidlutta.filamu.UI.series.similarSeries.viewmodel.SimilarSeriesViewModelFactory;
import com.davidlutta.filamu.adapters.series.ViewAllSeriesAdapter;
import com.davidlutta.filamu.models.series.Series;

import java.util.Objects;

public class ViewAllSimilarSeriesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private SimilarSeriesViewModel mViewModel;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ViewAllSeriesAdapter adapter;

    public static ViewAllSimilarSeriesFragment newInstance() {
        return new ViewAllSimilarSeriesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_all_similar_series_fragment, container, false);
        recyclerView = view.findViewById(R.id.similarSeriesFragmentRecyclerView);
        swipeRefreshLayout = view.findViewById(R.id.similarSeriesFragmentSwipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.viewAllSimilarSeriesFragmentToolbar);
        toolbar.setTitle("Similar Series");
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setTitle("Similar Series");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        subscribeViewModels();
        setUpAdapter();
    }

    private void subscribeViewModels() {
        if (getActivity().getIntent().hasExtra("tvId")) {
            String tvId = getActivity().getIntent().getExtras().getString("tvId");
            mViewModel = ViewModelProviders.of(this, new SimilarSeriesViewModelFactory(getActivity().getApplication(), tvId)).get(SimilarSeriesViewModel.class);
            mViewModel.getPagedListLiveData().observe(getViewLifecycleOwner(), new Observer<PagedList<Series>>() {
                @Override
                public void onChanged(PagedList<Series> series) {
                    swipeRefreshLayout.setRefreshing(false);
                    adapter.submitList(series);
                }
            });
        }
    }

    private void setUpAdapter() {
        if (adapter == null) {
            adapter = new ViewAllSeriesAdapter(getContext());
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onRefresh() {
        subscribeViewModels();
    }
}
