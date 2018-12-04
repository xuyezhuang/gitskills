package cn.dooer.ydz.retrofitrxtest;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import cn.dooer.ydz.retrofitrxtest.MaterialDesign.LogUtil;
import cn.dooer.ydz.retrofitrxtest.MaterialDesign.Md;
import cn.dooer.ydz.retrofitrxtest.dagger2.Dagger2Activity;
import cn.dooer.ydz.retrofitrxtest.eventbus.MessageEvent;
import cn.dooer.ydz.retrofitrxtest.glide.Image;
import cn.dooer.ydz.retrofitrxtest.layout.Layouts;
import cn.dooer.ydz.retrofitrxtest.net.ApiClient;
import cn.dooer.ydz.retrofitrxtest.net.ApiException;
import cn.dooer.ydz.retrofitrxtest.rvTest.Recycler;
import cn.dooer.ydz.retrofitrxtest.rxjava.Rxbinding;
import cn.dooer.ydz.retrofitrxtest.rxjava.Rxjava;
import cn.dooer.ydz.retrofitrxtest.upload.UploadActivity;
import rx.Subscription;
import rx.functions.Action1;


public class MainActivity extends BaseActivity {
    private EditText t1,t2;
    private TextView t3,txt,upload,rxbinding;
    private String account;
    private String password;
    private String encrypt="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);//注册eventbus
        t1=(EditText)findViewById(R.id.account);
        t2=(EditText)findViewById(R.id.password);
        t3=(TextView)findViewById(R.id.login);
        txt=(TextView)findView(R.id.txt);
        rxbinding=findView(R.id.rxbinding);
        RxView.clicks(findView(R.id.dagger2))
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Dagger2Activity.start(getBaseContext());
                    }
                });
        rxbinding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Rxbinding.start(getBaseContext());
                getTest();
//                TestActivity.start(getBaseContext());//传感器设计rxbingding

            }
        });

        RxView.clicks(findView(R.id.recyclerview))
                .throttleFirst( 2 , TimeUnit.SECONDS )   //两秒钟之内只取一个点击事件，防抖操作
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Recycler.start(getBaseContext());
                    }
                });

        RxView.clicks(findView(R.id.MaterialDesign))
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Md.start(getBaseContext());
                    }
                });

        findView(R.id.download).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Image.start(getBaseContext());
            }
        });
        findView(R.id.rxjava).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rxjava.start(getBaseContext());
            }
        });
        upload=(TextView)findView(R.id.upload);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadActivity.upload(getBaseContext());
            }
        });
        RxView.clicks(findView(R.id.layouts))
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Layouts.start(getBaseContext());
                    }
                });
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (t1.getText().toString().trim()!=null&&t2.getText().toString().trim()!=null){
                    account=t1.getText().toString().trim();
                    password=t2.getText().toString().trim();

                    try{
                        encrypt=DESUtil.encode(t2.getText().toString().trim());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("account"+account+"   encrypt"+encrypt);
                            Subscription subscription = ApiClient.getInstance().login(account, encrypt).subscribe(new HttpObserver<LoginInfo>() {

                                @Override
                                protected void onError(ApiException ex) {
                                    System.out.println(ex.toString()+"onError");
                                }

                                public void onNext(LoginInfo o) {
                                    System.out.println("loging"+o.toString());
                                    getUserInfo();
                                }
                            });
                            mCompositeSubscription.add(subscription);
                        }
                    }).start();


                }
            }
        });


    }
    public void getUserInfo(){
        Subscription subscription = ApiClient.getInstance().getUserInfo().subscribe(new HttpObserver<Login>() {
            @Override
            protected void onError(ApiException ex) {

            }
            @Override
            public void onNext(Login userInfo) {
                Preferences.setIsLogin(/*userInfo.isIsLogin()*/true);
                ApplicationCache.setUser(userInfo);
                setResult(RESULT_OK);
               System.out.println("getUserInof"+userInfo.toString());
            }
        });
        mCompositeSubscription.add(subscription);
    }

    private void sendRequsetWithHttpURLConnection(){
        //开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection=null;
                BufferedReader reader=null;
                try {
                    URL url=new URL("https://www.baidu.com");
                    connection=(HttpURLConnection)url.openConnection();
                    connection.setConnectTimeout(8000);
                    connection.setRequestMethod("GET");
//                  connection.setRequestMethod("POST");
//                  DataOutputStream out=new DataOutputStream(connection.getOutputStream());
//                  out.writeBytes("username=admin&password=123456");
                    connection.setReadTimeout(8000);
                    InputStream in=connection.getInputStream();
                    //下面对获取到的输入流进行读取
                    reader=new BufferedReader(new InputStreamReader(in));
                    StringBuilder response=new StringBuilder();
                    String line;
                    while ((line=reader.readLine())!=null){
                        response.append(line);
                    }
                    showResponse(response.toString());

                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if (reader!=null){
                        try {
                            reader.close();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    if (connection!=null){
                        connection.disconnect();
                    }
                }
            }
        }).start();

    }
    private void showResponse(final String response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //在这里进行UI操作，讲结果显示到界面上

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    txt.setText(Html.fromHtml(response,Html.FROM_HTML_MODE_LEGACY));
                } else {
                    txt.setText(Html.fromHtml(response));
                }
            }
        });

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//取消注册
    }
    //相当于观察者模式中的update方法。观察者收到信息之后做的动作
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent messageEvent){
        System.out.println(rxbinding.getText());
        rxbinding.setText(messageEvent.getMessage());
        Toast.makeText(this,messageEvent.getMessage(),Toast.LENGTH_SHORT).show();
        System.out.println(rxbinding.getText());

        //现在测试是Main是观察者，而rxbinding是被观察者。不能反过来。因为如果rxbinding是观察者的话在main操作，rxbinding还没创建。
        //subscribe接口中有以下3个成员变量
        //threadmode:在主线程中运行  POSTING：在事件产生的线程中运行，默认。 BACKGROUND：在子线程中运行 ASYNC：总是在不同的线程中运行，即创建新的线程
        //priority：优先级
        //sticky：注意：只会接收到最近发送的一次粘性事件，之前的会接受不到。
    }

    public void getTest(){
        Subscription subscription = ApiClient.getInstance().Test().subscribe(new HttpObserver<Test>() {
            @Override
            protected void onError(ApiException ex) {
                LogUtil.i("onError",ex.getMsg());
            }
            @Override
            public void onNext(Test userInfo) {
                LogUtil.i("Test","成功");
            }
        });
        mCompositeSubscription.add(subscription);
    }
}
