package com.skipzen.onlineexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.skipzen.onlineexam.util.PrefManager;

public class Exams extends AppCompatActivity {

    Button btnA, btnB, btnC, btnD;
    TextView txtNomorSoal, txtSoalText, txtJmlSoal;
    ImageView imgSoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exams);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        btnA = findViewById(R.id.btnA);
        btnB = findViewById(R.id.btnB);
        btnC = findViewById(R.id.btnC);
        btnD = findViewById(R.id.btnD);
        txtNomorSoal = findViewById(R.id.txtNomorSoal);
        txtSoalText = findViewById(R.id.txtSoalText);
        txtJmlSoal = findViewById(R.id.txtJmlSoal);
        imgSoal = findViewById(R.id.imgSoal);
        PrefManager prefManager = new PrefManager(this);
        String question = prefManager.getQuestion();
        Toast.makeText(this, "token yang tesimpan: " + question, Toast.LENGTH_SHORT).show();

        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Exams.this, ResultExam.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
