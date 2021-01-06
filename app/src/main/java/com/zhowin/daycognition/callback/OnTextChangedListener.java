package com.zhowin.daycognition.callback;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * description: EditText监听的回调
 */
public abstract class OnTextChangedListener implements TextWatcher {


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public abstract void afterTextChanged(Editable editable);

}
