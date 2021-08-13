package com.example.splashtrial1;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class videoone extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoone);
        VideoView view =(VideoView) findViewById(R.id.videoView);
        view.setVideoPath("android.resource://" + getPackageName()+"/"+R.raw.div);//for online video just add path in bracket
        MediaController media =new MediaController(this);
        media.setAnchorView(view);
        view.setMediaController(media);
        view.start();

    }
}