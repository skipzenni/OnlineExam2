package com.skipzen.onlineexam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.internal.service.Common;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.skipzen.onlineexam.model.DataItem;
import com.skipzen.onlineexam.model.QuestionResponse;
import com.skipzen.onlineexam.model.Questions;
import com.skipzen.onlineexam.network.AuthProvider;
import com.skipzen.onlineexam.util.PrefManager;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;

import static com.skipzen.onlineexam.util.PrefManager.categoriId;

public class StartExam extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference Questions;
    Button btnStartExam;
    TextView txtUsername, txtTanggalSekarang, txtBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_exam);

        btnStartExam = findViewById(R.id.btnStartExam);
        txtUsername = findViewById(R.id.txtUsername);
        txtTanggalSekarang = findViewById(R.id.txtDateNow);
        txtBack = findViewById(R.id.txtBack);

        database = FirebaseDatabase.getInstance();
        Questions = database.getReference("Questions");
        loadQuestion(PrefManager.categoriId);

        btnStartExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartExam.this, Exams.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void loadQuestion(String categoriId) {
        if(PrefManager.questionsList.size()>0)
            PrefManager.questionsList.clear();
        Questions.orderByChild("CategoriId").equalTo(categoriId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                            Questions questions = dataSnapshot1.getValue(Questions.class);
                            PrefManager.questionsList.add(questions);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
        Collections.shuffle(PrefManager.questionsList);
    }

    public void Back(View view) {
        Intent intent = new Intent(StartExam.this, FaceRecognition.class);
        startActivity(intent);
        finish();
    }
    //time Converted
   /* private void convertStringToDate() {
        String dateString = "2019-09-27 22:15:42";
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-mm-dd", Locale.getDefault());

        try {
            Date tanggalDariServer = sdf.parse(dateString);
            Date dateNow = new Date();

            if(tanggalDariServer.equals(dateNow)){
                Toast.makeText(this, "tanggalnya sama", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "tanggalnya berbeda", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {

        }

    }

    */


}
