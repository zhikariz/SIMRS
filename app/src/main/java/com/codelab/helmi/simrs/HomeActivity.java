package com.codelab.helmi.simrs;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.codelab.helmi.simrs.chat.ChatFragment;
import com.codelab.helmi.simrs.history.HistoryFragment;
import com.codelab.helmi.simrs.home.HomeFragment;
import com.codelab.helmi.simrs.info.InfoFragment;
import com.codelab.helmi.simrs.login.LoginActivity;

public class HomeActivity extends AppCompatActivity {
    FragmentManager fragmentManager = getSupportFragmentManager();
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if(savedInstanceState==null) {
            fragment = new HomeFragment();
            callFragment(fragment);
        }


        BottomNavigationView bottomNavigationView =(BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        fragment = new HomeFragment();
                        callFragment(fragment);
                        break;
                    case R.id.action_chat:
                        fragment = new ChatFragment();
                        callFragment(fragment);
                        break;

                    case R.id.action_history:
                        fragment = new HistoryFragment();
                        callFragment(fragment);
                        break;

                    case R.id.action_info:
                       fragment = new InfoFragment();
                       callFragment(fragment);
                        break;

                }
                return true;
            }
        });


    }

    private void callFragment(Fragment fragment){
        fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack();
        fragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .replace(R.id.frame_container, fragment, fragment.getClass().getSimpleName())
                .commit();
    }
}
