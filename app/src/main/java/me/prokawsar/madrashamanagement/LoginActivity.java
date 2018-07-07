package me.prokawsar.madrashamanagement;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private Button loginButton;
    private EditText emailText, passText;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth= FirebaseAuth.getInstance();

        toolbar = findViewById(R.id.ad_tool_id);
        //set toolbar
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        setSupportActionBar(toolbar);
        String appname = getString(R.string.app_name);
        getSupportActionBar().setTitle(appname);

        emailText = findViewById(R.id.email_id);
        passText = findViewById(R.id.password_id);
        progressBar = findViewById(R.id.progressBar3);

        loginButton = findViewById(R.id.login_btn_id);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              // userLogin();
               startActivity(new Intent(LoginActivity.this,AdminActivity.class));

            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),AdminActivity.class));
            finish();
        }
    }

//user login process
        private void userLogin(){
            String email = emailText.getText().toString().trim();
            String password = emailText.getText().toString().trim();
            //email empty chek
            if (email.isEmpty()) {
                emailText.setError("Email is required.");
                emailText.requestFocus();
                return;
            }
            //check valid email address
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailText.setError("Please enter a valid.");
                emailText.requestFocus();
                return;
            }
            //password empty chek
            if (password.isEmpty()) {
                passText.setError("Password is required.");
                passText.requestFocus();
                return;
            }
            // password length validation
            if (password.length() < 6) {
                passText.setError("Minimum length of password should be 6.");
                passText.requestFocus();
                return;
            }
           progressBar.setVisibility(View.VISIBLE);

            //set progressbar visibility
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressBar.setVisibility(View.GONE);
                    if (task.isSuccessful()) {
                        Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

