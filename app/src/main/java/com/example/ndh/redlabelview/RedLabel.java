package com.example.ndh.redlabelview;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.telecom.PhoneAccount;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by ndh on 16/12/1.
 */

public class RedLabel extends View {
    int r = 50;//圆形背景 半径
    Paint paint;
    String mText = "0"; //中间将要显示的数字
    int bgColor=Color.RED;
    int textColor=Color.WHITE;
    /**
     *
     * @param context
     * @param r 需要传入自定义的圆形半径,该值单位是px

     */
    public RedLabel(Context context, int r) {
        this(context, null);
        this.r = r;
    }

    public RedLabel(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RedLabel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setColor(Color.WHITE);
        paint.setDither(true);
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
        //设置背景透明,以免 在使用 setScale时会覆盖底层控件
        setBackgroundColor(Color.TRANSPARENT);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(2 * r, 2 * r);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d("ndh--","onDraw--");

        paint.setColor(bgColor);
        paint.setAlpha(225);
        paint.setMaskFilter(new BlurMaskFilter(10, BlurMaskFilter.Blur.SOLID) );
        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, r, paint);
//        paint.setMaskFilter(null);
        //这两个矩形分别是为了定位text和+的位置的
        Rect rect = new Rect(getMeasuredWidth() / 2 - r, getMeasuredHeight() / 2 - r, getMeasuredWidth() / 2 + r, getMeasuredHeight() / 2 + r);//画一个矩形
        Rect rect1 = new Rect(getMeasuredWidth() / 2, getMeasuredHeight() / 2 - r, getMeasuredWidth() / 2 + r, getMeasuredHeight() / 2);//画一个矩形

        if (Integer.parseInt(mText) < 0) {
            mText = "0";
        }
        if (Integer.parseInt(mText) <= 99) {
            paint.setTextSize(r);
            drawStr(canvas, paint, rect, false);
        } else {
            paint.setTextSize(r);
            drawStr(canvas, paint, rect, true);
            paint.setColor(textColor);
            float x1 = getMeasuredWidth() / 2 + r / 2;
            float y1 = (rect1.bottom + rect1.top - paint.getFontMetrics().bottom - paint.getFontMetrics().top) / 2;
            canvas.drawText("+", x1, y1, paint);
        }

    }

    private void drawStr(Canvas canvas, Paint paint, Rect rect, boolean flag) {


        paint.setColor(textColor);
        float x = getMeasuredWidth() / 2;
        float y = (rect.bottom + rect.top - paint.getFontMetrics().bottom - paint.getFontMetrics().top) / 2;
        if (flag)
            canvas.drawText("99", x - r / 5, y, paint);
        else
            canvas.drawText(mText, x, y, paint);
    }


    public void setNum(int num) {
        mText = num + "";
        if (getVisibility() != VISIBLE)
            setVisibility(VISIBLE);
        postInvalidate();
    }
// 方法或缩小该控件,一般不使用
    public void setScale(float f) {
        setScaleY(f);
        setScaleX(f);
    }
//移除该控件,一般不使用
    public boolean remove() {
        try {
            ((ViewGroup) getParent()).removeView(this);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public void setBGColor(int color){
        bgColor=color;
    }
    public void setTextColor(int color){
     textColor=color;
    }
    /**
     *
     * @param target 控件作用到目标view上,最终会显示在右上角,如果目标控件是button,
     *               button的背景色会覆盖掉自定义控件,需要适当调节button的透明度
     *               也可以参考例子中将button外层在包装一层,然后再外层添加RedLabel
     */
    public void setTargetView(View target) {
        ViewGroup parentContainer = (ViewGroup) target.getParent();
        int index = parentContainer.indexOfChild(target);
        parentContainer.removeView(target);
        FrameLayout badgeContainer = new FrameLayout(getContext());
        ViewGroup.LayoutParams parentLayoutParams = target.getLayoutParams();
//        badgeContainer.setPadding(0,200,200,0);
        badgeContainer.setLayoutParams(parentLayoutParams);
        target.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        badgeContainer.addView(target);
        FrameLayout.LayoutParams fl = new FrameLayout.LayoutParams(parentLayoutParams);
        fl.gravity = Gravity.TOP | Gravity.RIGHT;
        badgeContainer.addView(this, fl);
        parentContainer.addView(badgeContainer, index, parentLayoutParams);
        postInvalidate();
    }

}
