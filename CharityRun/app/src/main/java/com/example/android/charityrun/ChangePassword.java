package com.example.android.charityrun;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ChangePassword extends AppCompatActivity {
    private TextView txt1, txt2, txt3;
    private EditText etcurrPass, etnewPass, etconfPass;
    private Button btnsave, btncancel;
    private String currentpassword, username;
    private static boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
    }

    public void init() {
        txt1 = (TextView) findViewById(R.id.txt1);
        txt2 = (TextView) findViewById(R.id.txt2);
        txt3 = (TextView) findViewById(R.id.txt3);
        etcurrPass = (EditText) findViewById(R.id.etcurrPass);
        currentpassword = etcurrPass.getText().toString().trim();
        etnewPass = (EditText) findViewById(R.id.etnewPass);
        etconfPass = (EditText) findViewById(R.id.etconfPass);
        btnsave = (Button) findViewById(R.id.btnsave);
        btncancel = (Button) findViewById(R.id.btncancel);


    }
}