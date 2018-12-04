package cn.dooer.ydz.retrofitrxtest.dagger2;

import android.text.TextUtils;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import cn.dooer.ydz.retrofitrxtest.ApiUtil;
import cn.dooer.ydz.retrofitrxtest.ApplicationCache;
import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;

@Module
public class OkhttpModule {
    public static final String BASE_URL = "http://tapi.cvh.io/";
    private static final int DEFAULT_TIMEOUT = 15;
    private static final int DEFAULT_READ_TIMEOUT = 60;
    private static final int DEFAULT_WRITE_TIMEOUT = 60;
    private Sp sp;




    public OkhttpModule(Sp sp){
        this.sp=sp;
    }
    @Named("cache")
    @Singleton
    @Provides
    public OkHttpClient providesAutoOkhttpClient(){
        OkHttpClient.Builder httpClientBuilder=new OkHttpClient.Builder();
         httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)//设置失败后重连时间
                .readTimeout(DEFAULT_READ_TIMEOUT,TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_WRITE_TIMEOUT,TimeUnit.SECONDS)
                .addNetworkInterceptor(new StethoInterceptor())//设置网络缓存拦截器
               .addInterceptor(new AddCookiesInterceptor())//判断本地是否缓存有cookie，有则加无则访问网络
//               .addInterceptor(new ReceivedCookiesInterceptor())//每次访问网络都会把头信息中cookie保存在本地
                .retryOnConnectionFailure(true);
        return httpClientBuilder.build();
    }

    public class AddCookiesInterceptor implements Interceptor {

        @Override
        public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
            Request.Builder builder = chain.request().newBuilder();
            if(!TextUtils.isEmpty(ApplicationCache.getCookies())){
                String cookie =sp.getCookie();
                builder.addHeader("Cookie", cookie);

            }
            builder.addHeader("User-Agent", ApiUtil.getExtraHeaderInfo(ApplicationCache.getContext()));
            return chain.proceed(builder.build());
        }
    }
    @Named("default")
    @Singleton
    @Provides
    public OkHttpClient providesOkhttpClient(){
        OkHttpClient.Builder httpClientBuilder=new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)//设置失败后重连时间
                .readTimeout(DEFAULT_READ_TIMEOUT,TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_WRITE_TIMEOUT,TimeUnit.SECONDS)
                .addNetworkInterceptor(new StethoInterceptor())//设置网络缓存拦截器
//                .addInterceptor(new AddCookiesInterceptor())//判断本地是否缓存有cookie，有则加无则访问网络
               .addInterceptor(new ReceivedCookiesInterceptor())//每次访问网络都会把头信息中cookie保存在本地
                .retryOnConnectionFailure(true);
        return httpClientBuilder.build();
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
                    sp.saveCookie(cookie);
                }
            }
            return originalResponse;
        }
    }
}
