package cn.dooer.ydz.retrofitrxtest.layout;

import android.graphics.Canvas;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.LinearLayout;

import com.bumptech.glide.load.engine.Initializable;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;

import java.util.ArrayList;
import java.util.List;

import cn.dooer.ydz.retrofitrxtest.R;

public class DragAndSwipeActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private DragAndSwipeAdapter adapter;
    private List<Home> list=new ArrayList<>();
    //滑动删除监听
    private OnItemSwipeListener swipeListener;
    //拖拽功能监听
    private OnItemDragListener dragListener;
    private ItemDragAndSwipeCallback mItemDragAndSwipeCallback;
    private ItemTouchHelper mItemTouchHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_and_swipe);
        recyclerView=findViewById(R.id.drag_andd_swipe);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        init();
        adapter=new DragAndSwipeAdapter(R.layout.drag_swipe, list);
        mItemDragAndSwipeCallback = new ItemDragAndSwipeCallback(adapter);//创建拖拽滑动回调
        mItemTouchHelper=new ItemTouchHelper(mItemDragAndSwipeCallback);//把拖拽滑动回调扔进选项触动助手
        mItemTouchHelper.attachToRecyclerView(recyclerView);//跟rv绑定
        mItemDragAndSwipeCallback.setSwipeMoveFlags(ItemTouchHelper.START | ItemTouchHelper.END);//设置能向左和向右滑动

        // 开启拖拽
        adapter.enableDragItem(mItemTouchHelper);
        adapter.setOnItemDragListener(dragListener);

        // 开启滑动删除
        adapter.enableSwipeItem();
        adapter.setOnItemSwipeListener(swipeListener);
        recyclerView.setAdapter(adapter);

    }
    private void init(){
        getlist();
        swipeListener=new OnItemSwipeListener() {
            @Override
            public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {

            }

            @Override
            public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {

            }

            @Override
            public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {

            }

            @Override
            public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {
                canvas.drawColor(ContextCompat.getColor(DragAndSwipeActivity.this, R.color.color_light_blue));//设置滑动之后的颜色
            }
        };

        dragListener=new OnItemDragListener() {
            @Override
            public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {

            }

            @Override
            public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {

            }

            @Override
            public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {

            }
        };
    }
    public void getlist(){
        for (int i=0;i<30;i++){
            Home home1=new Home("第几个item呢"+i,R.drawable.fruit);
//            Home home2=new Home("第二个",R.drawable.fruit);
//            Home home3=new Home("第三个",R.drawable.fruit);
//            Home home4=new Home("第四个",R.drawable.fruit);
            list.add(home1);
//            list.add(home2);
//            list.add(home3);
//            list.add(home4);
        }
    }
}
