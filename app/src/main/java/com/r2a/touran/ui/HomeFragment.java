package com.r2a.touran.ui;

import static com.r2a.touran.data.Place.PlaceType.CULTURE;
import static com.r2a.touran.data.Place.PlaceType.SHOPPING;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
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
import java.util.stream.Collectors;

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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        HomeFragmentBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.home_fragment, container, false);
        View view = binding.getRoot();


        ArrayList<Place> placeArrayList = new ArrayList<>();
        /*Place place =new  Place("hahha", 35.693403,-0.626094,5,"https://airalgerie.dz/wp-content/uploads/2016/10/oran-780-360.jpg.jpg","Very beautiful thing",CULTURE);
        placeArrayList.add(place);
        Place place1 = new Place("hahhdfgrga",CULTURE,30.00,-12.25);
        place1.setType(CULTURE);
        placeArrayList.add(place1);
        Place place2 = new Place("gyguy",SHOPPING,30.00,-12.25);
        placeArrayList.add(place2);*/
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
                Place myplace = mylist.stream().filter(item -> finalnom
                                .equals(String.format("%s | %s", item.getName(), item.getType()))).collect(Collectors.toList()).get(0);
                intent.putExtra("name", myplace.getName());
                intent.putExtra("imglink",myplace.getImglink());
                intent.putExtra("description", myplace.getDescription());
                intent.putExtra("rank", myplace.getRate());
                intent.putExtra("longitude", myplace.getLongitude());
                intent.putExtra("latitude",myplace.getLatitude());
                intent.putExtra("imglink",myplace.getImglink());
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