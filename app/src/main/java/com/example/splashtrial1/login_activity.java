package com.example.splashtrial1;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class login_activity extends AppCompatActivity {
    EditText emailBox, passwordBox;
    Button loginBtn;
    TextView signupBtn;
    FirebaseAuth auth;
    ProgressDialog dialog;
    CheckBox remember;
    TextView textView;
    String vali, id,nameuser;
    private FirebaseDatabase database;
    private DatabaseReference mReference;
    private DatabaseReference childReference;
    String customerid;
    //for fb login


    public void sw(View view) {
        startActivity(new Intent(login_activity.this, googleeventtrigger.class));
    }
    public void switching(View view) {
        startActivity(new Intent(login_activity.this, tlogin.class));
    }




    @RequiresApi(api = Build.VERSION_CODES.P)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_login_activity);
        //FB LOGIN START

        //FB LOGIN END

        dialog = new ProgressDialog(this);
        dialog.setMessage("You are logging in");
        dialog.setTitle("Sign in");
        auth = FirebaseAuth.getInstance();

        emailBox = findViewById(R.id.emailBox);
        passwordBox = findViewById(R.id.passwordBox);
        textView = (TextView) findViewById(R.id.textView3);
        loginBtn = findViewById(R.id.loginbtn);
        signupBtn = findViewById(R.id.signupbtn);
        remember = findViewById(R.id.remember);
        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String checkbox = preferences.getString("remember", "");
        if (checkbox.equals("true")) {
            Intent intent = new Intent(login_activity.this, dashboard.class);
            startActivity(intent);

        } else if (checkbox.equals("false")) {
            Toast.makeText(this, "Plz sign in", Toast.LENGTH_SHORT).show();
        }
        Executor executor = Executors.newSingleThreadExecutor();
        login_activity activity = this;
        BiometricPrompt biometricPrompt = new BiometricPrompt.Builder(this).setTitle("Finger print Authentication").setNegativeButton("cancel", executor, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).build();
        biometricPrompt.authenticate(new CancellationSignal(), executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(login_activity.this, "Authenticated", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                String email, password;
                email = emailBox.getText().toString();
                password = passwordBox.getText().toString();

                    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            dialog.dismiss();
                            if (task.isSuccessful()) {
                                if (auth.getCurrentUser().isEmailVerified()) {


                                    startActivity(new Intent(login_activity.this, dashboard.class));


                                } else {
                                    Toast.makeText(login_activity.this, "Please verify your email id", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(login_activity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
            }



        });
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login_activity.this, register.class));
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login_activity.this, forgotpassword.class));
            }
        });
        remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (compoundButton.isChecked()) {
                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember", "true");
                    editor.apply();


                } else if (!compoundButton.isChecked()) {

                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember", "false ");
                    editor.apply();
                }

            }
        });


    }



    @RequiresApi(api = Build.VERSION_CODES.ECLAIR)
    public void onLoginClick(View View) {
        startActivity(new Intent(this, register.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);

    }
    //FB LOGIN



}