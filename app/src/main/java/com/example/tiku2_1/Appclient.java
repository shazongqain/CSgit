package com.example.tiku2_1;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


public class Appclient extends Application {
    private static RequestQueue requestQueue;
    private SharedPreferences preferences;

    @Override
    public void onCreate() {
        super.onCreate();
        requestQueue= Volley.newRequestQueue(this);
        preferences= PreferenceManager.getDefaultSharedPreferences(this);
    }

    public static void setRequestQueue(JsonObjectRequest jsonObjectRequest) {
        requestQueue.add(jsonObjectRequest);
    }

}
