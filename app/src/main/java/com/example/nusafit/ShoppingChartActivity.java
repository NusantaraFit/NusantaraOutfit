package com.example.nusafit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nusafit.chat.ChatActivity;

public class ShoppingChartActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart_acceptbuy);

        //previous
        ImageButton previous = (ImageButton) findViewById(R.id.back_fromcart);
        previous.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(com.example.nusafit.ShoppingChartActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //com.example.project_nusantarafit.chat
        ImageButton chat = (ImageButton)findViewById(R.id.chat);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(com.example.nusafit.ShoppingChartActivity.this, ChatActivity.class);
                startActivity(intent);
            }
        });
    }

}
