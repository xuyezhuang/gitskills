package cn.dooer.ydz.retrofitrxtest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import cn.dooer.ydz.retrofitrxtest.net.ApiClient;
import cn.dooer.ydz.retrofitrxtest.net.ApiException;
import rx.Subscription;

/**
 * Created by zex on 2017/8/13.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText mEtPhone,mEtPassword;
    private TextView mTvLogin,mTvRegister,mTvForgetPassword;
    private CheckBox mCbRememberPassword;
    private TFragment currentFragment,myCenterFragment;
    private Boolean check=false;

    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }

    public static void startForResult(Activity context,int requestCode){
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivityForResult(intent,requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        ToolBarOptions options = new ToolBarOptions();
//        options.titleId = R.string.user_login;
//        setToolBar(R.id.toolbar,options);
//        mTvLogin = findView(R.id.tv_login);
//        mTvRegister = findView(R.id.tv_register);
//        mTvForgetPassword = findView(R.id.tv_forget_pw);
//        mEtPassword = findView(R.id.et_pw);
//        mEtPhone = findView(R.id.et_phone);
//       Toast.makeText(this,"微信版注册用户请走忘记密码流程添加密码",Toast.LENGTH_LONG).show();
//        mCbRememberPassword = findView(R.id.cb_remember);
        mCbRememberPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    check=true;
                }
            }
        });
        mTvForgetPassword.setOnClickListener(this);
        mTvRegister.setOnClickListener(this);
        mTvLogin.setOnClickListener(this);
//        if (Preferences.getPassword()!=null&&Preferences.getuserName()!=null){
//            toLogin(Preferences.getuserName(),Preferences.getPassword());
//        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
//            case R.id.tv_login:
//                login();
//                break;
//            case R.id.tv_register:
//                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
//                break;
//            case R.id.tv_forget_pw:
//                startActivity(new Intent(this,ForgetPasswordFirstStepActivity.class));
//                break;
        }
    }

    private void login() {
            final String phone = mEtPhone.getText().toString().trim();
            final String password = mEtPassword.getText().toString().trim();
            if (phone == null || phone.isEmpty()) {
//                Toasty.normal(this, "请输入手机号或昵称").show();
                return;
            }
            if (password == null || password.isEmpty()) {
//                Toasty.normal(this, "请输入密码").show();
                return;
            }
            String encrypt = "";
            try {
                encrypt = DESUtil.encode(password);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (check) {
                Preferences.setUserName(phone);
                Preferences.setPassword(encrypt);
            }
//            Subscription subscription = ApiClient.getInstance().login(phone, encrypt).subscribe(new ProgressObserver<LoginInfo>(this) {
//                @Override
//                public void onNext(LoginInfo o) {
//                    getUserInfo();
//                }
//            });
//            mCompositeSubscription.add(subscription);
        }
//    }
    public void getUserInfo(){
        Subscription subscription = ApiClient.getInstance().getUserInfo().subscribe(new HttpObserver<Login>() {
            @Override
            protected void onError(ApiException ex) {
//                Toasty.normal(LoginActivity.this,ex.getMsg()).show();
            }
            @Override
            public void onNext(Login userInfo) {
                Preferences.setIsLogin(/*userInfo.isIsLogin()*/true);
                ApplicationCache.setUser(userInfo);
                setResult(RESULT_OK);
                finish();
            }
        });
        mCompositeSubscription.add(subscription);//防止rx的内存泄漏
    }
    public void toLogin(String phone,String encrypt){
//        Subscription subscription = ApiClient.getInstance().login(phone, encrypt).subscribe(new ProgressObserver<LoginInfo>(this) {
//            @Override
//            public void onNext(LoginInfo o) {
//
//                getUserInfo();
//            }
//        });
//        mCompositeSubscription.add(subscription);
    }
}
