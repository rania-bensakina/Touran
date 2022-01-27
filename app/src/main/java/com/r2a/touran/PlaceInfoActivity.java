package com.r2a.touran;

import static com.r2a.touran.data.Place.PlaceType.SHOPPING;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.animation.Animation;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.r2a.touran.data.Place;
import com.r2a.touran.databinding.PlaceInfoActivityBinding;


public class PlaceInfoActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener{
    GoogleMap map;
    Place place;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

        PlaceInfoActivityBinding binding;
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.place_info_activity);
        if (this.getIntent() != null) {
            Bundle extras = this.getIntent().getExtras();
            binding.description.setText(extras.getString("name"));
            String price = extras.getString("descrition");
            Place place = new Place("gyguy",SHOPPING,35.693403,-0.626094);
        } else System.out.println("NO INTENT");


        binding.backtoHome.setOnClickListener((v) -> finish());



        }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;

        if (map != null) enableMyLocation();
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            enableMyLocation();
        else
            getPlacePosition(place);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressWarnings("deprecation")
    private void enableMyLocation() {
        // [START maps_check_location_permission]
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (map != null) {
                map.setMyLocationEnabled(true);
                getPlacePosition(place);
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        }
    }
    Location pharmaplace ;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void getPlacePosition(Place p) {
        try {
            @SuppressLint("UseCompatLoadingForDrawables") BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(R.mipmap.pharmapin, null);
            Bitmap b = bitmapDrawable.getBitmap();
            Bitmap smallmarker = Bitmap.createScaledBitmap(b, 72, 120, true);
            Toast.makeText(this, p.getLatitude().toString(), Toast.LENGTH_SHORT).show();
            pharmaplace.setLatitude(p.getLatitude());
            pharmaplace.setLongitude(p.getLongitude());
            if (pharmaplace != null) {
                LatLng pha = new LatLng(pharmaplace.getLatitude(), pharmaplace.getLongitude());
                map.addMarker(new MarkerOptions()
                        .position(pha)
                        .title(p.getName()).icon(BitmapDescriptorFactory.fromBitmap(smallmarker)));
                //  map.moveCamera(CameraUpdateFactory.newLatLng(pha));
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(pha, 14.0f));
            }
        } catch (NullPointerException e1) {
            Toast.makeText(this, "Cette pharmacie n'a pas encore ajouté sa position", Toast.LENGTH_SHORT).show();
        }

    }
}
