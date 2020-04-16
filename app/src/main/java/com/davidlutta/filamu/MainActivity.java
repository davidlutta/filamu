package com.davidlutta.filamu;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.davidlutta.filamu.fragments.MoviesFragment;
import com.davidlutta.filamu.fragments.SavedItemsFragment;
import com.davidlutta.filamu.fragments.TvFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.action_movies:
                        selectedFragment = MoviesFragment.newInstance();
                        break;
                    case R.id.action_tv:
                        selectedFragment = TvFragment.newInstance();
                        break;
                    case R.id.action_favourite:
                        selectedFragment = SavedItemsFragment.newInstance();
                        break;
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                assert selectedFragment != null;
                transaction.replace(R.id.mainActivityFrameLayout, selectedFragment);
                transaction.commit();
                return true;
            }
        });

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainActivityFrameLayout, MoviesFragment.newInstance());
        transaction.commit();
    }


}
