package com.developer.wang.utils.glide.progress;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.activenmax.lovanow.R;
import com.linknow.sensenow.common.utils.JWDensityUtils;
import com.linknow.sensenow.common.utils.string.JWStringUtil;
import com.linknow.sensenow.common.utils.widget.progress.CircleProgressView;
import com.linknow.sensenow.view.chat.JWZoomImageView;

;


/**
 * Date:2018/10/16
 * Time:13:51
 * Author:Loren
 * Desc:
 */
public class ProgressImageView extends RelativeLayout {
    private static final String TAG = ProgressImageView.class.getSimpleName();
    private Context context;
    private ImageView mImageView;
    private int mProgress;
    private ImageView beginLoadingIv;
    private CircleProgressView loadingView;

    private String imageUrl;
    private RelativeLayout tryAgainRoot;
    private TextView tv_loading_error_btn;

    public ProgressImageView(Context context) {
        super(context);
        this.context = context;

        init(context,true);
    }

    public ProgressImageView(Context context, boolean isZoomImageView) {
        super(context);
        this.context = context;
        init(context, isZoomImageView);

    }

    public ProgressImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        init(context,true);
    }

    private void init(Context context,boolean isZoomImageView){
        initView(context,isZoomImageView);
        initListener(context);
    }

    private void initView(Context context,boolean isZoomImageView) {
        //在显示进度前转圈加载，避免有时候进度一直没有回调显示0问题
        beginLoadingIv = new ImageView(context);
        beginLoadingIv.setImageResource(R.mipmap.dialogue_loading_icon_small);
        LayoutParams ivBeginLoading = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ivBeginLoading.addRule(CENTER_IN_PARENT);
        beginLoadingIv.setLayoutParams(ivBeginLoading);
        beginLoadingIv.setVisibility(GONE);
        addView(beginLoadingIv);
        //Loading加载动画
        loadingView = new CircleProgressView(context);
        loadingView.setMaxCount(100);
        loadingView.setScoreSpSize(12);
        loadingView.setCircleStrokeWithSp(1.0f);
        loadingView.setBackCircleColor(getResources().getColor(R.color.black_03));
        loadingView.setScoreVisible(true);
        loadingView.setCurrentCount(0);
        loadingView.setScore(0);
        int progressDiameter = JWDensityUtils.dip2px(context,52);
        LayoutParams ivLoadingLp = new LayoutParams(progressDiameter, progressDiameter);

        ivLoadingLp.addRule(CENTER_IN_PARENT);
        loadingView.setLayoutParams(ivLoadingLp);
        loadingView.setVisibility(GONE);
        addView(loadingView);


        //要显示图片的ImageView
        if (isZoomImageView) {
            mImageView = new JWZoomImageView(context);
        } else {

            mImageView = new ImageView(context);
        }
        LayoutParams ivLp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        ivLp.addRule(CENTER_IN_PARENT);
        mImageView.setLayoutParams(ivLp);
        mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        addView(mImageView);


        //重新加载按钮
        tryAgainRoot = new RelativeLayout(context);
        LayoutParams viewTryAgainLP = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        viewTryAgainLP.addRule(CENTER_IN_PARENT);
        View tryAgainView = LayoutInflater.from(context).inflate(R.layout.layout_view_loading, null,false);
        tryAgainRoot.setLayoutParams(viewTryAgainLP);
        tryAgainRoot.addView(tryAgainView);
        tryAgainRoot.setVisibility(GONE);
        addView(tryAgainRoot);
        tv_loading_error_btn = tryAgainRoot.findViewById(R.id.tv_loading_error_btn);

        //JWLogUtils.INSTANCE.i(TAG,"setProgress progress : XXXXX" + " loadingView : " + loadingView + " mImageView : " + mImageView);

        //延时处理，图片加载过快进度闪烁问题
        mImageView.postDelayed(new Runnable() {
            @Override
            public void run() {

                if(!isPicErrorUIVisible() && !JWStringUtil.isEmpty(imageUrl)){

                    startAnimation();
                }
            }
        }, 500);
    }

    private void initListener(Context context){
        tv_loading_error_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(progressListener != null){
                    progressListener.OnErrorTryAgainClick(ProgressImageView.this,imageUrl);
                }
            }
        });
    }

    public void setImageUlr(String imageUrl){
        this.imageUrl = imageUrl;
    }

    /**
     * 显示图片建在失败UI
     * tryAgainRoot和loadingView互斥显示
     */
    public void showPicErrorUI() {
        stopAnimation();
        hiddenLoadingView();
        if(tryAgainRoot.getVisibility() != VISIBLE)
            tryAgainRoot.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏图片加载失败UI
     */
    public void hiddenPicErrorUI() {
        if(tryAgainRoot.getVisibility() != GONE)
            tryAgainRoot.setVisibility(View.GONE);
    }

    /**
     * 当前图片加载UI是否显示
     */
    public boolean isPicErrorUIVisible(){
        return tryAgainRoot.getVisibility() == VISIBLE;
    }

    /**
     * 显示加载进度动画
     * tryAgainRoot和loadingView互斥显示
     */
    private void showLoadingView(){
        hiddenPicErrorUI();
        if(loadingView.getVisibility() != VISIBLE)
            loadingView.setVisibility(VISIBLE);
    }

    /**
     * 隐藏加载进度动画
     */
    private void hiddenLoadingView(){
        if(loadingView.getVisibility() != GONE)
            loadingView.setVisibility(GONE);
    }

    private void startAnimation() {
        hiddenPicErrorUI();
        hiddenLoadingView();
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.image_loading);
        if (beginLoadingIv.getVisibility() != VISIBLE)
            beginLoadingIv.setVisibility(VISIBLE);
        beginLoadingIv.startAnimation(animation);
    }

    private void stopAnimation() {
        beginLoadingIv.clearAnimation();
        if (beginLoadingIv.getVisibility() != GONE)
            beginLoadingIv.setVisibility(GONE);

    }

    public ProgressImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        init(context,true);

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ProgressImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;

        init(context,true);

    }

    private int previousProgress = 0;

    public void setProgress(int progress) {
        this.mProgress = progress;
        //JWLogUtils.INSTANCE.i(TAG,"setProgress progress : " + progress + " loadingView : " + loadingView + " mImageView : " + mImageView);
        stopAnimation();
        if (progress == 100) {
            hiddenLoadingView();
        }else {
            showLoadingView();
            if(progress < previousProgress){
                progress = previousProgress;
            }
        }
        loadingView.setCurrentCount(progress);
        loadingView.setScore(progress);

        previousProgress = progress;
    }

    public ImageView getImageView() {
        return mImageView;
    }


    private onProgressListener progressListener;

    public void setOnProgressListener(onProgressListener progressListener){
        this.progressListener = progressListener;

    }

    public interface onProgressListener {

        public void OnErrorTryAgainClick(ProgressImageView progressImageView,String imageUrl);

    }
}
