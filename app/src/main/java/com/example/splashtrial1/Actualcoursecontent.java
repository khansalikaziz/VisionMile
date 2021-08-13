package com.example.splashtrial1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Actualcoursecontent extends AppCompatActivity {
    ListView listView;
    ArrayList<String> videoList;
    ArrayAdapter adapter;
    Context context;
    ImageView down;

    ArrayList prgmName;

    public static int [] prgmImages={R.drawable.caplay,R.drawable.caplay,R.drawable.caplay,R.drawable.caplay};
    public static String [] prgmNameList={"Video 1","Video 2","Video 3","Video 4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualcoursecontent);
        context=this;
        //videoView=findViewById(R.id.videoview);
        listView=findViewById(R.id.lvideo);
        listView.setAdapter(new customlist2(this,prgmNameList,prgmImages));
       down=findViewById(R.id.down);
       down.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://firebasestorage.googleapis.com/v0/b/trialapplication-5871e.appspot.com/o/lpunest.pdf?alt=media&token=5ded2b5a-7a78-4825-9bda-7fb90025a060"));
               startActivity(browserIntent);
           }
       });

       /* videoList= new ArrayList<>();
        videoList.add("Introduction to IELTS");
        videoList.add("Listening skills");
        videoList.add("Speaking skills");
        videoList.add("Reading skills");
        videoList.add("Writing skills");*/

        //  adapter= new ArrayAdapter(this,android.R.layout.simple_list_item_1,videoList);
        //   listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(getApplicationContext(),videoone.class));
                        // Uri uri1=Uri.parse("https://firebasestorage.googleapis.com/v0/b/trialapplication-5871e.appspot.com/o/video%2FIntroductionone.mp4?alt=media&token=60b93ed1-a4ca-4b49-af87-ea10e9ebb4de");
                        //videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.introductionone));
                        Toast.makeText(context, "item 1 clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        startActivity(new Intent(getApplicationContext(),video2.class));

                        Toast.makeText(context, "item 2 cliked", Toast.LENGTH_SHORT).show();

                        //Uri uri=Uri.parse("https://firebasestorage.googleapis.com/v0/b/trialapplication-5871e.appspot.com/o/video%2FListeningskillstwo.mp4?alt=media&token=0bff9e03-b635-4f58-83ee-230fee1ec904");
                        //videoView.setVideoURI(uri);
                        // videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.listeningskillstwo));
                        break;
                    case 2:
                        startActivity(new Intent(getApplicationContext(),videothree.class));

                        // Uri uri1=Uri.parse("https://firebasestorage.googleapis.com/v0/b/trialapplication-5871e.appspot.com/o/video%2FIntroductionone.mp4?alt=media&token=60b93ed1-a4ca-4b49-af87-ea10e9ebb4de");
                        //videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.introductionone));
                        Toast.makeText(context, "item 3 clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        startActivity(new Intent(getApplicationContext(),videofour.class));
                        Toast.makeText(context, "item 4 cliked", Toast.LENGTH_SHORT).show();

                        //Uri uri=Uri.parse("https://firebasestorage.googleapis.com/v0/b/trialapplication-5871e.appspot.com/o/video%2FListeningskillstwo.mp4?alt=media&token=0bff9e03-b635-4f58-83ee-230fee1ec904");
                        //videoView.setVideoURI(uri);
                        // videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.listeningskillstwo));
                        break;

                    default:
                        break;
                }

               /* videoView.setMediaController(new MediaController(samplevideo.this));
                videoView.requestFocus();
                videoView.start();*/
            }
        });
    }
}