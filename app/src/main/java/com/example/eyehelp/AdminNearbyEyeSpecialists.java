package com.example.eyehelp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminNearbyEyeSpecialists extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList <UserInformation> list;
    DatabaseReference databaseReference;
    AdminMapAdapter adapter;
    ValueEventListener eventListener;
    FloatingActionButton fab;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_mapsedit);

        recyclerView = findViewById(R.id.recyclerview);
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdminMapAdapter(AdminNearbyEyeSpecialists.this,list);
        recyclerView.setAdapter(adapter);
        fab = findViewById(R.id.fab);
        databaseReference = FirebaseDatabase.getInstance("https://mapseyehelp-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("EyeSpecialists");


        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    UserInformation userInformation = dataSnapshot.getValue(UserInformation.class);

                    list.add(userInformation);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminNearbyEyeSpecialists.this, AdminNearby.class);
                startActivity(intent);
            }
        });


    }
}
