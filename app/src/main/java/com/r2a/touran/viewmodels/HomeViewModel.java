package com.r2a.touran.viewmodels;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.r2a.touran.BuildConfig;
import com.r2a.touran.data.Place;
import com.r2a.touran.service.PlaceService;

import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeViewModel extends ViewModel {
    MutableLiveData<List<Place>> listofallplaces;
    public HomeViewModel(){
        listofallplaces = new MutableLiveData<>();
    }


    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
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
    public void getAllPlaces(){

        Call<Map<String, List<Place>>> callSync = service.getAllPlaces();
        Log.i("here i am called", "called before http call: ");

        try {
            callSync.enqueue(new Callback<Map<String, List<Place>>>() {
                @Override
                public void onResponse(Call<Map<String, List<Place>>> call, Response<Map<String, List<Place>>> response) {
                    Log.i("TAG", "onResponse: succeed"+response.body().get("places"));
                    listofallplaces.setValue(response.body().get("places"));
                }

                @Override
                public void onFailure(Call<Map<String, List<Place>>> call, Throwable t) {
                    Log.e("error calling api", "onFailure: ", t.fillInStackTrace());
                }
            });

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public MutableLiveData getAllPlacesObject(){
        return listofallplaces;
    }

}