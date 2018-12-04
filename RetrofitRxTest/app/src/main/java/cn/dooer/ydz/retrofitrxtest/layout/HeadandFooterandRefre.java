package cn.dooer.ydz.retrofitrxtest.layout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.dooer.ydz.retrofitrxtest.R;

public class HeadandFooterandRefre extends BaseQuickAdapter<Home,BaseViewHolder> {

    public HeadandFooterandRefre(int layoutId,List data){
        super(layoutId,data);

    }

    @Override
    protected void convert(BaseViewHolder helper, Home item) {
        helper.setImageResource(R.id.card_image,item.getImageId());
        helper.setText(R.id.card_txt,item.getName());
    }
}
