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

import com.skipzen.onlineexam.model.Data;
import com.skipzen.onlineexam.model.DataItem;
import com.skipzen.onlineexam.model.QuestionResponse;
import com.skipzen.onlineexam.network.AuthService;
import com.skipzen.onlineexam.network.RetrofitClient;
import com.skipzen.onlineexam.util.PrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

        Retrofit retrofit = new Retrofit.Builder()
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
