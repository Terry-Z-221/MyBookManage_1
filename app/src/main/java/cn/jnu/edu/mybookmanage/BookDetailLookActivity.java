package cn.jnu.edu.mybookmanage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class BookDetailLookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail_look);

        ImageView image_look_cover = findViewById(R.id.Book_cover_look);
        TextView text_look_name = findViewById(R.id.Book_name_look);
        TextView text_look_author = findViewById(R.id.Book_author_look);
        TextView text_look_translator = findViewById(R.id.Book_translator_look);
        TextView text_look_publisher = findViewById(R.id.Book_publisher_look);
        TextView text_look_pubdate = findViewById(R.id.Book_pubdate_look);
        TextView text_look_isbn = findViewById(R.id.Book_isbn_look);
        TextView text_look_reading_status = findViewById(R.id.Book_reading_status_look);
        TextView text_look_shelf = findViewById(R.id.Book_shelf_look);
        TextView text_look_notes = findViewById(R.id.Book_notes_look);
        TextView text_look_tags = findViewById(R.id.Book_tags_look);

        int coverID = this.getIntent().getIntExtra("coverID",0);
        String name = this.getIntent().getStringExtra("name");
        String author = this.getIntent().getStringExtra("author");
        String translator = this.getIntent().getStringExtra("translator");
        String publisher = this.getIntent().getStringExtra("publisher");
        String pubdate = this.getIntent().getStringExtra("pubdate");
        String isbn = this.getIntent().getStringExtra("isbn");
        String reading_status = this.getIntent().getStringExtra("reading_status");
        String shelf = this.getIntent().getStringExtra("shelf");
        String notes = this.getIntent().getStringExtra("notes");
        String tags = this.getIntent().getStringExtra("tags");

        image_look_cover.setImageResource(coverID);
        text_look_name.setText(name);
        text_look_author.setText(author);
        text_look_translator.setText(translator);
        text_look_publisher.setText(publisher);
        text_look_pubdate.setText(pubdate);
        text_look_isbn.setText(isbn);
        text_look_reading_status.setText(reading_status);
        text_look_shelf.setText(shelf);
        text_look_notes.setText(notes);
        text_look_tags.setText(tags);

        ImageButton imageButton_look_back = findViewById(R.id.btn_back_look_book);
        imageButton_look_back.setOnClickListener(view -> BookDetailLookActivity.this.finish());

    }
}