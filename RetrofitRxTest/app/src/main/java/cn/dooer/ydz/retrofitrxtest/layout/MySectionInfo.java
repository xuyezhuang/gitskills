package cn.dooer.ydz.retrofitrxtest.layout;

import com.chad.library.adapter.base.entity.SectionEntity;

//继承SectionEntity至少必须重写一个构造函数
public class MySectionInfo extends SectionEntity<SectionInfo> {
    //item的数据
    public MySectionInfo(SectionInfo sectionInfo){
        super(sectionInfo);
    }
//头部的数据，第一个参数boolean必须传true。如果是false的时候会报没有实例化。也就是有item数据没有head是不行的
    public MySectionInfo(boolean ishead,String stringHead){
        super(ishead,stringHead);
    }
}
