package com.davidlutta.filamu.UI.credits.cast;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.davidlutta.filamu.R;
import com.davidlutta.filamu.adapters.cast.CastAdapter;
import com.davidlutta.filamu.models.cast.Cast;
import com.davidlutta.filamu.viewmodels.MoviesViewModel;
import com.davidlutta.filamu.viewmodels.TvViewModel;

import java.util.List;
import java.util.Objects;


public class CastFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private MoviesViewModel moviesViewModel;
    private TvViewModel seriesViewModel;
    private CastAdapter adapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    public static CastFragment newInstance() {
        return new CastFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cast, container, false);
        swipeRefreshLayout = view.findViewById(R.id.castFragmentSwipeRefreshLayout);
        recyclerView = view.findViewById(R.id.castFragmentRecyclerView);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.castFragmentToolbar);
        toolbar.setTitle("Cast");
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
        swipeRefreshLayout.setOnRefreshListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        seriesViewModel = ViewModelProviders.of(this).get(TvViewModel.class);
        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        subscribeViewModel();
    }

    private void subscribeViewModel() {
        if (getActivity().getIntent().hasExtra("Category") && getActivity().getIntent().hasExtra("id")) {
            String category = Objects.requireNonNull(getActivity().getIntent().getExtras()).getString("Category");
            String id = Objects.requireNonNull(getActivity().getIntent().getExtras()).getString("id");
            assert category != null;
            assert id != null;
            switch (category) {
                case "Series":
                    seriesViewModel.getSeriesCast(id).observe(getViewLifecycleOwner(), new Observer<List<Cast>>() {
                        @Override
                        public void onChanged(List<Cast> casts) {
                            swipeRefreshLayout.setRefreshing(false);
                            adapter = new CastAdapter(getContext(), casts);
                            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setVisibility(View.VISIBLE);
                        }
                    });
                    break;
                case "Movies":
                    moviesViewModel.getCastDetails(id).observe(getViewLifecycleOwner(), new Observer<List<Cast>>() {
                        @Override
                        public void onChanged(List<Cast> casts) {
                            swipeRefreshLayout.setRefreshing(false);
                            adapter = new CastAdapter(getContext(), casts);
                            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setVisibility(View.VISIBLE);
                        }
                    });
                    break;
            }
        }
    }

    @Override
    public void onRefresh() {
        subscribeViewModel();
    }
}
