package com.r2a.touran;

import static com.r2a.touran.data.Place.PlaceType.CULTURE;
import static com.r2a.touran.data.Place.PlaceType.SHOPPING;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.r2a.touran.adapters.ClosestPlacesAdapter;
import com.r2a.touran.data.Place;
import com.r2a.touran.viewmodels.HomeViewModel;
import com.r2a.touran.databinding.HomeFragmentBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    ArrayList<String> placesList = new ArrayList<>();
    RecyclerView.LayoutManager RecyclerViewLayoutManager;
    ClosestPlacesAdapter adapter;
    LinearLayoutManager HorizontalLayout;
    int RecyclerViewItemPosition;
String nom ;
    private HomeViewModel mViewModel;

    public HomeFragment(int contentLayoutId) {
        super(contentLayoutId);
    }

    public HomeFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        HomeFragmentBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.home_fragment, container, false);
        View view = binding.getRoot();


        ArrayList<Place> placeArrayList = new ArrayList<Place>();
        Place place =new  Place("hahha", CULTURE, 35.693403,-0.626094);
        placeArrayList.add(place);
        Place place1 = new Place("hahhdfgrga",CULTURE,30.00,-12.25);
        placeArrayList.add(place1);
        Place place2 = new Place("gyguy",SHOPPING,30.00,-12.25);
        placeArrayList.add(place2);
   /*     Place place3 = Place.builder().name("hugyuahha").type(CULTURE).longitude(40.00).latitude(-100.55).build();
        placeArrayList.add(place3);
        Place place4 = Place.builder().name("hhgeyzfguahha").type(CULTURE).longitude(12.00).latitude(200.5).build();
        placeArrayList.add(place4);
        Place place5 = Place.builder().name("hguhiuhiahha").type(Place.PlaceType.NATURE).longitude(512.23).latitude(-121.25).build();
        placeArrayList.add(place5);
        Place place6 = Place.builder().name("hahuugbjbhha").type(CULTURE).longitude(230.00).latitude(-1542.25).build();
        placeArrayList.add(place6);
        Place place7 = Place.builder().name("haygugiuhha").type(Place.PlaceType.SHOPPING).longitude(10.00).latitude(-125.25).build();
        placeArrayList.add(place7);
        Place place8 = Place.builder().name("hauguygiuhha").type(Place.PlaceType.SHOPPING).longitude(13.00).latitude(-200.00).build();
        placeArrayList.add(place8);*/

        for (int i = 0; i < placeArrayList.size(); i++) {
            final Place placei = placeArrayList.get(i);
            String fullplace = String.format("%s | %s", placei.getName(), placei.getType().toString());
            if (!placesList.contains(fullplace)) placesList.add(fullplace);
        }

        ArrayAdapter<String> adaptername = new ArrayAdapter<String>(getContext(), R.layout.row_places, placesList);
        binding.searchView.setAdapter(adaptername);
        binding.searchView.setThreshold(2);
        binding.searchView.setOnItemClickListener((adapterView, view12, j, l) -> {
            String finalnom = binding.searchView.getText().toString();
            nom = finalnom;
            Intent intent = new Intent(getActivity(), PlaceInfoActivity.class);
            intent.putExtra("name", finalnom);
            startActivity(intent);
            // intent.putExtra("description",p.getDescription());
        });
       int i =  placeArrayList.indexOf(nom);
        System.out.println(i);
        RecyclerViewLayoutManager
                = new LinearLayoutManager(
                getActivity().getApplicationContext());
        binding.closestPlaces.setLayoutManager(
                RecyclerViewLayoutManager);
        adapter = new ClosestPlacesAdapter(getActivity(),placeArrayList);
        HorizontalLayout
                = new LinearLayoutManager(
                getContext(),
                LinearLayoutManager.HORIZONTAL, false);
        binding.closestPlaces.setLayoutManager(HorizontalLayout);
        binding.closestPlaces.setAdapter(adapter);


        binding.cfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeColorWhenClicked(binding.r1);
            }
        });
        binding.cculture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeColorWhenClicked(binding.r2);
            }
        });
        binding.cshopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeColorWhenClicked(binding.r3);
            }
        });
        binding.cnature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeColorWhenClicked(binding.r4);
            }
        });
        binding.entertainement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeColorWhenClicked(binding.r5);
            }
        });
        return view;
    }


    public void changeColorWhenClicked(RelativeLayout relativeLayout) {
        ColorDrawable viewColor = (ColorDrawable) relativeLayout.getBackground();
        int colorId = viewColor.getColor();
        if (colorId == Color.parseColor("#f39149"))
            relativeLayout.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
        else
            relativeLayout.setBackgroundColor(Color.parseColor("#f39149"));

    }

    public void openPlaceInfos(Place p) {
        Intent intent = new Intent(getActivity(), PlaceInfoActivity.class);
        startActivity(intent);
        intent.putExtra("name", p.getName());
        intent.putExtra("description", p.getDescription());

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        // TODO: Use the ViewModel
    }

}