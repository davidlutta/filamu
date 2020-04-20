package com.davidlutta.filamu.api;

import com.davidlutta.filamu.models.profile.Profile;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProfileApi {
    @GET("/3/person/{person_id}")
    Call<Profile> getProfile(@Path("person_id") String personId, @Query("api_key") String key);

}
