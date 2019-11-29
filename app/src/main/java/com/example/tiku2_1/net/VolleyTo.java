package com.example.tiku2_1.net;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.tiku2_1.Appclient;

import org.json.JSONException;
import org.json.JSONObject;

public class VolleyTo extends Thread {
    private int Time;
    private String Url = "http://10.173.79.143:8080/traffic/";
    private boolean isloop;
    private JSONObject jsonObject = new JSONObject();
    private VolleyLo volleyLo;

    public VolleyTo setTime(int time) {
        Time = time;
        return this;
    }


    public VolleyTo setUrl(String url) {
        Url += url;
        return this;
    }

    public VolleyTo setIsloop(boolean isloop) {
        this.isloop = isloop;
        return this;
    }


    public VolleyTo setJsonObject(String K, Object V) {
        try {
            jsonObject.put(K, V);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }


    public VolleyTo setVolleyLo(VolleyLo volleyLo) {
        this.volleyLo = volleyLo;
        return this;
    }

    @Override
    public void run() {
        super.run();
        do {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    volleyLo.onResponse(jsonObject);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    volleyLo.onErrorResponse(volleyError);
                }
            });
            Appclient.setRequestQueue(jsonObjectRequest);
            try {
                Thread.sleep(Time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (isloop);
    }
}
