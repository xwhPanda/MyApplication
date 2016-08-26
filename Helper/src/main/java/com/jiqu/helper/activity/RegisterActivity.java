package com.jiqu.helper.activity;

import android.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jiqu.database.AccountDao;
import com.jiqu.database.DaoMaster;
import com.jiqu.helper.BaseActivity;
import com.jiqu.helper.R;
import com.jiqu.helper.application.HelperApplication;
import com.jiqu.helper.data.RegisterData;
import com.jiqu.helper.interfaces.GetDataCallback;
import com.jiqu.helper.okhttp.OkHttpManager;
import com.jiqu.helper.okhttp.OkHttpRequest;
import com.jiqu.helper.tools.MD5;
import com.jiqu.helper.tools.RequestTools;
import com.jiqu.helper.tools.Tools;
import com.jiqu.helper.view.TitleBar;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * Created by xiongweihua on 2016/8/24.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener{
    private RadioButton manRadio,femaleRadio;
    private RadioGroup genderGroup;
    private TitleBar titleBar;
    private EditText nicknameEdit,phoneNumberEdit,emailEdit,passwordEdit,confirmPasswordEdit;
    private Button registerButton;
    /** 1:男；2:女 **/
    private int gender = 0;
    private boolean registering = false;

    @Override
    public int getContentView() {
        return R.layout.login_layout;
    }

    @Override
    public void initView() {
        titleBar = (TitleBar) findViewById(R.id.title_bar);
        manRadio = (RadioButton) findViewById(R.id.man_radio);
        femaleRadio = (RadioButton) findViewById(R.id.female_radio);
        genderGroup = (RadioGroup) findViewById(R.id.gender_group);
        nicknameEdit = (EditText) findViewById(R.id.nickname_edit);
        phoneNumberEdit = (EditText) findViewById(R.id.phone_number_edit);
        emailEdit = (EditText) findViewById(R.id.email_edit);
        passwordEdit = (EditText) findViewById(R.id.password_edit);
        confirmPasswordEdit = (EditText) findViewById(R.id.confirm_password_edit);
        registerButton = (Button) findViewById(R.id.register_btn);

        manRadio.setPadding(15,0,0,0);
        femaleRadio.setPadding(15,0,0,0);
        titleBar.setShareVisible(View.INVISIBLE);
        titleBar.setActivity(this);
        titleBar.setTitle("账号注册");

        genderGroup.setOnCheckedChangeListener(this);
        registerButton.setOnClickListener(this);
    }

    @Override
    public void initViewSize() {
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.register_btn){
            if (registering){
                Tools.showToast(this,"正在注册");
                return;
            }
            registering = true;
            register();
        }
    }

    private void register(){
        String nickname = nicknameEdit.getText().toString();
        String phoneNumber = phoneNumberEdit.getText().toString();
        String email = emailEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        String confirmPassword = confirmPasswordEdit.getText().toString();
        if (TextUtils.isEmpty(nickname) || TextUtils.isEmpty(phoneNumber) || TextUtils.isEmpty(email)
                || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword) || gender == 0){
            Tools.showToast(this,"请填写好相关资料");
        }else {
            if (!password.equals(confirmPassword)){
                Tools.showToast(this,"两次密码不一致");
                return;
            }
            RequestBody params = new FormBody.Builder()
                    .add("username",nickname)
                    .add("nickname",nickname)
                    .add("email",email)
                    .add("password",MD5.getMD5Code(password))
                    .add("gender",String.valueOf(gender))
                    .add("rePasswd", MD5.getMD5Code(confirmPassword))
                    .build();
            OkHttpManager.getInstance().execute(new OkHttpRequest(RequestTools.REGISTER_ACCOUNT, "", null, null).build("POST",params), RegisterData.class, new GetDataCallback() {
                @Override
                public void onFailed(Call call, IOException e) {
                    Tools.showToast(RegisterActivity.this,"注册失败");
                    registering = false;
                }

                @Override
                public void onSucceed(Call call, Object data) {
                    registering = false;
                    RegisterData registerData = (RegisterData) data;
                    if (registerData != null){
                        if (registerData.getStatus() == 1 && registerData.getData() != null){
                            AccountDao accountDao = HelperApplication.daoSession.getAccountDao();
                            accountDao.insertOrReplace(RegisterData.AccountInfo.toAccount(registerData.getData()));
                            Tools.showToast(RegisterActivity.this,"注册成功");
                            RegisterActivity.this.finish();
                        }else {
                            Tools.showToast(RegisterActivity.this,"注册失败");
                        }
                    }
                }
            });
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.man_radio)
            gender = 1;
        else if (checkedId == R.id.female_radio)
            gender = 2;
    }
}
