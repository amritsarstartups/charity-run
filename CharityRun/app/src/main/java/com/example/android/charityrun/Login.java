package com.example.android.charityrun;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Login extends AppCompatActivity implements View.OnClickListener {
    TextView tvforgot;
    EditText etusername,etpassword;
    Button btnsignin;
    Spinner spinner;
    String login_type;
    String reg_url;

   // private Communicator communicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    initComponents();
     //   communicator = new Communicator();


    }
    private void initComponents()
    {
        spinner=(Spinner)findViewById(R.id.spinner);
        List<String> categories = new ArrayList<String>();
        categories.add("User");
        categories.add("Society");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                login_type= parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        tvforgot=(TextView)findViewById(R.id.tvforgot);
        etusername=(EditText)findViewById(R.id.etusername);
        etpassword=(EditText)findViewById(R.id.etpassword);
        btnsignin=(Button)findViewById(R.id.btnsignin);
        tvforgot.setOnClickListener(this);
        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (login_type.equals("User")) {
                    if (etusername.getText().toString().isEmpty() || etpassword.getText().toString().isEmpty()) {
                        Toast.makeText(Login.this, "Fields Empty", Toast.LENGTH_LONG).show();
                    } else {
                        JSONObject bodyObj = new JSONObject();

                        try {
                            reg_url = "http://" + GlobalApp.IP + ":" + GlobalApp.PORT + "/CharityRun/login?e=" + URLEncoder.encode(etusername.getText().toString(), "UTF-8") + "&p=" + URLEncoder.encode(etpassword.getText().toString(), "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        JsonObjectRequest jRequest = new JsonObjectRequest(Request.Method.GET, reg_url, bodyObj, new Response.Listener() {
                            @Override
                            public void onResponse(Object response) {

                                System.out.println(response.toString());
                                try {
                                    JSONObject jsonObject = new JSONObject(response.toString());
                                    String info = jsonObject.getString("info");
                                    if (info.trim().equals("success")) {
                                        String username = jsonObject.getString("username");
                                        String stepbank = jsonObject.getString("steps");
                                        String current_multiply = jsonObject.getString("multiplier");
                                        Toast.makeText(getApplication(), "Success--", Toast.LENGTH_LONG).show();
                                        Intent in = new Intent(Login.this, Home.class);
                                        SharedPreferences sharedPreferences = getSharedPreferences("pass", MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString("username", username);
                                        editor.putString("steps", stepbank);
                                        editor.putString("multiplier", current_multiply);
                                        editor.commit();
                                        in.putExtra("username", username);
                                        in.putExtra("steps", stepbank);
                                        in.putExtra("multiplier", current_multiply);
                                        startActivity(in);
                                        Login.this.finish();
                                    } else {
                                        Toast.makeText(Login.this, "Wrong Credentials", Toast.LENGTH_LONG).show();
                                    }
                                } catch (JSONException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(Login.this, "Error...Check internet connectivity..!" + error, Toast.LENGTH_SHORT).show();
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
                        MySingleton.getInstance(Login.this).addToRequestQueue(jRequest);

                    }
                }
                else if(login_type.equals("Society"))
                {
                    JSONObject bodyObj = new JSONObject();
                    try {
                        reg_url = "http://" + GlobalApp.IP + ":" + GlobalApp.PORT + "/CharityRun/loginSociety?e=" +URLEncoder.encode(etusername.getText().toString(), "UTF-8") + "&p=" + URLEncoder.encode(etpassword.getText().toString(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    JsonObjectRequest jRequest = new JsonObjectRequest(Request.Method.GET, reg_url,bodyObj , new Response.Listener() {
                        @Override
                        public void onResponse(Object response) {
                            try {
                                JSONObject jsonObject=new JSONObject(response.toString());
                                String info=(String)jsonObject.get("info");
                                if(info.equals("failure"))
                                {
                                    Toast.makeText(Login.this,"Wrong Credentials",Toast.LENGTH_LONG).show();
                                }
                                else
                                {
                                    Intent i=new Intent(Login.this,Society_home.class);
                                    i.putExtra("Name",info);
                                    i.putExtra("Email",etusername.getText().toString());
                                    startActivity(i);
                                    Login.this.finish();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Login.this, "Error...Check internet connectivity..!" + error, Toast.LENGTH_SHORT).show();
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
                    MySingleton.getInstance(Login.this).addToRequestQueue(jRequest);


                }
                else
                {

                }
            }
        });
    }
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case(R.id.tvforgot):
            {
                Intent i=new Intent(Login.this,Forgot_Password.class);
                startActivity(i);
                finish();
            }
        }
    }
}
