package com.example.eyehelp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class EyeSpecialistLogin extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eyespecialistlogin);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(!validateUsername() | !validatePassword())) {
                    checkUser();
                }
            }
        });


    }

    public Boolean validateUsername() {
        String val = etUsername.getText().toString();
        if (val.isEmpty()) {
            etUsername.setError("Username cannot be empty");
            return false;
        } else {
            etUsername.setError(null);
            return true;
        }
    }

    public Boolean validatePassword(){
        String val = etPassword.getText().toString();
        if (val.isEmpty()) {
            etPassword.setError("Password cannot be empty");
            return false;
        } else {
            etPassword.setError(null);
            return true;
        }
    }


    public void checkUser(){
        String userUsername = etUsername.getText().toString().trim();
        String userPassword = etPassword.getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase
                .getInstance("https://mapseyehelp-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("users");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(userUsername);
        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    etUsername.setError(null);
                    String passwordFromDB = snapshot.child(userUsername).child("password").getValue(String.class);
                    if (passwordFromDB.equals(userPassword)) {
                        etUsername.setError(null);
                        String nameFromDB = snapshot.child(userUsername).child("name").getValue(String.class);
                        String emailFromDB = snapshot.child(userUsername).child("email").getValue(String.class);
                        String usernameFromDB = snapshot.child(userUsername).child("username").getValue(String.class);
                        Intent intent = new Intent(EyeSpecialistLogin.this, EyeSpecialistPage.class);
                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("username", usernameFromDB);
                        intent.putExtra("password", passwordFromDB);
                        startActivity(intent);
                    } else {
                        etPassword.setError("Invalid Credentials");
                        etPassword.requestFocus();
                    }
                } else {
                    etUsername.setError("User does not exist");
                    etUsername.requestFocus();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}
