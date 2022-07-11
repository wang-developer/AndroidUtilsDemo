package com.developer.wang.utils.glide.progress;



import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;


/**
 * Date:2018/10/11
 * Time:13:41
 * Author:Loren
 * Desc:
 */
public class ProgressInterceptor implements Interceptor {

    private ProgressListener progressListener;

    public ProgressInterceptor(ProgressListener progressListener) {
        this.progressListener = progressListener;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        return originalResponse.newBuilder().body(new ProgressResponseBody(originalResponse.body(), progressListener)).build();
    }

}