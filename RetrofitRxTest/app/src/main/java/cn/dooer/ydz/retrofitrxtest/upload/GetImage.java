package cn.dooer.ydz.retrofitrxtest.upload;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import org.devio.takephoto.app.TakePhoto;
import org.devio.takephoto.app.TakePhotoActivity;
import org.devio.takephoto.compress.CompressConfig;
import org.devio.takephoto.model.CropOptions;
import org.devio.takephoto.model.TImage;
import org.devio.takephoto.model.TResult;

import java.io.File;
import java.util.ArrayList;

import cn.dooer.ydz.retrofitrxtest.R;
//继承TakePhotoActivity  重写他的3个方法成功失败取消
public class GetImage extends TakePhotoActivity {
    ArrayList<TImage> images;
    private TextView paizhao,choose;
    private CheckBox cj;
    private boolean caijian=false;


    //TakePhoto
    private TakePhoto takePhoto;
    private CropOptions cropOptions;  //裁剪参数
    private CompressConfig compressConfig;  //压缩参数
    private Uri imageUri;  //图片保存路径
    public static void startForResult(Activity context, int requestCode){
        Intent intent = new Intent(context, GetImage.class);
        context.startActivityForResult(intent,requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_image);


        paizhao=findViewById(R.id.paizhao);
        choose=findViewById(R.id.choose);

        cj=findViewById(R.id.cj);
        cj.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                caijian=isChecked;
                if (caijian){
                    Toast.makeText(GetImage.this,"booelan"+caijian,Toast.LENGTH_SHORT).show();
                }

            }
        });

        paizhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageUri=getImageCropUri();
                initData();
                if (images!=null){
                    images.clear();
                }
                //拍照并裁剪
                takePhoto.onPickFromCaptureWithCrop(imageUri, cropOptions);
                //仅仅拍照不裁剪返回的地址的imageUri这个输入进去的地址。
//                takePhoto.onPickFromCapture(imageUri);
            }
        });

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageUri=getImageCropUri();
                initData();
               if (images!=null){
                   images.clear();
               }
                //从相册中选取图片并裁剪
//                takePhoto.onPickFromGalleryWithCrop(imageUri, cropOptions);
                //从相册中选取不裁剪
//                takePhoto.onPickFromGallery();
                //从相册中选择多张
                takePhoto.onPickMultipleWithCrop(9, cropOptions);
            }
        });
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
//        String iconPath = result.getImage().getOriginalPath();
        //Toast显示图片路径
//        System.out.println("url"+iconPath);
        Toast.makeText(this, "imagePath:", Toast.LENGTH_SHORT).show();
        //Google Glide库 用于加载图片资源
//        Glide.with(this).load(iconPath).into(image);
        images=result.getImages();
//        showImage();//显示图片
        setResult();//返回图片给上一个活动界面
    }

    @Override
    public void takeCancel() {
        super.takeCancel();
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
    }

    private void initData() {

        //设置裁剪参数
        cropOptions = new CropOptions.Builder().setAspectX(1).setAspectY(1).setWithOwnCrop(false).create();
        //设置压缩参数
        compressConfig=new CompressConfig.Builder().setMaxSize(50*1024).setMaxPixel(800).create();
        takePhoto.onEnableCompress(compressConfig,true);  //设置为需要压缩
    }
    //获得照片的输出保存Uri
    private Uri getImageCropUri() {
        takePhoto=getTakePhoto();//放在这里实例化是为了每次重新选择的时候是新的实例
        File file=new File(Environment.getExternalStorageDirectory(), "/temp/"+System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists())file.getParentFile().mkdirs();
        return Uri.fromFile(file);
    }

   private void setResult(){
       Intent intent = new Intent();
       intent.putExtra("images", images);
       setResult(3,intent);
       Handler handler = new Handler();
       handler.postDelayed(new Runnable() {
           @Override
           public void run() {
               /**
                *要执行的操作
                */
               finish();
           }
       }, 1000);//3秒后执行Runnable中的run方法

    }

    public void showImage(){

        Intent intent = new Intent(this, ShowImage.class);
        intent.putExtra("images", images);
        startActivity(intent);
    }
}
