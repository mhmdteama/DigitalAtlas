package com.compubase.mhmd.digitalatlas;

import android.app.Activity;
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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static android.media.MediaRecorder.VideoSource.CAMERA;

public class Sample2Activity extends Activity {

    ImageView img1;
    ImageView img2;
    ImageView img3;
    ImageView img4;
    ImageView img5;
    final int PICK_IMAGE_REQUEST = 71;
    Button finish;
    Uri filePath;
    //String imgUrl;
    FirebaseStorage storage;
    StorageReference storageReference;
    TinyDB tinyDB;
    String GET_JSON_DATA_HTTP_URL;


    String pic1,pic2,pic3,pic4,pic5;
    String  userage , usergender , usersystem , usersupervision , userfinding, userrq1 ,userrq2,userrq3 ,
            userrq4 , userop1 , userop2 , usersenario,userabnormal , userimg;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_addfinding2);
        FirebaseApp.initializeApp(this);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        tinyDB = new TinyDB(this);

        pic1 = "";
        pic2 = "";
        pic3 = "";
        pic4 = "";
        pic5 = "";

        img1 = findViewById(R.id.pic1);
        img2 = findViewById(R.id.pic2);
        img3 = findViewById(R.id.pic3);
        img4 = findViewById(R.id.pic4);
        img5 = findViewById(R.id.pic5);

        Bundle extras = getIntent().getExtras();

        assert extras != null;
        userage = extras.getString("name");
            usergender = extras.getString("email");
            usersystem = extras.getString("system");
            userfinding = extras.getString("finding");
            userrq1 = extras.getString("keywords");
            userrq2 = extras.getString("keywords1");
            userrq3 = extras.getString("keywords2");
            userrq4 = extras.getString("keywords3");
            userop1 = extras.getString("keywords4");
            userop2 = extras.getString("keywords5");
            userabnormal = extras.getString("userabnormal");
            userimg = extras.getString("img1String");
            usersenario = extras.getString("scenario");



        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPicturDialog();

            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPicturDialog();

            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPicturDialog();

            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPicturDialog();

            }
        });
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPicturDialog();

            }
        });
        finish = findViewById(R.id.tofinish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //volleyConnection();
                showMessage(userage);
                showMessage(usergender);
                showMessage(usersystem);
                showMessage(userfinding);
                showMessage(userrq1);
                showMessage(userrq2);
                showMessage(userrq3);
                showMessage(userrq4);
                showMessage(userop1);
                showMessage(userop2);
                showMessage(userimg);
                showMessage(pic1);
                showMessage(pic2);
                showMessage(pic3);
                showMessage(pic4);
                showMessage(pic5);
                showMessage(userabnormal);
                showMessage(usersenario);

            }
        });


    }


    private   void  showPicturDialog()
    {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
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
        if(pictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(pictureIntent,
                    PICK_IMAGE_REQUEST);
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if((requestCode == PICK_IMAGE_REQUEST) && (resultCode == RESULT_OK)
                && (data != null) && (data.getData() != null))
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                if(pic1.isEmpty() && pic2.isEmpty() && pic3.isEmpty() && pic4.isEmpty() && pic5.isEmpty())
                {
                    img1.setImageBitmap(bitmap);
                    uploadImage(filePath);
                }else if(!pic1.isEmpty() && pic2.isEmpty() && pic3.isEmpty() && pic4.isEmpty() && pic5.isEmpty())
                {
                    img2.setImageBitmap(bitmap);
                    uploadImage(filePath);
                }else if(!pic1.isEmpty() && !pic2.isEmpty() && pic3.isEmpty() && pic4.isEmpty() && pic5.isEmpty())
                {
                    img3.setImageBitmap(bitmap);
                    uploadImage(filePath);
                }else if(!pic1.isEmpty() && !pic2.isEmpty() && !pic3.isEmpty() && pic4.isEmpty() && pic5.isEmpty())
                {
                    img4.setImageBitmap(bitmap);
                    uploadImage(filePath);
                }else if(!pic1.isEmpty() && !pic2.isEmpty() && !pic3.isEmpty() && !pic4.isEmpty() && pic5.isEmpty())
                {
                    img5.setImageBitmap(bitmap);
                    uploadImage(filePath);
                }

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    private void uploadImage(Uri customfilepath) {

        if(customfilepath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
            ref.putFile(customfilepath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(Sample2Activity.this, "Uploaded", Toast.LENGTH_SHORT).show();
                            Uri downloadUrl = taskSnapshot.getUploadSessionUri(); //getDownloadUrl not found
                            assert downloadUrl != null;
                            if(pic1.isEmpty() && pic2.isEmpty() && pic3.isEmpty() && pic4.isEmpty() && pic5.isEmpty())
                            {
                                pic1 = downloadUrl.toString();
                                showMessage(pic1);
                            }else if(!pic1.isEmpty() && pic2.isEmpty() && pic3.isEmpty() && pic4.isEmpty() && pic5.isEmpty())
                            {
                                pic2 = downloadUrl.toString();
                                showMessage(pic2);
                            }else if(!pic1.isEmpty() && !pic2.isEmpty() && pic3.isEmpty() && pic4.isEmpty() && pic5.isEmpty())
                            {
                                pic3 = downloadUrl.toString();
                                showMessage(pic3);
                            }else if(!pic1.isEmpty() && !pic2.isEmpty() && !pic3.isEmpty() && pic4.isEmpty() && pic5.isEmpty())
                            {
                                pic4 = downloadUrl.toString();
                                showMessage(pic4);
                            }else if(!pic1.isEmpty() && !pic2.isEmpty() && !pic3.isEmpty() && !pic4.isEmpty() && pic5.isEmpty())
                            {
                                pic5 = downloadUrl.toString();
                                showMessage(pic5);
                            }


                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(Sample2Activity.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
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





    public void volleyConnection()
    {
        GET_JSON_DATA_HTTP_URL = "http://alosboiya.com.sa/atlas.asmx/insert_pat?";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, GET_JSON_DATA_HTTP_URL,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showMessage(response);
                        if(Objects.equals(response, "True")){
                            showMessage("Patient Successful Congratulations ");
                            Intent intent = new Intent(Sample2Activity.this,MainActivity.class);
                            startActivity(intent);
                        }else {
                            showMessage("Failed please Try Again ");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                showMessage(error.toString());

            }

        }) {

            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("name", userage);
                params.put("email", usergender);
                params.put("system", usersystem);
                params.put("finding", userfinding);
                params.put("imageimage", userimg);
                params.put("scenario", usersenario);
                params.put("abnormality", userabnormal);
                params.put("img1", img1.toString());
                params.put("img2", img2.toString());
                params.put("img3", img3.toString());
                params.put("img4", img4.toString());
                params.put("img5", img5.toString());
                params.put("keywords", userrq1);
                params.put("keywords1", userrq2);
                params.put("keywords2", userrq3);
                params.put("keywords3", userrq4);
                params.put("keywords4", userop1);
                params.put("keywords5", userop2);

                return params;
            }



        };

        RequestHandler.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }


    private void showMessage(String _s) {
        Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_LONG).show();
    }

        }




