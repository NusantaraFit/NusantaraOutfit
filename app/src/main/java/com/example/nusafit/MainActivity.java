package com.example.nusafit;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.nusafit.R;
import com.example.nusafit.UI.FragmentHome;
import com.example.nusafit.UI.FragmentProfile;
import com.example.nusafit.UI.FragmentStore;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

//public class MainActivity extends AppCompatActivity{
//    ImageView imageView;
//    ImageButton btn;
    public class MainActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            BottomNavigationView navView = findViewById(R.id.navigation);
            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
            AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.navigation_home, R.id.navigation_store, R.id.navigation_profile)
                    .build();
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
            NavigationUI.setupWithNavController(navView, navController);



        }

    }
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_home);
//
//        loadFragment(new FragmentHome());
//
//        BottomNavigationView navigation = findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(this);
//
//    }
//
//    int counter = 0;
//
//    @Override
//    public void onBackPressed(){
//        counter++;
//        if (counter == 2 ){
//            super.onBackPressed();
//        }
//    }
//
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item){
//        Fragment fragment = null;
//
//        switch (item.getItemId()){
//            case R.id.navigation_home:
//                fragment = new FragmentHome();
//                break;
//            case R.id.navigation_store:
//                fragment = new FragmentStore();
//                break;
//            case R.id.navigation_profile:
//                fragment = new FragmentProfile();
//                break;
//        }
//        return loadFragment(fragment);
//    }
//    private boolean loadFragment (Fragment fragment){
//        if(fragment != null){
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.fragment_container , fragment)
//                    .commit();
//            return  true;
//        }
//        return false;
//    }

//}