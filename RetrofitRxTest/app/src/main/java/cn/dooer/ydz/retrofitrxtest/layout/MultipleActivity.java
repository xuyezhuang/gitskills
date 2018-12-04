package cn.dooer.ydz.retrofitrxtest.layout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jakewharton.rxbinding.view.RxView;

import cn.dooer.ydz.retrofitrxtest.R;
import rx.functions.Action1;

public class MultipleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple);
        RxView.clicks(findViewById(R.id.baseMulti))
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        BaseMultiActivity.Start(getBaseContext());
                    }
                });

        RxView.clicks(findViewById(R.id.mulitpleRv))
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        MultipleRvAdapter.start(getBaseContext());
                    }
                });
    }
}
