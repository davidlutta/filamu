package com.davidlutta.filamu;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.davidlutta.filamu.models.profile.Profile;
import com.davidlutta.filamu.util.Constants;
import com.davidlutta.filamu.viewmodels.ProfileViewModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ProfileActivity extends AppCompatActivity {
    private ImageView profilePicImageView;
    private TextView nameTextView;
    private TextView jobTextView;
    private TextView bio;
    private TextView DOBTextView;
    private TextView POBTextView;

    private ProfileViewModel profileViewModel;

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
        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
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
}
