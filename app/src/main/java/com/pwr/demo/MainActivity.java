package com.pwr.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.pwr.ttaa.TUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TUtils.MyTUtils();
    }
}