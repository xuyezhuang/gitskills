package cn.dooer.ydz.retrofitrxtest.glide;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

//import com.bumptech.glide.request.RequestOptions;

import java.io.File;

import cn.dooer.ydz.retrofitrxtest.R;

public class Image extends AppCompatActivity {
    private ImageView gilde;

    public static void start(Context context){
        Intent intent = new Intent(context, Image.class);
        context.startActivity(intent);
    }
//由于项目中选择图片第三库takephoto使用的gilde是3.6，跟现在最新的4.6.1用法有一些不同，导致如果升级glidetakephoto无法使用
// 解决方法：一项目使用takephoto自带的glide版本。二：下载takephoto的源码，导入项目module，修改源码
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        gilde=findViewById(R.id.glide);
        //大多数情况下只需要这么一行代码
//        Glide.with(Image.this).load("http://w.yidingzhong.cn/asset/images/y1.png").apply(RequestOptions.circleCropTransform()).into(gilde);
        //Glide.with(fragment).clear(imageView);
        //实际上你并不需要取消加载。。。
        //因为当你在with方法中传入的Activity或Fragment被销毁的时候，Glide会自动取消加载并且回收所有的加载过程中所使用的资源。

        //其实Glide加载本地图片和加载网络图片调用的方法是一样的,唯一的区别是说加载SD卡的图片需要SD卡的权限,加载网络需要网络权限
        //本地文件
        File file = new File(Environment.getExternalStorageDirectory(), "xiayu.png");
        //加载图片


        //iconPath也可以是图片在手机里的string路劲
//        Glide.with(this).load(iconPath).into(image);


//          加载原先图片
//        Glide.with(this).load(file).apply(RequestOptions.circleCropTransform()).into()//4.0之后没有直接placeholder跟fitcenter方法。需要通过RequestOptions来设置
//        Glide.with(this).load(url).placeholder(R.mipmap.place).into(iv);//设置默认的占位图
//        Glide.with(this).load(url).placeholder(R.mipmap.place).error(R.mipmap.icon_photo_error).into(iv);//设置加载出错显示的图片

//Glide默认是包含淡入淡出动画的时间为300ms(毫秒),我们可以修改这个动画的时间
//        Glide.with(this).load(url).placeholder(R.mipmap.place).error(R.mipmap.icon_photo_error).crossFade(5000).into(iv);
        //取消淡入淡出动画效果
//        Glide.with(this).load(url).placeholder(R.mipmap.place).error(R.mipmap.icon_photo_error).dontAnimate().into(iv);

/**
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher)
                .priority(Priority.HIGH)//优先加载等级
                .diskCacheStrategy(DiskCacheStrategy.NONE);//磁盘缓存策略，还有内存缓存，每种缓存都各有几种模式
            https://blog.csdn.net/yulyu/article/details/55096713?fps=1&locationNum=2

         清除所有内存缓存(需要在Ui线程操作)

         Glide.get(this).clearMemory();
         1
         2
         清除所有磁盘缓存(需要在子线程操作)

         Glide.get(MainActivity.this).clearDiskCache();
 //通过调用override,就可以把图片压缩到相应的尺寸来显示了,类似这些被处理过的图片,就是之前文章中提到的RESULT(处理图)

 Glide.with(this).load(mUrl).override(300,300).into(mIv);

        Glide.with(this)
                .load(ImageConfig.URL_GIF)
                .apply(options)//加载图片request参数，options可以一个创建多用
                .thumbnail(Glide.with(this)
                        .load(ImageConfig.URL_JPEG))
                .into(iv_test1);

        Glide.with(this)
                .load(ImageConfig.URL_WEBP)
                .apply(options)
                .thumbnail(Glide.with(this)//缩略图，在gif没有加载出来之前可以先加载缩略图
                        .load(ImageConfig.URL_JPEG))//用load的这种图片作为缩略图。如果直接填写0.1f是用原图10分之一作为缩略图
                .into(iv_test2);


             对图片进行裁剪、模糊、滤镜等处理：
             推荐使用独立的图片处理库：wasabeef/glide-transformations，使用也很简单：

             compile 'jp.wasabeef:glide-transformations:2.0.0'
             1
             之后我们就可以使用GenericRequestBuilder或其子类的transform()或bitmapTransform()方法设置图片转换了：

             //圆形裁剪
             Glide.with(this)
             .load("http://inthecheesefactory.com/uploads/source/nestedfragment/fragments.png")
             .bitmapTransform(new CropCircleTransformation(this))
             .into(iv_0);
             //圆角处理
             Glide.with(this)
             .load("http://inthecheesefactory.com/uploads/source/nestedfragment/fragments.png")
             .bitmapTransform(new RoundedCornersTransformation(this,30,0, RoundedCornersTransformation.CornerType.ALL))
             .into(iv_0);
             //灰度处理
             Glide.with(this)
             .load("http://inthecheesefactory.com/uploads/source/nestedfragment/fragments.png")
             .bitmapTransform(new GrayscaleTransformation(this))
             .into(iv_0);
             //其它变换...

 */
    }
}
