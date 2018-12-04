package cn.dooer.ydz.retrofitrxtest.layout;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class Three implements MultiItemEntity {

    private String threeString1;
    private String threeString2;


    public Three(String threeString1, String threeString2) {
        this.threeString1 = threeString1;
        this.threeString2 = threeString2;
    }

    public String getThreeString1() {
        return threeString1;
    }

    public void setThreeString1(String threeString1) {
        this.threeString1 = threeString1;
    }

    public String getThreeString2() {
        return threeString2;
    }

    public void setThreeString2(String threeString2) {
        this.threeString2 = threeString2;
    }

    @Override
    public int getItemType() {
        return ExpandableItemActivity.infoItemType3;
    }
}
