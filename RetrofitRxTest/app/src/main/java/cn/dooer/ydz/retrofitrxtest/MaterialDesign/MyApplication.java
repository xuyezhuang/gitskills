package cn.dooer.ydz.retrofitrxtest.MaterialDesign;

import android.app.Application;
import android.content.Context;

import cn.dooer.ydz.retrofitrxtest.ApplicationCache;

public class MyApplication extends Application {
    private static Context context;
    //全局获取context方法，application在app启动的时候会实例化这个类，第一步继承  第二步在清单文件中注明
    //android:name="cn.dooer.ydz.retrofitrxtest.MaterialDesign.MyApplication"
    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
