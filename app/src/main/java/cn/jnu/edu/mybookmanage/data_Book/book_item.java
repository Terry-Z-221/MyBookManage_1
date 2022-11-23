package cn.jnu.edu.mybookmanage.data_Book;

public class book_item {
    public book_item(String name, String author, String publisher, String pubdate, int resId) {
        Name = name;
        Author = author;
        Publisher = publisher;
        Pubdate = pubdate;
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

    private String Name;
    private String Author;
    private String Publisher;
    private String Pubdate;
    private int resId;
}
