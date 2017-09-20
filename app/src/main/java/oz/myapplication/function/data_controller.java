package oz.myapplication.function;

/**
 * Created by xianchizou on 2017-09-14.
 */
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import oz.myapplication.NewsData;


public class data_controller {

    ArrayList<NewsData> data;

    private static final String at="data_controller";

    public data_controller(){
        data = new ArrayList<>();
    }


    public void getJsonObject(String link){

        try {

//            Log.i(data_controller.at,"ttttttttttttttttttttt");
            String data="";
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            data = bufferedReader.readLine();
//            Log.i("TAG", "-------------------dddd-------     "+data);

            JsonJX(data);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void JsonJX(String data){

        JSONObject jsObj;
        JSONArray jsonArray;

        try {

            jsObj = new JSONObject(data);
            jsonArray = jsObj.getJSONArray("posts");


//            Log.i("TAG", "------2--------"+jsonArray);
            for(int i=0; i<jsonArray.length();i++){
                JSONObject temp = jsonArray.getJSONObject(i);
                int id = temp.getInt("id");
                String title = temp.getString("title");
                String url = temp.getString("url");
                String date = temp.getString("date");
                String modified = temp.getString("modified");
                ArrayList<String> categories = new ArrayList<String>();
                JSONArray cateArray = temp.getJSONArray("categories");
                for(int j=0; j<cateArray.length(); j++){
                    categories.add(j,cateArray.getString(j));
                }
//                Log.i("TAG", "------xxxxxxxxxxxxx--------"+title);

                NewsData newsData = new NewsData(id,title,url,date,modified,categories);

//                Log.i("TAG", "---------yyyyyyyy--------"+newsData.getUrl());

                this.data.add(newsData);

//                Log.i("TAG", "--------dddddd-------:   "+this.data.size());

            }

        }catch (Exception e){
            e.printStackTrace();
            Log.i("TAG", "--------"+e.getMessage());
        }
    }

    public ArrayList<NewsData> getData(){
        return data;
    }

    public int getSize(){return data.size();}


}