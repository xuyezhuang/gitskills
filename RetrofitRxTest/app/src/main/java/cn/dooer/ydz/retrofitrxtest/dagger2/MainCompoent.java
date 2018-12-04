package cn.dooer.ydz.retrofitrxtest.dagger2;

import javax.inject.Singleton;

import cn.dooer.ydz.retrofitrxtest.MainActivity;
import dagger.Component;
import dagger.Module;
//component容器。可以把它想成一个容器， module中产出的东西都放在里面，
// 然后将component与我要注入的MainActivity做关联，MainActivity中需要的person就可以冲 component中去去取出来。
//修饰的只能是接口或者抽象类，之后rebuild 一下才能实例化让资源文件产生一个对应的类
//@Singleton  测试懒加载先注释掉
@ActivityScope
@Component(dependencies = {AppCompoent.class},modules = AppModule.class)
public interface MainCompoent {
    //Dagger2Activity实例化dagger2的activity
    void inject(Dagger2Activity mainActivity);

}
