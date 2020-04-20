package com.davidlutta.filamu.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.davidlutta.filamu.models.credits.CreditsCast;
import com.davidlutta.filamu.models.credits.CreditsCrew;
import com.davidlutta.filamu.models.profile.Profile;
import com.davidlutta.filamu.repository.ProfileRepository;

import java.util.List;

public class ProfileViewModel extends ViewModel {
    private ProfileRepository mProfileRepository;

    public ProfileViewModel() {
        mProfileRepository = ProfileRepository.getInstance();
    }

    public LiveData<Profile> getProfileInfo(String personId) {
        return mProfileRepository.getProfileInfo(personId);
    }

    public LiveData<List<CreditsCast>> getCreditCast(String personId) {
        return mProfileRepository.getCreditsCast(personId);
    }

    public LiveData<List<CreditsCrew>> getCreditsCrew(String personId) {
        return mProfileRepository.getCreditsCrew(personId);
    }
}
