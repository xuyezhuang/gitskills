package cn.dooer.ydz.retrofitrxtest.layout;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import cn.dooer.ydz.retrofitrxtest.MaterialDesign.LogUtil;
import cn.dooer.ydz.retrofitrxtest.R;

public class ItemClickAdapter extends BaseMultiItemQuickAdapter<Home,BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener,BaseQuickAdapter.OnItemLongClickListener,BaseQuickAdapter.OnItemChildClickListener{

    private List<SecondRvInfo> list;

    public ItemClickAdapter( List<Home> data){
        super(data);
        addItemType(Home.viewType1, R.layout.basemultiview1);
        addItemType(Home.viewType2, R.layout.basemutilview2);
        addItemType(Home.viewType3, R.layout.basemultiview3);
        addItemType(Home.viewType4,R.layout.second_recyclerview);
    }

    @Override
    protected void convert(BaseViewHolder helper, Home item) {
        switch (helper.getItemViewType()){
            case Home.viewType1:
                helper.setImageResource(R.id.imageview1,item.getImageId())
                        .addOnClickListener(R.id.imageview1)
                        .addOnLongClickListener(R.id.imageview1);

                break;
            case Home.viewType2:
                helper.setText(R.id.txtview2,item.getName());
                break;
            case Home.viewType3:
                helper.setText(R.id.view3txt,item.getName())
                        .addOnClickListener(R.id.view3txt);
                helper.setImageResource(R.id.view3image,item.getImageId());
                break;
            case Home.viewType4:
                // 添加布局id，让adapter支持该布局子控件childview的点击,以添加的方式加入进去，所谓nest_list的高度不能是match
                helper.setNestView(R.id.nest_list);
              final RecyclerView secondRv= helper.getView(R.id.second_rv);
               //第三个参数，是否开启自动测量，一般情况下，默认是会进行自动测量模式
                secondRv.setHasFixedSize(true);
               secondRv.setLayoutManager(new LinearLayoutManager(helper.itemView.getContext(),LinearLayoutManager.VERTICAL,false));
               SecondAdapter adapter=new SecondAdapter(R.layout.fruit_item1,item.getList());
               //嵌套recycleView的情况下需要使用你使用 adapter. setOnItemClickListener 来设置点击事件,如果使用recycleView.addOnItemTouchListener会累计添加的。
               adapter.setOnItemLongClickListener(this);
               adapter.setOnItemClickListener(this);
               adapter.setOnItemChildClickListener(this);
               list=item.getList();
                LogUtil.i("item1",item.getList().get(0).getName());
                LogUtil.i("item1",item.getList().get(1).getName());
                LogUtil.i("item1",item.getList().get(2).getName());
                LogUtil.i("item1",item.getList().get(3).getName());
               secondRv.setAdapter(adapter);
               break;
            default:
                break;
        }
    }

    @Override
    public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

        LogUtil.i("子Rv onItemLongClick",list.get(position).getName());
        return false;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        LogUtil.i("子Rv onItemClick",list.get(position).getName());
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        LogUtil.i("viewId",view.getId()+"");
        LogUtil.i("子Rv onItemChildClick",list.get(position).getName());
    }
}
