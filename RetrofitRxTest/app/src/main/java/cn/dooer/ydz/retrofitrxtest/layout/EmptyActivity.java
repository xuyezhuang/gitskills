package cn.dooer.ydz.retrofitrxtest.layout;


import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding.view.RxView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.dooer.ydz.retrofitrxtest.R;
import rx.functions.Action1;

public class EmptyActivity extends AppCompatActivity {

    public int number=0;
    private EmptyAdapter adapter;
    private List<Home> list=new ArrayList<>();
    private RecyclerView rv;
    View loadView;
    View errorView;
    View notDataView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
        RxView.clicks(findViewById(R.id.fab))
                .throttleFirst(4, TimeUnit.SECONDS)//4秒内取一个，防止重复点击
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        onRefre();
                    }
                });

        rv=findViewById(R.id.emptyRv);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
        getlist();
        adapter=new EmptyAdapter(R.layout.fruit_item1,list);
        rv.setAdapter(adapter);
        initView();
        onRefre();//一进来先清除掉数据，显示loadView再进行显示

    }
    public void getlist(){
        for (int i=0;i<4;i++){
            Home home1=new Home("第一个",R.drawable.fruit);
            Home home2=new Home("第二个",R.drawable.fruit);
            Home home3=new Home("第三个",R.drawable.fruit);
            Home home4=new Home("第四个",R.drawable.fruit);
            list.add(home1);
            list.add(home2);
            list.add(home3);
            list.add(home4);
        }
    }
    private void onRefre(){
        if (number<5){
            this.number=++number;
            adapter.setNewData(null);
            adapter.setEmptyView(loadView);
        }else {
            adapter.setNewData(list);
            return;
        }
     new Handler().postDelayed(new Runnable() {
         @Override
         public void run() {
             Toast.makeText(getBaseContext(),number%2+"",Toast.LENGTH_SHORT).show();
             switch (number%2){

                 case 0:adapter.setEmptyView(errorView);

                     break;

                 case 1:adapter.setEmptyView(notDataView);
                     break;
                     default:
                         break;
             }
         }
     },3000);
    }

    private void initView(){
        loadView= LayoutInflater.from(this).inflate(R.layout.load_view,(ViewGroup)rv.getParent(),false);
        Glide.with(this).load(R.drawable.ggg).into((ImageView) loadView.findViewById(R.id.gif));
        errorView=LayoutInflater.from(this).inflate(R.layout.error_view,(ViewGroup)rv.getParent(),false);
        RxView.clicks(errorView)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        onRefre();
                    }
                });
        notDataView=LayoutInflater.from(this).inflate(R.layout.not_data_view,(ViewGroup)rv.getParent(),false);
        RxView.clicks(notDataView)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        onRefre();
                    }
                });
    }

}
