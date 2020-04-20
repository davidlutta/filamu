package com.davidlutta.filamu;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.davidlutta.filamu.adapters.cast.CreditsCastAdapter;
import com.davidlutta.filamu.adapters.crew.CreditsCrewAdapter;
import com.davidlutta.filamu.models.credits.CreditsCast;
import com.davidlutta.filamu.models.credits.CreditsCrew;
import com.davidlutta.filamu.models.profile.Profile;
import com.davidlutta.filamu.util.Constants;
import com.davidlutta.filamu.viewmodels.ProfileViewModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class ProfileActivity extends AppCompatActivity {
    private ImageView profilePicImageView;
    private TextView nameTextView;
    private TextView jobTextView;
    private TextView bio;
    private TextView DOBTextView;
    private TextView POBTextView;

    private ProfileViewModel profileViewModel;
    private CreditsCastAdapter creditsCastAdapter;
    private CreditsCrewAdapter creditsCrewAdapter;
    private List<CreditsCrew> creditsCrewsList;
    private List<CreditsCast> creditsCastsList;
    private RecyclerView knownForRecycler;

    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profilePicImageView = findViewById(R.id.profilePicImageView);
        nameTextView = findViewById(R.id.userNameTextView);
        jobTextView = findViewById(R.id.jobTextView);
        bio = findViewById(R.id.bioTextView);
        DOBTextView = findViewById(R.id.DOBTextView);
        POBTextView = findViewById(R.id.POBTextView);
        knownForRecycler = findViewById(R.id.knownForRecycler);
        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        subscribeViewModels();
    }

    private void subscribeViewModels() {
        if (getIntent().hasExtra(getString(R.string.personIntentExtraName))) {
            String personId = getIntent().getExtras().getString("personId");
            profileViewModel.getProfileInfo(personId).observe(this, new Observer<Profile>() {
                @Override
                public void onChanged(Profile profile) {
                    populateData(profile);
                }
            });
            if (getIntent().hasExtra("Credits")) {
                String credits = getIntent().getExtras().getString("Credits");
                if (credits != null) {
                    if (credits.equals("Crew")) {
                        profileViewModel.getCreditsCrew(personId).observe(this, new Observer<List<CreditsCrew>>() {
                            @Override
                            public void onChanged(List<CreditsCrew> creditsCrews) {
                                creditsCrewsList = creditsCrews;
                                setUpCrewAdapter();
                            }
                        });
                    } else if (credits.equals("Cast")) {
                        profileViewModel.getCreditCast(personId).observe(this, new Observer<List<CreditsCast>>() {
                            @Override
                            public void onChanged(List<CreditsCast> creditsCasts) {
                                creditsCastsList = creditsCasts;
                                setUpCastAdapter();
                            }
                        });
                    }
                }
            }
        }
    }

    private void populateData(Profile profile) {
        if (profile != null) {
            nameTextView.setText(profile.getName());
            jobTextView.setText(profile.getKnownForDepartment());
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
                DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);
                LocalDate ld = LocalDate.parse(profile.getBirthday(), dtf);
                String date = "Born: " + dtf2.format(ld);
                DOBTextView.setText(date);
            }
            DOBTextView.setText(String.format("Born: %s", profile.getBirthday()));
            POBTextView.setText(profile.getPlaceOfBirth());
            bio.setText(profile.getBiography());
            String profileImage = Constants.IMAGE_BASE_URL + profile.getProfilePath();
            Glide.with(this)
                    .load(profileImage)
                    .placeholder(R.drawable.ic_launcher)
                    .into(profilePicImageView);
        }
    }

    private void setUpCastAdapter() {
        if (creditsCastAdapter == null) {
            creditsCastAdapter = new CreditsCastAdapter(this, creditsCastsList);
            knownForRecycler.setLayoutManager(linearLayoutManager);
            knownForRecycler.setAdapter(creditsCastAdapter);
            knownForRecycler.setNestedScrollingEnabled(false);
        }
    }

    private void setUpCrewAdapter() {
        if (creditsCrewAdapter == null) {
            creditsCrewAdapter = new CreditsCrewAdapter(this, creditsCrewsList);
            knownForRecycler.setLayoutManager(linearLayoutManager);
            knownForRecycler.setAdapter(creditsCrewAdapter);
            knownForRecycler.setNestedScrollingEnabled(false);
        }
    }
}
