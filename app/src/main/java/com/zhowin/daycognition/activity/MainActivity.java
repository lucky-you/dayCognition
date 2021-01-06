package com.zhowin.daycognition.activity;


import android.text.Editable;
import android.text.InputFilter;
import android.view.View;

import com.zhowin.daycognition.R;
import com.zhowin.daycognition.base.BaseActivity;
import com.zhowin.daycognition.callback.OnTextChangedListener;
import com.zhowin.daycognition.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private int maxLength = 350;
    private String editContent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        setOnClick(R.id.tvCreateQrCode, R.id.tvCreateCognition);
    }

    @Override
    public void initData() {
        mBinding.tvEditMaxNumber.setText("0/" + maxLength);
        mBinding.editContent.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
        mBinding.editContent.addTextChangedListener(new OnTextChangedListener() {
            @Override
            public void afterTextChanged(Editable editable) {
                editContent = editable.toString();
                if (editContent.length() <= maxLength)
                    mBinding.tvEditMaxNumber.setText(editContent.length() + "/" + maxLength);
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvCreateQrCode:
                CreateQrCodeActivity.start(mContext, editContent);
                break;
            case R.id.tvCreateCognition:
                CreateCognitionActivity.start(mContext, editContent);
                break;
        }
    }
}
