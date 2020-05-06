package com.davidlutta.filamu.fragments;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.davidlutta.filamu.models.movies.Movies;
import com.davidlutta.filamu.models.search.Result;
import com.davidlutta.filamu.repository.search.SearchRepository;

import java.util.List;

public class SearchViewModel extends ViewModel {
    private SearchRepository searchRepository;

    public SearchViewModel() {
        this.searchRepository = SearchRepository.getInstance();
    }

    public LiveData<List<Movies>> search(String query) {
        return searchRepository.search(query);
    }
}
