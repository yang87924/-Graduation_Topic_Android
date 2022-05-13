package com.example.smart_lamp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import com.example.smart_lamp.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private boolean isexist;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    Member m2 = new Member();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.imageMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.drawerlayout.openDrawer(GravityCompat.START);
            }
        });
        binding.NavigationView.setItemIconTintList(null);//選單圖案
        //選單控制
        binding.NavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // 取得選項id
                int id = item.getItemId();

                // 依照id判斷點了哪個項目並做相應事件
                if (id == R.id.action_adjust_brightness) {
                    // 按下「首頁」要做的事

                    return true;
                }
                else if (id == R.id.action_timing) {
                    // 按下「使用說明」要做的事

                    return true;
                }
                return false;
            }
        });

    }
    public void sign(View view) {
        db.collection("smartlamp")
                .whereEqualTo("Accunt",binding.editAccunt.getText().toString())
                .whereEqualTo("Passwd",binding.editPasswd.getText().toString())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful())
                        {
                            Member m1 = new Member();
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult())
                                {
                                    m1.Accunt = documentSnapshot.getString("Accunt");
                                    m1.Passwd = documentSnapshot.getString("Passwd");
                                }
                            if(m1.Accunt==null || m1.Passwd==null)
                            {
                                Log.d("Demo", "帳號密碼錯誤");
                            }else
                            {
                                m2.Accunt=m1.Accunt;
                                m2.Passwd=m1.Passwd;
                                Log.d("Demo", "帳號:"+m1.Accunt+"密碼:"+m1.Passwd);
                                //呼叫對話框
                                dialog("登入成功","歡迎使用");
                            }
                        }
                        else
                            {
                                Log.d("Demo", "Error => 失敗");
                            }
                        }
                    });
    }
    //註冊按鈕
    public void registerActivity(View view) {
        Intent it =new Intent(MainActivity.this,register.class);
        startActivity(it);
    }
    //對話框
    private void dialog(String title,String content ) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);  //設置標題
        builder.setIcon(R.mipmap.ic_launcher_round); //標題前面那個小圖示
        builder.setMessage(content); //提示訊息


        //確定 取消 一般 這三種按鈕就看你怎麼發揮你想置入的功能囉
        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                Log.d("Demo", "確定");
                Intent it =new Intent(MainActivity.this,AdjustBrightnessActivity.class);
                startActivity(it);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                Log.d("Demo", "取消");
            }
        });
        builder.create().show();
    }

}