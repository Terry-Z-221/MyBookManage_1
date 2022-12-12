package cn.jnu.edu.mybookmanage.data_Book;

import android.content.Context;

import androidx.annotation.NonNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Data_Saver {
    public void Save(Context context, ArrayList<book_item> data){
        try {
            FileOutputStream DateStream = context.openFileOutput("myDate.dat",Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(DateStream);
            objectOutputStream.writeObject(data);
            objectOutputStream.close();
            DateStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NonNull
    public ArrayList<book_item> Load(Context context){
        ArrayList<book_item> data = new ArrayList<>();
        try {
            FileInputStream fileInputStream = context.openFileInput("myDate.dat");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            data = (ArrayList<book_item>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
