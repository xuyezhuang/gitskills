package cn.dooer.ydz.retrofitrxtest.layout;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class Home implements MultiItemEntity{
    //多布局的类型
    public static final int viewType1=1;
    public static final int viewType2=2;
    public static final int viewType3=3;
    public static final int  viewType4=4;
    public List<SecondRvInfo> list;

    public List<SecondRvInfo> getList() {
        return list;
    }

    //所占据的行数
    private int spanSize;

    public int getSpanSize() {
        return spanSize;
    }
    private String name;
    private int imageId;
    private int viewType;
    public String getName() {
        return name;
    }

    @Override
    public int getItemType() {
        return this.viewType;
    }

    public Home(String name, int imageId){
        this.name=name;
        this.imageId=imageId;
    }
    //多布局的有参构造，如果是网格布局的话需要知道他占用了多少行
    public Home(String name, int imageId,int viewType,int spanSize){
        this.name=name;
        this.imageId=imageId;
        this.viewType=viewType;
        this.spanSize=spanSize;
    }

    //多布局的有参构造2 如果是线性布局的话不需要spanSize
    public Home(String name, int imageId,int viewType){
        this.name=name;
        this.imageId=imageId;
        this.viewType=viewType;

    }
    //子rv的数据
    public Home(List<SecondRvInfo> list,int viewType){
        this.list=list;
        this.viewType=viewType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
