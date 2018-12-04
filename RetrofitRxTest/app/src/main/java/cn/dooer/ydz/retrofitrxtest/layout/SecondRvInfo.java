package cn.dooer.ydz.retrofitrxtest.layout;

public class SecondRvInfo {
    int imageId;
    String name;


    public SecondRvInfo(int imageId,String name){
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
}
