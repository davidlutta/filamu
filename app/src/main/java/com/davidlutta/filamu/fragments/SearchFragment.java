package com.davidlutta.filamu.fragments;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.davidlutta.filamu.R;
import com.davidlutta.filamu.adapters.movies.MoviesAdapter;
import com.davidlutta.filamu.adapters.search.SearchAdapter;
import com.davidlutta.filamu.models.movies.Movies;
import com.davidlutta.filamu.models.search.Result;

import java.util.List;

public class SearchFragment extends Fragment implements SearchView.OnQueryTextListener {

    private SearchViewModel mViewModel;
    private RecyclerView recyclerView;
    private SearchAdapter adapter;
    private SearchView searchView;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment, container, false);
        recyclerView = view.findViewById(R.id.searchFragmentRecyclerView);
        searchView = view.findViewById(R.id.moviesFragmentSearchView);
        searchView.setOnQueryTextListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
    }

    private void subscribeViewModel(String query) {
        mViewModel.search(query).observe(getViewLifecycleOwner(), new Observer<List<Movies>>() {
            @Override
            public void onChanged(List<Movies> movies) {
                adapter = new SearchAdapter(getContext(), movies);
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
//                recyclerView.setNestedScrollingEnabled(false);
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        subscribeViewModel(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
