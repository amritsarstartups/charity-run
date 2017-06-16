package com.example.android.charityrun;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                SharedPreferences sharedPreferences=getSharedPreferences("pass",MODE_PRIVATE);
                String username = sharedPreferences.getString("username", null);
                if(username==null)
                    startActivity(new Intent(Splash.this,Login.class));
                else
                    startActivity(new Intent(Splash.this,Home.class));
                Splash.this.finish();
            }
        }, 1000);

    }


}