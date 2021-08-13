package com.example.splashtrial1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class testdashboard extends AppCompatActivity {
    CardView cardView,cardView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testdashboard);
        //String currentTime = new SimpleDateFormat("l", Locale.getDefault()).format(new Date());
       // Toast.makeText(this, currentTime+" day", Toast.LENGTH_SHORT).show();
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

       /* switch (day) {
            case Calendar.SATURDAY:
                // Current day is Sunday
                break;
            case Calendar.MONDAY:
                // Current day is Monday
                break;
            case Calendar.TUESDAY:
                // etc.
                break;
        }*/
        cardView=findViewById(R.id.cardview2);
        cardView1=findViewById(R.id.cardview3);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(testdashboard.this,quiz.class));
            }
        });
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(day== Calendar.WEDNESDAY){
                    startActivity(new Intent(testdashboard.this, quiz.class));

                }
                else {
                    Toast.makeText(testdashboard.this, "Currently this Test is unavailable", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}