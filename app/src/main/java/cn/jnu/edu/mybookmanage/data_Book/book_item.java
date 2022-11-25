package cn.jnu.edu.mybookmanage.data_Book;

import android.widget.Spinner;

public class book_item {

    private String Name;
    private String Author;
    private String Translator;
    private String Publisher;
    private String Pubdate;
    private String ISBN;
    private String Reading_status;
    private String Shelf;
    private String Notes;
    private String Tags;
    private int resId;

    public book_item(String name, String author, String translator, String publisher, String pubdate, String isbn,
                     String read_status, String shelf, String notes, String tags, int resId) {
        Name = name;
        Author = author;
        Translator = translator;
        Publisher = publisher;
        Pubdate = pubdate;
        ISBN = isbn;
        Reading_status = read_status;
        Shelf = shelf;
        Notes = notes;
        Tags = tags;
        this.resId = resId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getTranslator() {return Translator;}

    public void setTranslator(String translator) {Translator = translator;}

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }

    public String getPubdate() {
        return Pubdate;
    }

    public void setPubdate(String pubdate) {
        Pubdate = pubdate;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getISBN() {return ISBN;}

    public void setISBN(String ISBN) {this.ISBN = ISBN;}

    public String getReading_status() {return Reading_status;}

    public void setReading_status(String reading_status) {Reading_status = reading_status;}

    public String getShelf() {return Shelf;}

    public void setShelf(String shelf) {Shelf = shelf;}

    public String getNotes() {return Notes;}

    public void setNotes(String notes) {Notes = notes;}

    public String getTags() {return Tags;}

    public void setTags(String tags) {Tags = tags;}
}
