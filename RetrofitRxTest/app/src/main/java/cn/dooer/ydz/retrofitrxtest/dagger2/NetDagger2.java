package cn.dooer.ydz.retrofitrxtest.dagger2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.jakewharton.rxbinding.view.RxView;

import cn.dooer.ydz.retrofitrxtest.R;
import rx.functions.Action1;

public class NetDagger2 extends AppCompatActivity {

    String account;
    String pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_dagger2);
        EditText editText=findViewById(R.id.account_name);
        account=editText.getText().toString();
        EditText editText1=findViewById(R.id.pwd);
        pwd=editText1.getText().toString();

        RxView.clicks(findViewById(R.id.loggin))
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {

                    }
                });

    }
}
