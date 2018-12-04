package cn.dooer.ydz.retrofitrxtest.layout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.dooer.ydz.retrofitrxtest.R;

public class AnimationAdapter extends BaseQuickAdapter<Home,BaseViewHolder> {
    public AnimationAdapter(int layoutId, List data){
        super(layoutId,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Home item) {
        helper.setText(R.id.card_txt,item.getName());
        helper.setImageResource(R.id.card_image,item.getImageId());
    }
}
