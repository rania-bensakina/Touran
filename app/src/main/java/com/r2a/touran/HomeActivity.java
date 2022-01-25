package com.r2a.touran;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.r2a.touran.Adpters.ClosestPlacesAdapter;
import com.r2a.touran.Data.Place;

import com.r2a.touran.databinding.ActivtyHome2Binding;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringJoiner;

import static java.security.AccessController.getContext;

public class HomeActivity extends AppCompatActivity {
    ActivtyHome2Binding binding ;
    ArrayList <String> placesList = new ArrayList<>();
    RecyclerView.LayoutManager RecyclerViewLayoutManager;
    ClosestPlacesAdapter adapter;
    LinearLayoutManager HorizontalLayout;
    int RecyclerViewItemPosition;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_home2);

        binding = DataBindingUtil.setContentView(this, R.layout.activty_home2);
        ArrayList<Place> placeArrayList = new ArrayList<Place>();
        Place place = Place.builder().name("hahha").type(Place.PlaceType.CULTURE).location(new Point(22,30)).build();
        placeArrayList.add(place);
        Place place1 = Place.builder().name("hahhdfgrga").type(Place.PlaceType.CULTURE).location(new Point(2,4)).build();
        placeArrayList.add(place1);
        Place place2 = Place.builder().name("hargeghha").type(Place.PlaceType.DIVERTISSEMENT).location(new Point(30,2)).build();
        placeArrayList.add(place2);
        Place place3 = Place.builder().name("hugyuahha").type(Place.PlaceType.CULTURE).location(new Point(30,30)).build();
        placeArrayList.add(place3);
        Place place4 = Place.builder().name("hhgeyzfguahha").type(Place.PlaceType.CULTURE).location(new Point(10,12)).build();
        placeArrayList.add(place4);
        Place place5 = Place.builder().name("hguhiuhiahha").type(Place.PlaceType.NATURE).location(new Point(40,50)).build();
        placeArrayList.add(place5);
        Place place6 = Place.builder().name("hahuugbjbhha").type(Place.PlaceType.CULTURE).location(new Point(31,30)).build();
        placeArrayList.add(place6);
        Place place7 = Place.builder().name("haygugiuhha").type(Place.PlaceType.SHOPPING).location(new Point(18,20)).build();
        placeArrayList.add(place7);
        Place place8 = Place.builder().name("hauguygiuhha").type(Place.PlaceType.SHOPPING).location(new Point(50,60)).build();
        placeArrayList.add(place8);

        for (int i = 0; i < placeArrayList.size(); i++) {
            final Place placei = placeArrayList.get(i);
            String fullplace = String.format("%s | %s" , placei.getName(),placei.getType().toString());
            if (!placesList.contains(fullplace)) placesList.add(fullplace);
        }

        ArrayAdapter<String> adaptername = new ArrayAdapter<String>(this, R.layout.row_places, placesList);

        binding.searchView.setAdapter(adaptername);
        binding.searchView.setThreshold(2);
        binding.searchView.setOnItemClickListener((adapterView, view12, j, l) -> {
            String finalnom = binding.searchView.getText().toString();
        });
        RecyclerViewLayoutManager
                = new LinearLayoutManager(
                getApplicationContext());
        binding.closestPlaces.setLayoutManager(
                RecyclerViewLayoutManager);
        adapter = new ClosestPlacesAdapter(placeArrayList);
        HorizontalLayout
                = new LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false);
        binding.closestPlaces.setLayoutManager(HorizontalLayout);
        binding.closestPlaces.setAdapter(adapter);



        binding.cfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.r1.setBackgroundColor(Color.parseColor("#f39149"));
            }
        });
        binding.cculture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.r2.setBackgroundColor(Color.parseColor("#f39149"));
            }
        });
        binding.cshopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.r3.setBackgroundColor(Color.parseColor("#f39149"));
            }
        });
        binding.cnature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.r4.setBackgroundColor(Color.parseColor("#f39149"));
            }
        });
        binding.entertainement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.r5.setBackgroundColor(Color.parseColor("#f39149"));
            }
        });

    }}