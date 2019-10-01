package com.skipzen.onlineexam.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.skipzen.onlineexam.model.AnswersItem;

import java.util.List;

public class PrefManager {
    private Context context;
    private SharedPreferences sharedPreferences;

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
