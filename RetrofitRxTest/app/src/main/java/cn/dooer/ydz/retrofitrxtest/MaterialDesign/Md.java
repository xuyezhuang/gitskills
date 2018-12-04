package cn.dooer.ydz.retrofitrxtest.MaterialDesign;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.Random;

import cn.dooer.ydz.retrofitrxtest.MaterialDesign.adapter.FruitAdapter1;
import cn.dooer.ydz.retrofitrxtest.R;
import cn.dooer.ydz.retrofitrxtest.rvTest.Fruit;
import cn.dooer.ydz.retrofitrxtest.rvTest.Recycler;

public class Md extends AppCompatActivity {

    public static void start(Context context){
        Intent intent=new Intent(context,Md.class);
        context.startActivity(intent);
    }
    private SwipeRefreshLayout swipeRefreshLayout;
    private DrawerLayout drawerLayout;
    private FruitAdapter1 adapter1;
    private List<Fruit> list=new ArrayList<>();
    private Fruit[]fruits={
            new Fruit("Apple1",R.drawable.timg),new Fruit("Apple6",R.drawable.timg),
            new Fruit("Apple2",R.drawable.timg),new Fruit("Apple7",R.drawable.timg),
            new Fruit("Apple3",R.drawable.timg),new Fruit("Apple8",R.drawable.timg),
            new Fruit("Apple4",R.drawable.timg),new Fruit("Apple9",R.drawable.timg),
            new Fruit("Apple5",R.drawable.timg),new Fruit("Apple",R.drawable.timg)
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_md);

        android.support.v7.widget.Toolbar toolbar=(android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        //这里要全路劲，该库并不是sdk内置的库，因为低于21版本的不能用，不写全路劲系统会报错提示修改最新版本,或者import选择v7的库
        toolbar.setTitle("");
        setSupportActionBar(toolbar);//toolbar：第一步实例化控件  第二步setSupportActionBar(toolbar);
        drawerLayout=findViewById(R.id.drawer_layout);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);//讲home键显示出来并设置图标
//            actionBar.setHomeAsUpIndicator(R.drawable.timg);//默认是一个返回图标
        }
        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_call);//设置call为默认选中
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();//选中菜单中任何一项都会进入这个方法
                return true;
            }
        });

        FloatingActionButton floatingActionButton=findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(),"悬浮按钮",Toast.LENGTH_SHORT).show();
               //Snackbar：可交互按钮，点击弹出提示。
                Snackbar.make(view,"Data delted",Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //点击undo进入当前方法
                        System.out.println("悬浮按钮");
                        Toast.makeText(Md.this,"悬浮按钮",Toast.LENGTH_SHORT).show();
                    }
                }).show();//最后必须把交互按钮show出来
            }
        });

        initFruits();
        RecyclerView recyclerView=findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter1=new FruitAdapter1(list);
        recyclerView.setAdapter(adapter1);
        swipeRefreshLayout=findViewById(R.id.swipe_refre);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreFruits();
            }
        });

    }
    public void refreFruits(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {//返回主线程操作ui界面
                    @Override
                    public void run() {
                        initFruits();
                        adapter1.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);//表示刷新结束并隐藏进度条
                    }
                });
            }
        }).start();
    }

    public void initFruits(){
        list.clear();
        for (int i=0;i<50;i++){
            Random random=new Random();
            int index=random.nextInt(fruits.length);
            list.add(fruits[index]);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.backup:
                Toast.makeText(this,item.getTitle(),Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this,item.getTitle(),Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this,item.getTitle(),Toast.LENGTH_SHORT).show();
                break;
            case R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);//跟xml中的设置保持一致
                break;
                default:
                    break;
        }
        return true;
    }
}
