package cn.dooer.ydz.retrofitrxtest.layout;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.dooer.ydz.retrofitrxtest.R;
import cn.dooer.ydz.retrofitrxtest.rvTest.Recycler;

public class BaseMultiActivity extends AppCompatActivity {

    public static void Start(Context context){
        Intent intent=new Intent(context,BaseMultiActivity.class);
        context.startActivity(intent);
    }

    private List<Home> list=new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_multi);
        RecyclerView baseMultiRv=findViewById(R.id.baseMultiRv);
        GridLayoutManager layoutManager=new GridLayoutManager(this,4);
        baseMultiRv.setLayoutManager(layoutManager);
        getList();
        BaseMultiItemAdapter adapter=new BaseMultiItemAdapter(list);
        adapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return list.get(position).getSpanSize();
            }
        });
        baseMultiRv.setAdapter(adapter);
    }

    private void getList(){
       for (int i=0;i<4;i++){
            Home home1=new Home("第一个视图",R.drawable.ic_001,1,3);
            list.add(home1);
           Home home2=new Home("第二个视图",R.drawable.ic_001,2,1);
           list.add(home2);
           Home home3=new Home("第三个视图",R.drawable.aaa,3,4);
           list.add(home3);

       }
    }
}
