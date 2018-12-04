package cn.dooer.ydz.retrofitrxtest.MaterialDesign;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import cn.dooer.ydz.retrofitrxtest.R;
import cn.dooer.ydz.retrofitrxtest.glide.Image;

public class FruitActivity extends AppCompatActivity {
    public static final String FRUIT_NAME="fruit_name";
    public static final String FRUIT_IMAGE_ID="fruit_image_id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);

        Intent intent=getIntent();
        int fruitimage=intent.getIntExtra(FRUIT_IMAGE_ID,0);
        String fruitName=intent.getStringExtra(FRUIT_NAME);
        Toolbar toolbar=findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout=findViewById(R.id.clooapsing_toolbar);
        ImageView imageView=findViewById(R.id.fruit_image_view);
        TextView textView=findViewById(R.id.fruit_content_text);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
        collapsingToolbarLayout.setTitle(fruitName);
        Glide.with(this).load(fruitimage).into(imageView);
        String fruitContent=getnrateFruitContent(fruitName);
        textView.setText(fruitContent);
    }

    public String getnrateFruitContent(String fruitName){
        StringBuilder builder=new StringBuilder();
        for (int i=0;i<500;i++){
            builder.append(fruitName);
        }
        return builder.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                finish();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
/**
                     appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                    @Override
                    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                    if (verticalOffset == 0) {
                    //展开状态
                    if (state != CollapsingToolbarLayoutState.EXPANDED) {
                    state = CollapsingToolbarLayoutState.EXPANDED;//修改状态标记为展开
                    toolbarLayout.setTitle(movieName);//设置title为EXPANDED
                    }
                    //折叠的监听
                    } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                    playButton.setVisibility(View.VISIBLE);//显示播放按钮
                    toolbarLayout.setTitle("");//设置title不显示
                    state = CollapsingToolbarLayoutState.COLLAPSED;//修改状态标记为折叠
                    }
                    } else {
                    //展开的监听
                    if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                    if (state == CollapsingToolbarLayoutState.COLLAPSED) {
                    playButton.setVisibility(View.GONE);//由折叠变为中间状态时隐藏播放按钮
                    }
                    toolbarLayout.setTitle(movieName);//设置title为INTERNEDIATE
                    state = CollapsingToolbarLayoutState.INTERNEDIATE;//修改状态标记为中间
                    }
                    }
                    }
                    });
 */