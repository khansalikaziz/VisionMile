package com.example.splashtrial1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class bottomtoffle extends AppCompatActivity {
    private FirebaseDatabase database ;
    private DatabaseReference mReference ;
    private DatabaseReference childReference,toffleReference ;
    public String customerid,nameuser,toffleuser;
    public int c,d;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottomtoffle);
        auth = FirebaseAuth.getInstance();
        customerid = auth.getCurrentUser().getUid();
        database = FirebaseDatabase.getInstance();
        mReference = database.getReference("Users");
        childReference = mReference.child("Customers").child(customerid).child("toffle");
        //toffleReference = mReference.child("Customers").child(customerid).child("toffle");
        childReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                nameuser = (String) dataSnapshot.getValue(String.class);
                c = Integer.parseInt(nameuser.toString());
                if (c==0){
                    setContentView(R.layout.youdonthaveanycourseavailable);


                }

                if (c==1){
                    startActivity(new Intent(bottomtoffle.this,coursetoffle.class));



                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(bottomtoffle.this, "error in fetching data", Toast.LENGTH_SHORT).show();

            }
        });
     /*toffleReference.addValueEventListener(new  ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                toffleuser = (String) dataSnapshot1.getValue(String.class);
                Toast.makeText(bottomcoursepage.this, toffleuser+"value access", Toast.LENGTH_SHORT).show();
                 d = Integer.parseInt(toffleuser.toString());
              if (d==0){
                    setContentView(R.layout.youdonthaveanycourseavailable);
                    Toast.makeText(getApplicationContext(), "do tofflr no", Toast.LENGTH_SHORT).show();


                }

              if (d==1){
                    startActivity(new Intent(bottomcoursepage.this,course_page_after_payment.class));

                    Toast.makeText(bottomcoursepage.this, "ielts yes", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(bottomcoursepage.this, "error in fetching data", Toast.LENGTH_SHORT).show();

            }
        });*/

    }
}