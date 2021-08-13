package com.example.splashtrial1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class timetable extends AppCompatActivity {
    private DatabaseReference childReference;
    private FirebaseDatabase database;
    private DatabaseReference mReference;
    TextView getcode;
    CardView cardView2,cardView1,cardView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        URL server;
        cardView1=findViewById(R.id.videoview1);
        cardView2=findViewById(R.id.cardview2);
        cardView3=findViewById(R.id.videoview2);

        getcode=findViewById(R.id.textView8);
        database = FirebaseDatabase.getInstance();
        mReference = database.getReference("onlineclass");
        childReference = mReference.child("code");
        String currentTime = new SimpleDateFormat("hh", Locale.getDefault()).format(new Date());
        int c = Integer.parseInt(currentTime.toString());

       // Log.i("Time generated", currentTime);

        if(c==10){

            cardView2.setBackground(getResources().getDrawable(R.drawable.greencolor));

        }
        if(c==11){

            cardView3.setBackground(getResources().getDrawable(R.drawable.greencolor));

        }

        if(c==9){

            cardView1.setBackground(getResources().getDrawable(R.drawable.greencolor));

        }
        try {
            server = new URL("https://meet.jit.si");
            JitsiMeetConferenceOptions defaultOptions =
                    new JitsiMeetConferenceOptions.Builder()
                            .setServerURL(server)
                            .setWelcomePageEnabled(false)
                            .build();
            JitsiMeet.setDefaultConferenceOptions(defaultOptions);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            getda();

                AlertDialog.Builder builder = new AlertDialog.Builder(timetable.this);

                builder.setTitle("Confirm");
                builder.setMessage("Are you sure?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        if (c == 10){
                            JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                                    .setRoom(getcode.getText().toString())
                                    .setWelcomePageEnabled(false).build();
                        JitsiMeetActivity.launch(timetable.this, options);

                        dialog.dismiss();
                    }
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();







        }
        });
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getda();
               // cardView1.setBackground(getResources().getDrawable(R.drawable.gradient_blue));

                AlertDialog.Builder builder = new AlertDialog.Builder(timetable.this);

                builder.setTitle("Confirm");
                builder.setMessage("Are you sure?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        if (c == 9){
                            JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                                    .setRoom(getcode.getText().toString())
                                    .setWelcomePageEnabled(false).build();
                            JitsiMeetActivity.launch(timetable.this, options);

                            dialog.dismiss();
                        }
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();







            }
        });
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getda();
                //cardView3.setBackground(getResources().getDrawable(R.drawable.gradient_blue));

                AlertDialog.Builder builder = new AlertDialog.Builder(timetable.this);

                builder.setTitle("Confirm");
                builder.setMessage("Are you sure?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        if (c == 11){
                            JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                                    .setRoom(getcode.getText().toString())
                                    .setWelcomePageEnabled(false).build();
                            JitsiMeetActivity.launch(timetable.this, options);

                            dialog.dismiss();
                        }
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();







            }
        });


    }

    private void getda() {
        childReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String nameuser = dataSnapshot.child("name").getValue(String.class);
                getcode.setText(nameuser);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(timetable.this, "error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}