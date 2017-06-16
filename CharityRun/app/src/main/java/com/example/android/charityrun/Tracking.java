package com.example.android.charityrun;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Tracking extends AppCompatActivity implements View.OnClickListener{
    private Button start_track;
    private long timestamp;
    private TextView textViewStepCounter;

    private TextView textViewStepDetector;

    private  TextView Name,desc,points_total,points_earned;

    private TextView kmwalked1;

    private float kmwalked;

    private String steps1;

    private Double stridelength=0.000762;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);
        Toolbar toolbar=(Toolbar)findViewById(R.id.tracking_toolbar);
        textViewStepDetector = (TextView) findViewById(R.id.textView4);
        toolbar.setTitle("Tracking");
        setSupportActionBar(toolbar);

        Intent intent=getIntent();
        int position = intent.getIntExtra("position", -1);
        Name=(TextView)findViewById(R.id.textView5);
        desc=(TextView)findViewById(R.id.textView6);
        textViewStepCounter = (TextView) findViewById(R.id.textView2);
        kmwalked1=(TextView)findViewById(R.id.textView4);
        points_total=(TextView)findViewById(R.id.textView3);
        points_earned=(TextView)findViewById(R.id.textView);
        Name.setText(Home.campaignList.get(position).getName());
        desc.setText(Home.campaignList.get(position).getDescription());
        points_total.setText(Home.campaignList.get(position).getTotal());
        points_earned.setText(Home.campaignList.get(position).getRun_count());
        initComponents();
    }
    private void initComponents()
    {
        start_track=(Button)findViewById(R.id.start_track);
        start_track.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case(R.id.start_track):
            {
              registerForSensorEvents();

            }
        }
    }
    public void registerForSensorEvents() {
        SensorManager sManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Step Counter
        sManager.registerListener(new SensorEventListener() {

                                      @Override
                                      public void onSensorChanged(SensorEvent event) {
                                          float steps = event.values[0];
                                          textViewStepCounter.setText((int) steps + "");
                                          textViewStepDetector.setText(DateUtils.getRelativeTimeSpanString(timestamp));
                                          steps1=String.valueOf(textViewStepCounter.getText());
                                          kmwalked= (float) (Integer.parseInt(steps1)*stridelength);
                                          kmwalked1.setText(String.valueOf(kmwalked));
                                          if(steps1.equals("5000"))
                                          {
                                              Toast.makeText(Tracking.this,"SuccessFully Donated",Toast.LENGTH_LONG);
                                              JSONObject bodyObj = new JSONObject();
                                              JsonObjectRequest jRequest = new JsonObjectRequest(Request.Method.GET, "http://"+ GlobalApp.IP+":"+GlobalApp.PORT+"/CharityRun/updateCampaign", bodyObj, new Response.Listener() {
                                                  @Override
                                                  public void onResponse(Object response) {
                                                      try {


                                                      } catch (Exception e1) {
                                                          e1.printStackTrace();
                                                      }
                                                  }
                                              }, new Response.ErrorListener() {
                                                  @Override
                                                  public void onErrorResponse(VolleyError error) {
                                                      Toast.makeText(Tracking.this, "Error...Check internet connectivity..!"+error, Toast.LENGTH_SHORT).show();
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
                                              MySingleton.getInstance(Tracking.this).addToRequestQueue(jRequest);
                                          }
                                      }

                                      @Override
                                      public void onAccuracyChanged(Sensor sensor, int accuracy) {

                                      }
                                  }, sManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER),
                SensorManager.SENSOR_DELAY_UI);

        // Step Detector
        sManager.registerListener(new SensorEventListener() {

                                      @Override
                                      public void onSensorChanged(SensorEvent event) {
                                          // Time is in nanoseconds, convert to millis
                                          timestamp = event.timestamp / 1000000;
                                      }

                                      @Override
                                      public void onAccuracyChanged(Sensor sensor, int accuracy) {

                                      }
                                  }, sManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR),
                SensorManager.SENSOR_DELAY_UI);
    }
}
