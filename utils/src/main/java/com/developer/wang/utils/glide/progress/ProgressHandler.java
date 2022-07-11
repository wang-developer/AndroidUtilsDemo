package com.developer.wang.utils.glide.progress;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Date:2018/10/30
 * Time:14:36
 * Author:Loren
 * Desc:
 */
public class ProgressHandler extends Handler {

    private WeakReference<Activity> mActivity;
    private ProgressImageView mProgressImageView;

    public ProgressHandler(Activity activity, ProgressImageView progressImageView) {
        super(Looper.getMainLooper());
        mActivity = new WeakReference<>(activity);
        mProgressImageView = progressImageView;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        final Activity activity = mActivity.get();
        if (activity != null) {
            switch (msg.what) {
                case 1:
                    int percent = msg.arg1 * 100 / msg.arg2;
                    mProgressImageView.setProgress(percent);
                    break;
                default:
                    break;
            }
        }
    }
}

