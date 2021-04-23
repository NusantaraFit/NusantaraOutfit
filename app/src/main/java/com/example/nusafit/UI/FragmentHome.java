package com.example.nusafit.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.appcompat.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.nusafit.R;
import com.example.nusafit.ShoppingChartActivity;
import com.example.nusafit.chat.ChatActivity;


public class FragmentHome extends Fragment {
    ImageButton shopping;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home,
                container, false);

        //shopping chart
        ImageButton chart = (ImageButton) rootView.findViewById(R.id.sChart);
        chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                goToAttract();
                Intent intent = new Intent(getActivity(), ShoppingChartActivity.class);
                startActivity(intent);
            }
        });

        //com.example.project_nusantarafit.chat
        ImageButton chat = (ImageButton) rootView.findViewById(R.id.chat);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                goToAttract();
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                startActivity(intent);
            }
        });



        return rootView;

//        shopping = getActivity().findViewById(R.id.sChart);
//        shopping.setOnClickListener(this);
//        super.onActivityCreated(savedInstanceState);

    }



}