package cn.jnu.edu.mybookmanage;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cn.jnu.edu.mybookmanage.data_Book.book_item;

public class MainActivity extends AppCompatActivity {


    private MainRecycleViewAdapter mainRecycleViewAdapter;
    private ArrayList<book_item> book_items;

    private ActivityResultLauncher<Intent> add_data_launcher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
                    ,result -> {
                if(null != result){
                    Intent intent = result.getData();
                    if(result.getResultCode() == 1)
                    {
                        Bundle bundle = intent.getExtras();
                        String name = bundle.getString("name");
                        String author = bundle.getString("author");
                        String publisher = bundle.getString("publisher");
                        String pubdate = bundle.getString("pubdate");
                        book_items.add(0,new book_item(name,author,publisher,pubdate,R.drawable.ic_baseline_booknotes_24));
                        mainRecycleViewAdapter.notifyItemInserted(0);
                    }
                }
                    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_add = (Button)findViewById(R.id.btn_add); // 主页面添加书籍信息按钮

        btn_add.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,AddBookActivity.class); // 跳转页面至添加信息页面
            add_data_launcher.launch(intent);
        });

        RecyclerView recyclerView_item = findViewById(R.id.recycleview_item_main);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView_item.setLayoutManager(linearLayoutManager);

        book_items = new ArrayList<>();

        mainRecycleViewAdapter = new MainRecycleViewAdapter(book_items);
        recyclerView_item.setAdapter(mainRecycleViewAdapter);

    }

    public class MainRecycleViewAdapter extends RecyclerView.Adapter<MainRecycleViewAdapter.ViewHolder> {

        private ArrayList<book_item> localDataSet;

        /**
         * Provide a reference to the type of views that you are using
         * (custom ViewHolder).
         */
        public class ViewHolder extends RecyclerView.ViewHolder {
            private final TextView textView_name;
            private final TextView textView_author;
            private final TextView textView_publisher;
            private final TextView textView_pubdate;
            private final ImageView imageView;

            public ViewHolder(View view) {
                super(view);
                // Define click listener for the ViewHolder's View

                textView_name =  view.findViewById(R.id.text_view_item_name);
                textView_author = view.findViewById(R.id.text_view_item_author);
                textView_publisher = view.findViewById(R.id.text_view_item_publisher);
                textView_pubdate = view.findViewById(R.id.text_view_item_pubdate);
                imageView = view.findViewById(R.id.image_view_item);

            }


            public TextView getTextView_name() {
                return textView_name;
            }

            public ImageView getImageView() {
                return imageView;
            }

            public TextView getTextView_author() {
                return textView_author;
            }

            public TextView getTextView_publisher() {
                return textView_publisher;
            }

            public TextView getTextView_pubdate() {
                return textView_pubdate;
            }
        }

        /**
         * Initialize the dataset of the Adapter.
         *
         * @param dataSet String[] containing the data to populate views to be used
         * by RecyclerView.
         */
        public MainRecycleViewAdapter(ArrayList<book_item> dataSet) {
            localDataSet = dataSet;
        }

        // Create new views (invoked by the layout manager)
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            // Create a new view, which defines the UI of the list item
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_book_main, viewGroup, false);

            return new ViewHolder(view);
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder viewHolder, final int position) {

            // Get element from your dataset at this position and replace the
            // contents of the view with that element
            viewHolder.getTextView_name().setText(localDataSet.get(position).getName());
            viewHolder.getTextView_author().setText(localDataSet.get(position).getAuthor());
            viewHolder.getTextView_publisher().setText(localDataSet.get(position).getPublisher());
            viewHolder.getTextView_pubdate().setText(localDataSet.get(position).getPubdate());
            viewHolder.getImageView().setImageResource(localDataSet.get(position).getResId());
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return localDataSet.size();
        }
    }

}