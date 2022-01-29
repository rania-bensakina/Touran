package com.r2a.touran.ui;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.r2a.touran.R;
import com.r2a.touran.SuggestedPlacesActivity;
import com.r2a.touran.data.Place;
import com.r2a.touran.databinding.BudgetFragmentBinding;
import com.r2a.touran.viewmodels.BudgetViewModel;

import java.util.ArrayList;
import java.util.List;

public class BudgetFragment extends Fragment {

    private BudgetViewModel mViewModel;
   List<List<Place>> listeOfSuggesedPlaces;
int numberOfDays,numberOfPersons,budget;
ArrayList<String> activitiesList = new ArrayList<>();
    public static BudgetFragment newInstance() {
        return new BudgetFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        BudgetFragmentBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.budget_fragment, container, false);
        View view = binding.getRoot();
        BudgetViewModel model =ViewModelProviders.of(this).get(BudgetViewModel.class);
        model.getPlacesByBudget(budget,activitiesList.toArray(new String[0]));
        model.getPlacesObject().observe(getViewLifecycleOwner(),listoflists -> {

        });

       binding.chip1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (binding.chip1.isSelected()){
                  deleteFromTheList(binding.chip1,activitiesList);
               }else
               addToTheList(binding.chip1,activitiesList);
           }
       });
        binding.chip2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.chip2.isSelected()){
                    deleteFromTheList(binding.chip2,activitiesList);
                }else
                    addToTheList(binding.chip2,activitiesList);

            }
        });
        binding.chip3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.chip3.isSelected()){
                    deleteFromTheList(binding.chip3,activitiesList);
                }else
                    addToTheList(binding.chip3,activitiesList);

            }
        });
        binding.chip4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.chip4.isSelected()){
                    deleteFromTheList(binding.chip4,activitiesList);
                }else
                    addToTheList(binding.chip4,activitiesList);
            }
        });
        binding.chip5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.chip5.isSelected()){
                    deleteFromTheList(binding.chip5,activitiesList);
                }else
                    addToTheList(binding.chip5,activitiesList); }
        });
        binding.generer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           //  numberOfDays= Integer.parseInt(binding.nbrdays.getText().toString());
             numberOfPersons= Integer.parseInt(binding.nbrpeoeple.getText().toString());
             budget= Integer.parseInt(binding.budget.getText().toString())/numberOfPersons;
               System.out.println(listeOfSuggesedPlaces);
          /*      Intent intent = new Intent(getActivity(), SuggestedPlacesActivity.class);
                for (int i=0;i<listeOfSuggesedPlaces.getValue().size();i++){
                    Place place = (Place) listeOfSuggesedPlaces.getValue().get(i);
                    intent.putExtra("name", place.getName());
                    intent.putExtra("description", place.getDescription());
                    intent.putExtra("rank", place.getRate());
                    intent.putExtra("longitude", place.getLongitude());
                    intent.putExtra("latitude", place.getLatitude());
                    System.out.println(place);
                }
                startActivity(intent);*/

            }
        });
        return view;
    }

    private void addToTheList(Chip chip ,ArrayList<String> activities) {
       chip.setSelected(true);
       chip.setChipBackgroundColorResource(R.color.colorPrimary);
       activities.add(chip.getText().toString());
    }

    private void deleteFromTheList(Chip chip,ArrayList<String> activities) {
        chip.setSelected(false);
        chip.setChipBackgroundColorResource(R.color.standard);
        activities.remove(chip.getText().toString()); }

}