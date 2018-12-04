package cn.dooer.ydz.retrofitrxtest.layout;

import android.support.annotation.IntRange;
import android.view.PointerIcon;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.IExpandable;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

import cn.dooer.ydz.retrofitrxtest.MaterialDesign.LogUtil;
import cn.dooer.ydz.retrofitrxtest.R;

// 获取当前父级位置
// int cp = getParentPosition(person);
// 通过父级位置找到当前list，删除指定下级
//         ((Level1Item)getData().get(cp)).removeSubItem(person);
// 列表层删除相关位置的数据
//         getData().remove(holder.getLayoutPosition());
// 更新视图
//         notifyDataSetChanged();

public class ExpandableAdapter extends MultiTtemQuick<MultiItemEntity,BaseViewHolder> {
    public ExpandableAdapter(List<MultiItemEntity> data){
        super(data);
        addItemType(ExpandableItemActivity.infoItemType1, R.layout.expandable_item);
        addItemType(ExpandableItemActivity.infoItemType2,R.layout.second_item);
        addItemType(ExpandableItemActivity.infoItemType3,R.layout.three_item);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final MultiItemEntity item) {
        switch (helper.getItemViewType()){
            case ExpandableItemActivity.infoItemType1:
                final First first=(First)item;
                helper.setImageResource(R.id.iamge,first.getImageId());
                helper.setText(R.id.title,first.getName());
                helper.setImageResource(R.id.arrow,((First) item).isExpanded()?R.mipmap.arrow_b:R.mipmap.arrow_r);
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int pos=helper.getAdapterPosition();
                        LogUtil.i("view Name",((First) item).getName());
                        if (first.isExpanded()){
                            collapse(pos);
                        }else {
                            expand(pos);
                        }
                    }
                });
                break;
            case ExpandableItemActivity.infoItemType2:
                final Second second=(Second)item;
                helper.setImageResource(R.id.second_image,second.getImageId())
                        .setText(R.id.second_title,second.getName());
                helper.setImageResource(R.id.second_arrow,((Second) item).isExpanded()?R.mipmap.arrow_b:R.mipmap.arrow_r);
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int pos=helper.getAdapterPosition();
                        LogUtil.i("view Name",((Second) item).getName());
                        if (second.isExpanded()){
                            collapse(pos,false);
                        }else {
                            expand(pos,false);
                        }

                    }
                });
                helper.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        // int pos=helper.getLayoutPosition();//这个是获取布局中的位置单个的view
                        //在该层的adapter的位置
                        int pos=helper.getAdapterPosition();
                        remove(pos);

                        return true;//返回false：执行当前事件之后还会执行点击事件，true：执行当前事件之后不会再执行其他事件了
                    }
                });
                break;
            case ExpandableItemActivity.infoItemType3:
               final Three three=(Three)item;
                helper.setText(R.id.txt1,three.getThreeString1());
                helper.setText(R.id.txt2,three.getThreeString2());
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        LogUtil.i("view Name",((Three) item).getThreeString1());
                        int pos=helper.getAdapterPosition();
                        remove(pos);
                    }
                });
                break;

                default:
                    break;
        }
    }

}
