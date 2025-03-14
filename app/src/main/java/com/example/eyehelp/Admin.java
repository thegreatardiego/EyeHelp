package com.example.eyehelp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;


public class Admin extends AppCompatActivity {
    Button btnAwareness, btnNearby, btnSignOut, btnposts, btnNearby1,btnaddEyeSpecialist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        btnAwareness = findViewById(R.id.btnAwareness);
        btnAwareness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Admin.this,AdminAwareness.class);
                startActivity(intent);
            }
        });
        btnNearby = findViewById(R.id.btnNearby);
        btnNearby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Admin.this,AdminNearby.class);
                startActivity(intent);
            }
        });
        btnSignOut = findViewById(R.id.btnSignOut);
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Admin.this,ActivityLogin.class);
                startActivity(intent);
            }
        });
        btnposts = findViewById(R.id.btnposts);
        btnposts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Admin.this,MainActivity1.class);
                startActivity(intent);
            }
        });
        btnNearby1 = findViewById(R.id.btnNearby1);
        btnNearby1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Admin.this,AdminNearbyEyeSpecialists.class);
                startActivity(intent);
            }
        });
    }
}
