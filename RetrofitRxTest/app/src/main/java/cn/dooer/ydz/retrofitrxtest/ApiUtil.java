package cn.dooer.ydz.retrofitrxtest;

import android.content.Context;
import android.text.TextUtils;



/**
 * Created by zex on 2017/8/29.
 */

public class ApiUtil {
    public static String getExtraHeaderInfo(Context mContext) {
        String extraHeaderInfo = "Android";

        // 添加版本号
        extraHeaderInfo += " YiDingZhong/" + BuildConfig.VERSION_NAME;

        // 添加网络状态
        String networkState = NetworkUtil.getNetworkInfo(mContext);
        extraHeaderInfo += " NetType/" + networkState;

        // 添加系统版本号
        extraHeaderInfo += ", System Version:" + android.os.Build.VERSION.RELEASE;
        // 添加手机型号
        extraHeaderInfo += ", Device Type:" + android.os.Build.MODEL;

        return extraHeaderInfo;
    }

    public static String getSessionIdFromCookie(String cookie){
        if(TextUtils.isEmpty(cookie)) return null;
        int start = cookie.indexOf("sessionid=");
        int end = cookie.indexOf(";",start);
        String seesionid = cookie.substring(start,end);
        return seesionid;
    }
}
