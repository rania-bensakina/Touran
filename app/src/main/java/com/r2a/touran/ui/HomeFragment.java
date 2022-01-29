package com.r2a.touran.ui;

import static com.r2a.touran.data.Place.PlaceType.CULTURE;
import static com.r2a.touran.data.Place.PlaceType.SHOPPING;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.r2a.touran.R;
import com.r2a.touran.adapters.ClosestPlacesAdapter;
import com.r2a.touran.data.Place;
import com.r2a.touran.viewmodels.BudgetViewModel;
import com.r2a.touran.viewmodels.HomeViewModel;
import com.r2a.touran.databinding.HomeFragmentBinding;

import java.util.ArrayList;
import java.util.List;

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


        ArrayList<Place> placeArrayList = new ArrayList<>();
        HomeViewModel model = ViewModelProviders.of(this).get(HomeViewModel.class);
        model.getAllPlacesObject().observe(getViewLifecycleOwner(),listofplaces -> {
            List<Place> mylist = (List<Place>)listofplaces;
            // update ur ui hna
            for (int i = 0; i < mylist.size(); i++) {
                Place placei = mylist.get(i);
                String fullplace = String.format("%s | %s", placei.getName(), placei.getType());
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
            adapter = new ClosestPlacesAdapter(getActivity(),mylist);
            HorizontalLayout
                    = new LinearLayoutManager(
                    getContext(),
                    LinearLayoutManager.HORIZONTAL, false);
            binding.closestPlaces.setLayoutManager(HorizontalLayout);
            binding.closestPlaces.setAdapter(adapter);
        });
        Log.i("TAG", "onCreateView: before call ");
        model.getAllPlaces();







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