package com.zhengsr.viewpagerlib.callback;

/**
 * @author JeremyHwc;
 * @date 2017/11/14/014 11:25;
 * @email jeremy_hwc@163.com ;
 * @desc
 */
public interface BitmapListener {
    void onSuccess(Object responseObj);
    void onFailure(Object errorObj);
}