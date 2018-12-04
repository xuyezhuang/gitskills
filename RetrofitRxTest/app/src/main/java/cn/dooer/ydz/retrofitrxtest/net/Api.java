package cn.dooer.ydz.retrofitrxtest.net;

import java.util.List;
import java.util.Map;

import cn.dooer.ydz.retrofitrxtest.HttpResult;
import cn.dooer.ydz.retrofitrxtest.Login;
import cn.dooer.ydz.retrofitrxtest.LoginInfo;
import cn.dooer.ydz.retrofitrxtest.Test;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import rx.Observable;

/**
 * Created by xuyezhuangT5000 on 2018/5/9.
 */

public interface Api {
    @FormUrlEncoded
    @POST("mobile/card/login")
    Observable<HttpResult<LoginInfo>> login(@FieldMap Map<String,Object> map);

    @POST("app/member/memberIndex")
    Observable<HttpResult<Login>> getUserInfo();

    @FormUrlEncoded
    @POST("phone_login")
    Observable<HttpResult<Test>> Test(@FieldMap Map<String,Object> map);

    @Multipart
    @POST("app/feedback/saveFeedBack")
    Observable<HttpResult<Object>> feedback(@Part List<MultipartBody.Part> files, @PartMap Map<String, RequestBody> map);
}
