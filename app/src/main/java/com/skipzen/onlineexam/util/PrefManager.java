package com.skipzen.onlineexam.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.skipzen.onlineexam.model.AnswersItem;
import com.skipzen.onlineexam.model.Questions;
import com.skipzen.onlineexam.model.Users;

import java.util.ArrayList;
import java.util.List;

public class PrefManager {
    private Context context;
    private SharedPreferences sharedPreferences;

    public static String categoriId;
    public static Users CurrentUser;
    public static List<Questions> questionsList = new ArrayList<>();

    public PrefManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("projek", Context.MODE_PRIVATE);
    }

    public void saveToken(String accesToken) {
        sharedPreferences.edit().putString("TOKEN", accesToken).apply();
    }

    public String getToken() {
        return sharedPreferences.getString("TOKEN", null);
    }

    public void clearToken() {
        sharedPreferences.edit().remove("TOKEN").apply();
    }


}
