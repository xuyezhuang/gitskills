package cn.dooer.ydz.retrofitrxtest.net;

import android.text.TextUtils;
import android.util.Log;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import cn.dooer.ydz.retrofitrxtest.ApiUtil;
import cn.dooer.ydz.retrofitrxtest.ApplicationCache;
import cn.dooer.ydz.retrofitrxtest.HttpResult;
import cn.dooer.ydz.retrofitrxtest.HttpResultFunc;
import cn.dooer.ydz.retrofitrxtest.Login;
import cn.dooer.ydz.retrofitrxtest.LoginInfo;
import cn.dooer.ydz.retrofitrxtest.Preferences;
import cn.dooer.ydz.retrofitrxtest.ServerException;
import cn.dooer.ydz.retrofitrxtest.Test;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Func1;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xuyezhuangT5000 on 2018/5/9.
 */

public class ApiClient {
    public static final String BASE_URL = "http://tapi.cvh.io/";
    public static final String BASE_URL_2 = "";

    private static final int DEFAULT_TIMEOUT = 15;
    private static final int DEFAULT_READ_TIMEOUT = 60;
    private static final int DEFAULT_WRITE_TIMEOUT = 60;
    private Api api;
   private ApiClient(){
       OkHttpClient.Builder httpClientBuilder=new OkHttpClient.Builder();
       httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)//设置失败后重连时间
               .readTimeout(DEFAULT_READ_TIMEOUT,TimeUnit.SECONDS)
               .writeTimeout(DEFAULT_WRITE_TIMEOUT,TimeUnit.SECONDS)
               .addNetworkInterceptor(new StethoInterceptor())//设置网络缓存拦截器
               .addInterceptor(new AddCookiesInterceptor())//判断本地是否缓存有cookie，有则加无则访问网络
               .addInterceptor(new ReceivedCookiesInterceptor())//每次访问网络都会把头信息中cookie保存在本地
               .retryOnConnectionFailure(true);
       Retrofit retrofit=new Retrofit.Builder()
               .client(httpClientBuilder.build())
               .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))//设置retrofit转换器
               .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//设置rx转换器
               .baseUrl(BASE_URL)//baoseurl必须以/为结尾
               .build();
       api=retrofit.create(Api.class);//创建service准备进行网络访问

   }
    private static class SingletonHolder{
        private static final ApiClient INSTANCE = new ApiClient();
    }

    //获取单例
    public static ApiClient getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public class AddCookiesInterceptor implements Interceptor {

        @Override
        public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
            Request.Builder builder = chain.request().newBuilder();
            if(!TextUtils.isEmpty(ApplicationCache.getCookies())){
                String cookie = ApplicationCache.getCookies();
                builder.addHeader("Cookie", cookie);

            }
            builder.addHeader("User-Agent", ApiUtil.getExtraHeaderInfo(ApplicationCache.getContext()));
            return chain.proceed(builder.build());
        }
    }
    public class ReceivedCookiesInterceptor implements Interceptor {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            okhttp3.Response originalResponse = chain.proceed(chain.request());
            if(ApplicationCache.getCookies() == null || ApplicationCache.getCookies().isEmpty() || !ApplicationCache.getCookies().contains("sessionid")){
                StringBuilder cookies = new StringBuilder();
                if (!originalResponse.headers("Set-Cookie").isEmpty()) {
                    for (String header : originalResponse.headers("Set-Cookie")) {
                        cookies.append(header).append(";");
                    }
                    String cookie = cookies.substring(0,cookies.length() - 1);
                    ApplicationCache.setCookies(cookie);
                    Preferences.saveCookie(cookie);
                }
            }
            return originalResponse;
        }
    }

    private class ServerResultFunc<T> implements Func1<HttpResult<T>, T> {//RX中map方法的转换，输入HttpResult这个泛型类，这个是一个实体bean，服务器返回json通过gson解析来的。
        // 返回T，在这个项目是data，剔除其他我们不需要的信息。
        @Override
        public T call(HttpResult<T> httpResult) {
            Log.i("CODE",httpResult.getSta()+"");
            if (httpResult.getSta() != 1) {
                throw new ServerException(httpResult.getSta(),httpResult.getMsg());//根据sta的值判断接口返回的数据是否正确。
            }
            return httpResult.getData();
        }
    }

    public Observable<LoginInfo> login(String phone, String password){
        HashMap<String,Object> params = new HashMap<>();
        params.put("username",phone);
        params.put("password",password);
        return api.login(params)
                .map(new ServerResultFunc<LoginInfo>())
                .onErrorResumeNext(new HttpResultFunc<LoginInfo>())//错误方法的输出，这里的错误方法是服务器产生的错误例如502  500这些直接提示报错这里也是map的转换。
                .subscribeOn(Schedulers.io())//控制事件产生的线程类型为io流，没有创建新的线程，在rx专门的线程里执行
                .unsubscribeOn(Schedulers.io())//执行之后取消订阅
                .observeOn(AndroidSchedulers.mainThread());//用户消费的线程指定在ui线程
    }

    public Observable<Login> getUserInfo(){
        return api.getUserInfo()
                .map(new ServerResultFunc<Login>())
                .onErrorResumeNext(new HttpResultFunc<Login>())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Test> Test(){
        HashMap<String,Object> params = new HashMap<>();
        params.put("phone","13113991110" );
        params.put("pwd","000000");
        return api.Test(params)
                .map(new ServerResultFunc<Test>())
                .onErrorResumeNext(new HttpResultFunc<Test>())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

        //图文上传，意见反馈
    public Observable<Object> feedback(List<MultipartBody.Part> files, Map<String, RequestBody> params){
        return api.feedback(files,params)
                .map(new ServerResultFunc<Object>())
                .onErrorResumeNext(new HttpResultFunc<Object>())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
