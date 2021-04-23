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
//        ArrayList<Pay> arrayPay = new ArrayList<>();
//        PayAdapter adapter = new PayAdapter(this, arrayPay);
//        ListView listView = (ListView) findViewById(R.id.list);
//        listView.setAdapter(adapter);
//
//        Pay pay = new Pay(R.drawable.logo, "Fill in the balance", "11.12.21", "+ 7.750.000", "Success");
//        adapter.add(pay);
//
//        pay = new Pay(R.drawable.logo, "Payment", "11.12.21", "+ 10.000.000", "Succes");
//        adapter.add(pay);
//
//        pay = new Pay(R.drawable.logo, "Transfer", "11.12.21", "+ 100.000.000", "Failed");
//        adapter.add(pay);

        loadFragment(new FragmentHome());

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

//        //Camera
//        imageView = findViewById(R.id.image_view);
//        btn = findViewById(R.id.btnOpen);
//
//        if(ContextCompat.checkSelfPermission(MainActivity.this,
//                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
//                            Manifest.permission.CAMERA
//                    },
//                    100);
//        }
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent,100);
//            }
//        });

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
            case R.id.navigation_quiz:
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