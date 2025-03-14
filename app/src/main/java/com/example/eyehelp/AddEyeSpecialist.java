package com.example.eyehelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddEyeSpecialist extends AppCompatActivity {

    EditText etName, etEmail, etPassword, etUsername;
    Button btnRegister;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addeye);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);
        etUsername = findViewById(R.id.etUsername);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference reference = FirebaseDatabase
                        .getInstance("https://mapseyehelp-default-rtdb.asia-southeast1.firebasedatabase.app/")
                        .getReference("users");

                String name = etName.getText().toString();
                String email = etEmail.getText().toString();
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                HelperClass helperClass = new HelperClass(name, email,password,username);
                reference.child(name).setValue(helperClass);

                Toast.makeText(AddEyeSpecialist.this, "You have signup successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddEyeSpecialist.this, Admin.class);
                startActivity(intent);
            }
        });

    }
}