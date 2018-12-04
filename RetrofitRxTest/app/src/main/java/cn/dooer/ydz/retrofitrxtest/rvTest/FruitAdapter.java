package cn.dooer.ydz.retrofitrxtest.rvTest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.dooer.ydz.retrofitrxtest.R;

//跟listview相比，同样是用继承来实现对应的adapter抽象类，recyclervoew默认还可以带个泛型优化的viewholder，这个viewholder还必须继承自recyclerview的
public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private List<Fruit> mFruit;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView fruitImage;
        TextView fruitName;
        View fruitView;
        public ViewHolder(View view){
            super(view);//viewholder的构造函数必须传入一个view
            fruitView=view;
            fruitImage=view.findViewById(R.id.fruit_image);
            fruitName=view.findViewById(R.id.fruti_name);
        }
    }

    public FruitAdapter(List<Fruit> mFruit){
        this.mFruit=mFruit;
    }



    //下面三个方法都是必须实现的抽象方法
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
        //从方法名可以知道是要我们创建一个viewholder优化，
        // 而recyclerview内部已经有一个抽象viewholder等着我们去实现，所以这里返回我们实现的内部抽象类viewholder
    }
    //绑定了内部的viewholder
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Fruit fruit=mFruit.get(position);
        holder.fruitImage.setImageResource(fruit.getImageId());
        holder.fruitName.setText(fruit.getName());
        holder.fruitView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position=holder.getAdapterPosition();
                Fruit fruit1=mFruit.get(position);
                Toast.makeText(view.getContext(),"you click view"+fruit.getName(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFruit.size();
    }
}
