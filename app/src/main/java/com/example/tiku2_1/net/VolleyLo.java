package com.example.tiku2_1.net;

import com.android.volley.VolleyError;

import org.json.JSONObject;

public interface VolleyLo {
    void onResponse(JSONObject jsonObject);
    void onErrorResponse(VolleyError volleyError);

}
