package com.r2a.touran.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
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

import com.r2a.touran.BuildConfig;
import com.r2a.touran.service.PlaceService;
import com.r2a.touran.ui.PlaceInfoActivity;
import com.r2a.touran.data.Place;
import com.r2a.touran.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClosestPlacesAdapter extends RecyclerView.Adapter<ClosestPlacesAdapter.MyView> {

    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build();
    PlaceService service = retrofit.create(PlaceService.class);
    private static ClickListener clickListener;
        // List with Place type
        private List<Place> list;
Activity activity;
        // View Holder class which
        // extends RecyclerView.ViewHolder
        public class MyView extends RecyclerView.ViewHolder  implements View.OnClickListener, View.OnLongClickListener{
            Boolean itemliked= false;
            // Text View
            TextView name,coordinates;
            ImageView placeImage;
            ImageButton like;
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
                like = (ImageButton) view.findViewById(R.id.like);
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
            public ClosestPlacesAdapter(Activity activity ,List<Place> horizontalList)
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
                    .inflate(R.layout.closest_places_item,
                            parent,
                            false);

            // return itemView
            return new MyView(itemView);
        }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(list.get(position).getName());
        holder.coordinates.setText(String.format("%d people like this place",(int)list.get(position).getRate()));
        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!holder.itemliked) {
                    holder.itemliked = true;
                    holder.like.setImageResource(R.drawable.ic_heart_filled);
                    holder.coordinates.setText(String.format("%d people like this place",(int)list.get(position).getRate()+1));
                    Call<Void> mycall = service.likeAPlace(list.get(position).getId(),"LIKE");
                    mycall.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {

                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Log.i("failed", "onFailure: ",t.fillInStackTrace());
                            holder.like.setImageResource(R.drawable.ic_heart);
                            holder.coordinates.setText(String.format("%d people like this place",(int)list.get(position).getRate()-1));
                            holder.itemliked = false;
                        }
                    });
                }else{
                    holder.itemliked = false;
                    holder.like.setImageResource(R.drawable.ic_heart);
                    holder.coordinates.setText(String.format("%d people like this place",(int)list.get(position).getRate()-1));
                    Call<Void> mycall = service.likeAPlace(list.get(position).getId(),"DISLIKE");
                    mycall.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Log.i("failed", "onFailure: ",t.fillInStackTrace());
                            holder.like.setImageResource(R.drawable.ic_heart_filled);
                            holder.coordinates.setText(String.format("%d people like this place",(int)list.get(position).getRate()+1));
                            holder.itemliked = true;
                        }
                    });
                }
            }
        });
        Picasso.get().load(list.get(position).getImglink()).into(holder.placeImage);
        holder.placeImage.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PlaceInfoActivity.class);
    ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,holder.placeImage,"image_small");
                intent.putExtra("name", list.get(position).getName());
                intent.putExtra("description", list.get(position).getDescription());
                intent.putExtra("rank", list.get(position).getRate());
                intent.putExtra("longitude", list.get(position).getLongitude());
                intent.putExtra("latitude", list.get(position).getLatitude());
                intent.putExtra("imglink",list.get(position).getImglink());
                intent.putExtra("liked",holder.itemliked);
                v.getContext().startActivity(intent, optionsCompat.toBundle());
            }
        });
    }


    @Override
        public int getItemCount()
        {
            return list.size();
        }
    }


