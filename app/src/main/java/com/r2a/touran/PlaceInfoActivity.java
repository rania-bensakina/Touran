package com.r2a.touran;

import static com.r2a.touran.data.Place.PlaceType.SHOPPING;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.animation.Animation;
import android.widget.Toast;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.r2a.touran.data.Place;
import com.r2a.touran.databinding.PlaceInfoActivityBinding;

import java.util.TimeZone;


public class PlaceInfoActivity extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap map;
    Place place;
    Double longitude, latitude;
    String name;
    private boolean locationPermissionGranted;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    PlaceInfoActivityBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.place_info_activity);
        if (this.getIntent() != null) {
            Bundle extras = this.getIntent().getExtras();
            name = extras.getString("name");
            binding.description.setText(name);
            String price = extras.getString("description");
            longitude = extras.getDouble("longitude");
            latitude = extras.getDouble("latitude");//  place = new Place("gyguy",SHOPPING,35.693403,-0.626094);
            // Build the map.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maplace);
            if (mapFragment != null) {
                mapFragment.getMapAsync(this);
            }
        } else Log.e("error","NO INTENT");


        binding.backtoHome.setOnClickListener((v) -> finish());
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        if (map != null) {
            enableMyLocation();
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && grantResults.length > 0 && grantResults[0] + grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            getPlacePosition(longitude, latitude);
        } else Toast.makeText(this, "We need your location for this feature", Toast.LENGTH_SHORT).show();
    }




    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void enableMyLocation() {

        // [START maps_check_location_permission]
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (map != null) {
                map.setMyLocationEnabled(true);
                getPlacePosition(longitude, latitude);
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void getPlacePosition(Double longitude, Double latitude) {
        if (latitude != null && longitude != null) {
            LatLng pha = new LatLng(latitude, longitude);
            @SuppressLint("UseCompatLoadingForDrawables") BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.touran_pin, null);
            Bitmap b = bitmapDrawable.getBitmap();
            Bitmap smallmarker = Bitmap.createScaledBitmap(b, 72, 120, true);
            map.addMarker(new MarkerOptions()
                    .position(pha)
                    .title(name).icon(BitmapDescriptorFactory.fromBitmap(smallmarker)));
            //  map.moveCamera(CameraUpdateFactory.newLatLng(pha));
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(pha, 14.0f));
        } else {
            Toast.makeText(this, "Il n'existe pas de coordonnées", Toast.LENGTH_SHORT).show();
        }
    }

    private BitmapDescriptor BitmapFromVector(Context context, int vectorResId) {
        // below line is use to generate a drawable.
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);

        // below line is use to set bounds to our vector drawable.
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());

        // below line is use to create a bitmap for our
        // drawable which we have added.
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

        // below line is use to add bitmap in our canvas.
        Canvas canvas = new Canvas(bitmap);

        // below line is use to draw our
        // vector drawable in canvas.
        vectorDrawable.draw(canvas);

        // after generating our bitmap we are returning our bitmap.
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}
