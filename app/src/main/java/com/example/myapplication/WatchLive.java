package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class WatchLive extends AppCompatActivity {

    private Button goBack;
    private VideoView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_live);

        goBack = (Button)findViewById(R.id.goBackBtn);
        video = (VideoView)findViewById(R.id.videoShow);

        //Back Button redirect
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WatchLive.this, HomePage.class);
                startActivity(intent);
            }
        });
    }
}
