package cn.dooer.ydz.retrofitrxtest.layout;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import cn.dooer.ydz.retrofitrxtest.R;

public class MultipleRvAdapter extends AppCompatActivity {

    public static void start(Context context){
        Intent intent=new Intent(context,MultipleRvAdapter.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_rv_adapter);
        RecyclerView recyclerView=findViewById(R.id.multipRecy);
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);

    }
}
