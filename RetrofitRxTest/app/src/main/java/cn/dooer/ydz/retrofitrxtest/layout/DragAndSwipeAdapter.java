package cn.dooer.ydz.retrofitrxtest.layout;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.dooer.ydz.retrofitrxtest.R;

public class DragAndSwipeAdapter extends BaseItemDraggableAdapter<Home,BaseViewHolder> {
    public DragAndSwipeAdapter(int layoutId, List data){
        super(layoutId,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Home item) {
        helper.setText(R.id.ds_txt,item.getName());
        helper.setImageResource(R.id.ds_image,item.getImageId());
    }
}
