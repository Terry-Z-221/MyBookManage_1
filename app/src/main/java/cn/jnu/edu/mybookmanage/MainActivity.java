package cn.jnu.edu.mybookmanage;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import cn.jnu.edu.mybookmanage.data_Book.Data_Saver;
import cn.jnu.edu.mybookmanage.data_Book.book_item;

public class MainActivity extends AppCompatActivity {


    public static final int MENU_ID_ADD = 1;
    public static final int MENU_ID_UPDATE = 2;
    public static final int MENU_ID_DELETE = 3;
    private MainRecycleViewAdapter mainRecycleViewAdapter;
    private ArrayList<book_item> book_items;
    private DrawerLayout drawerLayout_main;

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
                        String link = bundle.getString("link");
                        String tags = bundle.getString("tags");
                        book_items.add(position, new book_item(name, author, translator, publisher, pubdate, isbn,
                                reading_status, shelf, link, tags, R.drawable.ic_book));
                        if(tags.equals("save")) new Data_Saver().Save(this,book_items);
                        mainRecycleViewAdapter.notifyItemInserted(position);
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
                                int position = bundle.getInt("position");
                                String name = bundle.getString("name");
                                String author = bundle.getString("author");
                                String translator = bundle.getString("translator");
                                String publisher = bundle.getString("publisher");
                                String pubdate = bundle.getString("pubdate");
                                String isbn = bundle.getString("isbn");
                                String reading_status = bundle.getString("reading_status");
                                String shelf = bundle.getString("shelf");
                                String link = bundle.getString("link");
                                String tags = bundle.getString("tags");
                                book_items.get(position).setName(name);
                                book_items.get(position).setAuthor(author);
                                book_items.get(position).setTranslator(translator);
                                book_items.get(position).setPublisher(publisher);
                                book_items.get(position).setPubdate(pubdate);
                                book_items.get(position).setISBN(isbn);
                                book_items.get(position).setLink(link);
                                book_items.get(position).setTags(tags);
                                book_items.get(position).setShelf(shelf);
                                book_items.get(position).setReading_status(reading_status);

                                new Data_Saver().Save(this,book_items);
                                mainRecycleViewAdapter.notifyItemChanged(position);
                            }
                        }
                    });



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText search = findViewById(R.id.edit_search);
        drawerLayout_main = findViewById(R.id.drawer_layout);

        ImageButton btn_menu = findViewById(R.id.btn_menu_main);
        btn_menu.setOnClickListener(view -> drawerLayout_main.openDrawer(GravityCompat.START));

        Button btn_add = findViewById(R.id.btn_add); // 主页面添加书籍信息按钮
        btn_add.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,AddBookActivity.class); // 跳转页面至添加信息页面
            add_data_launcher.launch(intent);
        });

//        final NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
//        navigationView.getMenu().getItem(0).setChecked(true);
//        navigationView.setNavigationItemSelectedListener(item -> {
//            switch (item.toString())
//            {
//                case "Books":
//                    Intent intent = new Intent(MainActivity.this,MainActivity.class);
//                    startActivity(intent);
//                    break;
//                case "To be read":
//
//            }
//            item.setCheckable(false);
//            item.setChecked(false);
//            drawerLayout_main.close();
//            return false;
//        });

        RecyclerView recyclerView_item = findViewById(R.id.recycleview_item_main);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView_item.setLayoutManager(linearLayoutManager);

        Data_Saver data_saver = new Data_Saver();
        book_items = data_saver.Load(this);


        if(book_items.size() == 0)
        {
            book_items.add(0,new book_item("白夜行","东野圭吾","刘姿君","南海出版公司","2008.09",
                    "9787544242516","Reading","Novel","https://book.douban.com/subject/10554308/","日系推理",R.drawable.ic_book_1));

            book_items.add(1,new book_item("计算机网络","谢希仁","无","电子工业出版社","2021.06",
                    "9787121411748","To be read","Textbook","https://book.douban.com/subject/35498120/","计算机教材",R.drawable.ic_book_2));

            book_items.add(2,new book_item("海边的卡夫卡","村上春树","林少华","上海译文出版社","2003.4",
                    "9787532734191","Finish reading","Novel","https://book.douban.com/subject/1059419/","日本小说",R.drawable.ic_book_1));
        }

        mainRecycleViewAdapter = new MainRecycleViewAdapter(book_items);
        recyclerView_item.setAdapter(mainRecycleViewAdapter);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mainRecycleViewAdapter.getFilter().filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case MENU_ID_ADD:
                Intent intent_add = new Intent(this,AddBookActivity.class);
                intent_add.putExtra("position",item.getOrder());
                add_data_launcher.launch(intent_add);
                break;
            case MENU_ID_UPDATE:
                Intent intent_update = new Intent(this,AddBookActivity.class);
//                intent_update.putExtra("coverID",book_items.get(item.getItemId()).getResId());
                intent_update.putExtra("position",item.getOrder());
                intent_update.putExtra("name",book_items.get(item.getOrder()).getName());
                intent_update.putExtra("author",book_items.get(item.getOrder()).getAuthor());
                intent_update.putExtra("translator",book_items.get(item.getOrder()).getTranslator());
                intent_update.putExtra("publisher",book_items.get(item.getOrder()).getPublisher());
                intent_update.putExtra("pubdate",book_items.get(item.getOrder()).getPubdate());
                intent_update.putExtra("isbn",book_items.get(item.getOrder()).getISBN());
                intent_update.putExtra("reading_status",book_items.get(item.getOrder()).getReading_status());
                intent_update.putExtra("shelf",book_items.get(item.getOrder()).getShelf());
                intent_update.putExtra("link",book_items.get(item.getOrder()).getLink());
                intent_update.putExtra("tags",book_items.get(item.getOrder()).getTags());
                update_data_launcher.launch(intent_update);
                break;
            case MENU_ID_DELETE:
                AlertDialog alertDialog = new AlertDialog.Builder(this)
                        .setTitle(R.string.MENU_Confirmation).setMessage(R.string.MENU_delete_to_sure)
                        .setNegativeButton(R.string.YES, (dialogInterface, i) -> {
                            book_items.remove(item.getOrder());
                            new Data_Saver().Save(MainActivity.this,book_items);
                            mainRecycleViewAdapter.notifyItemRemoved(item.getOrder());
                        }).setPositiveButton(R.string.NO, (dialogInterface, i) -> {
                        }).create();
                alertDialog.show();
                break;
        }

        return super.onContextItemSelected(item);
    }

    public class MainRecycleViewAdapter extends RecyclerView.Adapter<MainRecycleViewAdapter.ViewHolder> implements Filterable {

        private final ArrayList<book_item> localDataSet;
        private ArrayList<book_item> filterDataSet = new ArrayList<>();
        private ArrayList<book_item> OriDataSet;

//        Spinner shelf_main = findViewById(R.id.spinner_main_shelf);

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
                    intent_look.putExtra("coverID",filterDataSet.get(getAdapterPosition()).getResId());
                    intent_look.putExtra("name",filterDataSet.get(getAdapterPosition()).getName());
                    intent_look.putExtra("author",filterDataSet.get(getAdapterPosition()).getAuthor());
                    intent_look.putExtra("translator",filterDataSet.get(getAdapterPosition()).getTranslator());
                    intent_look.putExtra("publisher",filterDataSet.get(getAdapterPosition()).getPublisher());
                    intent_look.putExtra("pubdate",filterDataSet.get(getAdapterPosition()).getPubdate());
                    intent_look.putExtra("isbn",filterDataSet.get(getAdapterPosition()).getISBN());
                    intent_look.putExtra("reading_status",filterDataSet.get(getAdapterPosition()).getReading_status());
                    intent_look.putExtra("shelf",filterDataSet.get(getAdapterPosition()).getShelf());
                    intent_look.putExtra("link",filterDataSet.get(getAdapterPosition()).getLink());
                    intent_look.putExtra("tags",filterDataSet.get(getAdapterPosition()).getTags());
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
        @SuppressLint("NotifyDataSetChanged")
        public MainRecycleViewAdapter(ArrayList<book_item> dataSet) {

            notifyDataSetChanged();
            localDataSet = dataSet;
            if(filterDataSet.size() == 0) filterDataSet = localDataSet;
            Spinner shelf = findViewById(R.id.spinner_main_shelf);
            shelf.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String shelf_cur = shelf.getSelectedItem().toString();
                    if(shelf_cur.equals("All")) filterDataSet = localDataSet;
                    else{
                        ArrayList<book_item>localDataList = new ArrayList<>();
                        for (int j = 0; j < localDataSet.size(); j++) {
                            if(shelf_cur.equals(localDataSet.get(j).getShelf()))
                                localDataList.add(localDataSet.get(j));
                        }
                        filterDataSet = localDataList;
                    }
                    notifyDataSetChanged();
                    OriDataSet = filterDataSet;
                }


                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            final NavigationView navigationView = findViewById(R.id.nav_view);

            navigationView.getMenu().getItem(0).setChecked(true);
            navigationView.setNavigationItemSelectedListener(item -> {

                switch (item.toString()) {
                    case "Books":
                        break;

                    case"豆瓣读书":
                        Intent intent_1 = new Intent(MainActivity.this,MoreActivity.class);
                        intent_1.putExtra("url","https://book.douban.com/");
                        startActivity(intent_1);
                        break;

                    case"读书网":
                        Intent intent_2 = new Intent(MainActivity.this,MoreActivity.class);
                        intent_2.putExtra("url","https://www.dushu.com/");
                        startActivity(intent_2);
                        break;

                    case "百度":
                        Intent intent_3 = new Intent(MainActivity.this,MoreActivity.class);
                        intent_3.putExtra("url","https://www.baidu.com/");
                        startActivity(intent_3);
                        break;

                    case"About us":
                        Intent intent_4 = new Intent(MainActivity.this,AboutActivity.class);
                        startActivity(intent_4);
                }
                drawerLayout_main.close();
                return false;
            });
//            filterDataSet = localDataSet;

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
        public void onBindViewHolder(ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {

            // Get element from your dataset at this position and replace the
            // contents of the view with that element
            viewHolder.getTextView_name().setText(filterDataSet.get(position).getName());
            viewHolder.getTextView_author().setText(filterDataSet.get(position).getAuthor());
            viewHolder.getTextView_publisher().setText(filterDataSet.get(position).getPublisher());
            viewHolder.getTextView_pubdate().setText(filterDataSet.get(position).getPubdate());
            viewHolder.getImageView().setImageResource(filterDataSet.get(position).getResId());

        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return filterDataSet.size();
        }

        @Override
        public Filter getFilter() {
            return new Filter() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                protected FilterResults performFiltering(CharSequence charSequence) {

                    String name_cur = charSequence.toString();
                    if(name_cur.isEmpty())
                    {
                        filterDataSet = OriDataSet;
                    }
                    else{
                        ArrayList<book_item> searchDataList = new ArrayList<>();

                        for (int i = 0; i < OriDataSet.size(); i++) {
                            if(OriDataSet.get(i).getName().contains(name_cur))
                                searchDataList.add(OriDataSet.get(i));
                        }
                        filterDataSet = searchDataList;
                    }


                    FilterResults searchResults = new FilterResults();
                    searchResults.values = filterDataSet;
                    return searchResults;
                }


                @SuppressLint("NotifyDataSetChanged")
                @Override
                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                    filterDataSet = (ArrayList<book_item>) filterResults.values;
                    notifyDataSetChanged();
                }
            };
        }


    }



}