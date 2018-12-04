package cn.dooer.ydz.retrofitrxtest.layout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.dooer.ydz.retrofitrxtest.R;

public class SecondAdapter extends BaseQuickAdapter<SecondRvInfo,BaseViewHolder> {
    public SecondAdapter(int layoutId,List data){
        super(layoutId,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SecondRvInfo item) {
        helper.setText(R.id.card_txt,item.getName());
        helper.setImageResource(R.id.card_image,item.getImageId());
        helper.addOnClickListener(R.id.card_txt);

    }
}
