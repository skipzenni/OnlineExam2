package com.skipzen.onlineexam.network;

import com.skipzen.onlineexam.model.DataItem;
import com.skipzen.onlineexam.model.QuestionResponse;
import com.skipzen.onlineexam.model.Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AuthService {
    @FormUrlEncoded
    @POST("authenticate")
    Call<Response> login(
            @Field("login") String login,
            @Field("password") String password
    );

    @GET("exam/question")
    Call<QuestionResponse> getExamData(@Header("Authorization") String token);

    @GET("question")
    Call<List<QuestionResponse>> getQuestion();
}
