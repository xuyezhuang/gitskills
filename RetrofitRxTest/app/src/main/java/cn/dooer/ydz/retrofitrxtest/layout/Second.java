package cn.dooer.ydz.retrofitrxtest.layout;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

public class Second extends AbstractExpandableItem<Three> implements MultiItemEntity {

    private int imageId;
    private String name;

    public Second(int imageId, String name) {
        this.imageId = imageId;
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getLevel() {
        return 1;
    }

    @Override
    public int getItemType() {
        return ExpandableItemActivity.infoItemType2;
    }
}
