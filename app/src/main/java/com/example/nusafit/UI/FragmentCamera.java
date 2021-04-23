package com.example.nusafit.UI;

//import android.support.v4.app.Fragment;

//public class CameraFragment extends Fragment {
////    ImageView imageView;
////    ImageButton btn;
////
////    public View onCreateView(@Nullable LayoutInflater inflater,
////                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
////
////
////
////        View view ;
////        inflater = LayoutInflater.from(container.getContext());
////        view = inflater.inflate(R.layout.nusantara_camera_fragment,container,false);
////
////
////
//////        super.onCreate(savedInstanceState);
//////        setContentView(R.layout.nusantara_camera_fragment);
////
////        //Variabel
//////        imageView = btn.findViewById(R.id.image_view);
////        btn = btn.findViewById(R.id.btnOpen);
////
////        if (ContextCompat.checkSelfPermission(CameraFragment.this.getContext(),
////                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
////            ActivityCompat.requestPermissions(CameraFragment.this.getActivity(), new String[]{
////                            Manifest.permission.CAMERA
////                    },
////                    100);
////        }
////        btn.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
////                startActivityForResult(intent, 100);
////            }
////        });
////        return inflater.inflate(R.layout.nusantara_camera_fragment, null);
////
////    }
//
//
////        imageView = imageView.findViewById(R.id.image_view);
////        btn = btn.findViewById(R.id.btnOpen);
////
////        if(ContextCompat.checkSelfPermission(CameraFragment.this,
////                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
////            ActivityCompat.requestPermissions(CameraFragment.this, new String[]{
////                            Manifest.permission.CAMERA
////                    },
////                    100);
////        }
////        btn.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
////                startActivityForResult(intent,100);
////            }
////        });
//
//}
//
////    @Override
////    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
////        super.onActivityResult(requestCode, resultCode, data);
////        if (requestCode == 100) {
////            Bitmap capture = (Bitmap) data.getExtras().get("data");
////            //set
////            imageView.setImageBitmap(capture);
////        }
////    }
