package com.r2a.touran.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.r2a.touran.data.Place;
import com.r2a.touran.R;

import java.util.List;

public class ClosestPlacesAdapter extends RecyclerView.Adapter<ClosestPlacesAdapter.MyView> {
    private static ClickListener clickListener;
        // List with Place type
        private List<Place> list;

        // View Holder class which
        // extends RecyclerView.ViewHolder
        public class MyView extends RecyclerView.ViewHolder  implements View.OnClickListener, View.OnLongClickListener{

            // Text View
            TextView name,coordinates;
            ImageView placeImage;

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
                        .findViewById(R.id.coodinates);
                placeImage = (ImageView)view.findViewById(R.id.place_image);
            }

            @Override
            public void onClick(View view) {
                clickListener.onItemClick(getAdapterPosition(), view);
            }

            @Override
            public boolean onLongClick(View view) {
                return false;
            }
        }
    public void setOnItemClickListener(ClickListener clickListener) {
        ClosestPlacesAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
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
        public MyView onCreateViewHolder(ViewGroup parent,int viewType)
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
        holder.coordinates.setText(String.format("%s , %s" , list.get(position).getLongitude(),list.get(position).getLatitude()));
      //  holder.placeImage.(list.get(position).getName());
    }


    @Override
        public int getItemCount()
        {
            return list.size();
        }
    }


