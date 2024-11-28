package com.example.shoppinglistapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {
    TextView tvSignin;
    public FirebaseAuth mAuth;
    Button btnSignup;
    EditText etpassword;
    EditText etEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        tvSignin=findViewById(R.id.tvsignin);
        tvSignin.setOnClickListener(e->onTvSignInClick());
        btnSignup=findViewById(R.id.regibutton);
        btnSignup.setOnClickListener(r->register());
        etpassword=findViewById(R.id.password);
        etEmail=findViewById(R.id.email);
        mAuth=FirebaseAuth.getInstance();
    }

    private void register() {
     String password=etpassword.getText().toString();
     String email=etEmail.getText().toString();
     if(password.isEmpty() || email.isEmpty())
     {
         Toast.makeText(this,"Fields Cannot be Empty",Toast.LENGTH_LONG).show();
         return;
     }
     if(!isValidEmail(email))
     {
         Toast.makeText(this,"Invalid Email",Toast.LENGTH_LONG).show();
         return;
     }
     LottieLoader lottieLoader=new LottieLoader(this);
     lottieLoader.show();
     mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
         @Override
         public void onComplete(@NonNull Task<AuthResult> task) {
             lottieLoader.dismiss();
             if (task.isSuccessful()) {
                 FirebaseUser user = mAuth.getCurrentUser();
                 try {
                     if (user != null)
                         user.sendEmailVerification()
                                 .addOnCompleteListener(new OnCompleteListener<Void>() {
                                     @Override
                                     public void onComplete(@NonNull Task<Void> task) {
                                         if (task.isSuccessful()) {
                                             Toast.makeText(SignUpActivity.this, "Email Sent",
                                                     Toast.LENGTH_SHORT).show();
                                             AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                                     SignUpActivity.this);
                                             // set title
                                             alertDialogBuilder.setTitle("Please Verify Your EmailID");
                                             // set dialog message
                                             alertDialogBuilder
                                                     .setMessage("A verification Email Is Sent To Your Registered EmailID, please click on the link and Sign in again!")
                                                     .setCancelable(false)
                                                     .setPositiveButton("Sign In", new DialogInterface.OnClickListener() {
                                                         public void onClick(DialogInterface dialog, int id) {
                                                             Intent signInIntent = new Intent(SignUpActivity.this,LoginActivity.class);
                                                             SignUpActivity.this.finish();
                                                             startActivity(signInIntent);
                                                         }
                                                     });

                                             // create alert dialog
                                             AlertDialog alertDialog = alertDialogBuilder.create();

                                             // show it
                                             alertDialog.show();


                                         }
                                     }
                                 });

                 } catch (Exception e) {
                     Toast.makeText(SignUpActivity.this, e.getMessage(),
                             Toast.LENGTH_SHORT).show();
                 }
             } else {
                 Toast.makeText(SignUpActivity.this, "Authentication failed.",
                         Toast.LENGTH_SHORT).show();

                 if (task.getException() != null) {
                     Toast.makeText(SignUpActivity.this, task.getException().getMessage(),
                             Toast.LENGTH_SHORT).show();
                 }

             }

         }
     });

    }

    private void onTvSignInClick() {
        Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    public static boolean isValidEmail(String email) {
        // Define the regex pattern for a valid email address
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        // Compile the regex
        Pattern pattern = Pattern.compile(emailRegex);

        // Check if email matches the pattern
        if (email == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}