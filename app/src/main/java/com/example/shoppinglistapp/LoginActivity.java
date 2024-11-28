package com.example.shoppinglistapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
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

public class LoginActivity extends AppCompatActivity {
    TextView tvSignUp;
    Button btnSignin;
    FirebaseAuth mAuth;
    EditText etpassword;
    EditText etEmail;
    Button btnemailagain;
    TextView tvforgot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
       tvSignUp=findViewById(R.id.tvSignup);
       tvSignUp.setOnClickListener(e->ontvSignUpClick());
       btnSignin=findViewById(R.id.login);
       btnSignin.setOnClickListener(e->login());
        etpassword=findViewById(R.id.password);
        etEmail=findViewById(R.id.email);
        mAuth=FirebaseAuth.getInstance();
        btnemailagain=findViewById(R.id.emailagain);
        btnemailagain.setOnClickListener(e->emailagain());
        tvforgot=findViewById(R.id.forget);
        tvforgot.setOnClickListener(e->forgot());
    }

    private void forgot() {
        Intent HomeActivity = new Intent(LoginActivity.this, ForgotPassword.class);
        startActivity(HomeActivity);
        LoginActivity.this.finish();
    }

    private void emailagain() {
        LottieLoader lottieLoader=new LottieLoader(this);
        lottieLoader.show();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            user.sendEmailVerification()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            lottieLoader.dismiss();
                            if (task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Verification email sent again. Please check your inbox.", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(LoginActivity.this, "Failed to send verification email. Try again later.", Toast.LENGTH_LONG).show();
                                if (task.getException() != null) {
                                    Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
        } else {
            Toast.makeText(LoginActivity.this, "No user logged in to send verification email.", Toast.LENGTH_SHORT).show();
        }
    }

    private void login() {
        LottieLoader lottieLoader=new LottieLoader(this);
        lottieLoader.show();
        String password=etpassword.getText().toString();
        String email=etEmail.getText().toString();
        if(password.isEmpty() || email.isEmpty())
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

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        lottieLoader.dismiss();
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                if (user.isEmailVerified()) {
                                    System.out.println("Email Verified : " + user.isEmailVerified());
                                    Intent HomeActivity = new Intent(LoginActivity.this, HomeActivity.class);
                                    startActivity(HomeActivity);
                                    LoginActivity.this.finish();

                                } else {
                                    btnemailagain.setVisibility(View.VISIBLE);
                                    Toast.makeText(LoginActivity.this, "\"Please Verify your EmailID and SignIn\"", Toast.LENGTH_SHORT).show();
                                }
                            }

                        } else {
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            if (task.getException() != null) {
                                Toast.makeText(LoginActivity.this, task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }

                        }

                    }
                });
    }

    private void ontvSignUpClick() {
        Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
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