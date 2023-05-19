package com.travelease.nitant;

import static android.content.ContentValues.TAG;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.travelease.nitant.databinding.ActivityGoogleMapBinding;

import java.io.IOException;
import java.util.List;

public class GoogleMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityGoogleMapBinding binding;

    private Geocoder geocoder;

    private String place,city,state, LocationName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        place = intent.getStringExtra("place");
        city = intent.getStringExtra("city");
        state = intent.getStringExtra("state");
        LocationName = place+" "+city+" "+state;

        binding = ActivityGoogleMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        geocoder = new Geocoder(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);



        try {
            List<Address> addresses = geocoder.getFromLocationName(LocationName,1);
            Address addressplace = addresses.get(0);
            Log.d(TAG, "onMapReady: "+addressplace.getFeatureName()+" Latitude : " +addressplace.getLatitude()
                    +" Longitude : "+addressplace.getLongitude()
                    +"\n"+"Place :" + place);

            //Getting LAT LNG of the place from google api
            LatLng ll = new LatLng(addressplace.getLatitude(),addressplace.getLongitude());

            MarkerOptions markerOptions = new MarkerOptions()
                    .position(ll)
                    .title(place+ " " +addressplace.getFeatureName());
            mMap.addMarker(markerOptions);

            //Zoom effect on your location
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(ll,16);
            mMap.animateCamera(cameraUpdate);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}