package com.example.nusafit;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import com.example.nusafit.R;
import com.example.nusafit.UI.FragmentHome;
import com.example.nusafit.UI.FragmentProfile;
import com.example.nusafit.UI.FragmentStore;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    ImageView imageView;
    ImageButton btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

        loadFragment(new FragmentHome());

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

    }

    int counter = 0;

    @Override
    public void onBackPressed(){
        counter++;
        if (counter == 2 ){
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item){
        Fragment fragment = null;

        switch (item.getItemId()){
            case R.id.navigation_home:
                fragment = new FragmentHome();
                break;
            case R.id.navigation_store:
                fragment = new FragmentStore();
                break;
            case R.id.navigation_profile:
                fragment = new FragmentProfile();
                break;
        }
        return loadFragment(fragment);
    }
    private boolean loadFragment (Fragment fragment){
        if(fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container , fragment)
                    .commit();
            return  true;
        }
        return false;
    }

}