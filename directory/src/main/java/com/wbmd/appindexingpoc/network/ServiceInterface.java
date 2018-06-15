package com.wbmd.appindexingpoc.network;

import com.wbmd.appindexingpoc.model.Profile;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ServiceInterface {

    @GET("baseball-profiles.json")
    Call<List<Profile>> getBaseballProfiles();
}
