package com.r2a.touran;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.r2a.touran.Adpters.ClosestPlacesAdapter;
import com.r2a.touran.Data.Place;
import com.r2a.touran.databinding.ActivtyHome2Binding;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

import nl.joery.animatedbottombar.AnimatedBottomBar;

public class HomeActivity extends AppCompatActivity {
    ActivtyHome2Binding binding ;
    Fragment lastFragment;




    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activty_home2);
        getSupportFragmentManager().beginTransaction().replace(R.id.drawer_frame, new HomeFragment()).commit();
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        binding.bottomBar.setOnTabSelectListener(new AnimatedBottomBar.OnTabSelectListener() {
            Fragment selected_fragment;

            @Override
            public void onTabSelected(int i, @Nullable AnimatedBottomBar.Tab tab, int i1, @NotNull AnimatedBottomBar.Tab tab1) {
                switch (i) {
                    case 1:
                        selected_fragment = new HomeFragment();
                        break;
                    case 2:
                        selected_fragment = new MapFragment();
                        break;
                    case 3:
                        selected_fragment = new BudgetFragment();
                        break;
                    case 4:
                        selected_fragment = new BusFragment();
                        break;
                    case 5:
                        selected_fragment = new ProfilFragment();
                        break;


                }
                getSupportFragmentManager().beginTransaction().replace(R.id.drawer_frame, selected_fragment).commit();
                lastFragment = selected_fragment;
            }

            @Override
            public void onTabReselected(int i, @NotNull AnimatedBottomBar.Tab tab) {

            }
        });

    }

}