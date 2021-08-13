package com.example.splashtrial1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;

public class googleuserprofile extends AppCompatActivity {
    ImageView img;
    TextView name,email;
    Button logoutbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_googleuserprofile);
        img=(ImageView)findViewById(R.id.pimage);
        name=(TextView)findViewById(R.id.nametext);
        email=(TextView)findViewById(R.id.emailtext);
        logoutbtn=(Button)findViewById(R.id.button);
        GoogleSignInAccount account= GoogleSignIn.getLastSignedInAccount(this);
        name.setText(account.getDisplayName());
        email.setText(account.getEmail());
        Glide.with(this).load(account.getPhotoUrl()).into(img);
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),login_activity.class));
                finish();
            }
        });
    }
}