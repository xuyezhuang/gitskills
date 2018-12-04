package cn.dooer.ydz.retrofitrxtest.dagger2;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
@ActivityScope
@Module
public class AppModule {
    //@Provides标注的方法就是提供对象的，这种方法一般会返回一个对象实例，例如上面返回一个 Person对象
    @Named("context")
//    @Singleton
    @Provides
    Person providesPersonContext(Context context){
        return new Person(context);
    }

    @Named("string")
//    @Singleton
    @Provides
    Person providesPersonName(){
        return new Person("孙子啊");
    }
}
