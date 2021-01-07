package com.zhowin.daycognition.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.daycognition.R;
import com.zhowin.daycognition.model.HistoryList;

import java.util.List;

/**
 * author : zho
 * date  ：2021/1/6
 * desc ：历史日知列表
 */
public class HistoryCognitionAdapter extends BaseQuickAdapter<HistoryList, BaseViewHolder> {
    public HistoryCognitionAdapter(@Nullable List<HistoryList> data) {
        super(R.layout.include_history_cognition_item_view, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, HistoryList item) {
        System.out.println("info:" + item.toString());
        helper.setText(R.id.tvContent, item.contentText)
                .setText(R.id.tvCreateTime, item.createTime);

    }
}
