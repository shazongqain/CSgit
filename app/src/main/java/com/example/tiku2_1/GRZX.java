package com.example.tiku2_1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.tiku2_1.net.VolleyLo;
import com.example.tiku2_1.net.VolleyTo;

import org.json.JSONException;
import org.json.JSONObject;

public class GRZX extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_cd;
    private TextView tv_mc;
    private TextView tv_ye;
    private Spinner sp_ch;
    private EditText et_je;
    private Button bt_cx;
    private Button bt_cz;
    private GRZX_dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grzx);
        initView();
        setVolley();
    }

    private void setDialog() {
        String carid = sp_ch.getSelectedItem().toString();
        String qian = et_je.getText().toString();
        dialog = new GRZX_dialog(carid, qian);
        dialog.setDiacz(new GRZX_dialog.diacz() {
            @Override
            public void diacz() {
                setVolley();
            }
        });
        dialog.show(getSupportFragmentManager(), "");

    }

    private void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_balance_b")
                .setJsonObject("UserName", "user1")
                .setJsonObject("number", sp_ch.getSelectedItem().toString())
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            tv_ye.setText(jsonObject.getString("balance"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.i("11111111111111111111", "onErrorResponse: ");
                    }
                }).start();

    }

    private void initView() {

        tv_ye = (TextView) findViewById(R.id.tv_ye);
        sp_ch = (Spinner) findViewById(R.id.sp_ch);
        et_je = (EditText) findViewById(R.id.et_je);
        bt_cx = (Button) findViewById(R.id.bt_cx);
        bt_cz = (Button) findViewById(R.id.bt_cz);
        bt_cx.setOnClickListener(this);
        bt_cz.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_cx:
                setVolley();
                break;
            case R.id.bt_cz:
                setDialog();
                break;
        }
    }

}
