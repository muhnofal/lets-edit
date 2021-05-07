package com.example.projectse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginMenu extends AppCompatActivity {

    TextView signup;
    EditText Email, Password;
    ProgressBar progressBar;
    Button LoginBtn;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_menu);

        signup = (TextView) findViewById(R.id.SignUpNow);
        Email = (EditText) findViewById(R.id.EmailLogin);
        Password = (EditText) findViewById(R.id.PasswordLogin);
        LoginBtn = (Button) findViewById(R.id.loginbutton);
        progressBar = (ProgressBar) findViewById(R.id.ProgressBarLogin);
        auth = FirebaseAuth.getInstance();

        LoginUser();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginMenu.this, RegisterMenu.class);
                startActivity(intent);

            }
        });



    }

    private void LoginUser() {

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //menampung imputan user
                final String emailUser = Email.getText().toString().trim();
                final String passwordUser = Password.getText().toString().trim();

                //validasi email dan password
                // jika email kosong
                if (emailUser.isEmpty()){
                    Email.setError("Email cannot empty");
                }
                // jika email not valid
                else if (!Patterns.EMAIL_ADDRESS.matcher(emailUser).matches()){
                    Email.setError("Email not valid");
                }
                // jika password kosong
                else if (passwordUser.isEmpty()){

                    Password.setError("Password cannot empty");

                }else if (passwordUser.length() < 6){

                    Password.setError("Password must consist of at least 6 characters");

                }else{
                    progressBar.setVisibility(View.VISIBLE);
                    auth.signInWithEmailAndPassword(emailUser,passwordUser).addOnCompleteListener(LoginMenu.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(LoginMenu.this, "Login Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }else {
                                Bundle bundle = new Bundle();
                                bundle.putString("email", emailUser);
                                bundle.putString("pass", passwordUser);
                                startActivity(new Intent(LoginMenu.this, MainActivity.class).putExtra("emailpass",bundle));
                                finish();
                            }
                        }
                    });
                }
            }
        });

    }

}