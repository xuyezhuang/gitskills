package cn.dooer.ydz.retrofitrxtest.layout;

import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.dooer.ydz.retrofitrxtest.R;

public class Animation extends AppCompatActivity {
    private List<Home> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        Toolbar toolbar=findViewById(R.id.animation_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setHomeButtonEnabled(true);
        }
        RecyclerView animationRv=findViewById(R.id.animation_rv);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        animationRv.setLayoutManager(linearLayoutManager);
        getlist();
        AnimationAdapter adapter=new AnimationAdapter(R.layout.fruit_item1,list);
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        adapter.setNotDoAnimationCount(3);
        animationRv.setHasFixedSize(false);
        animationRv.setAdapter(adapter);
    }
    public void getlist(){
        for (int i=0;i<10;i++){
            Home home1=new Home("第一个",R.drawable.aaa);
            Home home2=new Home("第二个",R.drawable.aaa);
            Home home3=new Home("第三个",R.drawable.aaa);
            Home home4=new Home("第四个",R.drawable.aaa);
            list.add(home1);
            list.add(home2);
            list.add(home3);
            list.add(home4);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
