package com.compubase.mhmd.digitalatlas;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Document extends AppCompatActivity {
    ImageView important;
    Button downloadimg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document);
        important = findViewById(R.id.importan);
        downloadimg = findViewById(R.id.saveimg);

        downloadimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                important.buildDrawingCache();
                Bitmap bm=important.getDrawingCache();
            }
        });
    }




}
