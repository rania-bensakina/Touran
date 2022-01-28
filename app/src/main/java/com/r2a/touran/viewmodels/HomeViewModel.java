package com.r2a.touran.viewmodels;

import android.content.res.Resources;

import androidx.lifecycle.ViewModel;

import com.r2a.touran.data.Place;
import com.r2a.touran.service.PlaceService;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeViewModel extends ViewModel {
    String BASE_URL="https://bendabizadam.westeurope.cloudapp.azure.com/api/v1/";
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build();
    PlaceService service = retrofit.create(PlaceService.class);
    // TODO: Implement the ViewModel
    public void getPlace(){
        Call<Place> callSync = service.getPlaceById(1L);

        try {
            Response<Place> response = callSync.execute();
            Place p = response.body();
        } catch (Exception ex) {  }
    }
    public void getPlacesByBudget(){

        Call<Place> callSync = service.getPlaceById(1L);

        try {
            Response<Place> response = callSync.execute();
            Place p = response.body();
        } catch (Exception ex) {  }
    }

}