package com.example.eyehelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Instructions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);
    }
    public void home(View view) {
        Intent intent = new Intent(this, IshiharaTest.class);
        startActivity(intent);
    }
}