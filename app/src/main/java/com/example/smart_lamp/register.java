package com.example.smart_lamp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Instrumentation;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.smart_lamp.databinding.ActivityMainBinding;
import com.example.smart_lamp.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class register extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ActivityRegisterBinding binding;
    DatePickerDialog.OnDateSetListener datePicker;//日期選擇
    Calendar calendar = Calendar.getInstance();//日期選擇
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_register);
        binding= ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
    //設定日期
    public void setdate(View view) {
        datePicker = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "yyyy/MM/dd";
                SimpleDateFormat sdf;
                sdf = new SimpleDateFormat(myFormat, Locale.TAIWAN);
                binding.TextDate.setText(sdf.format(calendar.getTime()));
            }
        };
        DatePickerDialog dialog = new DatePickerDialog(register.this,
                datePicker,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }
    //設定性別男生
    public void sexman(View view) {
        Drawable drawableman,drawablewoman;
        drawableman=getResources().getDrawable(R.drawable.manicon_click);
        drawablewoman=getResources().getDrawable(R.drawable.womanicon);
        binding.imageMan.setImageDrawable(drawableman);
        binding.imageWoman.setImageDrawable(drawablewoman);
        binding.textsex.setText("男");
    }
    //設定性別女生
    public void sexwoman(View view) {
        Drawable drawableman,drawablewoman;
        drawableman=getResources().getDrawable(R.drawable.manicon);
        drawablewoman=getResources().getDrawable(R.drawable.womanicon_click);
        binding.imageMan.setImageDrawable(drawableman);
        binding.imageWoman.setImageDrawable(drawablewoman);
        binding.textsex.setText("女");
    }
    //顯示密碼
    public void showpasswd(View view) {
        int type = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;
        if(binding.editTextPasswd.getInputType() == type){
            binding.editTextPasswd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            binding.editTextPasswd.setSelection(binding.editTextPasswd.getText().length());		//把游標設定到當前文字末尾

        }else{
            binding.editTextPasswd.setInputType(type);
            binding.editTextPasswd.setSelection(binding.editTextPasswd.getText().length());
        }
    }
    //顯示再次輸入密碼
    public void showpasswd2(View view) {
        int type = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;
        if(binding.editTextPasswd2.getInputType() == type){
            binding.editTextPasswd2.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            binding.editTextPasswd2.setSelection(binding.editTextPasswd2.getText().length());		//把游標設定到當前文字末尾

        }else{
            binding.editTextPasswd2.setInputType(type);
            binding.editTextPasswd2.setSelection(binding.editTextPasswd2.getText().length());
        }
    }
    //是近視
    public void isCheckYES(View view) {
        if(binding.checkBoxYES.isChecked()==true)
        {
            binding.checkBoxNO.setChecked(false);
        }else
        {
            binding.checkBoxNO.setChecked(true);
        }
        binding.editTextLeftEye.setEnabled(true);
        binding.editTextRightEye.setEnabled(true);
        binding.textcheck.setText("是近視");
    }
    //否近視
    public void isCheckNO(View view) {
        if(binding.checkBoxNO.isChecked()==true)
        {
            binding.checkBoxYES.setChecked(false);
        }else
        {
            binding.checkBoxYES.setChecked(true);
        }
        binding.editTextLeftEye.setEnabled(false);
        binding.editTextRightEye.setEnabled(false);
        binding.textcheck.setText("沒近視");
    }
    //註冊按鈕
    public void register(View view) {
        if(isempty()==true)
        {
            Toast.makeText(register.this,"請輸入完整資料 ",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Map<String ,Object> m = new HashMap<>();
            m.put("Accunt",binding.editTextAccunt.getText().toString());
            m.put("Passwd",binding.editTextPasswd.getText().toString());
            m.put("Name",binding.editTextName.getText().toString());
            m.put("Nickname",binding.editTextNickname.getText().toString());
            m.put("sex",binding.textsex.getText().toString());
            m.put("birthDate",binding.TextDate.getText().toString());
            m.put("check",binding.textcheck.getText().toString());
            m.put("LeftEye",binding.editTextLeftEye.getText().toString());
            m.put("RightEye",binding.editTextRightEye.getText().toString());
            m.put("Answer",binding.editTextAnswer.getText().toString());
            db.collection("smartlamp")
                    .document(binding.editTextAccunt.getText().toString())
                    .set(m)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d("Demo","輸入成功");
                            //Toast.makeText(registered_user.this,"輸入成功: ",Toast.LENGTH_SHORT).show();*/

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("Demo","輸入 失敗"+e.toString());
                        }
                    });
        }

    }
    //返回按鈕
    public void back(View view) {
        new Thread() {
            public void run() {
                try {
                    Instrumentation inst = new Instrumentation();
                    inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
                } catch (Exception e) {
                }
            }
        }.start();
    }
    //判斷資料是否為空
    private boolean isempty()
    {
        if(binding.editTextAccunt.getText().equals("")||
           binding.editTextPasswd.getText().equals("")||
           binding.editTextPasswd2.getText().equals("")||
           binding.editTextName.getText().equals("")||
           binding.editTextNickname.getText().equals("")||
           binding.textsex.getText().equals("")||
           binding.TextDate.getText().equals("")||
           binding.textcheck.getText().equals("")||
           binding.editTextLeftEye.getText().equals("")||
           binding.editTextRightEye.getText().equals("")||
           binding.editTextAnswer.getText().equals(""))
        {
            return true;
        }
        else
        {
            return false;
        }

    }
}