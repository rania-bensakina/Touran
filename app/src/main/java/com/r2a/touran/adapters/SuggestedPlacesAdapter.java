package com.r2a.touran.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.r2a.touran.R;
import com.r2a.touran.data.Place;
import com.r2a.touran.ui.PlaceInfoActivity;
import com.squareup.picasso.Picasso;


import java.util.List;

public class SuggestedPlacesAdapter extends RecyclerView.Adapter<SuggestedPlacesAdapter.MyView> {
    private static ClickListener clickListener;
        // List with Place type
        private List<List<Place>>list;
Activity activity;
        // View Holder class which
        // extends RecyclerView.ViewHolder
        public class MyView extends RecyclerView.ViewHolder  implements View.OnClickListener, View.OnLongClickListener{

            // Text View
            TextView name,type,number;
            ImageView placeImage;
            // parameterised constructor for View Holder class
            // which takes the view as a parameter
            @SuppressLint("CutPasteId")
            public MyView(View view)
            {
                super(view);

                name = (TextView)view
                        .findViewById(R.id.name);
                number = (TextView)view
                        .findViewById(R.id.solution_number);
                type = (TextView)view
                        .findViewById(R.id.categories);
                placeImage = (ImageView)view.findViewById(R.id.image);
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
        SuggestedPlacesAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }
        // Constructor for adapter class
        // which takes a list of place type
            public SuggestedPlacesAdapter(Activity activity , List<List<Place>> horizontalList)
        {
            this.list = horizontalList;
            this.activity= activity;
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
                    .inflate(R.layout.suggested_actvities,
                            parent,
                            false);

            // return itemView
            return new MyView(itemView);
        }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, @SuppressLint("RecyclerView") int position) {
            holder.number.setText(position);
        for (Place p:list.get(position)
             ) {

            holder.name.setText(p.getName());
            holder.type.setText(p.getType());
            Picasso.get().load(p.getImglink()).into(holder.placeImage);
            holder.placeImage.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), PlaceInfoActivity.class);
                    ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,holder.placeImage,"image_small");
                    intent.putExtra("name", list.get(position).get(position).getName());
                    intent.putExtra("description", list.get(position).get(position).getDescription());
                    intent.putExtra("rank", list.get(position).get(position).getRate());
                    intent.putExtra("longitude", list.get(position).get(position).getLongitude());
                    intent.putExtra("latitude", list.get(position).get(position).getLatitude());

                    v.getContext().startActivity(intent, optionsCompat.toBundle());
                }
            });

        }

       /* holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println(R.drawable.ic_heart);
                holder.like.setImageResource(R.drawable.ic_heart);
            }
        });*/
    }


    @Override
        public int getItemCount()
        {
            return list.size();
        }
    }


