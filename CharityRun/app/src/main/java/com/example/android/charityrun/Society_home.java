package com.example.android.charityrun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Society_home extends AppCompatActivity {

    List<campaign> list=new ArrayList<>();
    Button btnpost,btnviewprogress,btnfundtransfer;
    TextView welcome;
    String name,email;
    String reg_url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_society_home);
        btnpost=(Button)findViewById(R.id.btnpost);
        btnviewprogress=(Button)findViewById(R.id.btnviewprogress);
        btnfundtransfer=(Button)findViewById(R.id.btnfundtransfer);
        welcome=(TextView)findViewById(R.id.Welcome);
        Intent intent=getIntent();
        name = intent.getStringExtra("Name");
        email=intent.getStringExtra("Email");
        welcome.setText("Welcome "+name);
        sendRequest();
    }
    void sendRequest()
    {
        try {
            reg_url="http://" + GlobalApp.IP + ":" + GlobalApp.PORT + "/CharityRun/viewCampaigns/ngo="+URLEncoder.encode(email,"UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        JSONObject bodyObj = new JSONObject();
        JsonObjectRequest jRequest = new JsonObjectRequest(Request.Method.GET, reg_url,bodyObj , new Response.Listener() {
            @Override
            public void onResponse(Object response) {

                try {
                    JSONObject json=new JSONObject(response.toString());
                    JSONArray arr= (JSONArray) json.get("key");

                    for(int i=0;i<arr.length();i++) {
                        Log.d("RES----", i + "");

                        JSONObject obj = (JSONObject) arr.get(i);
                        obj.get("id");
                        obj.get("ngo");
                        String campaign_name = obj.get("campaign_name") + "";
                        String campaign_desc = obj.get("campaign_description") + "";
                        String points_needed = obj.get("points_needed") + "";

                        String points_earned = obj.getString("points_earned");
                        campaign cm=new campaign(campaign_name,campaign_desc,points_earned,points_needed);
                        list.add(cm);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Society_home.this, "Error...Check internet connectivity..!" + error, Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");

                return headers;
            }
        };
        MySingleton.getInstance(Society_home.this).addToRequestQueue(jRequest);

    }
}
