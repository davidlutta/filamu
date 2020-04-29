package com.davidlutta.filamu.UI.series.seriesOnAir;

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
import com.davidlutta.filamu.UI.series.seriesOnAir.viewmodel.ViewAllSeriesOnAirViewModel;

import java.util.Objects;

public class ViewAllSeriesOnAirFragment extends Fragment {

    private ViewAllSeriesOnAirViewModel mViewModel;

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
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ViewAllSeriesOnAirViewModel.class);
        // TODO: Use the ViewModel
    }

}
