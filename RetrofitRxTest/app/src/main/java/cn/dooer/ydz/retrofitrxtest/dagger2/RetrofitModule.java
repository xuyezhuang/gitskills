package cn.dooer.ydz.retrofitrxtest.dagger2;

import com.google.gson.GsonBuilder;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {
    @Singleton
    @Provides
    public DefaultRetrofit providerLocalRetrofit(@Named("default") OkHttpClient okHttpClient){
        return new DefaultRetrofit(okHttpClient);
    }

    @Singleton
    @Provides
    public CacheRetrofit providerTaobaoRetrofit(@Named("cache") OkHttpClient okHttpClient){
        return new CacheRetrofit(okHttpClient);
    }

}
class DefaultRetrofit {
    private static final String BASE_URL = "http://ip.taobao.com/";
    private static Retrofit retrofit;

    public DefaultRetrofit(OkHttpClient okHttpClient) {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))//设置retrofit转换器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//设置rx转换器
                .build();
    }


    public Retrofit getRetrofit() {
        return retrofit;

    }
}

class CacheRetrofit {
    private static final String BASE_URL = "http://ip.taobao.com/";
    private static Retrofit retrofit;

    public CacheRetrofit(OkHttpClient okHttpClient) {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))//设置retrofit转换器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//设置rx转换器
                .build();
    }


    public Retrofit CacheRetrofit() {
        return retrofit;

    }
}