package cn.dooer.ydz.retrofitrxtest.layout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.greenrobot.eventbus.util.ErrorDialogFragments;

import java.util.ArrayList;
import java.util.List;

import cn.dooer.ydz.retrofitrxtest.MaterialDesign.LogUtil;
import cn.dooer.ydz.retrofitrxtest.R;

public class ItemClickkActivity extends AppCompatActivity {


    List<Home> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_clickk);
        RecyclerView recyclerView=findViewById(R.id.mainRv);
        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        getList();
        ItemClickAdapter adapter=new ItemClickAdapter(list);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Home home=list.get(position);
                LogUtil.i("item Click",home.getName());
            }
        });
        adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                Home home=list.get(position);
                LogUtil.i("onItemLongClick",home.getName());
                return false;//返回false：执行当前事件之后还会执行点击事件，true：执行当前事件之后不会再执行其他事件了
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Home home=list.get(position);
                LogUtil.i("onItemChildClick",home.getName());
            }
        });

        recyclerView.setAdapter(adapter);
    }

    private void getList(){
        for (int i=0;i<2;i++){
            Home home1=new Home("第一个视图",R.drawable.ic_001,1);
            list.add(home1);
            Home home2=new Home("第二个视图",R.drawable.ic_001,2);
            list.add(home2);
            Home home3=new Home("第三个视图",R.drawable.aaa,3);
            list.add(home3);
           List<SecondRvInfo> list1=new ArrayList<>();
            list1.add(new SecondRvInfo(R.drawable.aaa,"叫我胖虎大人0"));
            list1.add(new SecondRvInfo(R.drawable.aaa,"叫我胖虎大人1"));
            list1.add(new SecondRvInfo(R.drawable.aaa,"叫我胖虎大人2"));
            list1.add(new SecondRvInfo(R.drawable.aaa,"叫我胖虎大人3"));
            list1.add(new SecondRvInfo(R.drawable.aaa,"叫我胖虎大人4"));
           list.add(new Home(list1,4));
        }
    }
}
