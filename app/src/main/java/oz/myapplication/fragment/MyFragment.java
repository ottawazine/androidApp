package oz.myapplication.fragment;

/**
 * Created by UnknownID on 2017/9/6.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oz.myapplication.MainActivity;
import oz.myapplication.NewsData;
import oz.myapplication.R;
import oz.myapplication.function.data_controller;

public class MyFragment extends Fragment {

    private String name;

    public ListView listView1;
    public ListView listView2;
    public ListView listView3;
    public ListView listView4;
    public ListView listView5;

    private ArrayList<NewsData> testData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = this.getArguments();
        name = b.getString("NAME");




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.activity_jia_guo, null);
        if(name == "tab01"){
            view = inflater.inflate(R.layout.activity_jia_guo, null);
            listView1 = (ListView) view.findViewById(R.id.list_view_jia_guo);

            listView1.setAdapter(getAdapter());
        }
        if(name == "tab02"){
            view = inflater.inflate(R.layout.activity_zhong_guo, null);
            listView2 = (ListView) view.findViewById(R.id.list_view_zhong_guo);;

            listView2.setAdapter(getAdapter());

        }
        if(name == "tab03"){
            view = inflater.inflate(R.layout.activity_bi_du, null);
            listView3 = (ListView) view.findViewById(R.id.list_view_bi_du);

            listView3.setAdapter(getAdapter());
        }
        if(name == "tab04"){
            view = inflater.inflate(R.layout.activity_chi_wan, null);
            listView4 = (ListView) view.findViewById(R.id.list_view_chi_wan);

            listView4.setAdapter(getAdapter());
        }
        if(name == "tab05"){
            view = inflater.inflate(R.layout.activity_bbs, null);
            listView5 = (ListView) view.findViewById(R.id.list_view_bbs);

            listView5.setAdapter(getAdapter());
        }

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

//        TextView text1 = (TextView) view.findViewById(android.R.id.text1);
//
//        text1.setText(name);

    }

    public SimpleAdapter getAdapter(){


//        testData = new ArrayList<>();
//
//        new Thread(
//                new Runnable(){
//
//                    @Override
//                    public void run() {
//
//                        data_controller dc = new data_controller();
//                        try {
//
//                            dc.getJsonObject("http://ottawazine.com/?json=Ottawazine");
//                            testData = dc.getData();
//                            Log.i("TAG","ttttttttttttttttttttt   "+testData.size());
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }).start();
//
//
//        String data1 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
//        Log.i("TAG","ttttttttttttttttttttt222222   "+testData.size());


        List<Map<String,?>> data = new ArrayList<>();

        for(int i=0; i< 20; i++){
            Map<String, String> keyValuePair = new HashMap<String, String>();
            keyValuePair.put("Text", "");
            data.add(keyValuePair);
        }
//
        SimpleAdapter simpleAdapter = new SimpleAdapter(this.getActivity(), data,
                R.layout.custom_list,new String[] { "Text" },
                new int[] { R.id.textview1 });

        return simpleAdapter;
    }
}
