package com.example.ocrr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

public class ImageText extends AppCompatActivity {


    ImageView imageView;
    Button scantext;
    Bitmap bitma;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_text);

        imageView = (ImageView)findViewById(R.id.imageView1);
        scantext = (Button)findViewById(R.id.scantext);



        Intent intent = getIntent();
        bitma = (Bitmap) intent.getParcelableExtra("bitmap");
        final Uri uri = intent.getParcelableExtra("uri");
            if(bitma==null){
                imageView.setImageURI(uri);
            }
            else  imageView.setImageBitmap(bitma);






        scantext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(ImageText.this, scanfinal.class);
                    if(bitma==null){
                        intent2.putExtra("uri",uri);
                    }
                    else intent2.putExtra("Bitmap",bitma);


                startActivity(intent2);
            }
        });








    }



}
