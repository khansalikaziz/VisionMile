package com.example.splashtrial1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class userprofile extends AppCompatActivity {
    CircleImageView img;
    TextView back;
    TextView name,email,about,name2,email2;
    private Context context;
    private TextView url;
    private FirebaseDatabase database ;
    private DatabaseReference mReference ;
    private DatabaseReference childReference ;
    String customerid;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        img=findViewById(R.id.profile_image);
        auth = FirebaseAuth.getInstance();
        back=findViewById(R.id.btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(userprofile.this,dashboard.class));
            }
        });
        name=findViewById(R.id.username);
        name2=findViewById(R.id.name2);
        email=findViewById(R.id.useremail);
        email2=findViewById(R.id.email2);
        context=this;
        customerid = auth.getCurrentUser().getUid();
        database = FirebaseDatabase.getInstance();
        mReference = database.getReference("Users");
        childReference = mReference.child("Customers").child(customerid);

    }
    @Override
    protected void onStart() {
        super.onStart();
        childReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String message = dataSnapshot.child("image").getValue(String.class);
                Picasso.get().load(message).into(img);
                String nameuser=dataSnapshot.child("name").getValue(String.class);
                name.setText(nameuser);
                String emailuser=dataSnapshot.child("email").getValue(String.class);
                email.setText(emailuser);
                String nameuser1=dataSnapshot.child("name").getValue(String.class);
                name2.setText(nameuser1);
                String emailuser1=dataSnapshot.child("email").getValue(String.class);
                email2.setText(emailuser1);


               // String nameuser=dataSnapshot.child("").getValue(String.class);

               /* String aboutuser=dataSnapshot.child("about").getValue(String.class);
                about.setText(aboutuser);*/
              /*  Picasso.with(context)
                        .load(message)
                        .into(img);*/
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}