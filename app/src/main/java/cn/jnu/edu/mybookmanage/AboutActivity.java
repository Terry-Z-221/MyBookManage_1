package cn.jnu.edu.mybookmanage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ImageButton imageButton = findViewById(R.id.btn_about_back);
        imageButton.setOnClickListener(view -> AboutActivity.this.finish());
    }
}