package com.r2a.touran.service;

import com.r2a.touran.data.Place;
import com.r2a.touran.data.Point;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PlaceService {

    @GET("places")
    public Call<Map<String,List<Place>>> getAllPlaces();

    @GET("places/{id}")
    public Call<Place> getPlaceById(@Path("id") Long id);

    @PATCH("places/{id}")
    public Call<Void> likeAPlace(@Path("id") Long id,@Query("type") String type);

    @GET("places")
    public Call<Map<String, List<List<Place>>>> getPlacesByBudget(@Query("budget") int budget,@Query("filter") String[] filter);

    @GET("places")
    public Call<Map<String,List<Place>>> getNearbyPlacesWithinDistance(@Query("currrentlocation") Point currentlocation, @Query("distancerange") double distancerange);

    @PATCH("places/{id}")
    public Call<Void> updateRate(@Path("id") Long id ,@Query("type") String type);
}
