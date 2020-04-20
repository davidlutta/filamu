package com.davidlutta.filamu.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.davidlutta.filamu.models.profile.Profile;
import com.davidlutta.filamu.repository.ProfileRepository;

public class ProfileViewModel extends ViewModel {
    private ProfileRepository mProfileRepository;

    public ProfileViewModel() {
        mProfileRepository = ProfileRepository.getInstance();
    }

    public LiveData<Profile> getProfileInfo(String personId) {
        return mProfileRepository.getProfileInfo(personId);
    }
}
