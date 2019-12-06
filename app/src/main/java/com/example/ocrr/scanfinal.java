package com.example.ocrr;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okio.ByteString;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ocrr.Model.ImageUploadResponse;
import com.example.ocrr.Model.Line;
import com.example.ocrr.Model.OcrResponse;
import com.example.ocrr.Model.ParsedResult;
import com.example.ocrr.Model.TextOverlay;
import com.example.ocrr.Ocr.IOCRCallBack;
import com.example.ocrr.Ocr.OCRAsyncTask;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.Text;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static android.widget.Toast.LENGTH_SHORT;

public class scanfinal extends AppCompatActivity implements IOCRCallBack {
    Bitmap bitmap1;
    Button savetext;
    ImageView copybutton;
    EditText editText;
    String scannedtext;
    Uri uri;
    TextToSpeech tts;
    String value1;
    ImageView image;
    TextView editTextJson;
    ArrayList<String> arrayList = new ArrayList<>();

    HashMap<String, String> map = new HashMap<>();


    private String mAPiKey = "2c8743488888957"; //TODO Add your own Registered API key
    private boolean isOverlayRequired;
    private String mImageUrl;
    private String mLanguage;
    private TextView mTxtResult;
    private IOCRCallBack mIOCRCallBack;
    private String sss;

    static final float SAME_LINE_DELTA = 12.0f;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanfinal);
        mIOCRCallBack = this;
        mImageUrl = "http://sellinggraphics.com/wp-content/uploads/Loremipsum1.jpg"; // Image url to apply OCR API
        mImageUrl = "http://acnure.com/upload/1573124057655cropped167906879969488684.jpg"; // Image url to apply OCR API
        mLanguage = "eng"; //Language
        isOverlayRequired = true;

        Intent intent2 = getIntent();
        editText = findViewById(R.id.editText);
        savetext = findViewById(R.id.saveText);
        copybutton = findViewById(R.id.copy);
        editTextJson = findViewById(R.id.editTextJson);
        image = findViewById(R.id.image);


        //change
        bitmap1 = intent2.getParcelableExtra("Bitmap");
        uri = intent2.getParcelableExtra("uri");
        value1 = intent2.getStringExtra("value");

//        uploadImage();
//        performOcr();

        if (value1 == null) {


            if (bitmap1 == null) {
//                image.setImageBitmap(bitmap1);
                Glide.with(scanfinal.this).load(uri).into(image);

                InputStream imageStream = null;
                try {
                    imageStream = getContentResolver().openInputStream(uri);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                bitmap1 = BitmapFactory.decodeStream(imageStream);

            }


            TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();
            if (!textRecognizer.isOperational()) {
                Toast.makeText(scanfinal.this, "NO TEXT", LENGTH_SHORT);
            } else {
                Frame frame = new Frame.Builder().setBitmap(bitmap1).build();

                SparseArray<TextBlock> items = textRecognizer.detect(frame);

//                SparseArray<TextBlock> abcc = sortTB(items);

                StringBuilder a = new StringBuilder();

                String blocks = "";
                String lines = "";
                String words = "";
                for (int index = 0; index < items.size(); index++) {
                    //extract scanned text blocks here
                    TextBlock tBlock = items.valueAt(index);
                    blocks = blocks + tBlock.getValue() + "\n" + "\n";
                    for (Text line : tBlock.getComponents()) {
                        //extract scanned text lines here
                        lines = lines + line.getValue() + "\n";
                        arrayList.add(line.getValue());

                    }
                }
//                for (int i = 0; i < items.size(); i++) {
//                    TextBlock myItem = items.valueAt(i);
//                    a.append(myItem.getValue());
//                    if (myItem.getValue().contains("\n")) {
//                        String[] list = myItem.getValue().split("\n");
//                        for (String abc : list) {
//                            arrayList.add(abc);
//                        }
//                    } else {
//                        arrayList.add(myItem.getValue());
//
//                    }
////                    arrayList.add(myItem.getValue());
//                    a.append("\n");
//                }

//                try {
////                    for (int i = 0; i < arrayList.size(); i = i + 2) {
////                        map.put(arrayList.get(i), arrayList.get(i + 1));
////                    }
//                    map.clear();
//                    map.put(arrayList.get(4), arrayList.get(3));
//                    map.put(arrayList.get(5), arrayList.get(9));
//                    map.put(arrayList.get(6), arrayList.get(10));
//                    map.put(arrayList.get(7), arrayList.get(11));
//                    map.put(arrayList.get(8), arrayList.get(12));
//
//                    map.put(arrayList.get(14), arrayList.get(13));
//                    map.put(arrayList.get(15), arrayList.get(16));
//                    map.put(arrayList.get(17), arrayList.get(18) + " " + arrayList.get(22));
//                    map.put(arrayList.get(24), arrayList.get(19) + " " + arrayList.get(23));
//                    map.put(arrayList.get(25), arrayList.get(20) + " " + arrayList.get(26));
//                    map.put(arrayList.get(27), arrayList.get(21) + " " + arrayList.get(28));
//
//
//                    map.put(arrayList.get(32), arrayList.get(29));
//                    map.put(arrayList.get(32) + "2", arrayList.get(30));
//
//                    map.put(arrayList.get(33), arrayList.get(31));
//                    map.put(arrayList.get(34) + " " + arrayList.get(35), arrayList.get(38));
//                    map.put(arrayList.get(34) + " " + arrayList.get(36), arrayList.get(39));
//                    map.put(arrayList.get(37), arrayList.get(40));
//                    map.put(arrayList.get(42), arrayList.get(43));
//
//                    map.put(arrayList.get(44), arrayList.get(45));
//                    map.put(arrayList.get(46), arrayList.get(47));
//
//
//                } catch (Exception e) {
//
//                }
                JsonObject responseObj = new JsonObject();
                try {
                    responseObj.addProperty(arrayList.get(3), arrayList.get(7));
                } catch (Exception e) {

                }
                try {
                    responseObj.addProperty(arrayList.get(4), arrayList.get(8));
                } catch (Exception e) {

                }
                try {
                    responseObj.addProperty(arrayList.get(5), arrayList.get(9));
                } catch (Exception e) {

                }
                try {
                    responseObj.addProperty(arrayList.get(6), arrayList.get(10));
                } catch (Exception e) {

                }
                try {
                    responseObj.addProperty(arrayList.get(12), arrayList.get(11));
                } catch (Exception e) {

                }
                try {
                    responseObj.addProperty(arrayList.get(14), arrayList.get(13));
                } catch (Exception e) {

                }
                try {
                    responseObj.addProperty(arrayList.get(15), arrayList.get(16));
                } catch (Exception e) {

                }
                try {
                    responseObj.addProperty(arrayList.get(17), arrayList.get(18) + " " + arrayList.get(22));
                } catch (Exception e) {

                }
                try {
                    responseObj.addProperty(arrayList.get(24), arrayList.get(19) + " " + arrayList.get(23));
                } catch (Exception e) {

                }
                try {
                    responseObj.addProperty(arrayList.get(25), arrayList.get(20) + " " + arrayList.get(26));
                } catch (Exception e) {

                }
                try {
                    responseObj.addProperty(arrayList.get(27), arrayList.get(21) + " " + arrayList.get(28));
                } catch (Exception e) {

                }
                try {

                    responseObj.addProperty(arrayList.get(32), arrayList.get(29));
                } catch (Exception e) {

                }
                try {
                    responseObj.addProperty(arrayList.get(32) + "2", arrayList.get(30));
                } catch (Exception e) {

                }
                try {
                    responseObj.addProperty(arrayList.get(33), arrayList.get(31));
                } catch (Exception e) {

                }
                try {
                    responseObj.addProperty(arrayList.get(34), "");
                } catch (Exception e) {

                }
                try {
                    responseObj.addProperty(arrayList.get(35), arrayList.get(38));
                } catch (Exception e) {

                }
                try {
                    responseObj.addProperty(arrayList.get(36), arrayList.get(39));
                } catch (Exception e) {

                }
                try {
                    responseObj.addProperty(arrayList.get(37), arrayList.get(40));
                } catch (Exception e) {

                }
                try {
                    responseObj.addProperty(arrayList.get(41), arrayList.get(42));
                } catch (Exception e) {

                }
                try {
                    responseObj.addProperty(arrayList.get(43), arrayList.get(44));
                } catch (Exception e) {

                }
                try {
                    responseObj.addProperty(arrayList.get(45), arrayList.get(46));
                } catch (Exception e) {

                }

//                Gson gson = new Gson();
//                sss = gson.toJson(responseObj);

                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String json = gson.toJson(responseObj);
                json = json.replace("u003e", "").replace("timee", "time").replace("\\", "");

                editTextJson.setText(json);

                editText.setText(json);
                sss = json;


                scannedtext = a.toString();
                // Log.i("a",scannedtext);
//                if (items.size() == 0) {
//                    editText.setText("NO TEXT");
//                } else {
//                    editText.setText(scannedtext);
//                    editTextJson.setText(json);
//                }
            }
        } else {
            scannedtext = value1;
            String json = "{key:\"" + value1 + "\"}";
            editText.setText(scannedtext);
            editTextJson.setText(json);
        }


        savetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent intent3  =  new Intent(scanfinal.this , HistoryActivity.class);

            }
        });


        copybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
//                ClipData clip = ClipData.newPlainText("label", scannedtext);
//                clipboard.setPrimaryClip(clip);
//                Toast.makeText(scanfinal.this, "Text Copied to Clipboard", LENGTH_SHORT).show();
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, sss);
                startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.share_using)));
            }
        });


    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    private SparseArray<TextBlock> sortTB(SparseArray<TextBlock> items) {
        if (items == null) {
            return null;
        }

        int size = items.size();
        if (size == 0) {
            return null;
        }

        //SparseArray to store the result, the same that the one in parameters but sorted
        SparseArray<TextBlock> sortedSparseArray = new SparseArray<>(size);

        //Moving from SparseArray to List, to use Lambda expression
        List<TextBlock> listTest = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            listTest.add(items.valueAt(i));
        }

        //sorting via a stream and lambda expression, then collecting the result
        listTest = listTest.stream().sorted((textBlock1, textBlock2) -> {
            RectF rect1 = new RectF(textBlock1.getComponents().get(0).getBoundingBox());
            RectF rect2 = new RectF(textBlock2.getComponents().get(0).getBoundingBox());

            //Test if textBlock are on the same line
            if (rect2.centerY() < rect1.centerY() + SAME_LINE_DELTA
                    && rect2.centerY() > rect1.centerY() - SAME_LINE_DELTA) {
                //sort on the same line (X value)
                return Float.compare(rect1.left, rect2.left);
            }
            //else sort them by their Y value
            return Float.compare(rect1.centerY(), rect2.centerY());
        }).collect(Collectors.toList());

        //Store the result to the empty sparseArray
        for (int i = 0; i < listTest.size(); i++) {
            sortedSparseArray.append(i, listTest.get(i));
        }

        //return the sorted result
        return sortedSparseArray;
    }

    public Comparator<TextBlock> TextBlockComparator
            = new Comparator<TextBlock>() {
        public int compare(TextBlock textBlock1, TextBlock textBlock2) {
            return textBlock1.getBoundingBox().top - textBlock2.getBoundingBox().top;
        }
    };

    private void uploadImage() {
//        File file = new File(uri.getPath());
        File file = new File(getRealPathFromURI(uri));


        // Parsing any Media type file
        final RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());

        UserClient getResponse = AppConfig.getRetrofit().create(UserClient.class);
        Call call = getResponse.uploadFile(fileToUpload, filename);
        call.enqueue(new Callback<ImageUploadResponse>() {
            @Override
            public void onResponse(Call<ImageUploadResponse> call, Response<ImageUploadResponse> response) {
                if (response.body() != null) {
                    if (response.body().getValue2() != null) {
                        mImageUrl = response.body().getValue2();
                        String url = response.body().getValue2();
                        performOcr(url);


                    }
                }
            }

            @Override
            public void onFailure(Call<ImageUploadResponse> call, Throwable t) {

            }
        });
    }

    private void performOcr(String url) {
//        String img = "http://acnure.com/upload/1573649403284IMG20191109WA0047.jpg";
        OCRAsyncTask oCRAsyncTask = new OCRAsyncTask(scanfinal.this, mAPiKey, isOverlayRequired, url, mLanguage, mIOCRCallBack);
        oCRAsyncTask.execute();
    }

    public String getRealPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    public void onPause() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onPause();
    }

    @Override
    public void getOCRCallBackResult(String response) {
//        Toast.makeText(this, "data is here", Toast.LENGTH_SHORT).show();
        Gson gson = new Gson();
        OcrResponse element = gson.fromJson(response, OcrResponse.class);
        if (element.getParsedResults() != null) {
            List<ParsedResult> abc = element.getParsedResults();
            String text = abc.get(0).getParsedText();
            editText.setText(text);
            List<Line> lines = abc.get(0).getTextOverlay().getLines();
            try {
                for (int i = 0; i < lines.size(); i = i + 2) {
                    map.put(lines.get(i).getLineText(), lines.get(i + 1).getLineText());
                }
            } catch (Exception e) {

            }

            Gson gson1 = new Gson();
            String sss = gson1.toJson(map);
            Log.d("map", "" + sss);
            editTextJson.setText(sss);
            editText.setText(sss);
        } else {
            Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
        }

    }
}
