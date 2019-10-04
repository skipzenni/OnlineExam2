package com.skipzen.onlineexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.skipzen.onlineexam.model.Common;
import com.skipzen.onlineexam.model.QuestionScore;
import com.skipzen.onlineexam.util.PrefManager;

public class ResultExam extends AppCompatActivity {

    TextView txtResultScore, getTxtResultQuestion, txtTotalSoal;
    ProgressBar progressBar;
    Button btnLogOut;

    FirebaseDatabase database;
    DatabaseReference question_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_exam);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        database = FirebaseDatabase.getInstance();
        question_score = database.getReference("Question_Score");

        txtResultScore = findViewById(R.id.txtTotalScore);
        getTxtResultQuestion = findViewById(R.id.txtTotalQuestion);
        txtTotalSoal = findViewById(R.id.txtTotalSoal);
        progressBar = findViewById(R.id.progressBar);
        btnLogOut = findViewById(R.id.btnLogOut);

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrefManager prefManager = new PrefManager(ResultExam.this);
                String token = prefManager.getToken();
                prefManager.clearToken();
                Intent intent = new Intent(ResultExam.this, FaceRecognition.class);
                startActivity(intent);
                finish();
            }
        });

        Bundle extra = getIntent().getExtras();
        if(extra != null){
            int score = extra.getInt("SCORE");
            int totalQuestion = extra.getInt("TOTAL");
            int correctAnswer = extra.getInt("CORRECT");

            txtResultScore.setText(String.format("SCORE  :  %d",score));
            getTxtResultQuestion.setText(String.format("PASSED : %d / %d", correctAnswer, totalQuestion));

            progressBar.setMax(totalQuestion);
            progressBar.setProgress(correctAnswer);

            question_score.child(String.format("%s", Common.categoriId))
                    .setValue(new QuestionScore(String.format("%s_%s", "Anda ", Common.categoriId),"Anda ", String.valueOf(score)));
        }

    }
}
