package com.example.splashtrial1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

public class tregister extends AppCompatActivity {
    private CircleImageView profile;
    public Uri imageUri;
    public String path;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    FirebaseAuth auth;
    EditText emailBox, passwordBox, nameBox,aboutbox;
    Button loginBtn, signupBtn;
    private FirebaseUser AppUser;
    DatabaseReference customersDatabaseRef;
    String customerid;
    private FirebaseAuth.AuthStateListener firebaseAuthListner;
    //  FirebaseFirestore database;
    ProgressDialog dialog;
    //String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tregister);
        changeStatusBarColor();
        //  database = FirebaseFirestore.getInstance();
        storage=FirebaseStorage.getInstance();
        storageReference=storage.getReference();
        profile =findViewById(R.id.profile_image);
        auth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(this);
        dialog.setTitle("Sign up");
        dialog.setMessage("Pleas verifiy your email...");
        emailBox = findViewById(R.id.emailBox);
        nameBox = findViewById(R.id.namebox);
        passwordBox = findViewById(R.id.passwordBox);
        aboutbox=findViewById(R.id.about);
        loginBtn = findViewById(R.id.loginbtn);
        signupBtn = findViewById(R.id.createBtn);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choosePicture();
            }

        });
    }
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }
    public void onLoginClick(View view){
        startActivity(new Intent(this,login_activity.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
    }
    private void choosePicture() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            imageUri=data.getData();
            profile.setImageURI(imageUri);
            uploadpicture();
        }
    }

    private void uploadpicture() {
        final ProgressDialog pd=new ProgressDialog(this);
        pd.setTitle("Uploading image....");
        pd.show();
        final String randomKey= UUID.randomUUID().toString();
        StorageReference riversRef=storageReference.child("images/"+randomKey);
        riversRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        pd.dismiss();
                        Snackbar.make(findViewById(android.R.id.content),"Image uploaded",Snackbar.LENGTH_LONG).show();
                        taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                path=task.getResult().toString();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(tregister.this, "image upload fail", Toast.LENGTH_SHORT).show();
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, pass, name,about;
                email = emailBox.getText().toString();
                pass = passwordBox.getText().toString();
                name = nameBox.getText().toString();
                about=aboutbox.getText().toString();
               /* User user=new User();
                user.setEmail(email);
                user.setPass(pass);
                user.setName(name);
                user.setAbout(about);*/
                dialog.show();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(tregister.this, "Please write your Email...", Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(tregister.this, "Please write your Password...", Toast.LENGTH_SHORT).show();
                }


                else {
                    auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                auth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            customerid = auth.getCurrentUser().getUid();
                                            customersDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Users").child("Teachers").child(customerid);
                                            customersDatabaseRef.setValue(true);
                                            DatabaseReference refname=customersDatabaseRef.child("name");
                                            refname.setValue(name);
                                            DatabaseReference refabout=customersDatabaseRef.child("about");
                                            refabout.setValue(about);
                                            DatabaseReference refemail=customersDatabaseRef.child("email");
                                            refemail.setValue(email);
                                            DatabaseReference refepath=customersDatabaseRef.child("image");
                                            refepath.setValue(path);
                                            startActivity(new Intent(tregister.this, login_activity.class));
                                            Toast.makeText(tregister.this, "Account created successfully please verify your mail", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();


                                        }
                                        else{
                                            Toast.makeText(tregister.this, "Error", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        }
                                    }
                                });

                            /*database.collection("Users").document().set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void v) {
                                    startActivity(new Intent(SignupActivity.this,Dashboard.class));
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(SignupActivity.this, "error", Toast.LENGTH_SHORT).show();
                                }
                            });*/

                                //startActivity(new Intent(SignupActivity.this,Dashboard.class));






                                //startActivity(new Intent(SignupActivity.this,Dashboard.class));
                            } else {
                                Toast.makeText(tregister.this, "Error", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }



                        }
                    });

                }
            }
        });
    }
}