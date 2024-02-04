package com.example.panelparadisebetaversion;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {
    EditText Email, Password;
    ; //Creating Object from EditText
    Button Login_bu, Registration, Forgot;//Create object from Method Button
    LottieAnimationView Json_Login;
    /*Create object of DatabaseReference class to access firebase realtime Database
    and take the URL in website to realtime database
     */
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://panel-paradise-beta-version-default-rtdb.firebaseio.com/");

    //Create object of FirebaseAuth class to access FirebaseAuth and Initialize Firebase Auth
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        java.text.DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        /*EditText*/
        Email = findViewById(R.id.Email);
        Password = findViewById(R.id.Password);
        /*Button*/
        Login_bu = findViewById(R.id.Login_Button);
        Registration = findViewById(R.id.Register_button);
        Forgot = findViewById(R.id.forgot);


        Json_Login = findViewById(R.id.Login_animation);
        /*R.id specifies the id from the class R which contains all the ids in application*/
        Json_Login.animate().setDuration(2000).setStartDelay(3000);
        Json_Login.setRepeatCount(Animation.INFINITE);


        /* to Registration*/
        Registration.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, Registration.class);
            startActivity(intent);
        });
        /*Forgot password*/
        Forgot.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, ForgotPassword.class);
            startActivity(intent);
        });

        Login_bu.setOnClickListener(v -> {

            
            String email_text = Email.getText().toString();
            String password_text = Password.getText().toString();

            if(email_text.isEmpty() || password_text.isEmpty()){
                Email.setError("Email cannot be empty");
                Email.requestFocus();
                Password.setError("Password cannot be empty");
                Password.requestFocus();
            }

            else if(!Patterns.EMAIL_ADDRESS.matcher( email_text).matches()) {
                Email.setError("Email is Invalid");
                Email.requestFocus();
                return;
            }

            else if (password_text.length() < 6 ){
                Password.setError("Password has to be at least 6 characters ");
                Password.requestFocus();
                return;
            }
            else{
                //Sign in with email and password
                firebaseAuth.signInWithEmailAndPassword(email_text,password_text).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //in case of successful login
                        if(task.isSuccessful()) {
                            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                            //Email Verification
                            if(firebaseUser.isEmailVerified()){
                                //redirect to Main
                                Toast.makeText(Login.this,"successful Login",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Login.this, MainActivity.class);
                                startActivity(intent);
                            }
                            else{
                                firebaseUser.sendEmailVerification();
                                Toast.makeText(Login.this,"Check your email for verification",Toast.LENGTH_SHORT).show();
                            }
                        }
                        // unsuccessful Login
                        else{
                            Toast.makeText(Login.this,"Login error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}