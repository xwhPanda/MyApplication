package com.jiqu.helper.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiqu.helper.BaseActivity;
import com.jiqu.helper.R;
import com.jiqu.helper.view.CircleImageView;
import com.jiqu.helper.view.TitleBar;

import java.io.File;

/**
 * Created by xiongweihua on 2016/8/22.
 */
public class AccountInfoActivity extends BaseActivity implements View.OnClickListener {
    private boolean isModify = false;
    private final int TAKE_PHOTO_OK = 1;
    private File photoFile;
    private String path;
    private TitleBar titleBar;
    private CircleImageView accountIcon;
    private Button modifyBtn;
    private TextView modifyTip;
    private LinearLayout modifyLin;
    private Button takePhoto, choicePhoto;
    private Button modifyConfirm;
    private View emptyView;
    private EditText nameEdit,birthEdit,phoneNumberEdit,emailEdit;
    private boolean modifing = false;

    @Override
    public int getContentView() {
        return R.layout.account_info_layout;
    }

    @Override
    public void initView() {
        path = Environment.getExternalStorageDirectory() + "/helper/image/";
        photoFile = new File(path);
        if (!photoFile.exists()) {
            photoFile.mkdirs();
        }
        titleBar = (TitleBar) findViewById(R.id.titleBar);
        accountIcon = (CircleImageView) findViewById(R.id.account_icon);
        modifyBtn = (Button) findViewById(R.id.modifyBtn);
        modifyTip = (TextView) findViewById(R.id.modifyTip);
        modifyLin = (LinearLayout) findViewById(R.id.modifyLin);
        takePhoto = (Button) findViewById(R.id.take_photo);
        choicePhoto = (Button) findViewById(R.id.choice_photo);
        emptyView = (View) findViewById(R.id.emptyView);
        modifyConfirm = (Button) findViewById(R.id.modifyConfirm);
        nameEdit = (EditText) findViewById(R.id.name_edit);
        birthEdit = (EditText) findViewById(R.id.birth_edit);
        phoneNumberEdit = (EditText) findViewById(R.id.phone_number_edit);
        emailEdit = (EditText) findViewById(R.id.email_edit);

        titleBar.setActivity(this);
        titleBar.setShareVisible(View.INVISIBLE);
        titleBar.setTitle("个人资料");

        modifyBtn.setOnClickListener(this);
        takePhoto.setOnClickListener(this);
        choicePhoto.setOnClickListener(this);
        modifyConfirm.setOnClickListener(this);
    }

    @Override
    public void initViewSize() {

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_CANCELED) {//通过结果码来判断是否拍取了图片
            switch (requestCode) {//通过请求码来判断是哪个请求的数据
                case TAKE_PHOTO_OK:
                    if (data != null) {
                        Bundle bundle = data.getExtras();
                        Bitmap bitmap = bundle.getParcelable("data");
                        accountIcon.setImageBitmap(bitmap);
                    }
                    break;
            }
        }
    }

    private void changeState(boolean state){
        modifyBtn.setEnabled(state);
        nameEdit.setEnabled(state);
        birthEdit.setEnabled(state);
        phoneNumberEdit.setEnabled(state);
        emailEdit.setEnabled(state);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.modifyConfirm) {
            if (modifing) {//提交修改
                modifing = false;
                changeState(modifing);
                modifyConfirm.setText("修改");
            } else {//编辑修改信息
                modifing = true;
                changeState(modifing);
                modifyConfirm.setText("提交修改");
            }
        } else {
            switch (v.getId()) {
                case R.id.modifyBtn:
                    if (isModify) {
                        modifyBtn.setBackgroundResource(R.mipmap.modify);
                        modifyTip.setText("更换头像");
                        modifyLin.setVisibility(View.GONE);
                        emptyView.setVisibility(View.VISIBLE);
                        isModify = false;
                    } else {
                        modifyBtn.setBackgroundResource(R.mipmap.del);
                        modifyTip.setText("取消修改");
                        modifyLin.setVisibility(View.VISIBLE);
                        emptyView.setVisibility(View.GONE);
                        isModify = true;
                    }
                    break;
                case R.id.take_photo:
                    Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    Uri imageUri = Uri.fromFile(new File(path + "account.png"));
                    // 指定照片保存路径（SD卡），temp.jpg为一个临时文件，每次拍照后这个图片都会被替换
//                intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                    startActivityForResult(intentFromCapture, TAKE_PHOTO_OK);
                    break;
                case R.id.choice_photo:
                    break;
                case R.id.modifyConfirm:
                    break;
            }
        }
    }
}
