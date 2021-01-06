package com.zhowin.daycognition.callback;


import androidx.core.widget.NestedScrollView;

/**
 * function  : 滑动监听
 */
public interface NestedScrollViewListener {

    /*** 在滑动的时候调用*/
    void onScrollChanged(NestedScrollView scrollView, int scrollX, int scrollY, int oldScrollX, int oldScrollY);

    /*** 在滑动的时候调用，scrollY为已滑动的距离*/
    void onScroll(int scrollY);
}
