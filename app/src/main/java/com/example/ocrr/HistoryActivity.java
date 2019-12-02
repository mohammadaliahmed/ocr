package com.example.ocrr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN =123 ;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        list = new ArrayList<>();
            listView = findViewById(R.id.list_view);

            adapter = new ArrayAdapter<String>(this, R.layout.user_info, R.id.userinfo, list);
            Intent intent = getIntent();
            //String a = intent.getStringExtra("scannedtext");
             user = FirebaseAuth.getInstance().getCurrentUser();
            Log.i("uid", user.getUid());

//            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                    String value1 = (String) adapterView.getItemAtPosition(i);
//                    //Toast.makeText(HistoryActivity.this,value1,Toast.LENGTH_SHORT).show();
//                    Intent intent2 = new Intent(HistoryActivity.this,scanfinal.class);
//                    intent2.putExtra("value",value1);
//                    startActivity(intent2);
//                }
//            });





    }
}
