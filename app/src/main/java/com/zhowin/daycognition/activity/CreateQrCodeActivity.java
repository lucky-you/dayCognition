package com.zhowin.daycognition.activity;



import android.content.Context;
import android.content.Intent;

import com.zhowin.daycognition.R;
import com.zhowin.daycognition.base.BaseActivity;
import com.zhowin.daycognition.databinding.ActivityCreateQrCodeBinding;

/**
 * 生成二维码
 */
public class CreateQrCodeActivity extends BaseActivity<ActivityCreateQrCodeBinding> {


    public static void start(Context context, String content) {
        Intent intent = new Intent(context, CreateQrCodeActivity.class);
        intent.putExtra("",content);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_create_qr_code;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
