package com.zhengsr.viewpagerlib.anim;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by Administrator on 2017/11/8.
 */

/**
 * https://www.cnblogs.com/baiyi168/p/6946594.html
 */
public class MzTransformer implements ViewPager.PageTransformer {
    private static final float MAX_SCALE = 1.0f;
    private static final float MIN_SCALE = 0.9f;//0.85f

    /**
     * Apply a property transformation to the given page.
     *
     * @param page Apply the transformation to this page
     * @param position Position of page relative to the current front-and-center
     *                 position of the pager. 0 is front and center. 1 is one full
     *                 page position to the right, and -1 is one page position to the left.
     */
    @Override
    public void transformPage(View page, float position) {
        //setScaleY只支持api11以上
        if (position < -1) {
            // page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
        } else if (position <= 1) //a页滑动至b页 ； a页从 0.0 -1 ；b页从1 ~ 0.0
        { // [-1,1]
//              Log.e("TAG", page + " , " + position + "");
            float scaleFactor = MIN_SCALE + (1 - Math.abs(position)) * (MAX_SCALE - MIN_SCALE);
            //  page.setScaleX(scaleFactor);
            //每次滑动后进行微小的移动目的是为了防止在三星的某些手机上出现两边的页面为显示的情况
            page.setScaleY(scaleFactor);
        } else { // (1,+Infinity]
            // page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
        }
    }
}
