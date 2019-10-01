package com.skipzen.onlineexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.method.MetaKeyKeyListener;
import android.view.View;
import android.widget.Button;

public class TakePhoto extends AppCompatActivity {

    Button btnTake, btnChoose, btnUploud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        btnUploud = findViewById(R.id.buttonUpload);

        btnUploud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TakePhoto.this, StartExam.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
