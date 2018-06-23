package com.codelab.helmi.simrs;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.codelab.helmi.simrs.chat.ChatFragment;
import com.codelab.helmi.simrs.history.HistoryFragment;
import com.codelab.helmi.simrs.home.HomeFragment;
import com.codelab.helmi.simrs.info.InfoFragment;

public class HomeActivity extends AppCompatActivity {
    final FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if(savedInstanceState==null) {
            fragmentManager.beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .replace(R.id.frame_container, new HomeFragment())
                    .commit();
        }


        BottomNavigationView bottomNavigationView =(BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        fragmentManager.beginTransaction()
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .replace(R.id.frame_container, new HomeFragment())
                            .commit();
                        break;
                    case R.id.action_chat:
                        fragmentManager.beginTransaction()
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .replace(R.id.frame_container, new ChatFragment())
                                .commit();
                        break;

                    case R.id.action_history:
                        fragmentManager.beginTransaction()
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .replace(R.id.frame_container, new HistoryFragment())
                                .commit();
                        break;

                    case R.id.action_info:
                        fragmentManager.beginTransaction()
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .replace(R.id.frame_container, new InfoFragment())
                                .commit();
                        break;

                }
                return true;
            }
        });


    }
}
