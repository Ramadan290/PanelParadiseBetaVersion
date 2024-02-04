package com.example.panelparadisebetaversion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {
    TextView Back1 ;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();;
    EditText reset ;

    Button Forgot ;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        java.text.DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext()); // acting as an exception in case of Date
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        Back1 = findViewById(R.id.back_btn);
        Forgot = findViewById(R.id.forgot_btn);

        // I Modified this part to locate the EditText
        reset = findViewById(R.id.reset_email_password);

        //navigate from registration back to Login using the back button
        Back1.setOnClickListener(v -> {
            Intent intent = new Intent(ForgotPassword.this,Login.class);
            startActivity(intent);
        });

        Forgot.setOnClickListener(v -> {
            String email = reset.getText().toString().trim();
            /*Check that Field is not Null*/
            if(email.isEmpty()){
                reset.setError("Email Cannot be empty");
                reset.requestFocus();
                return;
            }
            /*Check the Email validity*/
            else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                reset.setError("This is not a Valid Email");
                reset.requestFocus();
                return;
            }
            else{
                //send Password Reset to Email (if Valid)
                firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(ForgotPassword.this,"Check your Email for Password Reset",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(ForgotPassword.this,"Try Again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });




    }
}