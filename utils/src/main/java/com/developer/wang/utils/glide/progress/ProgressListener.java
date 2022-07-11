package com.developer.wang.utils.glide.progress;

/**
 * Date:2018/10/11
 * Time:13:41
 * Author:Loren
 * Desc:
 */
public interface ProgressListener {
    void progress(long bytesRead, long contentLength, boolean done);
}
