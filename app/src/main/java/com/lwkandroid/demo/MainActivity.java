package com.lwkandroid.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lwkandroid.widget.ComActionBar;

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
        actionBar.setRightOnItemClickListener01(new ComActionBar.OnItemClickListener()
        {
            @Override
            public void onActionBarItemClicked(int viewId, TextView textView, View dividerLine)
            {
                showToast("点击Right01");
            }
        });
        actionBar.setRightOnItemClickListener02(new ComActionBar.OnItemClickListener()
        {
            @Override
            public void onActionBarItemClicked(int viewId, TextView textView, View dividerLine)
            {
                showToast("点击Right02");
            }
        });

    }

    private void showToast(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
