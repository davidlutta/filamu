package com.davidlutta.filamu.UI.series.popularSeries;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.davidlutta.filamu.R;
import com.davidlutta.filamu.UI.series.popularSeries.viewmodel.ViewAllPopularViewModel;

import java.util.Objects;

public class ViewAllPopularFragment extends Fragment {

    private ViewAllPopularViewModel mViewModel;

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
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ViewAllPopularViewModel.class);
        // TODO: Use the ViewModel
    }

}
