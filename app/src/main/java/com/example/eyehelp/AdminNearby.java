package com.example.eyehelp;


import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.eyehelp.databinding.FragmentMapsBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.text.DateFormat;
import java.util.Calendar;

public class AdminNearby extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMarkerClickListener {
    FragmentMapsBinding binding;
    SupportMapFragment mapFragment;
    private GoogleMap mMap;
    private TextView latitudeTextView;
    private TextView longitudeTextView;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Marker marker;
    private MarkerOptions markerOptions;
    private DatabaseReference mDatabase;
    private Button btnSave;
    private EditText editTextName;
    private EditText editTextLatitude;
    private EditText editTextLongitude;
    private EditText editLocation;
    private EditText editEmail;


    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nearby_admin);

        mDatabase= FirebaseDatabase.getInstance("https://mapseyehelp-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("EyeSpecialists");
        editTextName = findViewById(R.id.editTextName);
        editLocation = findViewById(R.id.editLocation);
        editEmail = findViewById(R.id.editEmail);
        editTextLatitude = findViewById(R.id.editTextLatitude);
        editTextLongitude = findViewById(R.id.editTextLongitude);
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Set map click listener
        mMap.setOnMapClickListener(this);

        // Set map type to satellite
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            }

    @Override
    public void onMapClick(LatLng latLng) {
        // Clear previous markers
        mMap.clear();

        // Add marker at clicked position
        MarkerOptions markerOptions = new MarkerOptions()
                .position(latLng)
                .title("Clicked Location");
        markerOptions.isDraggable();
        mMap.addMarker(markerOptions);

        // Update latitude and longitude TextViews
        editTextLatitude.setText(String.valueOf(latLng.latitude));
        editTextLongitude.setText(String.valueOf(latLng.longitude));
    }




    private void saveUserInformation(){
        String name = editTextName.getText().toString().trim();
        String location = editLocation.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        double latitude = Double.parseDouble(editTextLatitude.getText().toString().trim());
        double longitude = Double.parseDouble(editTextLongitude.getText().toString().trim());
        UserInformation userInformation = new UserInformation (name, latitude, longitude, location, email);
        String currentDate = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        mDatabase.child(currentDate).setValue(userInformation);
        Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();

    }
    @Override
    public void onClick (View view){
        if (view==btnSave){
            saveUserInformation();
            editTextName.getText().clear();
            editTextLatitude.getText().clear();
            editTextLongitude.getText().clear();
            editEmail.getText().clear();
            editLocation.getText().clear();
        }

    }


    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        return false;
    }
}

