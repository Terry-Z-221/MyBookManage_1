package cn.jnu.edu.mybookmanage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookDetailFragment extends Fragment {

    public BookDetailFragment() {
        // Required empty public constructor
    }


    public static BookDetailFragment newInstance() {
        BookDetailFragment fragment = new BookDetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_book_detail, container, false);

        ImageView image_look_cover = rootview.findViewById(R.id.Book_cover_look);
        TextView text_look_name = rootview.findViewById(R.id.Book_name_look);
        TextView text_look_author = rootview.findViewById(R.id.Book_author_look);
        TextView text_look_translator = rootview.findViewById(R.id.Book_translator_look);
        TextView text_look_publisher = rootview.findViewById(R.id.Book_publisher_look);
        TextView text_look_pubdate = rootview.findViewById(R.id.Book_pubdate_look);
        TextView text_look_isbn = rootview.findViewById(R.id.Book_isbn_look);
        TextView text_look_reading_status = rootview.findViewById(R.id.Book_reading_status_look);
        TextView text_look_shelf = rootview.findViewById(R.id.Book_shelf_look);
        TextView text_look_link = rootview.findViewById(R.id.Book_link_look);
        TextView text_look_tags = rootview.findViewById(R.id.Book_tags_look);

        image_look_cover.setImageResource(getActivity().getIntent().getIntExtra("coverID",0));
        text_look_name.setText(getActivity().getIntent().getStringExtra("name"));
        text_look_author.setText(getActivity().getIntent().getStringExtra("author"));
        text_look_translator.setText(getActivity().getIntent().getStringExtra("translator"));
        text_look_publisher.setText(getActivity().getIntent().getStringExtra("publisher"));
        text_look_pubdate.setText(getActivity().getIntent().getStringExtra("pubdate"));
        text_look_isbn.setText(getActivity().getIntent().getStringExtra("isbn"));
        text_look_reading_status.setText(getActivity().getIntent().getStringExtra("reading_status"));
        text_look_shelf.setText(getActivity().getIntent().getStringExtra("shelf"));
        text_look_link.setText(getActivity().getIntent().getStringExtra("link"));
        text_look_tags.setText(getActivity().getIntent().getStringExtra("tags"));

        return rootview;
    }
}
