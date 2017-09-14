package oz.myapplication.function;

/**
 * Created by UnknownID on 2017/9/7.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import oz.myapplication.R;

public class postAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader; //用来下载图片的类，后面有介绍

    public postAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.custom_list, null);

//        TextView title = (TextView)vi.findViewById(R.id.title); // 标题
//        TextView artist = (TextView)vi.findViewById(R.id.artist); // 歌手名
//        TextView duration = (TextView)vi.findViewById(R.id.duration); // 时长
//        ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // 缩略图

        HashMap<String, String> song = new HashMap<String, String>();
        song = data.get(position);

//        // 设置ListView的相关值
//        title.setText(song.get(JiaGuoActivity.KEY_TITLE));
//        artist.setText(song.get(JiaGuoActivity.KEY_ARTIST));
//        duration.setText(song.get(JiaGuoActivity.KEY_DURATION));
//        imageLoader.DisplayImage(song.get(JiaGuoActivity.KEY_THUMB_URL), thumb_image);
        return vi;
     }
}
