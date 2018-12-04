package cn.dooer.ydz.retrofitrxtest.rxjava;

import android.content.Context;
import android.content.Intent;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.dooer.ydz.retrofitrxtest.MainActivity;
import cn.dooer.ydz.retrofitrxtest.R;
import cn.dooer.ydz.retrofitrxtest.eventbus.MessageEvent;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class Rxbinding extends AppCompatActivity {


    private EditText editText;
    private ListView listView;
    private TextView txt;
    private Button btn1,btn2,btn3,btn4;

    public static void start(Context context){
        Intent intent=new Intent(context,Rxbinding.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxbinding);

        RxView.clicks(findViewById(R.id.btn1))
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                            EventBus.getDefault().postSticky(new MessageEvent("粘性事件1"));
                    }
                });
        RxView.clicks(findViewById(R.id.btn2))
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        EventBus.getDefault().postSticky(new MessageEvent("粘性事件2"));
                    }
                });
        RxView.clicks(findViewById(R.id.btn3))
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        EventBus.getDefault().postSticky(new MessageEvent("粘性事件3"));
                    }
                });
        RxView.clicks(findViewById(R.id.btn4))
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        EventBus.getDefault().register(Rxbinding.this);//要接收时注册
                    }
                });

        RxView.clicks(findViewById(R.id.btn))
                .throttleFirst( 2 , TimeUnit.SECONDS )   //两秒钟之内只取一个点击事件，防抖操作
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Toast.makeText(Rxbinding.this, "点击了", Toast.LENGTH_SHORT).show();
                    }

                }) ;

        txt=findViewById(R.id.txt);
        RxView.longClicks(txt)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        EventBus.getDefault().post(new MessageEvent("eventbus测试"));
                        Toast.makeText(Rxbinding.this, "长按", Toast.LENGTH_SHORT).show();
                    }
                });

/*

                 //item click event
                RxAdapterView.itemClicks( listView )


                     //item long click
                     RxAdapterView.itemLongClicks( listView)


                    RxCompoundButton.checkedChanges( checkBox )
                        .subscribe(new Action1<Boolean>() {
                            @Override
                            public void call(Boolean aBoolean) {
                                button.setEnabled( aBoolean );
                                button.setBackgroundResource( aBoolean ? R.color.button_yes : R.color.button_no );
                                }
                            }) ;
```



        editText = (EditText) findViewById( R.id.editText );
        listView = (ListView) findViewById( R.id.listview );

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1 );
        listView.setAdapter( adapter );

        RxTextView.textChanges( editText )
                .debounce( 600 , TimeUnit.MILLISECONDS )
                .map(new Func1<CharSequence, String>() {
                    @Override
                    public String call(CharSequence charSequence) {
                        //get the keyword
                        String key = charSequence.toString() ;
                        return key ;
                    }
                })
                .observeOn( Schedulers.io() )
                .map(new Func1<String, List<String>>() {
                    @Override
                    public List<String> call(String keyWord ) {
                        //get list
                        List<String> dataList = new ArrayList<String>() ;
                        if ( ! TextUtils.isEmpty( keyWord )){
                            for ( String s : getData()  ) {
                                if (s != null) {
                                    if (s.contains(keyWord)) {
                                        dataList.add(s);
                                    }
                                }
                            }
                        }
                        return dataList ;
                    }
                })
                .observeOn( AndroidSchedulers.mainThread() )
                .subscribe(new Action1<List<String>>() {
                    @Override
                    public void call(List<String> strings) {
                        adapter.clear();
                        adapter.addAll( strings );
                        adapter.notifyDataSetChanged();
                    }
                }) ;
 */
    }
        @Subscribe(sticky =true)
        public void onEvent(MessageEvent messageEvent){
            System.out.println("接收到了来自eventbus的粘性事件"+messageEvent.getMessage());
        }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
