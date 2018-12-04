package cn.dooer.ydz.retrofitrxtest.layout;

import android.support.v7.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.dooer.ydz.retrofitrxtest.R;

public class EmptyAdapter extends BaseQuickAdapter<Home,BaseViewHolder> {
    public EmptyAdapter(int layoutId,List data){
        super(layoutId,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Home item) {
        helper.setImageResource(R.id.card_image,item.getImageId());
        helper.setText(R.id.card_txt,item.getName());
    }
}
