package com.example.splashtrial1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class settingactivity extends AppCompatActivity {
   private Switch darkModeSwitch;
   TextView sign;
    ProgressDialog dialog;
    

  /* public void logout(View view){
       //auth.getInstance().signOut();
       final Timer timer=new Timer();
       timer.schedule(new TimerTask() {
           @Override
           public void run() {
               dialog.dismiss();
               timer.cancel();
               SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
               SharedPreferences.Editor editor=preferences.edit();
               editor.putString("remember","false");
               editor.apply();
               finish();
               startActivity(new Intent(settingactivity.this,login_activity.class));
           }
       },2000);
   }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingactivity);
        dialog = new ProgressDialog(this);
        dialog.setMessage("you will be signout from the app");
        dialog.setTitle("Sign out");
        sign=findViewById(R.id.signout);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                final Timer timer=new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                        timer.cancel();
                        SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                        SharedPreferences.Editor editor=preferences.edit();
                        editor.putString("remember","false");
                        editor.apply();
                        finish();
                        startActivity(new Intent(settingactivity.this,login_activity.class));
                    }
                },2000);
                //startActivity(new Intent(Dashboard.this,LoginActivity.class));


            }
        });
      /* if(new DarkModePrefManager(this).isNightMode()){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }*/
      //  AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);


        //function for enabling dark mode
        //setDarkModeSwitch();
    }
  /* private void setDarkModeSwitch(){
        darkModeSwitch = findViewById(R.id.darkModeSwitch);
        darkModeSwitch.setChecked(new DarkModePrefManager(this).isNightMode());
        darkModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                DarkModePrefManager darkModePrefManager = new DarkModePrefManager(getApplicationContext());
                darkModePrefManager.setDarkMode(!darkModePrefManager.isNightMode());
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                recreate();
            }
        });
    }*/
}