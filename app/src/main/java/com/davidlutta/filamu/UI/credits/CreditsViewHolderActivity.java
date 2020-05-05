package com.davidlutta.filamu.UI.credits;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.davidlutta.filamu.R;
import com.davidlutta.filamu.UI.credits.cast.CastFragment;
import com.davidlutta.filamu.UI.credits.crew.CrewFragment;

import java.util.Objects;

public class CreditsViewHolderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits_view_holder);
        selectedFragment();
    }

    private void selectedFragment() {
        if (getIntent().hasExtra("Credits")) {
            String credit = Objects.requireNonNull(getIntent().getExtras()).getString("Credits");
            assert credit != null;
            Fragment selectedFragment = null;
            switch (credit) {
                case "cast":
                    selectedFragment = CastFragment.newInstance();
                    break;
                case "crew":
                    selectedFragment = CrewFragment.newInstance();
                    break;
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            assert selectedFragment != null;
            transaction.replace(R.id.creditsViewHolderFrameLayout, selectedFragment);
            transaction.commit();
        }
    }
}
