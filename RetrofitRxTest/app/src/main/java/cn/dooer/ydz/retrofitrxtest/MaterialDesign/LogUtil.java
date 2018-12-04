package cn.dooer.ydz.retrofitrxtest.MaterialDesign;

import android.util.Log;

public class LogUtil {

    //定制日志打印工具  在开发阶段可以根据需要设置level的值，在上线之后可以把level设置成NOTHING。
    public static final int VERBOSE=1;
    public static final int DEBUG=2;
    public static final int INFO=3;
    public static final int WARW=4;
    public static final int ERROR=5;
    public static final int NOTHING=6;
    public static final int level=VERBOSE;


    public static void v(String tag,String msg){
        if (level<=VERBOSE){
            Log.v(tag,msg);
        }
    }
    public static void d(String tag,String msg){
        if (level<=DEBUG){
            Log.d(tag,msg);
        }
    }
    public static void i(String tag,String msg){
        if (level<=INFO){
            Log.i(tag,msg);
        }
    }
    public static void w(String tag,String msg){
        if (level<=WARW){
            Log.w(tag,msg);
        }
    }
    public static void e(String tag,String msg){
        if (level<=ERROR){
            Log.e(tag,msg);
        }
    }
}
