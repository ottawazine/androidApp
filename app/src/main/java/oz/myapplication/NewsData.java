package oz.myapplication;


import java.util.ArrayList;

/**
 * Created by xianchizou on 2017-09-15.
 */

public class NewsData {

    int id;
    String title;
    String url;
    String date;
    String modified;
    ArrayList<String>  categories;

    public NewsData(int id, String title, String url, String date, String modified, ArrayList<String> categories){
        this.id=id;
        this.title=title;
        this.url = url;
        this.date = date;
        this.modified = modified;
        this.categories=categories;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getModified() {
        return modified;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

}
