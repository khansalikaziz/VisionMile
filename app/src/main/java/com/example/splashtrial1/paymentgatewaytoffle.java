package com.example.splashtrial1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class paymentgatewaytoffle extends AppCompatActivity implements PaymentResultListener {
    Button B;
    TextView pay;
    // variable for FirebaseAuth class
    FirebaseAuth auth,emailauth;
    DatabaseReference ielts;
    // variable for our text input
    // field for phone and OTP.
    private EditText edtPhone, edtOTP;

    // buttons for generating OTP and verifying OTP
    private Button verifyOTPBtn, generateOTPBtn,b;

    // string for storing our verification ID
    private String verificationId,customerid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentgatewaytoffle);
        Checkout.preload(getApplicationContext());
        pay=findViewById(R.id.editTextTextPersonName);
        auth = FirebaseAuth.getInstance();
        b=findViewById(R.id.button4);

        // initializing variables for button and Edittext.
        edtPhone = findViewById(R.id.idEdtPhoneNumber);
        edtOTP = findViewById(R.id.idEdtOtp);
        verifyOTPBtn = findViewById(R.id.idBtnVerify);
        generateOTPBtn = findViewById(R.id.idBtnGetOtp);
        /*verifyOTPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPayment();
            }
        });*/
        startPayment();
        // setting onclick listner for generate OTP button.
       /* generateOTPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // below line is for checking weather the user
                // has entered his mobile number or not.
                if (TextUtils.isEmpty(edtPhone.getText().toString())) {
                    // when mobile number text field is empty
                    // displaying a toast message.
                    Toast.makeText(paymentgateway.this, "Please enter a valid phone number.", Toast.LENGTH_SHORT).show();
                } else {
                    // if the text field is not empty we are calling our
                    // send OTP method for getting OTP from Firebase.
                    String phone = "+91" + edtPhone.getText().toString();
                    sendVerificationCode(phone);
                }
            }
        });*/

        // initializing on click listener
        // for verify otp button
       /* verifyOTPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validating if the OTP text field is empty or not.
                if (TextUtils.isEmpty(edtOTP.getText().toString())) {
                    // if the OTP text field is empty display
                    // a message to user to enter OTP
                    Toast.makeText(paymentgateway.this, "Please enter OTP", Toast.LENGTH_SHORT).show();
                } else {
                    // if OTP field is not empty calling
                    // method to verify the OTP.
                    verifyCode(edtOTP.getText().toString());
                }
            }
        });*/

       /* b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPayment();

            }
        });*/
    }
    public void startPayment() {
        final Activity activity=this;
        final Checkout co=new Checkout();
        try {
            JSONObject options=new JSONObject();
            options.put("name","VISIONMILE");
            options.put("description","class fee");
            //options.put("image",R.drawable.meeting);
            options.put("currency","INR");
            String payment=pay.getText().toString();
            double total=Double.parseDouble(payment);
            total=total*100;
            options.put("amount",total);
            JSONObject prefill=new JSONObject();
            prefill.put("email","khansalikaziz786@gmail.com");
            prefill.put("contact","99999999998");
            options.put("pefill",prefill);
            co.open(activity,options);
        }catch (Exception e){
            Toast.makeText(activity, "Error :"+e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this, "Your payment id is "+s.toString(), Toast.LENGTH_SHORT).show();
        customerid = auth.getCurrentUser().getUid();
        ielts = FirebaseDatabase.getInstance().getReference().child("Users").child("Customers").child(customerid).child("toffle");
        ielts.setValue("1");
        startActivity(new Intent(getApplicationContext(),dashboard.class));
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Payment Unsuccessfull...Try again", Toast.LENGTH_SHORT).show();
    }
 /*   private void signInWithCredential(PhoneAuthCredential credential) {
        // inside this method we are checking if
        // the code entered is correct or not.
        auth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // if the code is correct and the task is successful
                            // we are sending our user to new activity.
                            Toast.makeText(paymentgateway.this, "Verification successfull", Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(paymentgateway.this,videosActivity.class));
                            //finish();
                            startPayment();
                        } else {
                            // if the code is not correct then we are
                            // displaying an error message to the user.
                            Toast.makeText(paymentgateway.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }*/


 /*   private void sendVerificationCode(String number) {
        // this method is used for getting
        // OTP on user phone number.
       /* PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number, // first parameter is user's mobile number
                60, // second parameter is time limit for OTP
                // verification which is 60 seconds in our case.
                TimeUnit.SECONDS, // third parameter is for initializing units
                // for time period which is in seconds in our case.
                TaskExecutors.MAIN_THREAD,// this task will be excuted on Main thread.
                mCallBack // we are calling callback method when we recieve OTP for
                // auto verification of user.
        );*/
     /*   PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber(number)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallBack)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }*/

    // callback method is called on Phone auth provider.
   /* private PhoneAuthProvider.OnVerificationStateChangedCallbacks

            // initializing our callbacks for on
            // verification callback method.
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        // below method is used when
        // OTP is sent from Firebase
        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            // when we receive the OTP it
            // contains a unique id which
            // we are storing in our string
            // which we have already created.
            verificationId = s;
        }

        // this method is called when user
        // receive OTP from Firebase.
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            // below line is used for getting OTP code
            // which is sent in phone auth credentials.
            final String code = phoneAuthCredential.getSmsCode();

            // checking if the code
            // is null or not.
            if (code != null) {
                // if the code is not null then
                // we are setting that code to
                // our OTP edittext field.
                edtOTP.setText(code);

                // after setting this code
                // to OTP edittext field we
                // are calling our verifycode method.
                verifyCode(code);

                // startActivity(new Intent(paymentgateway.this,videosActivity.class));
            }
        }

        // this method is called when firebase doesn't
        // sends our OTP code due to any error or issue.
        @Override
        public void onVerificationFailed(FirebaseException e) {
            // displaying error message with firebase exception.
            Toast.makeText(paymentgateway.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };

    // below method is use to verify code from Firebase.
  /*  private void verifyCode(String code) {
        // below line is used for getting getting
        // credentials from our verification id and code.
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);

        // after getting credential we are
        // calling sign in method.
        signInWithCredential(credential);
    }*/
}