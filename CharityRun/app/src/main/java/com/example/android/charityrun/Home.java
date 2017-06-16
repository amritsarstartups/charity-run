package com.example.android.charityrun;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.*;
import android.support.v7.widget.DividerItemDecoration;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Home extends AppCompatActivity {

    private TextView Name,steps,current_mutiply,nextmultiply;
    private CampaignAdapter campaignAdapter;
    public static List<campaign> campaignList=new ArrayList<>();
    private RecyclerView recyclerView;
    private AdView adView;
    String reg_url="http://"+GlobalApp.IP+":"+GlobalApp.PORT+"/CharityRun/getCampaign";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar=(Toolbar)findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);

        Name=(TextView)findViewById(R.id.Name);
        steps=(TextView)findViewById(R.id.steps);
        current_mutiply=(TextView)findViewById(R.id.current_multiply);

        try
        {
            SharedPreferences sharedPreferences=getSharedPreferences("pass",MODE_PRIVATE);
            String username1 = sharedPreferences.getString("username", null);
            if(username1==null) {
                Intent intent = getIntent();
                String username = intent.getStringExtra("username");
                String stepbank = intent.getStringExtra("steps");
                String current_multiply = intent.getStringExtra("multiplier");


                Log.d("In Home", username + "-" + stepbank);
                Name.setText(username);
                steps.setText(stepbank);
                current_mutiply.setText(current_multiply);
            }else
            {
                String step=sharedPreferences.getString("steps",null);
                String current_multiplier=sharedPreferences.getString("multiplier",null);
                Name.setText(username1);
                steps.setText(step);
                current_mutiply.setText(current_multiplier);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        adView=(AdView)findViewById(R.id.bannerAd);
        recyclerView=(RecyclerView)findViewById(R.id.campaign_recycle);
        campaignAdapter=new CampaignAdapter(campaignList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(campaignAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(),new RecyclerItemClickListener.OnItemClickListener()
        {

            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(Home.this,Tracking.class);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        }));
        prepareData();

    }
    void prepareData()
    {
        MobileAds.initialize(this,"ca-app-pub-9440597611368791~1125024669");
        adView = (AdView) findViewById(R.id.bannerAd);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("BDE9B2B5EF31082DD1C0C330985AD03D")
                .build();
        adView.loadAd(adRequest);


        JSONObject bodyObj = new JSONObject();
        JsonObjectRequest jRequest = new JsonObjectRequest(Request.Method.GET, "http://"+ GlobalApp.IP+":"+GlobalApp.PORT+"/CharityRun/getCampaign", bodyObj, new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                try {
                    Log.d("RES-------",response.toString());
                    JSONObject json=new JSONObject(response.toString());
                    JSONArray arr= (JSONArray) json.get("key");

                    for(int i=0;i<arr.length();i++){
                        Log.d("RES----",i+"");

                        JSONObject obj= (JSONObject) arr.get(i);
                        obj.get("id");
                        obj.get("ngo");
                        String campaign_name=obj.get("campaign_name")+"";
                        String campaign_desc=obj.get("campaign_description")+"";
                        String points_needed=obj.get("points_needed")+"";

                        String points_earned=obj.getString("points_earned");
                        campaign cm=new campaign(campaign_name,campaign_desc,points_earned,points_needed);
                        campaignList.add(cm);
                    }
                    campaignAdapter.notifyDataSetChanged();

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Home.this, "Error...Check internet connectivity..!"+error, Toast.LENGTH_SHORT).show();
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
        MySingleton.getInstance(Home.this).addToRequestQueue(jRequest);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu); //your file name
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {

        }
        return super.onOptionsItemSelected(item);
    }
}
