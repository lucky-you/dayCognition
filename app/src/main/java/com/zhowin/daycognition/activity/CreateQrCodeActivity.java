package com.zhowin.daycognition.activity;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.view.View;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.SnackbarUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.UtilsTransActivity;
import com.zhowin.daycognition.R;
import com.zhowin.daycognition.base.BaseActivity;
import com.zhowin.daycognition.databinding.ActivityCreateQrCodeBinding;
import com.zhowin.daycognition.utils.ActivityManager;
import com.zhowin.daycognition.utils.ConstantValue;
import com.zhowin.daycognition.utils.DateHelperUtil;
import com.zhowin.daycognition.utils.QRCodeUtils;

import java.io.File;
import java.util.List;

/**
 * 生成二维码
 */
public class CreateQrCodeActivity extends BaseActivity<ActivityCreateQrCodeBinding> {

    private String contentQrText;

    public static void start(Context context, String content) {
        Intent intent = new Intent(context, CreateQrCodeActivity.class);
        intent.putExtra(ConstantValue.CONTENT, content);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_create_qr_code;
    }

    @Override
    public void initView() {
        setOnClick(R.id.ivReturnBack, R.id.tvSaveQrCode);
    }

    @Override
    public void initData() {
        contentQrText = getIntent().getStringExtra(ConstantValue.CONTENT);
        Bitmap bitmap = QRCodeUtils.createQRCode(contentQrText);
        mBinding.ivQrImage.setImageBitmap(bitmap);
        String currentDayTime = DateHelperUtil.getCurrentDayTime();
        mBinding.tvCurrentDayTime.setText(currentDayTime);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivReturnBack:
                ActivityManager.getAppInstance().finishActivity();
                break;
            case R.id.tvSaveQrCode:
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
                            saveQrCodeToAlbum(mBinding.clQrRootLayout);
                        }

                        @Override
                        public void onDenied() {
                        }
                    }).request();
        }
    }

    private void saveQrCodeToAlbum(View llQrCodeLayout) {
        Bitmap bitmapSrc = getCacheBitmapFromView(llQrCodeLayout);
        ThreadUtils.executeBySingle(new ThreadUtils.SimpleTask<File>() {
            @Override
            public File doInBackground() throws Throwable {
                return ImageUtils.save2Album(bitmapSrc, Bitmap.CompressFormat.JPEG);
            }

            @Override
            public void onSuccess(File result) {
                if (result != null) {
                    ToastUtils.showLong("保存成功");
                } else {
                    ToastUtils.showLong("保存失败");
                }
            }
        });
    }
}
