package com.example.ocrr;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RecieveImageActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieve);
//        Intent intent = getIntent();
//        Bundle extras = intent.getExtras();
//        String action = intent.getAction();
//
//        // if this is from the share menu
//        if (Intent.ACTION_SEND.equals(action)) {
//            if (extras.containsKey(Intent.EXTRA_STREAM)) {
//                // Get resource path
//            }
//        }
        Intent intent = getIntent();
        String type = intent.getType();
        String action = intent.getAction();
        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if (type.startsWith("image/")) {
                handleSendImage(intent);
            }
        }
        if (Intent.ACTION_SEND_MULTIPLE.equals(action) && getIntent().hasExtra(Intent.EXTRA_STREAM)) {
            ArrayList<Parcelable> list = getIntent().getParcelableArrayListExtra(Intent.EXTRA_STREAM);
//            Intent i = new Intent(this, RequestListingActivity.class);
//            i.putExtra(PARAM_MULTIPLE_IMAGE, list);
//            startActivity(i);
//            finish();
        }
    }

    private void handleSendImage(Intent intent) {
        Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
        if (imageUri != null) {
//            Uri uri = result.getUri();
            Intent cropintent = new Intent(RecieveImageActivity.this, scanfinal.class);
            cropintent.putExtra("uri", imageUri);
            Log.i("nice", "crop is working");
            startActivity(cropintent);
//            CropImage.activity(imageUri)
//                    .start(this);
//            Intent i = new Intent(this, DataAddActivity.class);
//            i.putExtra(PARAM_IMAGE, imageUri.toString());
//            startActivity(i);
//            finish();
        } else {
            Toast.makeText(this, "Error occured, URI is invalid", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri uri = result.getUri();
                Intent cropintent = new Intent(RecieveImageActivity.this, ImageText.class);
                cropintent.putExtra("uri", uri);
                Log.i("nice", "crop is working");
                startActivity(cropintent);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
}
