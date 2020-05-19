package com.davidlutta.filamu.UI.credits.moviesknownfor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.davidlutta.filamu.R;
import com.davidlutta.filamu.adapters.cast.CreditsCastAdapter;
import com.davidlutta.filamu.adapters.crew.CreditsCrewAdapter;
import com.davidlutta.filamu.models.credits.CreditsCast;
import com.davidlutta.filamu.models.credits.CreditsCrew;
import com.davidlutta.filamu.viewmodels.ProfileViewModel;

import java.util.List;

public class KnownForActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private ProfileViewModel mViewModel;
    private CreditsCrewAdapter crewAdapter;
    private List<CreditsCrew> crewList;
    private CreditsCastAdapter castAdapter;
    private List<CreditsCast> castList;

    private RecyclerView knownForRecycler;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_known);
        setTitle("Known For");
        knownForRecycler = findViewById(R.id.KnowForActivityRecyclerView);
        swipeRefreshLayout = findViewById(R.id.KnowForActivitySwipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        mViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        subscribeViewModel();
    }

    private void subscribeViewModel() {
        if (getIntent().hasExtra("personId") && getIntent().hasExtra("Credits")) {
            String personId = getIntent().getExtras().getString("personId");
            String credits = getIntent().getExtras().getString("Credits");
            if (credits != null) {
                switch (credits) {
                    case "Crew":
                        mViewModel.getCreditsCrew(personId).observe(this, new Observer<List<CreditsCrew>>() {
                            @Override
                            public void onChanged(List<CreditsCrew> creditsCrews) {
                                crewList = creditsCrews;
                                swipeRefreshLayout.setRefreshing(false);
                                setUpCrewAdapter();
                            }
                        });
                        break;
                    case "Cast":
                        mViewModel.getCreditCast(personId).observe(this, new Observer<List<CreditsCast>>() {
                            @Override
                            public void onChanged(List<CreditsCast> creditsCasts) {
                                swipeRefreshLayout.setRefreshing(false);
                                castList = creditsCasts;
                                setUpCastAdapter();
                            }
                        });
                        break;
                }
            }
        }
    }

    private void setUpCastAdapter() {
        if (castAdapter == null) {
            castAdapter = new CreditsCastAdapter(this, castList);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
            knownForRecycler.setLayoutManager(layoutManager);
            knownForRecycler.setAdapter(castAdapter);
            knownForRecycler.setVisibility(View.VISIBLE);
        }
    }

    private void setUpCrewAdapter() {
        if (crewAdapter == null) {
            crewAdapter = new CreditsCrewAdapter(this, crewList);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
            knownForRecycler.setLayoutManager(layoutManager);
            knownForRecycler.setAdapter(crewAdapter);
            knownForRecycler.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onRefresh() {
        subscribeViewModel();
    }
}
