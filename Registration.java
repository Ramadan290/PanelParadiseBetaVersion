package com.example.panelparadisebetaversion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;

public class Registration extends AppCompatActivity {
    TextView Back;
    Button Register;


    /*Creating object of DatabaseReference class to access firebase realtime Database
     and take URL in website to realtime database
     */
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://panel-paradise-beta-version-default-rtdb.firebaseio.com/");

    //Create object of FirebaseAuth class to access FirebaseAuth and Initialize Firebase Auth
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    EditText username, email, password, re_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        java.text.DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());

        LottieAnimationView lottieAnimationView = findViewById(R.id.Register_Page_Animation);
        lottieAnimationView.animate().setDuration(1000).setStartDelay(3000);
        lottieAnimationView.setRepeatCount(Animation.INFINITE);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        re_password = findViewById(R.id.re_password);
        Register = findViewById(R.id.Register_btn);

        //Back button : drawable used
        Back = findViewById(R.id.back_button);

        /*Creating a User in Realtime Database */
        Register.setOnClickListener(v -> {

        //get data from EditTexts into String variables
        String full_name_text = username.getText().toString();
        String email_text = email.getText().toString().trim();
        String password_text = password.getText().toString();
        String re_password_text = re_password.getText().toString();

        //Check that none of the data fields are null
        if (full_name_text.isEmpty() || email_text.isEmpty() || password_text.isEmpty() || re_password_text.isEmpty()) {
            email.setError("Make sure none of the fields are empty");
            email.requestFocus();
            username.setError("Make sure none of the fields are empty");
            username.requestFocus();
            password.setError("Make sure none of the fields are empty");
            password.requestFocus();
            re_password.setError("Make sure none of the fields are empty");
            re_password.requestFocus();
            return;
        }
        //Check Valid Email
        else if (!Patterns.EMAIL_ADDRESS.matcher(email_text).matches()) {
            email.setError("This is not a valid Email");
            email.requestFocus();
            return;
        }
        //Check Password length
        else if (password_text.length() < 6) {
            password.setError("Password has to have a minimum of 6 characters");
            password.requestFocus();
            return;
        }
        //Check if password are matching with each other
        else if (!password_text.equals(re_password_text)) {
            Toast.makeText(Registration.this, "Passwords has to match ", Toast.LENGTH_SHORT).show();
            re_password.setError("Password are not matching");
        }
        else {
            //to create new user with Authentication inside the realtime database
            firebaseAuth.createUserWithEmailAndPassword(email_text, password_text).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    //if email is not registered before
                    if (task.isSuccessful()) {
                        //store information in realtime database
                        databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                //check if email is not registered before
                                if (snapshot.hasChild(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                                    Toast.makeText(Registration.this, "email is already registered", Toast.LENGTH_SHORT).show();
                                } else {
                                    //using user id as unique identity of every user so all other details of user comes under user id
                                    databaseReference.child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .child("Username").setValue(full_name_text);
                                    databaseReference.child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .child("Email").setValue(email_text);
                                    Toast.makeText(Registration.this, "registration is successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Registration.this,Login.class);
                                    startActivity(intent);
                                }


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                    } else {
                        email.setError(task.getException().getMessage());
                    }
                }
            });
        }



        //navigate from registration back to Login using the back button
        Back.setOnClickListener(vi -> {
            Intent intent = new Intent(Registration.this,Login.class);
            startActivity(intent);
        });

    });
}
}