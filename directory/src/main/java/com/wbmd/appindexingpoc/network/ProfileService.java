package com.wbmd.appindexingpoc.network;

import com.wbmd.appindexingpoc.model.Profile;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileService {
    private static ProfileService mService;
    private final ServiceInterface mInterface;

    public static ProfileService getService(){
        if(mService != null){
            return mService;
        } else {
            mService = new ProfileService();
            return mService;
        }
    }

    public ProfileService(){
        String baseUrl = "https://alburt.us/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mInterface = retrofit.create(ServiceInterface.class);
    }

    public Call<List<Profile>> getBaseballProfiles(){
        return mInterface.getBaseballProfiles();
    }
}
