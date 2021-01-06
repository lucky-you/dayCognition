package com.zhowin.daycognition.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.zhowin.daycognition.R;
import com.zhowin.daycognition.base.BaseActivity;
import com.zhowin.daycognition.databinding.ActivityCreateCognitionBinding;
import com.zhowin.daycognition.utils.ActivityManager;
import com.zhowin.daycognition.utils.ConstantValue;
import com.zhowin.daycognition.utils.DateHelperUtil;
import com.zhowin.dbdao.DaoManagerUtils;

import java.io.File;
import java.util.Random;

/**
 * 生成认知
 */
public class CreateCognitionActivity extends BaseActivity<ActivityCreateCognitionBinding> {


    private String contentCognition, currentDayTime;

    public static void start(Context context, String content) {
        Intent intent = new Intent(context, CreateCognitionActivity.class);
        intent.putExtra(ConstantValue.CONTENT, content);
        context.startActivity(intent);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_create_cognition;
    }

    @Override
    public void initView() {
        setOnClick(R.id.ivReturnBack, R.id.tvSaveCognition, R.id.tvHistoryCognition);
    }

    @Override
    public void initData() {
        contentCognition = getIntent().getStringExtra(ConstantValue.CONTENT);
        mBinding.tvContent.setText(contentCognition);
        if (contentCognition.length() <= 50) {
            mBinding.tvContent.setTextSize(23);
            mBinding.llCognitionLayout.getLayoutParams().height = SizeUtils.dp2px(160);
        } else if (contentCognition.length() > 50 && contentCognition.length() <= 100) {
            mBinding.tvContent.setTextSize(21);
            mBinding.llCognitionLayout.getLayoutParams().height = SizeUtils.dp2px(200);
        } else if (contentCognition.length() > 100 && contentCognition.length() <= 150) {
            mBinding.tvContent.setTextSize(19);
            mBinding.llCognitionLayout.getLayoutParams().height = SizeUtils.dp2px(240);
        } else if (contentCognition.length() > 150 && contentCognition.length() <= 200) {
            mBinding.tvContent.setTextSize(18);
            mBinding.llCognitionLayout.getLayoutParams().height = SizeUtils.dp2px(280);
        } else if (contentCognition.length() > 200 && contentCognition.length() <= 250) {
            mBinding.llCognitionLayout.getLayoutParams().height = SizeUtils.dp2px(300);
            mBinding.tvContent.setTextSize(17);
        } else if (contentCognition.length() > 250 && contentCognition.length() <= 300) {
            mBinding.tvContent.setTextSize(16);
            mBinding.llCognitionLayout.getLayoutParams().height = SizeUtils.dp2px(300);
        } else if (contentCognition.length() > 300) {
            mBinding.tvContent.setTextSize(15);
            mBinding.llCognitionLayout.getLayoutParams().height = SizeUtils.dp2px(320);
        }

        //当前时间
        currentDayTime = DateHelperUtil.getCurrentDayTime();
        mBinding.tvCurrentDayTime.setText(currentDayTime);

        //随机获取颜色
        Random random = new Random();
        int index = random.nextInt(ConstantValue.bgColorList.length);
        int color = ConstantValue.bgColorList[index];
        mBinding.llCognitionLayout.setBackgroundColor(getBaseColor(color));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivReturnBack:
                ActivityManager.getAppInstance().finishActivity();
                break;
            case R.id.tvHistoryCognition:
                HistoryCognitionActivity.start(mContext);
                break;
            case R.id.tvSaveCognition:
                requestPermission();
                break;
        }
    }


    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PermissionUtils.permission(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .callback(new PermissionUtils.SimpleCallback() {
                        @Override
                        public void onGranted() {
                            saveCognitionToAlbum(mBinding.llCognitionLayout);
                        }

                        @Override
                        public void onDenied() {
                        }
                    }).request();
        }
    }

    private void saveCognitionToAlbum(View cognitionRootLayout) {
        Bitmap bitmapSrc = getCacheBitmapFromView(cognitionRootLayout);
        ThreadUtils.executeBySingle(new ThreadUtils.SimpleTask<File>() {
            @Override
            public File doInBackground() throws Throwable {
                return ImageUtils.save2Album(bitmapSrc, Bitmap.CompressFormat.JPEG);
            }

            @Override
            public void onSuccess(File result) {
                if (result != null) {
                    ToastUtils.showLong("保存成功");
                    DaoManagerUtils.insertHistoryDao(contentCognition, currentDayTime);
                } else {
                    ToastUtils.showLong("保存失败");
                }
            }
        });
    }
}
