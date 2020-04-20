package com.davidlutta.filamu.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.davidlutta.filamu.api.ProfileApi;
import com.davidlutta.filamu.api.RetrofitService;
import com.davidlutta.filamu.models.profile.Profile;
import com.davidlutta.filamu.util.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileRepository {
    public static final String TAG = "ProfileRepository";
    private static ProfileRepository instance;
    private ProfileApi profileApi;

    public ProfileRepository() {
        profileApi = RetrofitService.getProfileApi();
    }

    public static ProfileRepository getInstance() {
        if (instance == null) {
            instance = new ProfileRepository();
        }
        return instance;
    }

    public MutableLiveData<Profile> getProfileInfo(String personId) {
        final MutableLiveData<Profile> profileData = new MutableLiveData<>();
        profileApi.getProfile(personId, Constants.API_KEY).enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        profileData.postValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                Log.d(TAG, "GetProfileInfo: onFailure: FAILED TO RETRIEVE PROFILE INFORMATION");
            }
        });
        return profileData;
    }
}
