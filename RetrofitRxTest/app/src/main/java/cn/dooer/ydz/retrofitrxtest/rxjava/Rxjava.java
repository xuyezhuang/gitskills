package cn.dooer.ydz.retrofitrxtest.rxjava;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import cn.dooer.ydz.retrofitrxtest.R;
import rx.Observable;
import rx.Observer;
import rx.Single;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

public class Rxjava extends AppCompatActivity {

    public static void start(Context context){
        Intent intent=new Intent(context,Rxjava.class);
        context.startActivity(intent);
    }

    private static final String tag="Rxjava";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        //-------------------------------------------------
        //创建观察者，onNext事件发生变化默认调用的方法，onCompleted是事件完成时默认调用，一般是空实现，onError是发送错误时调用的方法。
        //onNext可能会被多次调用，当发生错误时所有的事件停止，调用onError方法。

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onNext(String s) {
                Log.d(tag, "Item: " + s);
            }

            @Override
            public void onCompleted() {
                Log.d(tag, "Completed!");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(tag, "Error!");
            }
        };
        //-------------------------------------------------
        //创建被观察者 被观察者调用的时候依次把事件发送出去，这个方法其实是跟下面2个方法等价的
        Observable observable1 = Observable.just("Hello", "Hi", "Aloha");
        String[] words = {"Hello", "Hi", "Aloha"};
        Observable observable2= Observable.from(words);
        // 将会依次调用：
        // onNext("Hello");
        // onNext("Hi");
        // onNext("Aloha");
        // onCompleted();

        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
                subscriber.onNext("Hi");
                subscriber.onNext("Aloha");
                subscriber.onCompleted();
            }
        });
        //-------------------------------------------------
        //subscribe还支持不完整的回调把onNextAction等对象当成订阅者， 对应的call想当于subscriber的onNext方法


        Action1<String> onNextAction = new Action1<String>() {
            // onNext()
            @Override
            public void call(String s) {
                Log.d(tag, s);
            }
        };
        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            // onError()
            @Override
            public void call(Throwable throwable) {
                // Error handling
            }
        };
        Action0 onCompletedAction = new Action0() {
            // onCompleted()
            @Override
            public void call() {
                Log.d(tag, "completed");
            }
        };

// 自动创建 Subscriber ，并使用 onNextAction 来定义 onNext()
        observable.subscribe(onNextAction);
// 自动创建 Subscriber ，并使用 onNextAction 和 onErrorAction 来定义 onNext() 和 onError()
        observable.subscribe(onNextAction, onErrorAction);
// 自动创建 Subscriber ，并使用 onNextAction、 onErrorAction 和 onCompletedAction 来定义 onNext()、 onError() 和 onCompleted()
        observable.subscribe(onNextAction, onErrorAction, onCompletedAction);



        //-------------------------------------------------
        //观察者、被观察者都知道了，那么用subscribe把他们联系在一起就是rxjava的基本用法了

        String[] names = {"我是你爹","我是你爷爷","孙子啊"};
        //rxjava中语法句式是被观察者.subscribe(观察者），因为在subscribe中observabler会被转换成subscriber，即是在订阅方法中观察者会被转换成为订阅者
        //下面的语法是被观察者.subscribe（订阅者）  names是传递的参数类型  new Action1可以认为是订阅者
        Observable.from(names)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String name) {
                        Log.d(tag, name);//被调用三次
                    }
                });
    }





    //-------------------------------------------------
   //在不指定线程的情况下， RxJava 遵循的是线程不变的原则，即：在哪个线程调用 subscribe()，
    // 就在哪个线程生产事件；在哪个线程生产事件，就在哪个线程消费事件。如果需要切换线程，就需要用到 Scheduler （调度器）
    //Schedulers.immediate(): 直接在当前线程运行，相当于不指定线程。这是默认的 Scheduler。
    //Schedulers.newThread(): 总是启用新线程，并在新线程执行操作。
    //Schedulers.io(): I/O 操作（读写文件、读写数据库、网络信息交互等）所使用的 Scheduler。
    // 行为模式和 newThread() 差不多，区别在于 io() 的内部实现是是用一个无数量上限的线程池，
    // 可以重用空闲的线程，因此多数情况下 io() 比 newThread() 更有效率。不要把计算工作放在 io() 中，可以避免创建不必要的线程。
    //Schedulers.computation(): 计算所使用的 Scheduler。这个计算指的是 CPU 密集型计算，
    // 即不会被 I/O 等操作限制性能的操作，例如图形的计算。这个 Scheduler 使用的固定的线程池，
    // 大小为 CPU 核数。不要把 I/O 操作放在 computation() 中，否则 I/O 操作的等待时间会浪费 CPU。
    //另外， Android 还有一个专用的 AndroidSchedulers.mainThread()，它指定的操作将在 Android 主线程运行。
    //有了这几个 Scheduler ，就可以使用 subscribeOn() 和 observeOn() 两个方法来对线程进行控制了。
    // * subscribeOn(): 指定 subscribe() 所发生的线程，即 Observable.OnSubscribe 被激活时所处的线程。
    // 或者叫做事件产生的线程。 * observeOn(): 指定 Subscriber 所运行在的线程。或者叫做事件消费的线程。
    /*
    Observable.just(1, 2, 3, 4)
            .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
            .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
            .subscribe(new Action1<Integer>() {
        @Override
        public void call(Integer number) {
            Log.d(tag, "number:" + number);
        }
    });


            int drawableRes = ...;
        ImageView imageView = ...;
        Observable.create(new OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {//可以是网络取图片操作等
                Drawable drawable = getTheme().getDrawable(drawableRes));
                subscriber.onNext(drawable);
                subscriber.onCompleted();
            }
        })
        .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
        .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
        .subscribe(new Observer<Drawable>() {
            @Override
            public void onNext(Drawable drawable) {
                imageView.setImageDrawable(drawable);
            }

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(activity, "Error!", Toast.LENGTH_SHORT).show();
            }
        });

    //-------------------------------------------------

    操作符map转换 通过操作符map方法实现funcl被观察者的输入类型变成Bitmap
    Observable.just("images/logo.png") // 输入类型 String
            .map(new Func1<String, Bitmap>() {
        @Override
        public Bitmap call(String filePath) { // 参数类型 String
            return getBitmapFromPath(filePath); // 返回类型 Bitmap
        }
    })
            .subscribe(new Action1<Bitmap>() {
        @Override
        public void call(Bitmap bitmap) { // 参数类型 Bitmap
            showBitmap(bitmap);
        }
    });

    操作符flatmap
                Student[] students = ...;
            Subscriber<Course> subscriber = new Subscriber<Course>() {
                @Override
                public void onNext(Course course) {
                    Log.d(tag, course.getName()); //依次被调用打印
                }
                ...
            };
            Observable.from(students)
                .flatMap(new Func1<Student, Observable<Course>>() {//返回一个新的被观察者
                    @Override
                    public Observable<Course> call(Student student) {
                        return Observable.from(student.getCourses());//student.getCourses()是一个数组，一个学生会有很多的课程
                    }
                })
                .subscribe(subscriber);


    */
    
}
