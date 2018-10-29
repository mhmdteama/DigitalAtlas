package com.compubase.mhmd.digitalatlas;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Addfinging1ByAdmin extends AppCompatActivity {

    RequestQueue requestQueue;
    Button approve ,next;
    String id;
    final int PICK_IMAGE_REQUEST = 71;
    TextView approveText;
    String isApproved;
    Uri filePath;
    ImageView addnewimg;
    String imgUrl;
    FirebaseStorage storage;
    StorageReference storageReference;


    TinyDB tinyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfinging1_by_admin);
        approveText = findViewById(R.id.approvetext);
        approve = findViewById(R.id.approveadmin);
        next=findViewById(R.id.nextToSimple2Admin);
        addnewimg= findViewById(R.id.addpicadmin);



        tinyDB = new TinyDB(this);


        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Addfinging1ByAdmin.this , Sample1Activity.class);
                intent.putExtra("imageURL",imgUrl);
                startActivity(intent);

                tinyDB.putString("updateorcreate","update");
            }
        });
        addnewimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPicturDialog();
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getString("userID");
            isApproved = extras.getString("userApproval");
        }

        assert isApproved != null;
        if(isApproved.equals("yes"))
        {
            approveText.setVisibility(View.GONE);
            approve.setText("Approved");
            approve.setBackground(this.getDrawable(R.drawable.greenbutton));

        }

        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String URL = "http://atlas.alosboiya.com.sa//atlas.asmx/update_approval_pat?";


                StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,

                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                if(response.equals("True"))
                                {
                                    approveText.setVisibility(View.GONE);
                                    approve.setText("Approved");
                                    approve.setBackground(getDrawable(R.drawable.greenbutton));
                                }


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        showMessage();

                    }

                }) {


                    @Override
                    protected Map<String,String> getParams(){
                        Map<String,String> params = new HashMap<>();
                        params.put("id", id);
                        params.put("approval", "yes");
                        return params;
                    }

                };

                requestQueue = Volley.newRequestQueue(getApplicationContext());

                requestQueue.add(stringRequest);

            }
        });


    }


    private void showMessage() {
        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
    }





    public  void  showPicturDialog()
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
        if(pictureIntent.resolveActivity(getApplication().getPackageManager()) != null) {
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
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getApplication().getContentResolver(), filePath);
                addnewimg.setImageBitmap(bitmap);
                next.setEnabled(true);
                next.setBackground(this.getDrawable(R.drawable.greenbutton));

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
            final ProgressDialog progressDialog = new ProgressDialog(getApplicationContext());
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Uploaded", Toast.LENGTH_SHORT).show();
                            Uri downloadUrl = taskSnapshot.getUploadSessionUri(); //getDownloadUrl not found
                            assert downloadUrl != null;
                            imgUrl = downloadUrl.toString();
                            showMessage();

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
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


}
