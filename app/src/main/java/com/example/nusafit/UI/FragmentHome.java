package com.example.nusafit.UI;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.nusafit.MyDataProduct;
import com.example.nusafit.R;
import com.example.nusafit.adapterHomeProduk;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment {

    private ViewHome viewHome;

    private adapterHomeProduk adapter;
    private ArrayList<MyDataProduct> arrayList;
    //Image
    private int[] Image = {R.drawable.icon_hoodie, R.drawable.logo, R.drawable.cwok, R.drawable.cwek};
    //NamaProduk
    private String[] Nama = {"Hoodie", "logo", "cwok", "cwek"};
    //HargaProduk
    private String[] Harga = {"150000", "200000", "250000", "300000"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        setHasOptionsMenu(true);
        arrayList = new ArrayList<>();
        RecyclerView recyclerView = view.findViewById(R.id.recyclerHome);
        DaftarItem();
        //Menggunakan Layout Manager, Dan Membuat List Secara Vertical
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        //Membuat Underline pada Setiap Item Didalam List
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.line));
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new adapterHomeProduk(arrayList,getContext());
        //Memasang Adapter pada RecyclerView
        recyclerView.setAdapter(adapter);


        return view;
    }

    //option menu
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_bar, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = new SearchView(getActivity());
        searchView.setQueryHint("Cari Sesuatu....");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String nextText) {
                //Data akan berubah saat user menginputkan text/kata kunci pada SearchView
                nextText = nextText.toLowerCase();
                ArrayList<MyDataProduct> dataFilter = new ArrayList<>();
                for (MyDataProduct data : arrayList) {
                    String nama = data.getNamaProduk().toLowerCase();
                    if (nama.contains(nextText)) {
                        dataFilter.add(data);
                    }
                }
                adapter.setFilter(dataFilter);
                return true;
            }
            });
   searchItem.setActionView(searchView);
    }

    //Memasukan semua data dari variable Nama dan Gambar ke parameter Class DataFiter(Nama,ImageID)
    private void DaftarItem() {
        int count = 0;
        for (String nama : Nama) {
            arrayList.add(new MyDataProduct(Image[count],Nama[count],Harga[count]));
            count++;
        }
    }
}


//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater,
//                             @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//
//
////        viewHome = new ViewModelProvider(this).get(ViewHome.class);
////
////        View rootView = inflater.inflate(R.layout.fragment_home,
////                container, false);
////
////        return rootView;
//    }
//shopping chart
//        ImageButton chart = (ImageButton) rootView.findViewById(R.id.sChart);
//        chart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                goToAttract();
//                Intent intent = new Intent(getActivity(), ShoppingChartActivity.class);
//                startActivity(intent);
//            }
//        });

//com.example.project_nusantarafit.chat
//        ImageButton chat = (ImageButton) rootView.findViewById(R.id.btn_chathome);
//        chat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                goToAttract();
//                Intent intent = new Intent(getActivity(), ChatActivity.class);
//                startActivity(intent);
//            }
//        });

//        shopping = getActivity().findViewById(R.id.sChart);
//        shopping.setOnClickListener(this);
//        super.onActivityCreated(savedInstanceState);

