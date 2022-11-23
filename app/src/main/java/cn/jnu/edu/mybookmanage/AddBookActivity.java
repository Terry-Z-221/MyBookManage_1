package cn.jnu.edu.mybookmanage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        EditText editText_name = findViewById(R.id.edit_name);
        EditText editText_author = findViewById(R.id.edit_author);
        EditText editText_translator = findViewById(R.id.edit_translator);
        EditText editText_publisher = findViewById(R.id.edit_publisher);
        EditText editText_pubdate = findViewById(R.id.edit_pubdate);
        EditText editText_isbn = findViewById(R.id.edit_isbn);
        Spinner spinner = findViewById(R.id.spinner_choose_shelf);


        Button button_save = findViewById(R.id.btn_save);
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("name",editText_name.getText().toString());
                bundle.putString("author",editText_author.getText().toString() + " 著");
                bundle.putString("publisher",editText_publisher.getText().toString());
                bundle.putString("pubdate",editText_pubdate.getText().toString());

                intent.putExtras(bundle);
                setResult(Integer.parseInt("1"),intent);
                AddBookActivity.this.finish();
            }
        });
    }
}