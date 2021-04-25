package com.example.nusafit.UI;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nusafit.R;
import com.example.nusafit.RoomchatViewPager;
import com.example.nusafit.SettingProfileActivity;
import com.example.nusafit.ShoppingChartActivity;
import com.example.nusafit.auth.AuthLoginActivity;
import com.example.nusafit.entity.UserNF;
import com.example.nusafit.activity_input_product;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FragmentProfile extends Fragment {

    // Firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private String getUserID;

    private TextView namaProfil;

    private ImageButton BtnSetProfile;
    private ImageButton BtnCartProfile;
    private ImageButton BtnChat;
    private Button BtnLogout;
    private Button BtnHapusakun;
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

        namaProfil = (TextView)rootView.findViewById(R.id.name_profile);
        mReference.child("userNF").child(getUserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserNF userNf = snapshot.getValue(UserNF.class);
                if (userNf != null) {
                    namaProfil.setText(userNf.getNamalengkap());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        namaProfil = (TextView)rootView.findViewById(R.id.name_profile);
//        mReference.child("userNF");
//        mReference.child(getUserID);
//        mReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                UserNF userNf = snapshot.getValue(UserNF.class);
//                if (userNf != null) {
//                    namaProfil.setText(userNf.getNamalengkap());
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });

        // Button ke Setting Profile
        BtnSetProfile = (ImageButton)rootView.findViewById(R.id.btn_setprof);
        BtnSetProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        // Hapus Akun Activity
        BtnHapusakun = (Button)rootView.findViewById(R.id.btn_hapusakun);
        BtnHapusakun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Apakah anda yakin untuk menghapus akun ?");
                builder.setMessage("Ketika anda menghapus akun, akun tidak bisa digunakan kembali.");

                builder.setPositiveButton("HAPUS", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        hapusDataAkun();

                        FirebaseUser usernf = mAuth.getCurrentUser();
                        if (usernf != null) {
                            usernf.delete()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.e("MyData", ": Berhasil Dihapus");
                                                startActivity(new Intent(getActivity(), AuthLoginActivity.class));
                                            }
                                        }
                                    });
                        }
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

    private void hapusDataAkun() {
        mReference.child("userNF").child(getUserID).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.e("MyData", ": Akun Berhasil dihapus");
            }
        });
    }

    //function go to activity

    private void gostore() {
        Intent intent = new Intent(getActivity(), activity_input_product.class);
        startActivity(intent);
    }

    private void gocart() {
        Intent i = new Intent(getActivity(), ShoppingChartActivity.class);
        startActivity(i);
    }

    private void gochat() {
        Intent i = new Intent(getActivity(), RoomchatViewPager.class);
        startActivity(i);
    }

    private void gosett() {
        Intent i = new Intent(getActivity(), SettingProfileActivity.class);
        startActivity(i);
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