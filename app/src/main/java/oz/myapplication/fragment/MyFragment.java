package oz.myapplication.fragment;

/**
 * Created by UnknownID on 2017/9/6.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oz.myapplication.R;
import oz.myapplication.bbsPostListActivity;
import oz.myapplication.function.PostRunnable;

public class MyFragment extends Fragment {
    private String name;

    private Handler handler=null;
    public ListView listView1;
    public ListView listView2;
    public ListView listView3;
    public ListView listView4;
    public ExpandableListView expandableListView1;
    public List<Map<String,?>> data;

    //--------------
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = this.getArguments();
        name = b.getString("NAME");

        handler=new Handler();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_jia_guo, null);
        if(name == "tab01"){
            view = inflater.inflate(R.layout.activity_jia_guo, null);
            listView1 = (ListView) view.findViewById(R.id.list_view_jia_guo);

            PostRunnable pR = new PostRunnable(this.getActivity(),listView1);
            new Thread(pR).start();


        }
        if(name == "tab02"){
            view = inflater.inflate(R.layout.activity_zhong_guo, null);
            listView2 = (ListView) view.findViewById(R.id.list_view_zhong_guo);;

            PostRunnable pR = new PostRunnable(this.getActivity(),listView2);
            new Thread(pR).start();

        }
        if(name == "tab03"){
            view = inflater.inflate(R.layout.activity_bi_du, null);
            listView3 = (ListView) view.findViewById(R.id.list_view_bi_du);

            PostRunnable pR = new PostRunnable(this.getActivity(),listView3);
            new Thread(pR).start();
        }
        if(name == "tab04"){
            view = inflater.inflate(R.layout.activity_chi_wan, null);
            listView4 = (ListView) view.findViewById(R.id.list_view_chi_wan);

            PostRunnable pR = new PostRunnable(this.getActivity(),listView4);
            new Thread(pR).start();
        }
        if(name == "tab05"){
            view = inflater.inflate(R.layout.activity_bbs, null);
            expandableListView1 = (ExpandableListView) view.findViewById(R.id.expandableListView);
            expandableListDetail = new HashMap<String, List<String>>();

            List<String> zonghe = new ArrayList<String>();
            zonghe.add("Big Circle");
            zonghe.add("神吐槽");


            List<String> shenghuo = new ArrayList<String>();
            shenghuo.add("找室友");
            shenghuo.add("求兼职");
            shenghuo.add("移民留学");
            shenghuo.add("逛黑市");
            shenghuo.add("车天下");
            shenghuo.add("原创区");
            shenghuo.add("寻美食");

            List<String> xueshu = new ArrayList<String>();
            xueshu.add("编程发烧区");
            xueshu.add("水课集中营/rate my professor");
            xueshu.add("学渣问/学霸答");

            expandableListDetail.put("渥村综合圈", zonghe);
            expandableListDetail.put("渥村生活圈", shenghuo);
            expandableListDetail.put("渥村学术圈", xueshu);

            expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());

            expandableListAdapter = new CustomExpandableListAdapter(getActivity(), expandableListTitle, expandableListDetail);

            expandableListView1.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView parent, View v,
                                            int groupPosition, int childPosition, long id) {
                    Intent intent = new Intent(getActivity(), bbsPostListActivity.class);
                    startActivity(intent);
                    return true;
                }
            });
            expandableListView1.setAdapter(expandableListAdapter);
        }



        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

//        TextView text1 = (TextView) view.findViewById(android.R.id.text1);
//
//        text1.setText(name);


    }


    //---------------CustomExpandableListAdapter----------------------------
    public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

        private Context context;
        private List<String> expandableListTitle;
        private HashMap<String, List<String>> expandableListDetail;

        public CustomExpandableListAdapter(Context context, List<String> expandableListTitle,
                                           HashMap<String, List<String>> expandableListDetail) {
            this.context = context;
            this.expandableListTitle = expandableListTitle;
            this.expandableListDetail = expandableListDetail;
        }

        @Override
        public Object getChild(int listPosition, int expandedListPosition) {
            return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                    .get(expandedListPosition);
        }

        @Override
        public long getChildId(int listPosition, int expandedListPosition) {
            return expandedListPosition;
        }

        @Override
        public View getChildView(int listPosition, final int expandedListPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {
            final String expandedListText = (String) getChild(listPosition, expandedListPosition);
            if (convertView == null) {
                LayoutInflater layoutInflater = (LayoutInflater) this.context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = layoutInflater.inflate(R.layout.expandlist_item, null);
            }
            ImageView expandedListImageView = (ImageView) convertView.findViewById(R.id.expandedListItem1);
            if(expandedListText == "Big Circle"){
                expandedListImageView.setImageResource(R.mipmap.bbs1);
            }

            if(expandedListText == "神吐槽"){
                expandedListImageView.setImageResource(R.mipmap.bbs2);
            }

            if(expandedListText == "找室友"){
                expandedListImageView.setImageResource(R.mipmap.bbs3);
            }

            if(expandedListText == "求兼职"){
                expandedListImageView.setImageResource(R.mipmap.bbs4);
            }

            if(expandedListText == "移民留学"){
                expandedListImageView.setImageResource(R.mipmap.ic_launcher);
            }

            if(expandedListText == "逛黑市"){
                expandedListImageView.setImageResource(R.mipmap.bbs5);
            }

            if(expandedListText == "车天下"){
                expandedListImageView.setImageResource(R.mipmap.bbs6);
            }

            if(expandedListText == "原创区"){
                expandedListImageView.setImageResource(R.mipmap.bbs7);
            }

            if(expandedListText == "寻美食"){
                expandedListImageView.setImageResource(R.mipmap.bbs8);
            }

            if(expandedListText == "编程发烧区"){
                expandedListImageView.setImageResource(R.mipmap.bbs9);
            }

            if(expandedListText == "水课集中营/rate my professor"){
                expandedListImageView.setImageResource(R.mipmap.bbs10);
            }

            if(expandedListText == "学渣问/学霸答"){
                expandedListImageView.setImageResource(R.mipmap.bbs11);
            }

            TextView expandedListTextView = (TextView) convertView
                    .findViewById(R.id.expandedListItem2);
            expandedListTextView.setText(expandedListText);
            return convertView;
        }

        @Override
        public int getChildrenCount(int listPosition) {
            return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                    .size();
        }

        @Override
        public Object getGroup(int listPosition) {
            return this.expandableListTitle.get(listPosition);
        }

        @Override
        public int getGroupCount() {
            return this.expandableListTitle.size();
        }

        @Override
        public long getGroupId(int listPosition) {
            return listPosition;
        }

        @Override
        public View getGroupView(int listPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            String listTitle = (String) getGroup(listPosition);
            if (convertView == null) {
                LayoutInflater layoutInflater = (LayoutInflater) this.context.
                        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = layoutInflater.inflate(R.layout.expandlist_group, null);
            }
            TextView listTitleTextView = (TextView) convertView
                    .findViewById(R.id.listTitle);
            listTitleTextView.setTypeface(null, Typeface.BOLD);
            listTitleTextView.setText(listTitle);
            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public boolean isChildSelectable(int listPosition, int expandedListPosition) {
            return true;
        }
    }

}
