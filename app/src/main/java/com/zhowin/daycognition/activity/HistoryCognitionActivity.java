package com.zhowin.daycognition.activity;


import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.zhowin.daycognition.R;
import com.zhowin.daycognition.adapter.HistoryCognitionAdapter;
import com.zhowin.daycognition.base.BaseActivity;
import com.zhowin.daycognition.databinding.ActivityHistoryCognitionBinding;
import com.zhowin.daycognition.model.HistoryList;
import com.zhowin.daycognition.utils.ActivityManager;
import com.zhowin.daycognition.view.DivideLineItemDecoration;
import com.zhowin.dbdao.DaoManagerUtils;

import java.util.List;

/**
 * 历史日知
 */
public class HistoryCognitionActivity extends BaseActivity<ActivityHistoryCognitionBinding> {


    public static void start(Context context) {
        Intent intent = new Intent(context, HistoryCognitionActivity.class);
        context.startActivity(intent);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_history_cognition;
    }

    @Override
    public void initView() {
        setOnClick(R.id.ivReturnBack);
    }

    @Override
    public void initData() {
        List<HistoryList> historyLists = DaoManagerUtils.queryAllHistoryData();
        if (historyLists != null && !historyLists.isEmpty()) {
            HistoryCognitionAdapter historyCognitionAdapter = new HistoryCognitionAdapter(historyLists);
            mBinding.historyRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            mBinding.historyRecyclerView.addItemDecoration(new DivideLineItemDecoration(mContext, getBaseColor(R.color.color_f6f6f6), 1));
            mBinding.historyRecyclerView.setAdapter(historyCognitionAdapter);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivReturnBack:
                ActivityManager.getAppInstance().finishActivity();
                break;
        }
    }
}
