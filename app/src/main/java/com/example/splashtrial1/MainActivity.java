package com.example.splashtrial1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    VideoView videoView;
    ImageView imageView;
    TextView textView1, textView2;
    Animation top, bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       videoView=findViewById(R.id.videoView);
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.vmvid));
        videoView.start();

        /*imageView = findViewById(R.id.imageView);
        textView1 = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);


        top = AnimationUtils.loadAnimation(this, R.anim.top);
        bottom = AnimationUtils.loadAnimation(this, R.anim.bottom);
        imageView.setAnimation(top);
        textView1.setAnimation(bottom);
        textView2.setAnimation(bottom);*/
        SharedPreferences dataSave = getSharedPreferences("firstLog", 0);

        if(dataSave.getString("firstTime", "").toString().equals("no")){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(MainActivity.this, login_activity.class));
                }
            },4000);// first run is happened
        }
        else{ //  this is the first run of application
            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this, onboard.class);
                    startActivity(intent);
                    finish();
                }
            },4000);
            SharedPreferences.Editor editor = dataSave.edit();
            editor.putString("firstTime", "no");
            editor.commit();
        }

    }
}