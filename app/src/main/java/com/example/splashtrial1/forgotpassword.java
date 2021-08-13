package com.example.splashtrial1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgotpassword extends AppCompatActivity {
    private EditText inputEmail;
    Button btnback;
    private Button btnReset;
    TextView textView;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        auth=FirebaseAuth.getInstance();
        inputEmail=(EditText) findViewById(R.id.editTextTextEmailAddress);
        btnReset=(Button) findViewById(R.id.button);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=inputEmail.getText().toString();
                auth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(forgotpassword.this, "Please check your email to reset Password", Toast.LENGTH_LONG).show();
                                    //textView.setText("Check Email");
                                    startActivity(new Intent(forgotpassword.this,login_activity.class));
                                }
                            }
                        });

            }
        });

    }
}