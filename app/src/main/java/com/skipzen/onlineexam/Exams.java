package com.skipzen.onlineexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.internal.service.Common;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.skipzen.onlineexam.model.Data;
import com.skipzen.onlineexam.model.DataItem;
import com.skipzen.onlineexam.model.QuestionResponse;
import com.skipzen.onlineexam.model.Questions;
import com.skipzen.onlineexam.network.AuthService;
import com.skipzen.onlineexam.network.RetrofitClient;
import com.skipzen.onlineexam.util.PrefManager;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Exams extends AppCompatActivity implements View.OnClickListener {

    Button btnA, btnB, btnC, btnD;
    TextView txtScore, txtQuestion, txtJmlSoal;
    ImageView imgQuestion;
    final static Long INTERVAL = Long.valueOf(2000);
    final static Long TIMOUT = Long.valueOf(1800000);
    int progress = 0;
    CountDownTimer countDownTimer;
    int index=0, score=0, questionStayIn=0, jmlQuestion, Answer;

    FirebaseDatabase database;
    DatabaseReference Question;

    ProgressBar progressBar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exams);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        database = FirebaseDatabase.getInstance();
        Question = database.getReference("Questions");

        btnA = findViewById(R.id.btnA);
        btnB = findViewById(R.id.btnB);
        btnC = findViewById(R.id.btnC);
        btnD = findViewById(R.id.btnD);
        txtScore = findViewById(R.id.txtNomorSoal);
        txtQuestion = findViewById(R.id.txtSoalText);
        txtJmlSoal = findViewById(R.id.txtJmlSoal);
        imgQuestion = findViewById(R.id.imgSoal);

        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);

       /* Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://indiarkmedia.com/api/v2/exam/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AuthService authService = retrofit.create(AuthService.class);

        Call<DataItem> call = authService.getQuestion();
        authService.getQuestion().enqueue(new Callback<DataItem>() {
            @Override
            public void onResponse(Call<DataItem> call, Response<DataItem> response) {
                if(!response.isSuccessful()){
                    txtSoalText.setText("Code: " + response.code());
                    return;
                }
                DataItem dataItems =response.body();
                String content = "";
                content += "questionText: "  + dataItems.getQuestionText() + "\n";
                content += "questionText: "  + dataItems.getQuestionId().toString() + "\n";
                txtSoalText.append(content);
            }

            @Override
            public void onFailure(Call<DataItem> call, Throwable t) {
                txtSoalText.setText(t.getMessage());
            }
        });
        */

      /*  btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Exams.this, ResultExam.class);
                startActivity(intent);
                finish();
            }
        });
       */
    }

    @Override
    public void onClick(View view) {
        countDownTimer.cancel();
        if(index<jmlQuestion){
            Button clickButton = (Button) view;
            if(clickButton.getText().equals(PrefManager.questionsList.get(index).getCurrentAnswer())){
                score+=4;
                Answer++;
                showQuestion(++index);
            }
            else{
                Intent intent = new Intent(this, ResultExam.class);
                Bundle bundle = new Bundle();
                bundle.putInt("Score : ", score);
                bundle.putInt("Total Pertanyaan : ", jmlQuestion);
                bundle.putInt("Answer : ", Answer);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
            txtScore.setText(String.format("%d", score));
        }
    }

    private void showQuestion(int i) {
        if(index<jmlQuestion){
            questionStayIn++;
            txtJmlSoal.setText(String.format("%d / %d", questionStayIn, jmlQuestion));
            progressBar.setProgress(progress);

            if(PrefManager.questionsList.get(index).getImageQuestion().equals("true")){
                //if is image wuestion
                Picasso.with(getBaseContext())
                        .load(PrefManager.questionsList.get(index).getQuestion())
                        .into(imgQuestion);
                imgQuestion.setVisibility(View.VISIBLE);
                txtQuestion.setVisibility(View.VISIBLE);
            }
            else {
                txtQuestion.setText(PrefManager.questionsList.get(index).getQuestion());
                imgQuestion.setVisibility(View.INVISIBLE);
                txtQuestion.setVisibility(View.VISIBLE);
            }
            btnA.setText(PrefManager.questionsList.get(index).getAnswer_A());
            btnB.setText(PrefManager.questionsList.get(index).getAnswer_B());
            btnC.setText(PrefManager.questionsList.get(index).getAnswer_C());
            btnD.setText(PrefManager.questionsList.get(index).getAnswer_C());

            countDownTimer.start();//start timer
        }
        else {
            //if it is final question
            Intent intent =  new Intent(this,ResultExam.class);
            Bundle bundle = new Bundle();
            bundle.putInt("SCORE", score);
            bundle.putInt("TOTAL",jmlQuestion);
            bundle.putInt("Answer", Answer);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onPostResume(){
        super.onPostResume();
        jmlQuestion = PrefManager.questionsList.size();
        countDownTimer = new CountDownTimer(TIMOUT,INTERVAL) {
            @Override
            public void onTick(long l) {
                progressBar.setProgress(progress);
                progress++;
            }

            @Override
            public void onFinish() {
                countDownTimer.cancel();
                showQuestion(++index);
            }
        };
        showQuestion(index);
    }
}
