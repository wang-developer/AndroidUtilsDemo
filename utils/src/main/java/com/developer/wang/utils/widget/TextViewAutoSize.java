package com.developer.wang.utils.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;

import androidx.appcompat.widget.AppCompatTextView;

import com.developer.wang.utils.device.DeviceDensity;


/**
 * Date:2018/9/20
 * Time:10:29
 * Author:Loren
 * Desc: textview内容显示完全，根据文字宽度自动缩小字体大小
 */
public class TextViewAutoSize extends AppCompatTextView {
    private Paint testPaint;
    private float cTextSize;


    public TextViewAutoSize(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Re size the font so the specified text fits in the text box * assuming
     * the text box is the specified width.
     * 在此方法中学习到：getTextSize返回值是以像素(px)为单位的，而setTextSize()是以sp为单位的，
     * 因此要这样设置setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
     */
    private void refitText(String text, int textWidth) {
        if (textWidth > 0) {
            testPaint = new Paint();
            testPaint.set(this.getPaint());
            //获得当前TextView的有效宽度
            int availableWidth = textWidth - this.getPaddingLeft()
                    - this.getPaddingRight();
            float[] widths = new float[text.length()];
            Rect rect = new Rect();
            testPaint.getTextBounds(text, 0, text.length(), rect);
            //所有字符串所占像素宽度
            int textWidths = rect.width();
            cTextSize = this.getTextSize();
            //这个返回的单位为px
            while (textWidths > availableWidth) {
                //根据手机密度一次缩小一个sp
                cTextSize = cTextSize - DeviceDensity.sp2px(getContext(), 1);
                testPaint.setTextSize(cTextSize);
                //这里传入的单位是px
                textWidths = testPaint.getTextWidths(text, widths);
            }
            this.setTextSize(TypedValue.COMPLEX_UNIT_PX, cTextSize);//这里制定传入的单位是px
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        refitText(getText().toString(), this.getWidth());
    }
}
