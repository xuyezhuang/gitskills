package cn.dooer.ydz.retrofitrxtest.layout;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jakewharton.rxbinding.view.RxView;

import cn.dooer.ydz.retrofitrxtest.R;
import rx.functions.Action1;

public class Layouts extends AppCompatActivity {
    public static void start(Context context){
        Intent intent=new Intent(context,Layouts.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layouts);
        RxView.clicks(findViewById(R.id.bravh))
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        BravhActivity.start(getBaseContext());
                    }
                });
        RxView.clicks(findViewById(R.id.vlayout))
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        VlayoutActivity.start(getBaseContext());
                    }
                }); RxView.clicks(findViewById(R.id.tablelayout))
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        TableLayoutActivity.start(getBaseContext());
                    }
                });

    }
}
