package com.zhengsr.viewpagerlib;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.zhengsr.viewpagerlib.callback.BitmapListener;

/**
 * @author JeremyHwc;
 * @date 2017/11/14/014 10:41;
 * @email jeremy_hwc@163.com ;
 * @desc 使用建造者模式对Glide加载图片进行封装
 */
public class GlideManager {
    public static final int BITMAP_SCAN_CENTER_CROP = 1;
    public static final int BITMAP_SCAN_FIT_CENTER = 2;
    private BitmapListener mBitmapListener;

    public GlideManager(Builder builder) {
        RequestOptions options = new RequestOptions().placeholder(builder.placeresid);

        if (builder.eroorresid != 0) {
            options.error(builder.eroorresid);
        }

        switch (builder.type) {
            case BITMAP_SCAN_CENTER_CROP:
                options.centerCrop();
                break;
            case BITMAP_SCAN_FIT_CENTER:
                options.fitCenter();
                break;
            default:
                break;
        }
        if (builder.setCircleCrop) {
            options.circleCrop();
        }
        if (builder.radius != 0) {
            options.transform(new RoundedCorners(builder.radius));
        }

        RequestBuilder  requestBuilder = Glide.with(builder.context).load(builder.source);

        if (builder.animtime > 0) {
            requestBuilder.transition(new DrawableTransitionOptions().crossFade(builder.animtime));
        }

        requestBuilder.apply(options) .listener(new LoadListener()) .into(builder.imageView);
    }


    /**
     * 设置Glide加载图片监听
     * @param listener
     * @return
     */
    public GlideManager addLoadLstner(BitmapListener listener) {
        mBitmapListener = listener;
        return this;
    }

    /**
     * Glide加载监听
     */
    class LoadListener implements RequestListener {

        @Override
        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
            if (mBitmapListener != null) {
                mBitmapListener.onFailure(e);
            }
            return false;
        }

        @Override
        public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean isFirstResource) {
            if (mBitmapListener != null) {
                mBitmapListener.onSuccess(resource);
            }
            return false;
        }
    }

    public static class Builder {
        Context context;
        int eroorresid;
        int placeresid;
        Object source;
        boolean setCircleCrop;
        int radius = 0;
        int type;
        int animtime;
        ImageView imageView;

        /**
         * 设置上下文
         * @param context
         * @return
         */
        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }

        /**
         * 设置图片缩放类型
         * @param type
         * @return
         */
        public Builder setBitmapScanType(int type) {
            this.type = type;
            return this;
        }

        /**
         * 设置加载图
         * @param resid
         * @return
         */
        public Builder setLoadingBitmap(int resid) {
            this.placeresid = resid;
            return this;
        }

        /**
         * 设置加载错误图
         * @param resid
         * @return
         */
        public Builder setErrorBitmap(int resid) {
            this.eroorresid = resid;
            return this;
        }


        /**
         * 设置图片资源
         * @param source
         * @return
         */
        public Builder setImgSource(Object source) {
            this.source = source;
            return this;
        }

        /**
         * 设置环形裁剪
         * @param setCircleCrop
         * @return
         */
        public Builder setCircleCrop(boolean setCircleCrop) {
            this.setCircleCrop = setCircleCrop;
            return this;
        }

        /**
         * 设置环形裁剪半径
         * @param radius
         * @return
         */
        public Builder setRoundCrop(int radius) {
            this.radius = radius;
            return this;
        }


        /**
         * 设置动画时间
         * @param animtime
         * @return
         */
        public Builder setAnimation(int animtime) {
            this.animtime = animtime;
            return this;
        }


        /**
         * 设置显示图片的ImageView
         * @param imageView
         * @return
         */
        public Builder setImageView(ImageView imageView) {
            this.imageView = imageView;
            return this;
        }

        public GlideManager builder() {
            return new GlideManager(this);
        }
    }
}
