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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterMenu extends AppCompatActivity {

    TextView sigin;
    EditText Email, FullName, Password;
    Button RegisterBtn;
    FirebaseAuth auth;
    ProgressBar progressBar;
    RadioButton RadioMale, RadioFemale;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    String gender = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_menu);

        sigin = (TextView) findViewById(R.id.SignInNow);
        Email = (EditText) findViewById(R.id.EmailRegister);
        FullName = (EditText) findViewById(R.id.FullNameRegister);
        Password = (EditText) findViewById(R.id.PasswordRegister);
        RegisterBtn = (Button) findViewById(R.id.RegisterBtn);
        progressBar = (ProgressBar) findViewById(R.id.ProgressBar);
        RadioMale = findViewById(R.id.radiomale);
        RadioFemale = findViewById(R.id.radiofemale);

        databaseReference = FirebaseDatabase.getInstance().getReference("user");
        auth = FirebaseAuth.getInstance();



        registerUser();

        sigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterMenu.this, LoginMenu.class);
                startActivity(intent);
            }
        });

    }

    private void registerUser() {
        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //menampung imputan user

                final String fullname = FullName.getText().toString().trim();
                final String emailUser = Email.getText().toString().trim();
                String passwordUser = Password.getText().toString().trim();

                if (RadioMale.isChecked()){
                    gender="Male";
                }

                if(RadioFemale.isChecked()){
                    gender="Female";
                }

                //validasi email dan password
                // jika email kosong
                if (emailUser.isEmpty()){
                    Email.setError("Email tidak boleh kosong");
                }
                // jika email not valid
                else if (!Patterns.EMAIL_ADDRESS.matcher(emailUser).matches()){
                    Email.setError("Email tidak valid");
                }
                // jika password kosong
                else if (passwordUser.isEmpty()){

                    Password.setError("Password tidak boleh kosong");

                }else if (fullname.isEmpty()){

                    FullName.setError("Email tidak boleh kosong");

                }else if (passwordUser.length() < 6){

                    Password.setError("Password minimal terdiri dari 6 karakter");

                }else{
                    progressBar.setVisibility(View.VISIBLE);
                    auth.createUserWithEmailAndPassword(emailUser,passwordUser).addOnCompleteListener(
                            RegisterMenu.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isComplete()){
                                Toast.makeText(RegisterMenu.this, "Register Failed because" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }else{
                                Data_User user = new Data_User(fullname, emailUser, gender );
                                FirebaseDatabase.getInstance().getReference("user").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(RegisterMenu.this, "Register Success", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent (RegisterMenu.this, LoginMenu.class) );
                                    }
                                });
                            }

                        }
                    });
                }



            }
        });
    }
}
