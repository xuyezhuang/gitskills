package cn.dooer.ydz.retrofitrxtest.layout;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.dooer.ydz.retrofitrxtest.R;

public class BaseMultiItemAdapter extends BaseMultiItemQuickAdapter<Home,BaseViewHolder> {

    public BaseMultiItemAdapter(List data){
        super(data);
        addItemType(Home.viewType1, R.layout.basemultiview1);
        addItemType(Home.viewType2, R.layout.basemutilview2);
        addItemType(Home.viewType3, R.layout.basemultiview3);
    }

    @Override
    protected void convert(BaseViewHolder helper, Home item) {
        switch (helper.getItemViewType()){
            case Home.viewType1:
                    helper.setImageResource(R.id.imageview1,item.getImageId());
                break;
            case Home.viewType2:
                helper.setText(R.id.txtview2,item.getName());
                break;
            case Home.viewType3:
                helper.setText(R.id.view3txt,item.getName());
                helper.setImageResource(R.id.view3image,item.getImageId());
                break;
                default:
            break;
        }

    }
}
