package com.davidlutta.filamu;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.davidlutta.filamu.fragments.MoviesFragment;
import com.davidlutta.filamu.fragments.SavedItemsFragment;
import com.davidlutta.filamu.fragments.TvFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    final Fragment moviesFragment = new MoviesFragment();
    final Fragment tvFragment = new TvFragment();
    final Fragment savedItemsFragment = new SavedItemsFragment();
    final FragmentManager mFragmentManager = getSupportFragmentManager();
    public ProgressBar mProgressBar;
    Fragment selectedFragment = moviesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView);
        mProgressBar = findViewById(R.id.progress_bar);

        // Hiding Tv and saved items fragment to display only movies fragment as the home
        mFragmentManager.beginTransaction().add(R.id.mainActivityFrameLayout, tvFragment, "TvFragment").hide(tvFragment).commit();
        mFragmentManager.beginTransaction().add(R.id.mainActivityFrameLayout, savedItemsFragment, "SavedItemsFragment").hide(moviesFragment).commit();
        mFragmentManager.beginTransaction().add(R.id.mainActivityFrameLayout, moviesFragment, "MoviesFragment").commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_movies:
                        mFragmentManager.beginTransaction().hide(selectedFragment).show(moviesFragment).commit();
                        selectedFragment = moviesFragment;
                        return true;
                    case R.id.action_tv:
                        mFragmentManager.beginTransaction().hide(selectedFragment).show(tvFragment).commit();
                        selectedFragment = tvFragment;
                        return true;
                    case R.id.action_favourite:
                        mFragmentManager.beginTransaction().hide(selectedFragment).show(savedItemsFragment).commit();
                        selectedFragment = savedItemsFragment;
                        return true;
                }
                return true;
            }
        });
    }

    public void showProgressBar(boolean visibility) {
        mProgressBar.setVisibility(visibility ? View.VISIBLE : View.INVISIBLE);
    }
}
