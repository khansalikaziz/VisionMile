package com.example.splashtrial1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;


public class tdashboard extends AppCompatActivity {
    EditText secretCodeBox;
    Button joinBtn,creatclass;
    DatabaseReference customersDatabaseRef;
    private DatabaseReference childReference;
    private FirebaseDatabase database;
    private DatabaseReference mReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tdashboard);
        database = FirebaseDatabase.getInstance();
        mReference = database.getReference("onlineclass");
        childReference = mReference.child("code");
        secretCodeBox = findViewById(R.id.editTextTextPersonName);
        joinBtn = findViewById(R.id.button2);
        creatclass = findViewById(R.id.button3);
        creatclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code;
                code = secretCodeBox.getText().toString();
                customersDatabaseRef = FirebaseDatabase.getInstance().getReference().child("onlineclass").child("code");
                customersDatabaseRef.setValue(true);
                DatabaseReference refname = customersDatabaseRef.child("name");
                refname.setValue(code);
            }
        });
        URL serverURL;
        try {
            serverURL = new URL("https://meet.jit.si");
            JitsiMeetConferenceOptions defaultOptions =
                    new JitsiMeetConferenceOptions.Builder()
                            .setServerURL(serverURL)
                            .setWelcomePageEnabled(false)
                            .build();
            JitsiMeet.setDefaultConferenceOptions(defaultOptions);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }





        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                        .setRoom(secretCodeBox.getText().toString())
                        .setWelcomePageEnabled(false)
                        .build();

                JitsiMeetActivity.launch(tdashboard.this, options);
               /* cid = auth.getCurrentUser().getUid();
                customersDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Users").child("Customers").child(cid);
                customersDatabaseRef.setValue(true);
                DatabaseReference refname=customersDatabaseRef.child("secretcode");
                refname.setValue(secretCodeBox);*/
            }
        });
    }
}