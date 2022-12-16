package cn.jnu.edu.mybookmanage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentOnAttachListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class BookDetailLookActivity extends AppCompatActivity {

    public class BookDetailFragmentAdapter extends FragmentStateAdapter {

        public BookDetailFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position)
            {
                case 0:return BookDetailFragment.newInstance();
                case 1:return DoubanFragment.newInstance();
            }
            return BookDetailFragment.newInstance();
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail_look);

        ViewPager2 viewPager2Book_detail = findViewById(R.id.viewpager2_bookdetail);
        viewPager2Book_detail.setAdapter(new BookDetailFragmentAdapter(getSupportFragmentManager(),getLifecycle()));

        TabLayout tabLayout_Book_detail = findViewById(R.id.tablayout_bookdetail);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout_Book_detail, viewPager2Book_detail, (tab, position) -> {
            if(position == 0) tab.setText(R.string.图书信息);
            else tab.setText(R.string.豆瓣相关信息);
        });
        tabLayoutMediator.attach();

        ImageButton imageButton_look_back = findViewById(R.id.btn_back_look_book);
        imageButton_look_back.setOnClickListener(view -> BookDetailLookActivity.this.finish());

        /*
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

        Bundle bundle = new Bundle();

        bundle.putInt("coverID",coverID);
        bundle.putString("name",name);
        bundle.putString("author",author);
        bundle.putString("translator",translator);
        bundle.putString("publisher",publisher);
        bundle.putString("pubdate",pubdate);
        bundle.putString("isbn",isbn);
        bundle.putString("reading_status", reading_status);
        bundle.putString("shelf",shelf);
        bundle.putString("notes",notes);
        bundle.putString("tags",tags);
*/

    }
}