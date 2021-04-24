package com.example.nusafit;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class ChatActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    RoomchatViewPager roomchatViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom_list);

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tabs);

        roomchatViewPager = new RoomchatViewPager(
                getSupportFragmentManager());
        viewPager.setAdapter(roomchatViewPager);

        // It is used to join TabLayout with ViewPager.
        tabLayout.setupWithViewPager(viewPager);
    }

//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.nusantara_chatroom_list);
//    }

}
