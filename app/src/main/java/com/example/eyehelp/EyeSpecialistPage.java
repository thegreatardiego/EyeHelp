package com.example.eyehelp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;


public class EyeSpecialistPage extends AppCompatActivity {
    Button btnAwareness, btnSignOut, btnposts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eyespecialist_page);

        btnAwareness = findViewById(R.id.btnAwareness);
        btnAwareness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(EyeSpecialistPage.this,AdminAwareness.class);
                startActivity(intent);
            }
        });
        btnSignOut = findViewById(R.id.btnSignOut);
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(EyeSpecialistPage.this,ActivityLogin.class);
                startActivity(intent);
            }
        });
        btnposts = findViewById(R.id.btnposts);
        btnposts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(EyeSpecialistPage.this,MainActivity1.class);
                startActivity(intent);
            }
        });
    }
}
