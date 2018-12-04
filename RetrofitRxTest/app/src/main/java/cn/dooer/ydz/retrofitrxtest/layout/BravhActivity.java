package cn.dooer.ydz.retrofitrxtest.layout;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.dooer.ydz.retrofitrxtest.R;

public class BravhActivity extends AppCompatActivity {
    Class<?> []Classs={Animation.class,HeaderAndFooterAndRefre.class,MultipleActivity.class,MySection.class,EmptyActivity.class,
            DragAndSwipeActivity.class,ItemClickkActivity.class,ExpandableItemActivity.class};
    RecyclerView homeRv;
    List<Home> list=new ArrayList<>();
    public static void start(Context context){
        Intent intent=new Intent(context,BravhActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bravh);
        homeRv=findViewById(R.id.home_page);
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        homeRv.setLayoutManager(layoutManager);
        getlist();
        HomeAdapter adapter=new HomeAdapter(R.layout.fruit_item1,list);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(BravhActivity.this,Classs[position]);
                startActivity(intent);
            }
        });

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(getBaseContext(),"childClick",Toast.LENGTH_SHORT).show();
            }
        });

        homeRv.setAdapter(adapter);

    }
    public void getlist(){

            Home home1=new Home("Animation",R.drawable.fruit);
            Home home2=new Home("HeaderAndFooterAndRefre",R.drawable.fruit);
            Home home3=new Home("MultipleActivity",R.drawable.fruit);
            Home home4=new Home("MySection",R.drawable.fruit);
            list.add(home1);
            list.add(home2);
            list.add(home3);
            list.add(home4);
            list.add(new Home("EmptyActivity",R.drawable.fruit));
            list.add(new Home("DragAndSwipeActivity",R.drawable.fruit));
            list.add(new Home("ItemClickkActivity",R.drawable.fruit));
            list.add(new Home("ExpandableItemActivity",R.drawable.fruit));

    }
}
