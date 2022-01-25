package com.r2a.touran;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;

import com.r2a.touran.Adpters.ClosestPlacesAdapter;
import com.r2a.touran.Data.Place;
import com.r2a.touran.databinding.HomeFragmentBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    ArrayList <String> placesList = new ArrayList<>();
    RecyclerView.LayoutManager RecyclerViewLayoutManager;
    ClosestPlacesAdapter adapter;
    LinearLayoutManager HorizontalLayout;
    int RecyclerViewItemPosition;

    private HomeViewModel mViewModel;

    public HomeFragment(int contentLayoutId) {
        super(contentLayoutId);
    }

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        HomeFragmentBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.home_fragment, container, false);
        View view = binding.getRoot();

        view.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.
                            INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                    adapter.notifyDataSetChanged();
                }
                return true;
            }
        });


        ArrayList<Place> placeArrayList = new ArrayList<Place>();
        Place place = Place.builder().name("hahha").type(Place.PlaceType.CULTURE).location(new Point(22, 30)).build();
        placeArrayList.add(place);
        Place place1 = Place.builder().name("hahhdfgrga").type(Place.PlaceType.CULTURE).location(new Point(2, 4)).build();
        placeArrayList.add(place1);
        Place place2 = Place.builder().name("hargeghha").type(Place.PlaceType.DIVERTISSEMENT).location(new Point(30, 2)).build();
        placeArrayList.add(place2);
        Place place3 = Place.builder().name("hugyuahha").type(Place.PlaceType.CULTURE).location(new Point(30, 30)).build();
        placeArrayList.add(place3);
        Place place4 = Place.builder().name("hhgeyzfguahha").type(Place.PlaceType.CULTURE).location(new Point(10, 12)).build();
        placeArrayList.add(place4);
        Place place5 = Place.builder().name("hguhiuhiahha").type(Place.PlaceType.NATURE).location(new Point(40, 50)).build();
        placeArrayList.add(place5);
        Place place6 = Place.builder().name("hahuugbjbhha").type(Place.PlaceType.CULTURE).location(new Point(31, 30)).build();
        placeArrayList.add(place6);
        Place place7 = Place.builder().name("haygugiuhha").type(Place.PlaceType.SHOPPING).location(new Point(18, 20)).build();
        placeArrayList.add(place7);
        Place place8 = Place.builder().name("hauguygiuhha").type(Place.PlaceType.SHOPPING).location(new Point(50, 60)).build();
        placeArrayList.add(place8);

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
        });
        RecyclerViewLayoutManager
                = new LinearLayoutManager(
                getActivity().getApplicationContext());
        binding.closestPlaces.setLayoutManager(
                RecyclerViewLayoutManager);
        adapter = new ClosestPlacesAdapter(placeArrayList);
        HorizontalLayout
                = new LinearLayoutManager(
                getContext(),
                LinearLayoutManager.HORIZONTAL,
                false);
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




    public void changeColorWhenClicked(RelativeLayout relativeLayout){
        ColorDrawable viewColor = (ColorDrawable) relativeLayout.getBackground();
        int colorId = viewColor.getColor();
        if(colorId == Color.parseColor("#f39149"))
            relativeLayout.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
        else
            relativeLayout.setBackgroundColor(Color.parseColor("#f39149"));

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        // TODO: Use the ViewModel
    }

}