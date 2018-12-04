package cn.dooer.ydz.retrofitrxtest.dagger2;

import com.facebook.stetho.json.annotation.JsonProperty;

import javax.inject.Inject;

public class ZhaiNan {
    @Inject
    Baozhi baozhi;

    @Inject
    Noodle noodle;
    @Inject
    public ZhaiNan(){

    }
    public String eat(){
        StringBuilder builder=new StringBuilder();
        builder.append("我就要吃");
        if (baozhi!=null){
            builder.append(baozhi.toString());
        }
        if (noodle!=null){
            builder.append(" ");
            builder.append(noodle.toString());
        }
        return builder.toString();
    }

}

class Baozhi{
    @Inject
    public Baozhi(){

    }

    @Override
    public String toString() {
        return "包子";
    }
}

 class Noodle{

    @Inject
    public Noodle(){

    }

     @Override
     public String toString() {
         return "面条";
     }
 }

