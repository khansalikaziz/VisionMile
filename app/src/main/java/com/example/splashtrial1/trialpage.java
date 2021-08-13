package com.example.splashtrial1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class trialpage extends AppCompatActivity {
    CardView cardView;
    public void hit(View view){
        startActivity(new Intent(trialpage.this,paymentgatewaytrialpage.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trialpage);
        cardView=findViewById(R.id.cardview2);
        cardView.setBackground(getResources().getDrawable(R.drawable.gradient_blue));
    }
}