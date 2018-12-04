package cn.dooer.ydz.retrofitrxtest.dagger2;


import javax.inject.Singleton;

import cn.dooer.ydz.retrofitrxtest.net.Api;
import dagger.Module;
import dagger.Provides;

@Module
public class CacheService {
    @Singleton
    @Provides
    public Api providesGetCacheRetrofit(DefaultRetrofit defaultRetrofit){
        return defaultRetrofit.getRetrofit().create(Api.class);
    }
}
