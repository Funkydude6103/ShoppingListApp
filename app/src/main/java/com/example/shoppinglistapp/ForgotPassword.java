package com.example.shoppinglistapp;

import static com.example.shoppinglistapp.LoginActivity.isValidEmail;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {
     Button btnreset;
     EditText etEmail;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forgot_password);
        etEmail=findViewById(R.id.email);
        mAuth=FirebaseAuth.getInstance();
        btnreset=findViewById(R.id.btnreset);
        btnreset.setOnClickListener(e->reset());
    }

    private void reset() {
        LottieLoader lottieLoader=new LottieLoader(this);
        lottieLoader.show();
        String email=etEmail.getText().toString();
        if(email.isEmpty())
        {
            lottieLoader.dismiss();
            Toast.makeText(this,"Fields Cannot be Empty",Toast.LENGTH_LONG).show();
            return;
        }
        if(!isValidEmail(email))
        {
            lottieLoader.dismiss();
            Toast.makeText(this,"Invalid Email",Toast.LENGTH_LONG).show();
            return;
        }
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        lottieLoader.dismiss();
                        if (task.isSuccessful()) {

                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                    ForgotPassword.this);

                            // set title
                            alertDialogBuilder.setTitle("Reset Password");

                            // set dialog message
                            alertDialogBuilder
                                    .setMessage("A Reset Password Link Is Sent To Your Registered EmailID")
                                    .setCancelable(false)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            Intent signInIntent = new Intent(ForgotPassword.this,LoginActivity.class);
                                            ForgotPassword.this.finish();
                                            startActivity(signInIntent);
                                        }
                                    });

                            AlertDialog alertDialog = alertDialogBuilder.create();
                            alertDialog.show();

                        }
                        else{
                            if (task.getException() != null) {
                                Toast.makeText(ForgotPassword.this, task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });

    }
}