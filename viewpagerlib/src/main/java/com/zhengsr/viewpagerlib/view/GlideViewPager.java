package com.zhengsr.viewpagerlib.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhengsr.viewpagerlib.callback.PageHelperListener;
import com.zhengsr.viewpagerlib.bean.PageBean;
import com.zhengsr.viewpagerlib.indicator.NormalIndicator;
import com.zhengsr.viewpagerlib.indicator.TextIndicator;
import com.zhengsr.viewpagerlib.indicator.TransIndicator;
import com.zhengsr.viewpagerlib.indicator.ZoomIndicator;

import java.util.List;

/**
 * @author JeremyHwc;
 * @date 2017/11/14/014 11:09;
 * @email jeremy_hwc@163.com ;
 * @desc
 */
public class GlideViewPager extends ViewPager {
    private LayoutInflater mInflater;

    public GlideViewPager(Context context) {
        this(context,null);
    }

    public GlideViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mInflater = LayoutInflater.from(context);
    }

    /**
     * 设置数据以及加载的布局
     * @param bean
     * @param layoutid
     * @param listener
     */
    public void setPageListener(PageBean bean, int layoutid, PageHelperListener listener){
        CusViewPagerAdapter adapter = new CusViewPagerAdapter<>(bean.datas,layoutid,listener);
        setAdapter(adapter);

        setOffscreenPageLimit(3);
//        setCurrentItem(bean.datas.size());
        setCurrentItem(0);
        if (bean.bottomLayout != null){
            //选择不同的indicator
            if (bean.bottomLayout instanceof NormalIndicator){
                ((NormalIndicator) bean.bottomLayout).addPagerData(bean,this);
            }
            if (bean.bottomLayout instanceof TransIndicator){
                ((TransIndicator) bean.bottomLayout).addPagerData(bean,this);
            }
            if (bean.bottomLayout instanceof ZoomIndicator){
                ((ZoomIndicator) bean.bottomLayout).addPagerData(bean,this);
            }
            if (bean.bottomLayout instanceof TextIndicator){
                ((TextIndicator) bean.bottomLayout).addPagerData(bean,this);
            }
        }
    }

    /**
     * ViewpagerAdapter
     * @param <T>
     */
    class CusViewPagerAdapter<T> extends PagerAdapter{
        List<T> list;
        int layoutid;
        PageHelperListener listener;

        public CusViewPagerAdapter(List<T> list,int layoutid,PageHelperListener listener) {
            this.listener = listener;
            this.list = list;
            this.layoutid = layoutid;
        }

        @Override
        public int getCount() {
            return this.list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        /**
         * Create the page for the given position
         * @param container The containing View in which the page will be shown.
         * @param position The page position to be instantiated.
         * @return Returns an Object representing the new page.  This does not
         * need to be a View, but can be some other container of the page.
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mInflater.inflate(layoutid,null);
            this.listener.getItemView(view, this.list.get(position));
            container.addView(view,0);
            return view;
        }

        /**
         * Remove a page for the given position.
         * @param container The containing View from which the page will be removed.
         * @param position The page position to be removed.
         * @param object The same object that was returned by
         * {@link #instantiateItem(View, int)}.
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
