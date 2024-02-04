package com.example.panelparadisebetaversion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        java.text.DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("lifecycle", "onCreate invoked");


        //Locating Bottom menu navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        //Listener for the menu navigation
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                /*itemID locates the item on the menu bottom navigation and replaces fragment(using method replaceFragment) when listener is activated*/
                int itemId = item.getItemId();
                if (itemId == R.id.mylist_nav) { //I changed Switch case to if-else due to the program not able to locate Id on the bottom menu nav
                    replaceFragment(new MyList_Fragment());
                } else if (itemId == R.id.profile_nav) {
                    replaceFragment(new Profile_Fragment());
                } else if (itemId == R.id.home_nav) {
                    replaceFragment(new Home_Fragment());
                }
                return false;
            }

        });
    }
    private void replaceFragment(Fragment fragment){
        /*Fragment Manager is the class responsible for performing actions and manipulating the app Fragments*/
        FragmentManager fragmentManager = getSupportFragmentManager();
        /*Each set of Fragment changed is called Transaction and method can be specified*/
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        /* Navigate between fragments using replace and locating the container view id */
        fragmentTransaction.replace(R.id.fragmentContainerView,fragment);
        /*commit = perform Transaction*/
        fragmentTransaction.commit();

    }

    /*Optimization for App */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("lifecyle", "onStart invoked");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("lifecycle", "onResume invoked");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d("lifecycle", "onStop invoked");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("lifecycle", "onRestart invoked");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("lifecycle", "onDestroy invoked");
    }
}