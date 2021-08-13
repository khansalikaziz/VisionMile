package com.example.splashtrial1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import mehdi.sakout.aboutpage.Element;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;

import mehdi.sakout.aboutpage.AboutPage;

public class aboutus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        Element adsElement = new Element();
        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setDescription("This app aims to develop a highly cost-effective English Platform which can satisfy the needs of people all around the world including rural areas where courses like such aren’t affordable. ")
                .addItem(new Element().setTitle("Version 1.0"))
                .addGroup("CONNECT WITH US!")
                .addEmail("khansalikaziz786@gmail.com")
                .addWebsite("https://karanjc.github.io/visionmile.github.io/")
               // .addYoutube("https://www.youtube.com/channel/UCo37AL-AmmmoEInFC6cqauw")   //Enter your youtube link here (replace with my channel link)
                //.addPlayStore("https://drive.google.com/file/d/19dtdd_aKE-Y_dUjkFDVxyKeA23izEbVC/view?usp=sharing")   //Replace all this with your package name
                .addInstagram("vision_mile")    //Your instagram id
                .addItem(createCopyright())
                .create();
        setContentView(aboutPage);
    }
    private Element createCopyright()
    {
        Element copyright = new Element();
        @SuppressLint("DefaultLocale") final String copyrightString = String.format("Copyright %d by Visionmile", Calendar.getInstance().get(Calendar.YEAR));
        copyright.setTitle(copyrightString);
        // copyright.setIcon(R.mipmap.ic_launcher);
        copyright.setGravity(Gravity.CENTER);
        copyright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(aboutus.this,copyrightString,Toast.LENGTH_SHORT).show();
            }
        });
        return copyright;
}
}