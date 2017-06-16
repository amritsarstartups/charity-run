package com.example.android.charityrun;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
  Button btnpost,btnviewprogress,btnfundtransfer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    initcomponents();
    }
public void initcomponents()
 {
     btnpost=(Button)findViewById(R.id.btnpost);
     btnviewprogress=(Button)findViewById(R.id.btnviewprogress);
     btnfundtransfer=(Button)findViewById(R.id.btnfundtransfer);
 }
}
