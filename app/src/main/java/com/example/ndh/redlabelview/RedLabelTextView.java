package com.example.ndh.redlabelview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.SuperscriptSpan;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by ndh on 16/12/7.
 */

public class RedLabelTextView extends TextView {
    public RedLabelTextView(Context context) {
        super(context);
    }

    public RedLabelTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RedLabelTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        String destStr="";
       int num= Integer.parseInt(text+"");
        if(num<=0){
            num=0;
        }
        if(num>99){
         destStr="99+";
            SpannableString spannableString=new SpannableString(destStr);
            spannableString.setSpan(new SuperscriptSpan(),2,3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            super.setText(spannableString,type);
        }else{
            super.setText(num+"");
        }
    }

}
