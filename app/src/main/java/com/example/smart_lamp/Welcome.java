package com.example.smart_lamp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import java.util.HashMap;

public class Welcome extends AppCompatActivity {

    private final long DelayTime=10;
    Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //設定隱藏標題
      // getSupportActionBar().hide();
        //設定隱藏狀態
       // getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent it =new Intent(Welcome.this,MainActivity.class);
                startActivity(it);

            }
        },DelayTime);
    }
}