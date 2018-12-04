package cn.dooer.ydz.retrofitrxtest.layout;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.dooer.ydz.retrofitrxtest.R;

public class HeaderAndFooterAndRefre extends AppCompatActivity {
    private HeadandFooterandRefre headandFooterandRefre;
    private RecyclerView rv;
    private List<Home> list=new ArrayList<>();
    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header_and_footer_and_refre);
        getlist();
        rv=findViewById(R.id.headandfooterandrefre);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
        headandFooterandRefre=new HeadandFooterandRefre(R.layout.fruit_item1,list);
        View headerView = getHeaderView(0, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headandFooterandRefre.addHeaderView(getHeaderView(1, getRemoveHeaderListener()), 0);
            }
        });
        headandFooterandRefre.addHeaderView(headerView);

        View footerView = getFooterView(0, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headandFooterandRefre.addFooterView(getFooterView(1, getRemoveFooterListener()), 0);
            }
        });
        headandFooterandRefre.addFooterView(footerView, 0);


        swipeRefreshLayout=findViewById(R.id.swipe_refre);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getBaseContext(),"下拉",Toast.LENGTH_SHORT).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                        Thread.sleep(2000);
                    }catch (Exception e){
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                swipeRefreshLayout.setRefreshing(false);//表示刷新结束并隐藏进度条
                                swipeRefreshLayout.setEnabled(false);//清楚下拉
                            }
                        });

                    }
                }).start();
            }
        });


        headandFooterandRefre.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        });
        headandFooterandRefre.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        headandFooterandRefre.setNotDoAnimationCount(6);
        headandFooterandRefre.setPreLoadNumber(2);//当列表滑动到倒数第二个的时候刷新
        rv.setAdapter(headandFooterandRefre);

    }

    public void getlist(){
        for (int i=0;i<2;i++){
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
    private void loadMore() {

//                setData(false, list);
//                headandFooterandRefre.loadMoreComplete();
//                headandFooterandRefre.loadMoreFail();

              new Thread(new Runnable() {
                  @Override
                  public void run() {
                    try{
                        Thread.sleep(1000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                           headandFooterandRefre.loadMoreComplete();//加载完成
//                            headandFooterandRefre.loadMoreFail();//加载失败
                            headandFooterandRefre.loadMoreEnd(false);//结束
//                            setData(false,list);
                        }
                    });
                  }
              }).start();
                Toast.makeText(getBaseContext(), "网络错误", Toast.LENGTH_LONG).show();


    }

    private void setData(boolean isRefresh, List data) {

        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            headandFooterandRefre.setNewData(data);
        } else {
            if (size > 0) {
                headandFooterandRefre.addData(data);
            }
        }
        if (size < 6) {
            //第一页如果不够一页就不显示没有更多数据布局
            headandFooterandRefre.loadMoreEnd(isRefresh);
            Toast.makeText(this, "no more data", Toast.LENGTH_SHORT).show();
        } else {
            headandFooterandRefre.loadMoreComplete();
        }
    }

    private View getHeaderView(int type, View.OnClickListener listener) {
        View view = getLayoutInflater().inflate(R.layout.head_view, (ViewGroup) rv.getParent(), false);
        if (type == 1) {
            ImageView imageView = (ImageView) view.findViewById(R.id.iv);
            imageView.setImageResource(R.mipmap.rm_icon);
        }
        view.setOnClickListener(listener);
        return view;
    }

    private View getFooterView(int type, View.OnClickListener listener) {
        View view = getLayoutInflater().inflate(R.layout.footer_view, (ViewGroup) rv.getParent(), false);
        if (type == 1) {
            ImageView imageView = (ImageView) view.findViewById(R.id.iv);
            imageView.setImageResource(R.mipmap.rm_icon);
        }
        view.setOnClickListener(listener);
        return view;
    }

    private View.OnClickListener getRemoveHeaderListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headandFooterandRefre.removeHeaderView(v);
            }
        };
    }


    private View.OnClickListener getRemoveFooterListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headandFooterandRefre.removeFooterView(v);
            }
        };
    }

    //默认头部尾部都是占满一行，如果需要不占满可以配置
//        homeAdapter.setHeaderViewAsFlow(true);
//        homeAdapter.setFooterViewAsFlow(true);
    //删除
//	headerAndFooterAdapter.removeHeaderView(v);
//	 headerAndFooterAdapter.removeFooterView(v);

//    mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
//同理，下拉的时候也要防止上拉
// mAdapter.setEnableLoadMore(true);//加载完之后设置回去
}
