package cn.dooer.ydz.retrofitrxtest.dagger2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import dagger.Component;


@ApplicationScepo
@Component(modules = {Mainmodule.class,OkhttpModule.class,RetrofitModule.class,RetrofitModule.class,DefaultService.class,CacheService.class})
public interface AppCompoent {
    //向下层提供context
    Context getContext();

}
