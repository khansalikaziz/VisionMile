package com.example.splashtrial1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class coursetoffle extends AppCompatActivity {
    CardView cardView,cardView1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coursetoffle);
        cardView=findViewById(R.id.cardview2);
        cardView1=findViewById(R.id.cardview3);

        cardView.setBackground(getResources().getDrawable(R.drawable.gradient_sample));
        cardView1.setBackground(getResources().getDrawable(R.drawable.gradient_sample));
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              startActivity(new Intent(coursetoffle.this,Actualtofflecontent.class));
            }
        });
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(coursetoffle.this,timetable.class));
            }
        });

    }
}