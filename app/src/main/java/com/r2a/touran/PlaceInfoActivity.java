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
import android.view.animation.Animation;
import android.widget.Toast;

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


public class PlaceInfoActivity extends AppCompatActivity implements OnMapReadyCallback{
    GoogleMap map;
    Place place;
    Double longitude,latitude;
    String name;
    private boolean locationPermissionGranted;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

        PlaceInfoActivityBinding binding;
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.place_info_activity);
        if (this.getIntent() != null) {
            Bundle extras = this.getIntent().getExtras();
          name =  extras.getString("name");
            binding.description.setText(name);
            String price = extras.getString("description");
          longitude=  extras.getDouble("longitude");
             latitude= extras.getDouble("latitude");//  place = new Place("gyguy",SHOPPING,35.693403,-0.626094);
        } else System.out.println("NO INTENT");


        binding.backtoHome.setOnClickListener((v) -> finish());
        }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        getLocationPermission();
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==100 && grantResults.length>0 && grantResults[0]+ grantResults[1]== PackageManager.PERMISSION_GRANTED){
            System.out.println("this is happening");
            Toast.makeText(this, "HIIII", Toast.LENGTH_SHORT).show();
            getPlacePosition(longitude,latitude);
        }else   Toast.makeText(this, "GIVE ME ACCESS", Toast.LENGTH_SHORT).show();
    }




    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressWarnings("deprecation")
    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }
    private void enableMyLocation() {
        // [START maps_check_location_permission]
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (map != null) {
                map.setMyLocationEnabled(true);
                System.out.println("hiiii "+longitude);
                getPlacePosition(longitude,latitude);
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        }
    }
    public void getPlacePosition(Double longitude,Double latitude) {

            if (latitude != null && longitude!= null ) {
                LatLng pha = new LatLng(latitude, longitude);
                map.addMarker(new MarkerOptions()
                        .position(pha)
                        .title(name).icon(BitmapFromVector(getApplicationContext(), R.drawable.touran_logo)));
                //  map.moveCamera(CameraUpdateFactory.newLatLng(pha));
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(pha, 14.0f));
            }else {
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
