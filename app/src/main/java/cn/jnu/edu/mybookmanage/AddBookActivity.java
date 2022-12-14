package cn.jnu.edu.mybookmanage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class AddBookActivity extends AppCompatActivity {

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        position = this.getIntent().getIntExtra("position",0);

        ImageView image_cover = findViewById(R.id.book_cover_image_view);
        EditText editText_name = findViewById(R.id.edit_name);
        EditText editText_author = findViewById(R.id.edit_author);
        EditText editText_translator = findViewById(R.id.edit_translator);
        EditText editText_publisher = findViewById(R.id.edit_publisher);
        EditText editText_pubdate = findViewById(R.id.edit_pubdate);
        EditText editText_isbn = findViewById(R.id.edit_isbn);
        Spinner spinner_reading_status = findViewById(R.id.spinner_reading_status);
        Spinner spinner_shelf = findViewById(R.id.spinner_choose_shelf);
        EditText editText_link = findViewById(R.id.edit_link);
        EditText editText_tags = findViewById(R.id.edit_tags);

        int cur_coverID = this.getIntent().getIntExtra("coverID",0);
        String cur_name = this.getIntent().getStringExtra("name");
        String cur_author = this.getIntent().getStringExtra("author");
        String cur_translator = this.getIntent().getStringExtra("translator");
        String cur_publisher = this.getIntent().getStringExtra("publisher");
        String cur_pubdate = this.getIntent().getStringExtra("pubdate");
        String cur_isbn = this.getIntent().getStringExtra("isbn");
        String cur_reading_status = this.getIntent().getStringExtra("reading_status");
        String cur_shelf = this.getIntent().getStringExtra("shelf");
        String cur_link = this.getIntent().getStringExtra("link");
        String cur_tags  = this.getIntent().getStringExtra("tags");

        if(null != cur_name)    // ???????????????????????????????????????????????????update????????????????????????????????????????????????
        {
            image_cover.setImageResource(cur_coverID);
            editText_name.setText(cur_name);
            editText_author.setText(cur_author);
            editText_translator.setText(cur_translator);
            editText_publisher.setText(cur_publisher);
            editText_pubdate.setText(cur_pubdate);
            editText_isbn.setText(cur_isbn);
            editText_link.setText(cur_link);
            editText_tags.setText(cur_tags);

            SpinnerAdapter spinner_reading_status_adapter = spinner_reading_status.getAdapter();
            int count = spinner_reading_status_adapter.getCount();
            for (int i = 0; i < count; i++) {
                if(cur_reading_status.equals(spinner_reading_status_adapter.getItem(i).toString())){
                    spinner_reading_status.setSelection(i,true);
                    break;
                }
            }

            SpinnerAdapter spinner_shelf_adapter = spinner_shelf.getAdapter();
            count = spinner_shelf_adapter.getCount();
            for (int i = 0; i < count; i++) {
                if(cur_shelf.equals(spinner_shelf_adapter.getItem(i).toString())){
                    spinner_shelf.setSelection(i,true);
                    break;
                }
            }
        }


        Button button_save = findViewById(R.id.btn_save);
        ImageButton imageButton_back = findViewById(R.id.btn_back_edit_book);

        button_save.setOnClickListener(view -> {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt("coverID",cur_coverID);
            bundle.putString("name",editText_name.getText().toString());
            bundle.putString("author",editText_author.getText().toString());
            bundle.putString("translator",editText_translator.getText().toString());
            bundle.putString("publisher",editText_publisher.getText().toString());
            bundle.putString("pubdate",editText_pubdate.getText().toString());
            bundle.putString("isbn",editText_isbn.getText().toString());
            bundle.putString("reading_status", spinner_reading_status.getSelectedItem().toString());
            bundle.putString("shelf",spinner_shelf.getSelectedItem().toString());
            bundle.putString("link",editText_link.getText().toString());
            bundle.putString("tags",editText_tags.getText().toString());
            bundle.putInt("position",position);

            intent.putExtras(bundle);
            setResult(Integer.parseInt("1"),intent);
            AddBookActivity.this.finish();
        });



        imageButton_back.setOnClickListener(view -> AddBookActivity.this.finish());
    }
}