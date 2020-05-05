package com.davidlutta.filamu.UI.series;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.davidlutta.filamu.R;
import com.davidlutta.filamu.UI.series.airingSeries.ViewAllAiringFragment;
import com.davidlutta.filamu.UI.series.popularSeries.ViewAllPopularFragment;
import com.davidlutta.filamu.UI.series.seriesOnAir.ViewAllSeriesOnAirFragment;
import com.davidlutta.filamu.UI.series.similarSeries.ViewAllSimilarSeriesFragment;

import java.util.Objects;

public class ViewAllSeriesHolderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_series);
        selectFragment();
    }

    private void selectFragment() {
        if (getIntent().hasExtra("Category")) {
            String category = Objects.requireNonNull(getIntent().getExtras()).getString("Category");
            Fragment selectedFragment = null;
            switch (category) {
                case "AiringToday":
                    selectedFragment = ViewAllAiringFragment.newInstance();
                    break;
                case "OnTheAir":
                    selectedFragment = ViewAllSeriesOnAirFragment.newInstance();
                    break;
                case "popularSeries":
                    selectedFragment = ViewAllPopularFragment.newInstance();
                    break;
                case "similarSeries":
                    selectedFragment = ViewAllSimilarSeriesFragment.newInstance();
                    break;
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            assert selectedFragment != null;
            transaction.replace(R.id.viewAllSeriesActivityFrameLayout, selectedFragment);
            transaction.commit();
        }
    }
}
