package com.developer.wang.utils.glide.progress;

import android.os.Handler;

import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.stream.StreamModelLoader;

import java.io.InputStream;

/**
 * Date:2018/10/11
 * Time:13:38
 * Author:Loren
 * Desc:
 */
public class ProgressModelLoader implements StreamModelLoader<String> {

    private Handler handler;

    public ProgressModelLoader(Handler handler) {
        this.handler = handler;
    }

    @Override
    public DataFetcher<InputStream> getResourceFetcher(String model, int width, int height) {
        return new ProgressDataFetcher(model, handler);
    }
}

