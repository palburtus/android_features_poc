package com.wbmd.appindexingpoc.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.wbmd.appindexingpoc.model.Profile;
import com.wbmd.appindexingpoc.directory.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleIntent(getIntent());
    }

    private void handleIntent(Intent intent) {
        String appLinkAction = intent.getAction();
        Uri appLinkData = intent.getData();

        if (Intent.ACTION_VIEW.equals(appLinkAction) && appLinkData != null) {
            String lastPath = appLinkData.getLastPathSegment();

            Log.e("lastpath", lastPath);
            if(lastPath.equalsIgnoreCase("directory.html")){
                Intent i = new Intent(BaseActivity.this, MainDirectoryActivity.class);
                startActivity(i);
                finish();
            } else if (lastPath.equalsIgnoreCase("valilind.html")){
                Intent i = new Intent(BaseActivity.this, ProfileDetailsActivity.class);
                i.putExtra("path", "valilind");
                startActivity(i);
                finish();
            } else if (lastPath.equalsIgnoreCase("valililind.html")){
                Intent i = new Intent(BaseActivity.this, ProfileDetailsActivity.class);
                i.putExtra("path", "valililind");
                startActivity(i);
                finish();
            } else if (lastPath.equalsIgnoreCase("valilililind.html")){
                Intent i = new Intent(BaseActivity.this, ProfileDetailsActivity.class);
                i.putExtra("path", "valilililind");
                startActivity(i);
                finish();
            }
        }
    }

    public Intent getPostInstallIntent() {
        return new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.google_play_link)))
                .addCategory(Intent.CATEGORY_BROWSABLE);
    }
}

//    private Drawable getDrawableResource(String photoRef){
//        Resources resources = this.getResources();
//        int resourceId = resources.getIdentifier(photoRef, "drawable", this.getPackageName());
//        return this.getDrawable(resourceId);
//    }


//        ProfileService service = ProfileService.getService();
//        Call<Teams> call = service.getTeams();
//        call.enqueue(new Callback<Teams>() {
//            @Override
//            public void onResponse(Call<Teams> call, Response<Teams> response) {
//                Teams teams = response.body();
//                Log.e(TAG, "onresponse" + " - " +response.body());
//
//                List<Player> players = teams.getPlayers();
//                mProfileList = new ArrayList<>();
//
//                for(Player p : players){
//                    Profile profile = new Profile();
//                    profile.setFirstName(p.getFirstName());
//                    profile.setLastName(p.getLastName());
//                    profile.setAddress(p.getBirthstate());
//                    profile.setSpecialty(p.getPrimaryPosition());
//                    mProfileList.add(profile);
//                }
//                mAdapter.updateAdapter(mProfileList);
//            }
//
//            @Override
//            public void onFailure(Call<Teams> call, Throwable t) {
//                Log.e(TAG, t.getMessage());
//                Log.e(TAG, t.getLocalizedMessage());
//                Log.e(TAG, call.toString());
//            }
//        });
//    }

//    /**
//     * ATTENTION: This was auto-generated to implement the App Indexing API.
//     * See https://g.co/AppIndexing/AndroidStudio for more information.
//     */
//    public Action getIndexApiAction() {
//        return Actions.newView("News", "http://alburt.us/news.html");
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        FirebaseUserActions.getInstance().start(getIndexApiAction());
//    }
//
//    @Override
//    public void onStop() {
//
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        FirebaseUserActions.getInstance().end(getIndexApiAction());
//        super.onStop();
//    }
