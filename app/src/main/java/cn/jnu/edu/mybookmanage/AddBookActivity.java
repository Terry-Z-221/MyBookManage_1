package cn.jnu.edu.mybookmanage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

public class AddBookActivity extends AppCompatActivity {

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        position = this.getIntent().getIntExtra("position",0);

        String cur_name = this.getIntent().getStringExtra("name");
        String cur_author = this.getIntent().getStringExtra("author");
        String cur_translator = this.getIntent().getStringExtra("translator");
        String cur_publisher = this.getIntent().getStringExtra("publisher");
        String cur_pubdate = this.getIntent().getStringExtra("pubdate");
        String cur_isbn = this.getIntent().getStringExtra("isbn");
        String cur_reading_status = this.getIntent().getStringExtra("reading_status");
        String cur_shelf = this.getIntent().getStringExtra("shelf");
        String cur_notes = this.getIntent().getStringExtra("notes");
        String cur_tags  = this.getIntent().getStringExtra("tags");

        EditText editText_name = findViewById(R.id.edit_name);
        EditText editText_author = findViewById(R.id.edit_author);
        EditText editText_translator = findViewById(R.id.edit_translator);
        EditText editText_publisher = findViewById(R.id.edit_publisher);
        EditText editText_pubdate = findViewById(R.id.edit_pubdate);
        EditText editText_isbn = findViewById(R.id.edit_isbn);
        Spinner spinner_reading_status = findViewById(R.id.spinner_reading_status);
        Spinner spinner_shelf = findViewById(R.id.spinner_choose_shelf);
        EditText editText_notes = findViewById(R.id.edit_notes);
        EditText editText_tags = findViewById(R.id.edit_tags);

        if(null != cur_name)    // 当图书名字不为空时，即代表是在进行update操作，将原有的数据传回来进行修改
        {
            editText_name.setText(cur_name);
            editText_author.setText(cur_author);
            editText_translator.setText(cur_translator);
            editText_publisher.setText(cur_publisher);
            editText_pubdate.setText(cur_pubdate);
            editText_isbn.setText(cur_isbn);
//            spinner_reading_status.toString();
//            spinner_shelf.toString();
            editText_notes.setText(cur_notes);
            editText_tags.setText(cur_tags);
        }


        Button button_save = findViewById(R.id.btn_save);
        ImageButton imageButton_back = findViewById(R.id.btn_back_edit_book);

        button_save.setOnClickListener(view -> {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("name",editText_name.getText().toString());
            bundle.putString("author",editText_author.getText().toString() + " 著");
            bundle.putString("translator",editText_translator.getText().toString());
            bundle.putString("publisher",editText_publisher.getText().toString());
            bundle.putString("pubdate",editText_pubdate.getText().toString());
            bundle.putString("isbn",editText_isbn.getText().toString());
            bundle.putString("reading_status",spinner_reading_status.toString());
            bundle.putString("shelf",spinner_shelf.toString());
            bundle.putString("notes",editText_notes.getText().toString());
            bundle.putString("tags",editText_tags.getText().toString());
            bundle.putInt("position",position);

            intent.putExtras(bundle);
            setResult(Integer.parseInt("1"),intent);
            AddBookActivity.this.finish();
        });

        imageButton_back.setOnClickListener(view -> AddBookActivity.this.finish());
    }
}