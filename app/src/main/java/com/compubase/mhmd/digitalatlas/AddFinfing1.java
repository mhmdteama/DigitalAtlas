package com.compubase.mhmd.digitalatlas;


import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;
import static android.media.MediaRecorder.VideoSource.CAMERA;


public class AddFinfing1 extends Fragment {

    final int PICK_IMAGE_REQUEST = 71;
    ImageView pikeimage;
    Button next;
    View view ;
    String imgUrl;

    FirebaseStorage storage;
    StorageReference storageReference;

    Uri filePath;



    public AddFinfing1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view =   inflater.inflate(R.layout.fragment_add_finfing1, container, false);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pikeimage = view.findViewById(R.id.addpic);

        FirebaseApp.initializeApp(getContext());

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        next =view.findViewById(R.id.nextToFinding2);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Sample1Activity.class);
                intent.putExtra("imageURL",imgUrl);
                startActivity(intent);

            }
        });
        pikeimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPicturDialog();
            }
        });

    }
    public  void  showPicturDialog()
    {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(getContext());
        pictureDialog.setTitle("Select Action");
        String[] pictureDlialogItem={"Select From Gallery" ,
                "Capture From Camera"};
        pictureDialog.setItems(pictureDlialogItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0 :
                        choosePhotoFromGallary();
                        break;
                    case 1:
                        takePhotoFromCamera();
                        break;
                }
            }
        });
        pictureDialog.show();
    }
    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent,71);
    }

    private void takePhotoFromCamera() {

        //From Camera

        Intent pictureIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE
        );
        if(pictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(pictureIntent,
                    PICK_IMAGE_REQUEST);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                pikeimage.setImageBitmap(bitmap);

                uploadImage();

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }




    private void uploadImage() {

        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(getContext());
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), "Uploaded", Toast.LENGTH_SHORT).show();
                            Uri downloadUrl = taskSnapshot.getUploadSessionUri(); //getDownloadUrl not found
                            assert downloadUrl != null;
                            imgUrl = downloadUrl.toString();
                            showMessage(imgUrl);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }

    private void showMessage(String _s) {
        Toast.makeText(getActivity().getApplicationContext(), _s, Toast.LENGTH_LONG).show();
    }

}
