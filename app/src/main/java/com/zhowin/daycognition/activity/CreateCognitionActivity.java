package com.zhowin.daycognition.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zhowin.daycognition.R;
import com.zhowin.daycognition.base.BaseActivity;
import com.zhowin.daycognition.databinding.ActivityCreateCognitionBinding;

/**
 * 生成认知
 */
public class CreateCognitionActivity extends BaseActivity<ActivityCreateCognitionBinding> {


    public static void start(Context context,String content) {
        Intent intent = new Intent(context, CreateCognitionActivity.class);
        intent.putExtra("",content);
        context.startActivity(intent);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_create_cognition;
    }

    @Override
    public void initView() {
        findViewById(R.id.ivReturnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initData() {

    }
}
