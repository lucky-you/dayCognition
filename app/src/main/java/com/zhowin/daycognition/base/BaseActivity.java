package com.zhowin.daycognition.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.zhowin.daycognition.R;
import com.zhowin.daycognition.utils.ActivityManager;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * author : zho
 * date  ：2021/1/6
 * desc ：
 */
public abstract class BaseActivity<VDB extends ViewDataBinding> extends SupportActivity implements View.OnClickListener {
    protected AppCompatActivity mContext;
    protected VDB mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);   //设定为竖屏
        mContext = this;
        setContent();
        ActivityManager.getAppInstance().addActivity(this);
        initView();
        initData();
        initListener();
        KeyboardUtils.fixAndroidBug5497(mContext);
        KeyboardUtils.fixSoftInputLeaks(mContext);
    }

    public void setContent() {
        setContentView(getLayoutId());
        mBinding = DataBindingUtil.setContentView(this, getLayoutId());
        BarUtils.setStatusBarColor(mContext, getBaseColor(R.color.theme_color));
        BarUtils.addMarginTopEqualStatusBarHeight(findViewById(R.id.topView));// 其实这个只需要调用一次即可
    }

    public abstract int getLayoutId();

    public abstract void initView();

    public abstract void initData();

    public void initListener() {

    }

    public <E extends View> E get(int id) {
        return (E) findViewById(id);
    }

    public int getBaseColor(int colorId) {
        return mContext.getResources().getColor(colorId);
    }

    @Override
    public void onClick(View v) {

    }

    public void setOnClick(int... ids) {
        for (int i = 0; i < ids.length; i++) {
            get(ids[i]).setOnClickListener(this);
        }
    }
}
