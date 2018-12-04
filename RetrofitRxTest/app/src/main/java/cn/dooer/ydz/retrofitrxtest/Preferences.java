package cn.dooer.ydz.retrofitrxtest;

import android.content.Context;
import android.content.SharedPreferences;


import java.util.Set;

/**
 * Created by zex on 2017/9/5.
 */

public class Preferences {
    public final static String KEY_IS_LOGIN = "KEY_IS_LOGIN";
    public final static String KEY_USER_ACCOUNT = "KEY_USER_ACCOUNT";
    public static final String KEY_COOKIE = "cookies";

    static SharedPreferences getSharedPreferences() {
        return ApplicationCache.getContext().getSharedPreferences("YiDingZhong", Context.MODE_PRIVATE);
    }

    public static boolean isLogin() {
        return (boolean)get(KEY_IS_LOGIN, false);
    }


    public static void setIsLogin(boolean earPhoneMode) {
        save(KEY_IS_LOGIN,earPhoneMode);
    }

    public static void setUserName(String userName){
        getSharedPreferences().edit().putString("userName",userName).commit();
    }
    public static void setPassword(String password){
        getSharedPreferences().edit().putString("password",password).commit();
    }
    public static void setCurrentTime(long currentTime){
        getSharedPreferences().edit().putLong("currentTime",currentTime).commit();
    }
    public static long getCurrentTime(){
        return getSharedPreferences().getLong("currentTime",11);
    }

    public static String getuserName(){
        return getSharedPreferences().getString("userName","");
    }

    public static String getPassword(){
        return getSharedPreferences().getString("password","");
    }

    public static void saveUserAccount(String account) {
        save(KEY_USER_ACCOUNT, account);
    }

    public static String getUserAccount() {
        return (String) get(KEY_USER_ACCOUNT,"");
    }

    public static void clear() {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.clear();
        editor.commit();
    }

    public static void saveCookie(String cookie) {
        save(KEY_COOKIE, cookie);
    }

    public static String getCookie() {
        return (String) get(KEY_COOKIE,"");
    }

    private static void  save(String key, Object object) {
        String type = object.getClass().getSimpleName();
        SharedPreferences.Editor editor = getSharedPreferences().edit();

        if("String".equals(type)){
            editor.putString(key, (String)object);
        }
        else if("Integer".equals(type)){
            editor.putInt(key, (Integer)object);
        }
        else if("Boolean".equals(type)){
            editor.putBoolean(key, (Boolean)object);
        }
        else if("Float".equals(type)){
            editor.putFloat(key, (Float)object);
        }
        else if("Long".equals(type)){
            editor.putLong(key, (Long)object);
        }else {
            editor.putStringSet(key,(Set<String>)object);
        }
        editor.commit();
    }

    private static Object get(String key,Object defaultObject) {
        SharedPreferences sp = getSharedPreferences();
        String type = defaultObject.getClass().getSimpleName();
        if("String".equals(type)){
            return sp.getString(key, (String)defaultObject);
        }
        else if("Integer".equals(type)){
            return sp.getInt(key, (Integer)defaultObject);
        }
        else if("Boolean".equals(type)){
            return sp.getBoolean(key, (Boolean)defaultObject);
        }
        else if("Float".equals(type)){
            return sp.getFloat(key, (Float)defaultObject);
        }
        else if("Long".equals(type)){
            return sp.getLong(key, (Long)defaultObject);
        }else if("HashSet".equals(type)){
            return sp.getStringSet(key,(Set<String>)defaultObject);
        }
        return null;
    }
}
