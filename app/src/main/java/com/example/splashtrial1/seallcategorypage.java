package com.example.splashtrial1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class seallcategorypage extends AppCompatActivity {
    CardView cardView2,cardView1,cardView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seallcategorypage);
        cardView1=findViewById(R.id.cardview4);
        cardView2=findViewById(R.id.cardview2);
        cardView3=findViewById(R.id.cardview5);
        cardView2.setBackground(getResources().getDrawable(R.drawable.gradient));
        cardView1.setBackground(getResources().getDrawable(R.drawable.gradient));
        cardView3.setBackground(getResources().getDrawable(R.drawable.gradient));
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(seallcategorypage.this,videowrite.class));
            }
        });
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(seallcategorypage.this,videoread.class));
            }
        });
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(seallcategorypage.this,vidlisten.class));
            }
        });
    }
}