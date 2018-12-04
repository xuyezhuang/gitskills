package cn.dooer.ydz.retrofitrxtest.upload;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;


import org.devio.takephoto.model.TImage;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.dooer.ydz.retrofitrxtest.BaseActivity;
import cn.dooer.ydz.retrofitrxtest.HttpObserver;
import cn.dooer.ydz.retrofitrxtest.MainActivity;

import cn.dooer.ydz.retrofitrxtest.R;

import cn.dooer.ydz.retrofitrxtest.net.ApiClient;
import cn.dooer.ydz.retrofitrxtest.net.ApiException;
import cn.dooer.ydz.retrofitrxtest.permission.MPermission;
import cn.dooer.ydz.retrofitrxtest.permission.annotation.OnMPermissionDenied;
import cn.dooer.ydz.retrofitrxtest.permission.annotation.OnMPermissionGranted;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscription;


public class UploadActivity extends BaseActivity {
    EditText edittext;
    private TextView submit;
    private ImageView iv_upload_image;
    private LinearLayout picture_container;
    private Uri imageUri;
    final int TAKE_PHOTO=2;
    final int BASIC_PERMISSION_REQUEST_CODE=100;
    ArrayList<TImage> images;
    public static void upload(Context context){
        Intent intent = new Intent(context, UploadActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        picture_container=(LinearLayout)findView(R.id.picture_container);
        iv_upload_image=(ImageView)findView(R.id.iv_upload_image);
        requestBasicPermission();
        edittext=findView(R.id.edittext);
        submit=findView(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    HashMap<String, RequestBody> params = new HashMap<>();
                    List<MultipartBody.Part> files = new ArrayList<>();
                    params.put("type",RequestBody.create(MediaType.parse("multipart/form-data"), "member"));
                    params.put("message",RequestBody.create(MediaType.parse("multipart/form-data"), edittext.getText().toString().trim()));
                    if(images != null && images.size() > 0){
                        for (int i = 0; i < images.size(); i++){
                            File file = new File(images.get(i).getCompressPath());
                            RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"),file);
                            MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
                            files.add(body);//这里的拼装主要是为了符合http文件上传的格式，是前端定义的。params是后端定义的表单字段
                        }
                    }else {
//                        Toasty.normal(this,"请先上传图片").show();
                        return;
                    }
                    Subscription subscription = ApiClient.getInstance().feedback(files,params).subscribe(new HttpObserver<Object>() {
                        @Override
                        protected void onError(ApiException ex) {

                        }

                        @Override
                        public void onNext(Object s) {
                            Toast.makeText(UploadActivity.this,"反馈成功",Toast.LENGTH_SHORT).show();

                        }
                    });
                    mCompositeSubscription.add(subscription);
                }

        });
        iv_upload_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetImage.startForResult(UploadActivity.this,1);
//                add();
            }
        });

    }
    private void add(){
//        final String path = data.getStringExtra(Extras.EXTRA_FILE_PATH);
        //创建File对象，用于存储拍照后的图片
        File outputImage=new File(getExternalCacheDir(),"output_image.jpg");//getCacheDir是外部缓存，专业点的话应该先获取存储状态
        // 有getExternalCacheDir()就拿没有就拿外部缓存
        try{
            if (outputImage.exists()){
                outputImage.delete();
            }
            outputImage.createNewFile();
        }catch (Exception e){
            e.printStackTrace();

        }
        if (Build.VERSION.SDK_INT>=24){//getUriForFile接收三个参数，第一个context，第二个字符串，第三个就是刚刚创建的file对象当7.0开始本地url不能直接暴露，
            // FileProvider是一种特殊的内容提供器，将封装过的Url共享给外部，需要在清单文件中对其注册
            imageUri=FileProvider.getUriForFile(UploadActivity.this,"xu.yezhuang.retrofitrxtest.fileprovider",outputImage);
        }else {
            imageUri=Uri.fromFile(outputImage);
        }
        //启动相机程序
        Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);//指定图片的输出地址，填入刚得到的Url
        startActivityForResult(intent,TAKE_PHOTO);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("resultcode=1"+resultCode);
        switch (requestCode){
            case TAKE_PHOTO:
                if (resultCode==RESULT_OK){
                    try{

                        //将拍摄的照片显示出来
                        Bitmap bitmap= BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
//                        Bitmap bm=this.rotateBitmapByDegree(bitmap,90);//拍照后的图片被旋转了90度，调用这个方法改回来。严谨点的话需要先检测被旋转了多少度再掉用本方法。
//                        picture.setImageBitmap(bm);

                        final RelativeLayout rl = new RelativeLayout(UploadActivity.this);
                        RelativeLayout.LayoutParams rl_lp = new RelativeLayout.LayoutParams(360,360);
                        rl_lp.setMargins(0,0,20,0);
                        rl.setLayoutParams(rl_lp);
                        ImageView tmp = new ImageView(UploadActivity.this);
                         rl.addView(tmp,LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                         ImageView action = new ImageView(UploadActivity.this);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        //lp.setMargins(0,-10,10,0);
        action.setLayoutParams(lp);
        action.setImageResource(R.drawable.ic_delete);
        rl.addView(action);
//        tmp.setImageResource(R.drawable.ic_001);
                        tmp.setImageBitmap(bitmap);
        picture_container.addView(rl,0);
//        if(urls == null){
//            urls = new ArrayList<>();
//        }
//        urls.add(path);
        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                picture_container.removeView(rl);
//                urls.remove(path);
            }
        });
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                break;
            case 1://通过takePhoto选择之后返回的图片
                if(resultCode ==3) {
                    images = (ArrayList<TImage>) data.getSerializableExtra("images");
                    setImage();
                }
//            case CHOOSE_PHOTO:
//                if (resultCode==RESULT_OK){
//                    //判断手机系统版本号
//                    if ((Build.VERSION.SDK_INT>=19)){
//                        //4.4及以上系统使用这个方法处理图片
//                        handleImageOnKitKat(data);
//                    }else {
//                        //4.4以下使用这个方法处理图片
//                        handleImageBeforeKitKat(data);
//                    }
//                }
                break;
            default:
                break;
        }
    }

    /**
     * 基本权限管理
     */
    private void requestBasicPermission() {
        MPermission.with(UploadActivity.this)
                .addRequestCode(BASIC_PERMISSION_REQUEST_CODE)
                .permissions(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                )
                .request();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        MPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @OnMPermissionGranted(BASIC_PERMISSION_REQUEST_CODE)
    public void onBasicPermissionSuccess(){
        Toast.makeText(this, "授权成功", Toast.LENGTH_SHORT).show();
    }

    @OnMPermissionDenied(BASIC_PERMISSION_REQUEST_CODE)
    public void onBasicPermissionFailed(){
        Toast.makeText(this, "授权失败", Toast.LENGTH_SHORT).show();
    }


    //显示图片
    private void setImage(){


        for (int i=0;i<images.size();i++){
            try{
                images.get(0).getCompressPath();
                final RelativeLayout rl = new RelativeLayout(UploadActivity.this);
                RelativeLayout.LayoutParams rl_lp = new RelativeLayout.LayoutParams(360,360);
                rl_lp.setMargins(0,0,20,0);
                rl.setLayoutParams(rl_lp);
                ImageView tmp = new ImageView(UploadActivity.this);
                rl.addView(tmp,LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                ImageView action = new ImageView(UploadActivity.this);
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                //lp.setMargins(0,-10,10,0);
                action.setLayoutParams(lp);
                action.setImageResource(R.drawable.ic_delete);
                rl.addView(action);
//        tmp.setImageResource(R.drawable.ic_001);
                Glide.with(this).load(new File(images.get(i).getCompressPath())).into(tmp);
                picture_container.addView(rl,0);
//        if(urls == null){
//            urls = new ArrayList<>();
//        }
//        urls.add(path);
                action.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        picture_container.removeView(rl);
//                urls.remove(path);
                    }
                });
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
