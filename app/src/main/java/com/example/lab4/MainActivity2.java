package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {
    private EditText editText;
    private Button butOK, butCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        editText = (EditText) findViewById(R.id.TextPersonName);
        butOK = (Button)findViewById(R.id.buttonOK);
        butCancel = (Button)findViewById(R.id.buttonCancel);
        butOK.setOnClickListener(this::onClick);
        butCancel.setOnClickListener((View view)->{finish();});
    }
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.putExtra("name", editText.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}