package cn.dooer.ydz.retrofitrxtest.layout;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.dooer.ydz.retrofitrxtest.R;

public class TableLayoutActivity extends AppCompatActivity {


    public static void start(Context context){
        Intent intent=new Intent(context, TableLayoutActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_layout);
    }
}
