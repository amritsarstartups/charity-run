package com.example.android.charityrun;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class Post_campagin extends AppCompatActivity implements View.OnClickListener{
    private int date,month,year;
    EditText etname,etdescription,etfunds;
    TextView tvstartdate,tvenddate;
    Button btnpost;
    Spinner spin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_campagin);
    initComponents();
    }
    private void initComponents()
    {
      etname=(EditText)findViewById(R.id.etname);
        etdescription=(EditText)findViewById(R.id.etdescription);
        etfunds=(EditText)findViewById(R.id.etfunds);
        tvstartdate=(TextView)findViewById(R.id.etstartdate);
        tvenddate=(TextView)findViewById(R.id.etenddate);
        tvstartdate.setOnClickListener(this);
        tvenddate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case(R.id.etstartdate):
            {
                Calendar cal = Calendar.getInstance();
                year = cal.get(Calendar.YEAR);
                month = cal.get(Calendar.MONTH);
                date = cal.get(Calendar.DATE);
                DatePickerDialog dpd = new DatePickerDialog(Post_campagin.this, ondatesetlistener, year, month, date);
                dpd.show();
                break;
                }
            case(R.id.etenddate):
            {
                Calendar cal = Calendar.getInstance();
                year = cal.get(Calendar.YEAR);
                month = cal.get(Calendar.MONTH);
                date = cal.get(Calendar.DATE);
                DatePickerDialog dpd = new DatePickerDialog(Post_campagin.this, ondatesetlistener, year, month, date);
                dpd.show();
                break;
            }
            }
        }

    private DatePickerDialog.OnDateSetListener ondatesetlistener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            tvstartdate.setText(String.valueOf(dayOfMonth) + "/" + String.valueOf(month + 1) + "/" + String.valueOf(year));

        }
    };
    }

