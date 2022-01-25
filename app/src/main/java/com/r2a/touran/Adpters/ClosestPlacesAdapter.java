package com.r2a.touran.Adpters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.r2a.touran.Data.Place;
import com.r2a.touran.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ClosestPlacesAdapter extends RecyclerView.Adapter<ClosestPlacesAdapter.MyView> {

        // List with String type
        private List<Place> list;

        // View Holder class which
        // extends RecyclerView.ViewHolder
        public class MyView extends RecyclerView.ViewHolder {

            // Text View
            TextView name,coordinates;
            CircleImageView placeImage;

            // parameterised constructor for View Holder class
            // which takes the view as a parameter
            @SuppressLint("CutPasteId")
            public MyView(View view)
            {
                super(view);

                // initialise TextView with id
                name = (TextView)view
                        .findViewById(R.id.name);
                coordinates = (TextView)view
                        .findViewById(R.id.coordinates);
                placeImage = (CircleImageView)view.findViewById(R.id.place_image);
            }
        }

        // Constructor for adapter class
        // which takes a list of place type
            public ClosestPlacesAdapter(List<Place> horizontalList)
        {
            this.list = horizontalList;
        }

        // Override onCreateViewHolder which deals
        // with the inflation of the card layout
        // as an item for the RecyclerView.
        @Override
        public MyView onCreateViewHolder(ViewGroup parent,
                                         int viewType)
        {

            // Inflate item.xml using LayoutInflator
            View itemView
                    = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.closest_places_item,
                            parent,
                            false);

            // return itemView
            return new MyView(itemView);
        }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.coordinates.setText(String.format("%s , %s" , list.get(position).getLocation().x,list.get(position).getLocation().y));
      //  holder.placeImage.(list.get(position).getName());
    }


    @Override
        public int getItemCount()
        {
            return list.size();
        }
    }


