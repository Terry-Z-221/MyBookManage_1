package cn.jnu.edu.mybookmanage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_add = (Button)findViewById(R.id.btn_add); // 主页面添加书籍信息按钮

        btn_add.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this,AddBookActivity.class); // 跳转页面至添加信息页面
            startActivity(i);
        });
        //java匿名类
    }


}