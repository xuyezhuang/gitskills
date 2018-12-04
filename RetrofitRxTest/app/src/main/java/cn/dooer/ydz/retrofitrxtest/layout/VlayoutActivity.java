package cn.dooer.ydz.retrofitrxtest.layout;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.dooer.ydz.retrofitrxtest.R;

public class VlayoutActivity extends AppCompatActivity {


    public static void start(Context context){
        Intent intent=new Intent(context,VlayoutActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vlayout);
    }
}
