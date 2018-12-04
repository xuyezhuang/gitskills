package cn.dooer.ydz.retrofitrxtest.MaterialDesign.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.dooer.ydz.retrofitrxtest.MaterialDesign.FruitActivity;
import cn.dooer.ydz.retrofitrxtest.R;
import cn.dooer.ydz.retrofitrxtest.rvTest.Fruit;

public class FruitAdapter1 extends RecyclerView.Adapter<FruitAdapter1.ViewHolder> {
    private Context context;
    private List<Fruit> mFruitList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView imageView;
        TextView textView;

        public ViewHolder(View view){//真正的viewholder
            super(view);
            cardView=(CardView)view;
            imageView=view.findViewById(R.id.card_image);
            textView=view.findViewById(R.id.card_txt);
        }
    }

    public FruitAdapter1(List<Fruit> list){
        this.mFruitList=list;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {//在这个方法里面处理ui数据的显示，holder已经拥有所有的控件，position查找具体的控件
        Fruit fruit=mFruitList.get(position);
        holder.textView.setText(fruit.getName());
        Glide.with(context).load(fruit.getImageId()).into(holder.imageView);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {//创建viewholder视图，实例化item布局，并做一些监听操作
        if (context==null){
            context=parent.getContext();
        }
        View view= LayoutInflater.from(context).inflate(R.layout.fruit_item1,parent,false);

        //recyclerview的点击事件
        final ViewHolder holder=new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position=holder.getAdapterPosition();
                Fruit fruit=mFruitList.get(position);
                Intent intent=new Intent(context, FruitActivity.class);
                intent.putExtra(FruitActivity.FRUIT_NAME,fruit.getName());
                intent.putExtra(FruitActivity.FRUIT_IMAGE_ID,fruit.getImageId());
                context.startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }
}
