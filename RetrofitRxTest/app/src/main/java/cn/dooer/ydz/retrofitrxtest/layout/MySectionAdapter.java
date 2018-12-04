package cn.dooer.ydz.retrofitrxtest.layout;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.dooer.ydz.retrofitrxtest.R;

//数据源类型继承SectionEntity
public class MySectionAdapter extends BaseSectionQuickAdapter<MySectionInfo,BaseViewHolder>{
    public MySectionAdapter(int layoutId, int headLayoutId, List data){
        super(layoutId,headLayoutId,data);//第一个参数是item布局，第二个是头布局，第三个是数据源
    }

    @Override
    protected void convertHead(BaseViewHolder helper, MySectionInfo item) {
        helper.setText(R.id.section_title,item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, MySectionInfo item) {
        SectionInfo info=item.t;
        helper.setText(R.id.view3txt,info.getName());
        helper.setImageResource(R.id.view3image,info.getImageId());
    }
}
