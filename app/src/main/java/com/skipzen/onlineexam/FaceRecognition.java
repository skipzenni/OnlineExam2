package com.skipzen.onlineexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.skipzen.onlineexam.network.AuthProvider;
import com.skipzen.onlineexam.util.PrefManager;

public class FaceRecognition extends AppCompatActivity {

    Button btnLogin;
    EditText txtUsername, txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_recognition);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        btnLogin = findViewById(R.id.btnLogin);
        txtUsername = findViewById(R.id.txtLogin);
        txtPassword = findViewById(R.id.txtPassword);

        PrefManager prefManager = new PrefManager(this);
        String token = prefManager.getToken();
        //Toast.makeText(this, "token yang tesimpan: " + token, Toast.LENGTH_SHORT).show();

        txtUsername.addTextChangedListener(LoginWather);
        txtPassword.addTextChangedListener(LoginWather);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtUsername.toString() == "100783" & txtPassword.toString() == "123456"){
                    login();
                }else{
                    Toast.makeText(FaceRecognition.this, "Password Salah ", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void login() {
        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();
            AuthProvider authProvider = new AuthProvider(this);
            authProvider.login(username, password);
            Intent intent = new Intent(FaceRecognition.this, TakePhoto.class);
            startActivity(intent);
            finish();
    }

    private TextWatcher LoginWather = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            String usernameInput = txtUsername.getText().toString().trim();
            String passwordInput = txtPassword.getText().toString().trim();

            btnLogin.setEnabled(!usernameInput.isEmpty() && !passwordInput.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}
