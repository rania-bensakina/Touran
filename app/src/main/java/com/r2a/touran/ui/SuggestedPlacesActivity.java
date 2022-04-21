package com.r2a.touran.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;

import com.r2a.touran.R;
import com.r2a.touran.adapters.ClosestPlacesAdapter;
import com.r2a.touran.adapters.SuggestedPlacesAdapter;
import com.r2a.touran.data.Place;
import com.r2a.touran.databinding.ActivitySuggestedPlacesBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SuggestedPlacesActivity extends AppCompatActivity {
ActivitySuggestedPlacesBinding binding;
    RecyclerView.LayoutManager RecyclerViewLayoutManager;
    SuggestedPlacesAdapter adapter;
    List<List<Place>> mylist;
    LinearLayoutManager VerticalLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_suggested_places);
        if (this.getIntent() != null) {
            Bundle extras = this.getIntent().getExtras();
            int count = extras.getInt("bigListSize");
            for(int i=0;i<count;i++){
                mylist.add(extras.getParcelableArrayList("list"+i));
            }

    }
        RecyclerViewLayoutManager
                = new LinearLayoutManager(this.getApplicationContext());
        binding.recyclerPlaces.setLayoutManager(
                RecyclerViewLayoutManager);
        adapter = new SuggestedPlacesAdapter(this,mylist);
        VerticalLayout
                = new LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerPlaces.setLayoutManager(VerticalLayout);
        binding.recyclerPlaces.setAdapter(adapter);

}}