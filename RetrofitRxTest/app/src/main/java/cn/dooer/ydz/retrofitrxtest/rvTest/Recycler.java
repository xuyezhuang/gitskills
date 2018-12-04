package cn.dooer.ydz.retrofitrxtest.rvTest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.dooer.ydz.retrofitrxtest.R;

public class Recycler extends AppCompatActivity {
    public static void start(Context context){
        Intent intent=new Intent(context,Recycler.class);
        context.startActivity(intent);
    }


    private List<Fruit> mfruitList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        initFruit();//初始化数据
        RecyclerView recyclerView=findViewById(R.id.recycler_view);//第一步，实例化rv控件
//        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);//默认垂直
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);//设置水平
//        StaggeredGridLayoutManager linearLayoutManager=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);//瀑布流
        GridLayoutManager linearLayoutManager=new GridLayoutManager(this,2);


        recyclerView.setLayoutManager(linearLayoutManager);//第二步设置布局属性
        FruitAdapter adapter=new FruitAdapter(mfruitList);
        recyclerView.setAdapter(adapter);//第三步 设置adapter
    }



    public void initFruit(){
        for (int i=0;i<2;i++){
            Fruit apple=new Fruit(getRandomLengthName("apple"),R.drawable.fruit);
            mfruitList.add(apple);
            Fruit banana=new Fruit(getRandomLengthName("banana"),R.drawable.fruit);
            mfruitList.add(banana);
            Fruit orange=new Fruit(getRandomLengthName("orange"),R.drawable.fruit);
            mfruitList.add(orange);
            Fruit watermelon=new Fruit(getRandomLengthName("watermelon"),R.drawable.fruit);
            mfruitList.add(watermelon);
            Fruit pear=new Fruit(getRandomLengthName("pear"),R.drawable.fruit);
            mfruitList.add(pear);
            Fruit apple1=new Fruit(getRandomLengthName("apple1"),R.drawable.fruit);
            mfruitList.add(apple1);
            Fruit apple2=new Fruit(getRandomLengthName("apple24"),R.drawable.fruit);
            mfruitList.add(apple2);
            Fruit apple3=new Fruit(getRandomLengthName("apple34444"),R.drawable.fruit);
            mfruitList.add(apple3);
            Fruit apple4=new Fruit(getRandomLengthName("apple444444444"),R.drawable.fruit);
            mfruitList.add(apple4);
            Fruit apple5=new Fruit(getRandomLengthName("apple5"),R.drawable.fruit);
            mfruitList.add(apple5);
        }
    }

    private String getRandomLengthName(String name){
        Random random=new Random();
        int length=random.nextInt(20)+1;//1到20的随机数
        StringBuilder builder=new StringBuilder();
        for (int i=0;i<length;i++){
            builder.append(name);
        }
        return builder.toString();
    }

}
