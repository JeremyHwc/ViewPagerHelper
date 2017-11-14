package com.zhengsr.viewpagerlib.callback;

import android.view.View;

/**
 * @author JeremyHwc;
 * @date 2017/11/14/014 11:26;
 * @email jeremy_hwc@163.com ;
 * @desc
 */

public interface PageHelperListener<T> {
    void getItemView(View view, T data);
}
