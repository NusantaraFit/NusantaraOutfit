package com.example.nusafit.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nusafit.NusaPayment;
import com.example.nusafit.R;
import com.example.nusafit.RoomchatViewPager;
import com.example.nusafit.SettingProfileActivity;
import com.example.nusafit.ShoppingChartActivity;
import com.example.nusafit.input_product;

public class FragmentProfile extends Fragment {

    ImageButton BtnVoucherMyList;
    ImageButton BtnPointProfile;
    ImageButton BtnNusapay;
    ImageButton BtnSetProfile;
    ImageButton BtnCartProfile;
    ImageButton BtnKatalog;
    ImageButton BtnChat;

    public View onCreateView(@Nullable LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

//        BtnNusapay = (ImageButton)rootView.findViewById(R.id.btn_nusapay);
        BtnSetProfile = (ImageButton)rootView.findViewById(R.id.btn_setprof);
        BtnCartProfile = (ImageButton)rootView.findViewById(R.id.btn_cartprof);
//        BtnKatalog = (ImageButton)rootView.findViewById(R.id.btn_katalog);
        BtnChat = (ImageButton)rootView.findViewById(R.id.btn_chatprof);


//        BtnKatalog.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent = new Intent(getActivity(), input_product.class);
////                startActivity(intent);
//                gostore();
//            }
//        });
//        BtnNusapay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                goToAttract();
//            }
//        });
        BtnCartProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), input_product.class);
//                startActivity(intent);
                gocart();
            }
        });
        BtnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), input_product.class);
//                startActivity(intent);
                gochat();
            }
        });
        BtnSetProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), input_product.class);
//                startActivity(intent);
                gosett();
            }
        });


        return rootView;
    }

    //function go to activity
    private void goToAttract() {
        Intent intent = new Intent(getActivity(), NusaPayment.class);
        startActivity(intent);
    }

    private void gostore() {
        Intent intent = new Intent(getActivity(), input_product.class);
        startActivity(intent);
    }

    private void gocart() {
        Intent intent = new Intent(getActivity(), ShoppingChartActivity.class);
        startActivity(intent);
    }

    private void gochat() {
        Intent intent = new Intent(getActivity(), RoomchatViewPager.class);
        startActivity(intent);
    }

    private void gosett() {
        Intent intent = new Intent(getActivity(), SettingProfileActivity.class);
        startActivity(intent);
    }

}
//    @Override
//            public void onClick(View v) {
//                goToAttract();
//            }
//        });

//        class click implements View.OnClickListener {
//            @Override
//            public void onClick(View v) {
//                switch (v.getId()) {
//                    case R.id.btn_nusapay:
//                        goToAttract();
//                        break;
//                    case R.id.setting_profile:
//                        gosett();
//                        break;
//                    case R.id.btn_katalog:
//                        gostore();
//                        break;
//                    case R.id.btn_chat:
//                        gochat();
//                        break;
//                    case R.id.btn_cart:
//                        gocart();
//                        break;
//                }
//            }
//        }
//        return rootView;
//    }


//
//        ImageButton BtnSetProfile = (ImageButton)rootView.findViewById(R.id.setting_profile);
//        BtnSetProfile.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v){
//                Intent intent = new Intent(getActivity(), SettingProfileActivity.class);
//                startActivity(intent);
//            }
//        });
//// katalog
//        ImageButton btnlog = (ImageButton)rootView.findViewById(R.id.btn_katalog);
//        btnlog.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v){
////                Intent intent = new Intent(getActivity(), input_product.class);
////                startActivity(intent);
//                gostore();
//            }
//        });
//
//        ImageButton BtnVoucherMyList = (ImageButton)rootView.findViewById(R.id.voucher_profile);
//        BtnVoucherMyList.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v){
//                Intent intent = new Intent(getActivity(), VoucherMyList.class);
//                startActivity(intent);
//            }
//        });
//
//        ImageButton BtnPointProfile = (ImageButton)rootView.findViewById(R.id.point_profile);
//        BtnPointProfile.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v){
//                Intent intent = new Intent(getActivity(), PointProfileActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        ImageButton BtnCartProfile = (ImageButton)rootView.findViewById(R.id.cart_profile);
//        BtnCartProfile.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v){
//                Intent intent = new Intent(getActivity(), ShoppingChartActivity.class);
//                startActivity(intent);
//            }
//        });
//        return rootView;
//------------------------------------------------

//        BtnNusapay = (ImageButton) rootView.findViewById(R.id.btn_nusapay);
//        BtnSetProfile = (ImageButton) rootView.findViewById(R.id.setting_profile);
//        BtnKatalog = (ImageButton) rootView.findViewById(R.id.btn_katalog);
//        BtnChat = (ImageButton) rootView.findViewById(R.id.chat);


//        class click implements View.OnClickListener {
//            @Override
//            public void onClick(View v) {
//                switch (v.getId()) {
//                    case R.id.btn_nusapay:
//                        goToAttract();
//                        break;
//                    case R.id.setting_profile:
//                        gosett();
//                        break;
//                    case R.id.btn_katalog:
//                        gostore();
//                        break;
//                    case R.id.btn_chat:
//                        gochat();
//                        break;
//                    case R.id.btn_cart:
//                        gocart();
//                        break;
//                }
//
//            }
//        }
//OnClick