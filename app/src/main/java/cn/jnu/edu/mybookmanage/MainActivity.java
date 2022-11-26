package cn.jnu.edu.mybookmanage;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cn.jnu.edu.mybookmanage.data_Book.book_item;

public class MainActivity extends AppCompatActivity {


    public static final int MENU_ID_ADD = 1;
    public static final int MENU_ID_UPDATE = 2;
    public static final int MENU_ID_DELETE = 3;
    private MainRecycleViewAdapter mainRecycleViewAdapter;
    private ArrayList<book_item> book_items;

    private final ActivityResultLauncher<Intent> add_data_launcher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
                    ,result -> {
                if(null != result){
                    Intent intent = result.getData();
                    if(result.getResultCode() == 1)
                    {
                        assert intent != null;
                        Bundle bundle = intent.getExtras();
                        int position = bundle.getInt("position");
                        int coverID = bundle.getInt("coverID");
                        String name = bundle.getString("name");
                        String author = bundle.getString("author");
                        String translator = bundle.getString("translator");
                        String publisher = bundle.getString("publisher");
                        String pubdate = bundle.getString("pubdate");
                        String isbn = bundle.getString("isbn");
                        String reading_status = bundle.getString("reading_status");
                        String shelf = bundle.getString("shelf");
                        String notes = bundle.getString("notes");
                        String tags = bundle.getString("tags");
                        book_items.add(0, new book_item(name, author, translator, publisher, pubdate, isbn,
                                reading_status, shelf, notes, tags, R.drawable.ic_book));
                        mainRecycleViewAdapter.notifyItemInserted(0);
                    }
                }
                    });

    private final ActivityResultLauncher<Intent> update_data_launcher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
                    ,result -> {
                        if(null != result){
                            Intent intent = result.getData();
                            if(result.getResultCode() == 1)
                            {
                                assert intent != null;
                                Bundle bundle = intent.getExtras();
                                int posiotion = bundle.getInt("position");
                                String name = bundle.getString("name");
                                String author = bundle.getString("author");
                                String translator = bundle.getString("translator");
                                String publisher = bundle.getString("publisher");
                                String pubdate = bundle.getString("pubdate");
                                String isbn = bundle.getString("isbn");
//                                String reading_status = bundle.getString("reading_status");
//                                String shelf = bundle.getString("shelf");
                                String notes = bundle.getString("notes");
                                String tags = bundle.getString("tags");
                                book_items.get(posiotion).setName(name);
                                book_items.get(posiotion).setAuthor(author);
                                book_items.get(posiotion).setTranslator(translator);
                                book_items.get(posiotion).setPublisher(publisher);
                                book_items.get(posiotion).setPubdate(pubdate);
                                book_items.get(posiotion).setISBN(isbn);
//                                book_items.get(posiotion).setReading_status(reading_status);
//                                book_items.get(posiotion).setShelf(shelf);
                                book_items.get(posiotion).setNotes(notes);
                                book_items.get(posiotion).setTags(tags);
                                mainRecycleViewAdapter.notifyItemChanged(posiotion);
                            }
                        }
                    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_add = findViewById(R.id.btn_add); // 主页面添加书籍信息按钮

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

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case MENU_ID_ADD:
                Intent intent_add = new Intent(this,AddBookActivity.class);
                add_data_launcher.launch(intent_add);
                break;
            case MENU_ID_UPDATE:
                Intent intent_update = new Intent(this,AddBookActivity.class);
//                intent_update.putExtra("coverID",book_items.get(item.getItemId()).getResId());
                intent_update.putExtra("name",book_items.get(item.getOrder()).getName());
                intent_update.putExtra("author",book_items.get(item.getOrder()).getAuthor());
                intent_update.putExtra("translator",book_items.get(item.getOrder()).getTranslator());
                intent_update.putExtra("publisher",book_items.get(item.getOrder()).getPublisher());
                intent_update.putExtra("pubdate",book_items.get(item.getOrder()).getPubdate());
                intent_update.putExtra("isbn",book_items.get(item.getOrder()).getISBN());
                intent_update.putExtra("reading_status",book_items.get(item.getOrder()).getReading_status().toString());
                intent_update.putExtra("shelf",book_items.get(item.getOrder()).getShelf().toString());
                intent_update.putExtra("notes",book_items.get(item.getOrder()).getNotes());
                intent_update.putExtra("tags",book_items.get(item.getOrder()).getTags());
                update_data_launcher.launch(intent_update);
                break;
            case MENU_ID_DELETE:
                AlertDialog alertDialog = new AlertDialog.Builder(this)
                        .setTitle(R.string.MENU_Confirmation).setMessage(R.string.MENU_delete_to_sure)
                        .setNegativeButton(R.string.YES, (dialogInterface, i) -> {
                            book_items.remove(item.getOrder());
                            mainRecycleViewAdapter.notifyItemRemoved(item.getOrder());
                        }).setPositiveButton(R.string.NO, (dialogInterface, i) -> {
                        }).create();
                alertDialog.show();
                break;
        }

        return super.onContextItemSelected(item);
    }

    public class MainRecycleViewAdapter extends RecyclerView.Adapter<MainRecycleViewAdapter.ViewHolder> {

        private final ArrayList<book_item> localDataSet;

        /**
         * Provide a reference to the type of views that you are using
         * (custom ViewHolder).
         */
        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
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

                view.setOnCreateContextMenuListener(this); // 长按item出现菜单

                view.setOnClickListener(view1 -> {
                    Intent intent_look = new Intent(MainActivity.this,BookDetailLookActivity.class);
                    intent_look.putExtra("coverID",book_items.get(getAdapterPosition()).getResId());
                    intent_look.putExtra("name",book_items.get(getAdapterPosition()).getName());
                    intent_look.putExtra("author",book_items.get(getAdapterPosition()).getAuthor());
                    intent_look.putExtra("translator",book_items.get(getAdapterPosition()).getTranslator());
                    intent_look.putExtra("publisher",book_items.get(getAdapterPosition()).getPublisher());
                    intent_look.putExtra("pubdate",book_items.get(getAdapterPosition()).getPubdate());
                    intent_look.putExtra("isbn",book_items.get(getAdapterPosition()).getISBN());
                    intent_look.putExtra("reading_status",book_items.get(getAdapterPosition()).getReading_status());
                    intent_look.putExtra("shelf",book_items.get(getAdapterPosition()).getShelf());
                    intent_look.putExtra("notes",book_items.get(getAdapterPosition()).getNotes());
                    intent_look.putExtra("tags",book_items.get(getAdapterPosition()).getTags());
                    startActivity(intent_look);
                });

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

            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                contextMenu.add(0,MENU_ID_ADD,getAdapterPosition(),"Add new Book details");
                contextMenu.add(0,MENU_ID_UPDATE,getAdapterPosition(),"Update this Book's details");
                contextMenu.add(0,MENU_ID_DELETE,getAdapterPosition(),"Delete this Book");
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