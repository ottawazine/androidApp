package oz.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class bbsPostListActivity extends AppCompatActivity {
    public ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bbs_post_list);


        lv = (ListView) findViewById(R.id.bbsListView);

        List<Map<String,?>> data = new ArrayList<Map<String,?>>();
        for (int i = 0; i < 15; i++) {
            Map<String, String> keyValuePair = new HashMap<String, String>();
            keyValuePair.put("Text", "测试数据" + i);
            data.add(keyValuePair);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, data,
                R.layout.custom_list,new String[] { "Text" },
                new int[] { R.id.textview1 });
        lv.setAdapter(simpleAdapter);
    }
}
