package cn.dooer.ydz.retrofitrxtest.dagger2;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;


//存储一些小数据的sp类，例如cookie、token的存储
public class Sp {

    public static final String KEY_COOKIE="cookie";

    public Sp(){

    }

    public SharedPreferences getSharedPreferences() {
        return  App.appCompoent.getContext().getSharedPreferences("我是你爹", Context.MODE_PRIVATE);
    }

    public  void saveCookie(String cookie) {
        save(KEY_COOKIE, cookie);
    }

    public  String getCookie() {
        return (String) get(KEY_COOKIE,"");
    }

    public void clear(){
        getSharedPreferences().edit().clear().commit();
    }

    private  void save(String key, Object object) {
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

    private  Object get(String key,Object defaultObject) {
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
