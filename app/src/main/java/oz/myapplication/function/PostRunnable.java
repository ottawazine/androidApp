package oz.myapplication.function;

import android.app.Activity;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oz.myapplication.R;

/**
 * Created by UnknownID on 2017/9/20.
 */

public class PostRunnable implements Runnable {
    private volatile data_controller dc = new data_controller();
    private volatile List<Map<String,?>> data = new ArrayList<>();
    private volatile SimpleAdapter simpleAdapter;
    private volatile Activity act;
    private volatile ListView lv;
    public PostRunnable(Activity a, ListView lv){
        this.act = a;
        this.lv = lv;
    }

    @Override
    public void run(){

        try {
            dc.getJsonObject("http://ottawazine.com/?json=Ottawazine");

        } catch (Exception e) {
            e.printStackTrace();
        }

        for(int i=0; i< 20; i++){
            Map<String, String> keyValuePair = new HashMap<String, String>();
            //Log.i("TAG", "--------DC   Load-------:   "+dc.getData().size());
            keyValuePair.put("Text", dc.getData().get(i).getTitle());
            data.add(keyValuePair);
        }
        act.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                simpleAdapter = new SimpleAdapter(act, data,
                        R.layout.custom_list,new String[] { "Text" },
                        new int[] { R.id.textview1 });
                lv.setAdapter(simpleAdapter);}
                });
    }
}
