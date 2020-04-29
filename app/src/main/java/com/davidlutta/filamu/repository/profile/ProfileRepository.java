package com.davidlutta.filamu.repository.profile;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.davidlutta.filamu.api.ProfileApi;
import com.davidlutta.filamu.api.RetrofitService;
import com.davidlutta.filamu.models.credits.CreditsCast;
import com.davidlutta.filamu.models.credits.CreditsCrew;
import com.davidlutta.filamu.models.credits.CreditsResponse;
import com.davidlutta.filamu.models.profile.Profile;
import com.davidlutta.filamu.util.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// TODO: 4/24/20 Add AsyncTask Classes to perform queries in the background
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

    public MutableLiveData<List<CreditsCast>> getCreditsCast(String personId) {
        final MutableLiveData<List<CreditsCast>> creditCastData = new MutableLiveData<>();
        profileApi.getProfileCredits(personId, Constants.API_KEY).enqueue(new Callback<CreditsResponse>() {
            @Override
            public void onResponse(Call<CreditsResponse> call, Response<CreditsResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<CreditsCast> cast = response.body().getCreditsCast();
                        creditCastData.postValue(cast);
                    }
                }
            }

            @Override
            public void onFailure(Call<CreditsResponse> call, Throwable t) {
                Log.d(TAG, "GetCreditsCast: onFailure: FAILED TO RETRIEVE CAST DATA FOR PROFILE");
            }
        });
        return creditCastData;
    }

    public MutableLiveData<List<CreditsCrew>> getCreditsCrew(String personId) {
        final MutableLiveData<List<CreditsCrew>> creditsCrewData = new MutableLiveData<>();
        profileApi.getProfileCredits(personId, Constants.API_KEY).enqueue(new Callback<CreditsResponse>() {
            @Override
            public void onResponse(Call<CreditsResponse> call, Response<CreditsResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<CreditsCrew> crew = response.body().getCreditsCrew();
                        creditsCrewData.postValue(crew);
                    }
                }
            }

            @Override
            public void onFailure(Call<CreditsResponse> call, Throwable t) {
                Log.d(TAG, "GetCreditsCrew: onFailure: FAILED TO RETRIEVE CREW DATA FOR PROFILE");
            }
        });
        return creditsCrewData;
    }
}
