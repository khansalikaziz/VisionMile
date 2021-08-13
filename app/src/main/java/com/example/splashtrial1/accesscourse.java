package com.example.splashtrial1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class accesscourse extends AppCompatActivity {
    private FirebaseDatabase database ;
    private DatabaseReference mReference ;
    private DatabaseReference childReference,toffleReference ;
    public String customerid,nameuser,toffleuser;
    public int c,d;
    FirebaseAuth auth;
    public void move(View view){
        startActivity(new Intent(accesscourse.this,paymentgateway.class));
    }
    public void hiti(View view){
        startActivity(new Intent(accesscourse.this,paymentgateway.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accesscourse);
        /*ActionBar bar=getActionBar();
       bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#eb6b9d")));*/
        auth = FirebaseAuth.getInstance();
        customerid = auth.getCurrentUser().getUid();
        database = FirebaseDatabase.getInstance();
        mReference = database.getReference("Users");
        childReference = mReference.child("Customers").child(customerid).child("ielts");
        //toffleReference = mReference.child("Customers").child(customerid).child("toffle");
        childReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                nameuser = (String) dataSnapshot.getValue(String.class);
                c = Integer.parseInt(nameuser.toString());


                if (c==1){
                    Toast.makeText(getApplicationContext(), "You already purchased this course", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),dashboard.class));


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "error in fetching data", Toast.LENGTH_SHORT).show();

            }
        });
    }
}