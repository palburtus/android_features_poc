//package com.wbmd.appindexingpoc;
//
//
//import com.wbmd.appindexingpoc.mlbmodel.Teams;
//import com.wbmd.appindexingpoc.model.Profile;
//
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class ProfileService {
//    private static ProfileService mService;
//    private final ServiceInterface mInterface;
//
//    public static ProfileService getService(){
//        if(mService != null){
//            return mService;
//        } else {
//            mService = new ProfileService();
//            return mService;
//        }
//    }
//
//    public ProfileService(){
//        String mMatchUrl = "";
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(mMatchUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        mInterface = retrofit.create(ServiceInterface.class);
//    }
//
//    public Call<List<Profile>> getTeams(){
//        return mInterface.getProfiles();
//    }
//}
