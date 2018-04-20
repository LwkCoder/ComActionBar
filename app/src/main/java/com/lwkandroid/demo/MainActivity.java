package com.lwkandroid.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.lwkandroid.widget.comactionbar.ComActionBar;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ComActionBar actionBar01 = (ComActionBar) findViewById(R.id.cab_main01);
        actionBar01.setRightClickListener01(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                showToast("呵呵");
            }
        });
        actionBar01.setRightClickListener02(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                showToast("嘿嘿");
            }
        });

        ComActionBar actionBar03 = (ComActionBar) findViewById(R.id.cab_main03);
        actionBar03.setTitle("超长标题超长标题超长标题超长标题超长标题超长标题超长标题超长标题超长标题超长标题");
    }

    private void showToast(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
