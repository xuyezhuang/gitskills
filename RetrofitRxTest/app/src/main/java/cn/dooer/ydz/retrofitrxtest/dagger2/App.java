package cn.dooer.ydz.retrofitrxtest.dagger2;

import android.app.Application;


//appcompoent实例化一些全局生命周期的类（单例）
public class App extends Application {
    public static AppCompoent appCompoent;

    @Override
    public void onCreate() {
        super.onCreate();
//        appCompoent=DaggerAppCompoent.builder().mainmodule(new Mainmodule(this)).build();

    }
}
