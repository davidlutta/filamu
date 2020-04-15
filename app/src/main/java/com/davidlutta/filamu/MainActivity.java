package com.davidlutta.filamu;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_movies:
                        selectedFragment = moviesFragment;
                        return true;
                    case R.id.action_tv:
                        selectedFragment = tvFragment;
                        return true;
                    case R.id.action_favourite:
                        selectedFragment = savedItemsFragment;
                        return true;
                    default:
                        selectedFragment = moviesFragment;
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.mainActivityFrameLayout, selectedFragment);
                transaction.commit();
                return true;
            }
        });

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainActivityFrameLayout, MoviesFragment.newInstance());
        transaction.commit();
    }

    public void showProgressBar(boolean visibility) {
        mProgressBar.setVisibility(visibility ? View.VISIBLE : View.INVISIBLE);
    }
}
