package cn.dooer.ydz.retrofitrxtest.dagger2;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
//Module标注的对象，你可以把它想象成一个工厂，可以向外提供一些类的对象。

@Module
public class Mainmodule {

    private Context context;
    //利用application来标记生命周期
    @ApplicationScepo
    public Mainmodule(Context context){
        this.context=context;
    }
    //这里需要强调的是， providesPerson(Context context)中的 context，不能直接使用 成员变量 this.context，
    // 而是要在本类中提供一个 Context providesContext() 的 @Provides 方法，这样在发现需要 context 的时候会调用 provideContext 来获取
    // ，这也是为了解耦。

    @Provides
    public Context providesContext(){
        return this.context;
    }
    @Singleton
    @Provides
    public Sp providesSp(){
        return new Sp();
    }


}
