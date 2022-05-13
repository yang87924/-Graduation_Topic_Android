package com.example.smart_lamp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.smart_lamp.databinding.ActivityAdjustBrightnessBinding;
import com.example.smart_lamp.databinding.ActivityMainBinding;
import com.example.smart_lamp.databinding.ActivityRegisterBinding;
import com.google.android.material.navigation.NavigationView;

public class AdjustBrightnessActivity extends AppCompatActivity {

    private ActivityAdjustBrightnessBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_adjust_brightness);
        binding=ActivityAdjustBrightnessBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.seektext.setText("目前拖移植：" +binding.seekbar.getProgress() + "  /  最大值："+binding.seekbar.getMax());
        binding.seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {


                binding.seektext.setText("目前拖移植：" + i + "  /  最大值："+binding.seekbar.getMax());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(AdjustBrightnessActivity.this, "觸碰SeekBar", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(AdjustBrightnessActivity.this, "放開SeekBar", Toast.LENGTH_SHORT).show();

            }
        });

        binding.NavigationView.setItemIconTintList(null);//選單圖案
        //選單控制
        binding.NavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // 取得選項id
                int id = item.getItemId();

                // 關閉選單亮度畫面
                if (id == R.id.action_adjust_brightness) {
                    // 按下「首頁」要做的事
                    binding.drawerlayout.closeDrawer(GravityCompat.START);//關閉選單
                    return true;
                }
                // 開啟調整亮度畫面
                else if (id == R.id.action_timing) {
                    // 按下「使用說明」要做的事

                    return true;
                }
                // 開啟調整亮度畫面
                else if (id == R.id.action_select_mode) {
                    // 按下「使用說明」要做的事

                    return true;
                }
                // 開啟調整亮度畫面
                else if (id == R.id.action_historical_record) {
                    // 按下「使用說明」要做的事

                    return true;
                }
                // 開啟調整亮度畫面
                else if (id == R.id.action_Sign_out) {
                    // 按下「使用說明」要做的事
                    Intent it =new Intent(AdjustBrightnessActivity.this,MainActivity.class);
                    startActivity(it);
                    return true;
                }
                return false;
            }
        });
    }
    //選單開啟
    public void menuclick(View view) {
        binding.drawerlayout.openDrawer(GravityCompat.START);
    }

    public void seekbar(View view) {

    }
}