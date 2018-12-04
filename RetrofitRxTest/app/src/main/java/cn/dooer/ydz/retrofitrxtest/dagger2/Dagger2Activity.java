
package cn.dooer.ydz.retrofitrxtest.dagger2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

import cn.dooer.ydz.retrofitrxtest.MaterialDesign.LogUtil;
import cn.dooer.ydz.retrofitrxtest.R;
import dagger.Lazy;
import rx.functions.Action1;

public class Dagger2Activity extends AppCompatActivity {

    public static void start(Context context){
        Intent intent=new Intent(context,Dagger2Activity.class);
        context.startActivity(intent);
    }
    //懒加载Lazy和强制重新加载Provider
    @Named("string")
    @Inject
    Lazy<Person> lazyPerson;

    @Named("string")
    @Inject
    Provider<Person> providerPerson;

    @Named("string")
    @Inject
    Person person;

    //named表明调用不同的构造函数创建person
    @Named("context")
    @Inject
    Person person2;//这样会创建2个不同的对象 想要让他都指向同一个对象必须在工厂类module跟compoent容器中@Singleton注解

    //需要非常注意的是：单例是基于Component的，所以不仅 Provides 的地方要加 @Singleton，Component上也需要加。
    // 并且如果有另外一个OtherActivity，并且创建了一个MainComponent，也注入Person，这个时候 MainActivity和OtherActivity中的Person是不构成单例的，
    // 因为它们的Component是不同的。

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2);
        //实例化容器                                                 创建工厂类
        AppCompoent appCompoent=DaggerAppCompoent.builder().mainmodule(new Mainmodule(this)).build();
                                                            //接收dependencies的容器         创建工厂类
        MainCompoent mainCompoent=DaggerMainCompoent.builder().appCompoent(appCompoent).appModule(new AppModule()).build();
        //使用全局的application，正常创建的对象都是单例App.appCompoent
//        MainCompoent mainCompoent1=DaggerMainCompoent.builder().appCompoent(App.appCompoent).appModule(new AppModule()).build();
        mainCompoent.inject(this);

        final ZhaiNan zhaiNan=DaggerPlatform.builder().build().waimai();
        RxView.clicks(findViewById(R.id.btn))
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Toast.makeText(getBaseContext(),zhaiNan.eat(),Toast.LENGTH_SHORT).show();
                    }
                });
        Person p1 = lazyPerson.get();
        Person p2 = lazyPerson.get();
        Person p3 = providerPerson.get();
        Person p4 = providerPerson.get();
        LogUtil.i("dagger2",person.getName());

        //说明 lazyPerson 多次get 的是同一个对象，providerPerson多次get，每次get都会尝试创建新的对象。
        LogUtil.i("lazy",p1.toString());
        LogUtil.i("lazy",p2.toString());
        LogUtil.i("provider",p3.toString());
        LogUtil.i("provider",p4.toString());
    }
}
                /**
                 *
                 1 依赖

                 //dagger2
                 implementation "com.google.dagger:dagger:2.14.1"
                 annotationProcessor "com.google.dagger:dagger-compiler:2.14.1"


                 1 创建要注入的类

                 2创建工厂输出类

                 3创建容器，输出的类都会放到容器当中

                 4先rebuild一下，再在对应的activity中实例化dagger

                 5commom也就是容器是可以相互依赖的dependencies = {AppCompoent.class}

                 6 实例化的时候先实例化第一个容器，主容器接受一个dagger  commom

                 什么时候用 new 创建对象，什么时候可以直接返回传入的参数就很明显了。对于被 @Inject 注解过构造方法或者在一个 Module 中的被 @Provides
                 注解的方法提供了依赖时，就可以直接返回传入的参数，而第三方的库或者 SDK 自带的类就必须手动创建了。


                 dagger2： 这个框架注解基本就是injext provides named  module commponet    socpe作用域在commpoent

                 没经过module产生的对象直接使用inject注入的对象的构造函数必须使用inject标记

                 commponet有组合跟继承的方式。推荐组合的方式。deci...

                 基本使用思路：创建一个全局的commpoent，使用socpe注明他的生命周期，在application中实例化。例如：toast、sp、网络框架等

                 一个activity或者几个拥有相同需求类的activity共用一个commpoent。

                 */