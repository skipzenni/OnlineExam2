package com.skipzen.onlineexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultExam extends AppCompatActivity {

    TextView txtScore, txtSoalBenar, txtTotalSoal;
    Button btnLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_exam);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        txtScore = findViewById(R.id.txtScore);
        txtSoalBenar = findViewById(R.id.txtSoalBenar);
        txtTotalSoal = findViewById(R.id.txtTotalSoal);
        btnLogOut = findViewById(R.id.btnLogOut);

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultExam.this, FaceRecognition.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
