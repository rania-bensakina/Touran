package com.r2a.touran;

import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.r2a.touran.databinding.ActivtyHome2Binding;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import nl.joery.animatedbottombar.AnimatedBottomBar;

public class HomeActivity extends AppCompatActivity {
    ActivtyHome2Binding binding ;
    Fragment lastFragment;

 /*   @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
                return super.dispatchTouchEvent(ev);
            }

        }}*/
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activty_home2);
        getSupportFragmentManager().beginTransaction().replace(R.id.drawer_frame, new HomeFragment()).commit();
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        System.out.println(binding.bottomBar.getSelectedIndex());
       binding.bottomBar.setOnTabSelectListener(new AnimatedBottomBar.OnTabSelectListener() {
            Fragment selected_fragment = new HomeFragment();

            @Override
            public void onTabSelected(int i, @Nullable AnimatedBottomBar.Tab tab, int i1, @NotNull AnimatedBottomBar.Tab tab1) {

                switch (binding.bottomBar.getSelectedIndex()) {
                    case 0:
                        selected_fragment = new HomeFragment();
                        System.out.println("m tab home");
                        break;
                    case 1:
                        selected_fragment = new MapsFragment();
                        System.out.println("m tab map");
                        break;
                    case 2:
                        selected_fragment = new BudgetFragment();
                        System.out.println("m tab budget");
                        break;
                    case 3:
                        selected_fragment = new BusFragment();
                        System.out.println("m tab bus");
                        break;
                    case 4:
                        selected_fragment = new ProfilFragment();
                        System.out.println("m tab profil");
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

