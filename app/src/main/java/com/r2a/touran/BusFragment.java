package com.r2a.touran;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.r2a.touran.viewmodels.BusViewModel;

public class BusFragment extends Fragment {

    private BusViewModel mViewModel;

    public static BusFragment newInstance() {
        return new BusFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bus_fragment, container, false);
    }



}