package com.davidlutta.filamu.UI.credits.crew;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.davidlutta.filamu.R;
import com.davidlutta.filamu.adapters.crew.CrewAdapter;
import com.davidlutta.filamu.models.cast.Crew;
import com.davidlutta.filamu.viewmodels.MoviesViewModel;
import com.davidlutta.filamu.viewmodels.TvViewModel;

import java.util.List;
import java.util.Objects;


public class CrewFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private MoviesViewModel moviesViewModel;
    private TvViewModel seriesViewModel;
    private CrewAdapter adapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    public static CrewFragment newInstance() {
        return new CrewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crew, container, false);
        recyclerView = view.findViewById(R.id.crewFragmentRecyclerView);
        swipeRefreshLayout = view.findViewById(R.id.castFragmentSwipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.crewFragmentToolbar);
        toolbar.setTitle("Crew");
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
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
                    seriesViewModel.getSeriesCrew(id).observe(getViewLifecycleOwner(), new Observer<List<Crew>>() {
                        @Override
                        public void onChanged(List<Crew> crews) {
                            swipeRefreshLayout.setRefreshing(false);
                            adapter = new CrewAdapter(getContext(), crews);
                            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setVisibility(View.VISIBLE);
                        }
                    });
                    break;
                case "Movies":
                    moviesViewModel.getCrewDetails(id).observe(getViewLifecycleOwner(), new Observer<List<Crew>>() {
                        @Override
                        public void onChanged(List<Crew> crews) {
                            swipeRefreshLayout.setRefreshing(false);
                            adapter = new CrewAdapter(getContext(), crews);
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
