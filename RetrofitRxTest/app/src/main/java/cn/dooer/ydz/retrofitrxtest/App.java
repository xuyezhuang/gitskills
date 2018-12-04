package cn.dooer.ydz.retrofitrxtest;

import android.app.Application;
import android.content.Context;


import com.facebook.stetho.Stetho;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by zex on 2017/8/13.
 */

public class App extends Application {
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
//        MultiDex.install(this);
    }

    public void onCreate() {
        super.onCreate();
        ApplicationCache.setContext(this);
        /*if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);*/

        //初始化Stetho
        Stetho.initializeWithDefaults(this);

//        UIKit.init(this);

        //初始化fresco
//        Set<RequestListener> requestListeners = new HashSet<>();
//        requestListeners.add(new RequestLoggingListener());
//        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
//                .setDownsampleEnabled(true)
//                .setRequestListeners(requestListeners)
//                .build();
//        Fresco.initialize(this, config);

        //bugly
//        CrashReport.initCrashReport(getApplicationContext());
//        CrashReport.setAppVersion(getApplicationContext(), BuildConfig.VERSION_NAME);
    }
}
