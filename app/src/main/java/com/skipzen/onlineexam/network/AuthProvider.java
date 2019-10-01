package com.skipzen.onlineexam.network;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.skipzen.onlineexam.model.QuestionResponse;
import com.skipzen.onlineexam.model.Response;
import com.skipzen.onlineexam.util.PrefManager;

import retrofit2.Call;
import retrofit2.Callback;

public class AuthProvider {
    private Context context;

    public AuthProvider(Context context) {
        this.context = context;
    }

    AuthService authService = new RetrofitClient().create(context).create(AuthService.class);

    public void login(String username, String password) {
        authService.login(username, password).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if(response.isSuccessful()){
                    PrefManager prefManager = new PrefManager(context);
                    String token = response.body().getData().getAccessToken();

                    prefManager.saveToken(token);
                    Toast.makeText(context, "access_token: " + token, Toast.LENGTH_SHORT).show();
                    Log.i("TEST GAN", "onResponse: " + token);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });

    }

    public void getExamData(String token){
        authService.getExamData("Bearer " + token);
    };
    public void Question(String token){
        authService.getExamData(token).enqueue(new Callback<QuestionResponse>() {
            @Override
            public void onResponse(Call<QuestionResponse> call, retrofit2.Response<QuestionResponse> response) {
                if (response.isSuccessful()){
                    PrefManager prefManager = new PrefManager(context);
                }

            }

            @Override
            public void onFailure(Call<QuestionResponse> call, Throwable t) {

            }
        });
    }
}
