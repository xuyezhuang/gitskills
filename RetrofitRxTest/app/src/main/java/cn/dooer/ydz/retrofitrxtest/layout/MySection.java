package cn.dooer.ydz.retrofitrxtest.layout;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.dooer.ydz.retrofitrxtest.R;

public class MySection extends AppCompatActivity {

    private List<MySectionInfo> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_section);

        RecyclerView recyclerView=findViewById(R.id.section);
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        getList();
        MySectionAdapter adapter=new MySectionAdapter(R.layout.basemultiview3,R.layout.section_head_title,list);
        recyclerView.setAdapter(adapter);
    }

    private void getList(){
        for (int i=0;i<2;i++){
            list.add(new MySectionInfo(true,"我是第一个标题"));
            list.add(new MySectionInfo(new SectionInfo(R.drawable.aaa,"我是第一个标题"+i)));
            list.add(new MySectionInfo(new SectionInfo(R.drawable.aaa,"我是第一个标题"+i)));
            list.add(new MySectionInfo(new SectionInfo(R.drawable.aaa,"我是第一个标题"+i)));
            list.add(new MySectionInfo(new SectionInfo(R.drawable.aaa,"我是第一个标题"+i)));
            list.add(new MySectionInfo(true,"我是第二个标题"));
            list.add(new MySectionInfo(new SectionInfo(R.drawable.aaa,"我是第一个标题"+i)));
            list.add(new MySectionInfo(new SectionInfo(R.drawable.aaa,"我是第一个标题"+i)));
            list.add(new MySectionInfo(new SectionInfo(R.drawable.aaa,"我是第一个标题"+i)));
            list.add(new MySectionInfo(new SectionInfo(R.drawable.aaa,"我是第一个标题"+i)));
            list.add(new MySectionInfo(new SectionInfo(R.drawable.aaa,"我是第一个标题"+i)));
            list.add(new MySectionInfo(true,"我是第三个标题"));
            list.add(new MySectionInfo(new SectionInfo(R.drawable.aaa,"我是第一个标题"+i)));
            list.add(new MySectionInfo(new SectionInfo(R.drawable.aaa,"我是第一个标题"+i)));
            list.add(new MySectionInfo(new SectionInfo(R.drawable.aaa,"我是第一个标题"+i)));
            list.add(new MySectionInfo(true,"我是第四个标题"));
            list.add(new MySectionInfo(new SectionInfo(R.drawable.aaa,"我是第一个标题"+i)));
            list.add(new MySectionInfo(new SectionInfo(R.drawable.aaa,"我是第一个标题"+i)));
            list.add(new MySectionInfo(new SectionInfo(R.drawable.aaa,"我是第一个标题"+i)));
        }
    }
}
