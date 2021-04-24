package com.example.nusafit.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nusafit.R;

import com.example.nusafit.activity_input_product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FragmentStore extends Fragment {
    FloatingActionButton addfloat;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_store,
                container, false);

        addfloat = (FloatingActionButton) rootView.findViewById(R.id.fb_addProduct);
        addfloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), activity_input_product.class);
                startActivity(intent);
            }
        });


        return rootView;
    }
    private void gofloat() {
        Intent intent = new Intent(getActivity(), activity_input_product.class);
        startActivity(intent);
    }
}

