package com.wbmd.appindexingpoc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wbmd.appindexingpoc.base.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button firstActivityButton = (Button) findViewById(R.id.first_activity_button);
        firstActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, FirstActivity.class);
//                startActivity(intent);
            }
        });

//        Button newsActivityButton = (Button) findViewById(R.id.news_activity_button);
//        newsActivityButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, NewsActivity.class);
//                startActivity(intent);
//            }
//        });

    }

}
