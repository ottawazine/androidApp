package oz.myapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import oz.myapplication.fragment.MyFragment;
import oz.myapplication.function.data_controller;
import oz.myapplication.view.SlidingTabLayout;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    //--------------------tab相关-------------------------------
    ArrayList<Fragment> tablist;
    private int[] imageResId = {
            R.mipmap.icon_1,
            R.mipmap.icon_2,
            R.mipmap.icon_3,
            R.mipmap.icon_4,
            R.mipmap.icon_5
    };


    //-----------------------------------------------------------------------------------------
    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //generated by sample
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //-----------------tab实例相关-------------------------

        //Fragment
        tablist = new ArrayList<Fragment>();
        tablist.add(setFragmentData("tab01"));
        tablist.add(setFragmentData("tab02"));
        tablist.add(setFragmentData("tab03"));
        tablist.add(setFragmentData("tab04"));
        tablist.add(setFragmentData("tab05"));

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        MyAdapter adapter = new MyAdapter(this.getSupportFragmentManager());

        viewPager.setAdapter(adapter);

        // 设置tab栏
        SlidingTabLayout mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding);
        mSlidingTabLayout.setCustomTabView(R.layout.custom_tab, 0);
        mSlidingTabLayout.setSelectedIndicatorColors(Color.WHITE);
        mSlidingTabLayout.setDividerColors(Color.WHITE);
        mSlidingTabLayout.setDistributeEvenly(true);
        mSlidingTabLayout.setTabStripWidth(150);

        mSlidingTabLayout.setViewPager(viewPager);

        data_controller dc = new data_controller();

        new Thread(
                new Runnable(){

                    @Override
                    public void run() {
                        data_controller dc = new data_controller();

                        try {
                            dc.getJsonObject("http://ottawazine.com/?json=Ottawazine");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

    }

    // 设置要传递给Fragment的参数
    private Fragment setFragmentData(String name) {
        Fragment f = new MyFragment();

        Bundle b = new Bundle();
        b.putString("NAME", name);
        f.setArguments(b);
        return f;
    }

    private class MyAdapter extends FragmentPagerAdapter {


        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return tablist.get(position);
        }

        @Override
        public int getCount() {
            return tablist.size();
        }

        // tab标题
        @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
        @Override
        public CharSequence getPageTitle(int position) {
            Drawable image = getResources().getDrawable(imageResId[position]);
            image= zoomDrawable(image, 350,350);
            image.setBounds(40, 10, image.getIntrinsicWidth() + 40, image.getIntrinsicHeight() + 10);
            SpannableString sb = new SpannableString(" ");
            ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
            sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return sb;
        }

        public  Bitmap drawableToBitmap(Drawable drawable) {
            // 取 drawable 的长宽
            int w = drawable.getIntrinsicWidth();
            int h = drawable.getIntrinsicHeight();

            // 取 drawable 的颜色格式
            Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                    : Bitmap.Config.RGB_565;
            // 建立对应 bitmap
            Bitmap bitmap = Bitmap.createBitmap(w, h, config);
            // 建立对应 bitmap 的画布
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, w, h);
            // 把 drawable 内容画到画布中
            drawable.draw(canvas);
            return bitmap;
        }

        public  Drawable zoomDrawable(Drawable drawable, int w, int h) {
            int width = drawable.getIntrinsicWidth();
            int height = drawable.getIntrinsicHeight();
            // drawable转换成bitmap
            Bitmap oldbmp = drawableToBitmap(drawable);
            // 创建操作图片用的Matrix对象
            Matrix matrix = new Matrix();
            // 计算缩放比例
            float sx = ((float) w / width);
            float sy = ((float) h / height);
            // 设置缩放比例
            matrix.postScale(sx, sy);
            // 建立新的bitmap，其内容是对原bitmap的缩放后的图
            Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height,
                    matrix, true);
            return new BitmapDrawable(newbmp);
        }
    }

    //-------------------------------------------------------------------

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_login) {
            // Handle the camera action
        } else if (id == R.id.nav_logout) {

        } else if (id == R.id.nav_about) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
