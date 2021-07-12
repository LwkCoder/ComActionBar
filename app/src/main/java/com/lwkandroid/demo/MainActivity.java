package com.lwkandroid.demo;

import android.os.Bundle;
import android.widget.Toast;

import com.lwkandroid.widget.cab.ComActionBar;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ComActionBar actionBar = findViewById(R.id.actionBar01);
        actionBar.setLeftText("上一级");
        actionBar.setRightOnItemClickListener01((viewId, textView, dividerLine) -> showToast("点击Right01"));
        actionBar.setRightOnItemClickListener02((viewId, textView, dividerLine) -> showToast("点击Right02"));

    }

    private void showToast(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
