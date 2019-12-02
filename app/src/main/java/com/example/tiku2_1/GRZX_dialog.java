package com.example.tiku2_1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.VolleyError;

import com.example.tiku2_1.net.VolleyLo;
import com.example.tiku2_1.net.VolleyTo;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressLint("ValidFragment")
public class GRZX_dialog extends DialogFragment {
    private TextView dia_time;
    private Button dia_qd, dia_qx;
    private String carid, qian, car;

    @SuppressLint("ValidFragment")
    public GRZX_dialog(String carid, String qian) {
        this.carid = carid;
        this.qian = qian;
        Log.i("22222222222", "GRZX_dialog: " + carid + "----" + qian);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.grzx_dialog, null);
        dia_time = view.findViewById(R.id.dia_time);
        dia_qd = view.findViewById(R.id.dia_qd);
        dia_qx = view.findViewById(R.id.dia_qx);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setData();
        setTime();
        setOnclick();


    }

    public interface diacz {
        void diacz();
    }

    public void setDiacz(GRZX_dialog.diacz diacz) {
        this.diacz = diacz;
    }

    private diacz diacz;


    private void setOnclick() {
        dia_qd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setVolley();
                dismiss();
            }
        });
        dia_qx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


    }

    private void setChongZhi() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("set_balance")
                .setJsonObject("UserName", "user1")
                .setJsonObject("plate", car)
                .setJsonObject("balance",qian)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            if (jsonObject.getString("RESULT").equals("S")) {
                                diacz.diacz();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();

    }

    private String setTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);

    }

    private void setData() {
        dia_time.setText("车 号：" + carid + "\n" + "充 值 金 额:" + qian + "\n" + "充值时间：" + setTime());
    }

    private void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_plate")
                .setJsonObject("UserName", "user1")
                .setJsonObject("number", carid)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            car = jsonObject.getString("plate");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        setChongZhi();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

}
