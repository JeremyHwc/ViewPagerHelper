package com.zhengsr.viewpagerlib.bean;

import android.view.View;

import java.util.List;

/**
 * @author JeremyHwc;
 * @date 2017/11/14/014 10:45;
 * @email jeremy_hwc@163.com ;
 * @desc 使用建造者模式对PageBean进行构造
 */

public class PageBean {

    public View bottomLayout;//Indicator
    public View openview;
    public List<Object> datas;//数据集

    public PageBean(Builder builder) {
        this.bottomLayout = builder.bottomLayout;
        this.openview = builder.openview;
        this.datas = builder.datas;
    }

    public static class Builder<T> {
        View bottomLayout;
        View openview;
        List<T> datas;

        public Builder setIndicator(View bottomLayout) {
            this.bottomLayout = bottomLayout;
            return this;
        }

        public Builder setOpenView(View openView) {
            this.openview = openView;
            return this;
        }

        public Builder setDataObjects(List<T> datas) {
            this.datas = datas;
            return this;
        }

        public PageBean builder() {
            return new PageBean(this);
        }
    }

}
