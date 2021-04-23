package com.example.nusafit.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nusafit.R;
import com.example.nusafit.R;
import com.example.nusafit.UI.*;
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

//        addfloat = new FloatingActionButton(R.id.fb_addProduct);



        return rootView;
//
//        Button btnprvs = rootView.findViewById(R.id.btnprvs);
//        btnprvs.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(),quiz.class);
//                startActivity(intent);
//            }
//        });


    }
}

