package com.zhowin.daycognition.activity;


import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;

import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.zhowin.daycognition.R;
import com.zhowin.daycognition.base.BaseActivity;
import com.zhowin.daycognition.callback.OnTextChangedListener;
import com.zhowin.daycognition.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private int maxLength = 365;
    private String editContent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        setOnClick(R.id.tvOneKeyDelete, R.id.tvCreateQrCode, R.id.tvCreateCognition);
    }

    @Override
    public void initData() {
        mBinding.editContent.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
        mBinding.editContent.addTextChangedListener(new OnTextChangedListener() {
            @Override
            public void afterTextChanged(Editable editable) {
                editContent = editable.toString();
                if (!TextUtils.isEmpty(editContent)) {
                    mBinding.tvOneKeyDelete.setVisibility(View.VISIBLE);
                    if (editContent.length() <= maxLength)
                        mBinding.tvEditMaxNumber.setText(editContent.length() + "/" + maxLength);
                } else {
                    mBinding.tvOneKeyDelete.setVisibility(View.GONE);
                    mBinding.tvEditMaxNumber.setText("0/" + maxLength);
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvOneKeyDelete:
                mBinding.editContent.setText("");
                KeyboardUtils.hideSoftInput(mContext);
                mBinding.tvOneKeyDelete.setVisibility(View.GONE);
                break;
            case R.id.tvCreateQrCode:
                if (TextUtils.isEmpty(editContent)) {
                    ToastUtils.showLong("内容不能为空哦");
                    return;
                }

                CreateQrCodeActivity.start(mContext, editContent);
                break;
            case R.id.tvCreateCognition:
                if (TextUtils.isEmpty(editContent)) {
                    ToastUtils.showLong("内容不能为空哦");
                    return;
                }
                KeyboardUtils.hideSoftInput(mContext);
                CreateCognitionActivity.start(mContext, editContent);
                break;
        }

    }
}
