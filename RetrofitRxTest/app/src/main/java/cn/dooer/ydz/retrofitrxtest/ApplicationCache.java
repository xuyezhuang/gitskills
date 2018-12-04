package cn.dooer.ydz.retrofitrxtest;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



/**
 * Created by zex on 2017/9/5.
 */

public class ApplicationCache {
    private static Context context;
    private static String cookies;
    private static Login user;


    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        ApplicationCache.context = context;
    }

    public static String getCookies(){
        if(TextUtils.isEmpty(cookies)){
            cookies = Preferences.getCookie();
        }
        return cookies;
    }

    public static void setCookies(String cookies){
        ApplicationCache.cookies = cookies;
    }

    public static Login getUser() {
        //app长时间在后台时，静态变量可能会被回收
        if(user == null){
            user = new GsonBuilder().setLenient().create().fromJson(Preferences.getUserAccount(),Login.class);
        }
        return user;
    }



    public static void setUser(Login user) {
        ApplicationCache.user = user;
        Gson gson = new Gson();
        String json = gson.toJson(user);
        Preferences.saveUserAccount(json);

    }

}
