package cn.dooer.ydz.retrofitrxtest;

import com.google.gson.Gson;


/**
 * Created by zex on 2017/8/13.
 */

public class AccountUtil {

    public static void logout() {
        // 清理缓存&注销监听&清除状态
        //ApplicationCache.clear();
//        DropManager.getInstance().destroy();
        Preferences.setIsLogin(false);
        //DBSolution.getInstance().clear();
    }

    public static boolean isLogin(){
        return Preferences.isLogin();
    }
}
