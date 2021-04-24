package com.example.nusafit.UI;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nusafit.R;
import com.example.nusafit.RoomchatViewPager;
import com.example.nusafit.SettingProfileActivity;
import com.example.nusafit.ShoppingChartActivity;
import com.example.nusafit.auth.AuthLoginActivity;
import com.example.nusafit.input_product;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FragmentProfile extends Fragment {

    // Firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private String getUserID;

    private ImageButton BtnSetProfile;
    private ImageButton BtnCartProfile;
    private ImageButton BtnChat;
    private Button BtnLogout;
//    ImageButton BtnVoucherMyList;
//    ImageButton BtnPointProfile;
//    ImageButton BtnNusapay;
//    ImageButton BtnKatalog;

    public View onCreateView(@Nullable LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        // Firebase
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        getUserID = user.getUid();

        // Button ke Setting Profile
        BtnSetProfile = (ImageButton)rootView.findViewById(R.id.btn_setprof);
        BtnSetProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), input_product.class);
//                startActivity(intent);
                gosett();
            }
        });

        // Button ke Cart
        BtnCartProfile = (ImageButton)rootView.findViewById(R.id.btn_cartprof);
        BtnCartProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gocart();
            }
        });

        // Button ke Chat
        BtnChat = (ImageButton)rootView.findViewById(R.id.btn_chatprof);
        BtnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gochat();
            }
        });

        // Button Logout Activity
        BtnLogout = (Button)rootView.findViewById(R.id.btn_logout);
        BtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Apakah anda yakin untuk Logout ?");
                builder.setMessage("Jika anda logout akun, maka harus melakukan login ulang");
                builder.setPositiveButton("LOGOUT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mAuth.signOut();
                    }
                });
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    Toast.makeText(getActivity(), "Logout Berhasil !", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), AuthLoginActivity.class));
                    getActivity().finish();
                }
            }
        };

        // Button ke Katalog
//        BtnKatalog = (ImageButton)rootView.findViewById(R.id.btn_katalog);
//        BtnKatalog.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                gostore();
//            }
//        });

        // Button ke Nusapay / Payment
//        BtnNusapay = (ImageButton)rootView.findViewById(R.id.btn_nusapay);
//        BtnNusapay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                goToAttract();
//            }
//        });
        return rootView;
    }

    //function go to activity

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
        Intent intent = new Intent(getActivity(),SettingProfileActivity.class);
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(authStateListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(authStateListener);
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