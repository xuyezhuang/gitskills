package cn.dooer.ydz.retrofitrxtest.layout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;
import java.util.List;

import cn.dooer.ydz.retrofitrxtest.R;

public class ExpandableItemActivity extends AppCompatActivity {

    public static final int infoItemType3=2;
    public static final int infoItemType2=1;
    public static final int infoItemType1=0;
    private RecyclerView recyclerView;
    private ExpandableAdapter adapter;
    private List<MultiItemEntity> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_item);
        recyclerView=findViewById(R.id.expandableRv);
       final GridLayoutManager layoutManager=new GridLayoutManager(this,3);
        getList();
        adapter=new ExpandableAdapter(list);//必须先实例化数据源，要不然会空指针
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

               return adapter.getItemViewType(position) == ExpandableItemActivity.infoItemType3 ? 1 : layoutManager.getSpanCount();
            }
        });
        recyclerView.setAdapter(adapter);
        //important! setLayoutManager should be called after setAdapter
        recyclerView.setLayoutManager(layoutManager);
        adapter.expandAll();//菜单全打开
    }

    private void getList(){

        //从代码的数据源跟视图分析：从视图知道共有三层。
        //	最底层实现接口MultiItemEntity，上一层就必须继承AbstractExpandableItem类
        int first=8;//最外层的item数
        int second=3;//第二层的item数
        int three=5;//最内层的item数

        for (int a=0;a<first;a++) {
            First first1 = new First(R.drawable.ic_001, "我是第一层"+a);
            for (int i = 0; i < second; i++) {
                Second second1 = new Second(R.drawable.fruit, "第二层"+i);
                for (int j = 0; j < three; j++) {
                    second1.addSubItem(new Three("第三层上面的txt" + j, "第三层xia面的txt" + j));
                }
                first1.addSubItem(second1);
            }
            list.add(first1);
        }
        list.add(new First(R.drawable.ic_001,"最外层"));
        list.add(new First(R.drawable.ic_001,"最外层"));
        list.add(new First(R.drawable.ic_001,"最外层"));
    }
}
