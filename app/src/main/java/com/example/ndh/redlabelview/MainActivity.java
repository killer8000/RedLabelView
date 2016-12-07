package com.example.ndh.redlabelview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.SuperscriptSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.tv);
//        SpannableString ss= new SpannableString("99+");
//        ss.setSpan(new SuperscriptSpan(),2,3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        textView.setText(ss);
        Button button = (Button) findViewById(R.id.bt);
        View view = (View) findViewById(R.id.v);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll);
        EditText editText = (EditText) findViewById(R.id.et);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rl);
        RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.rl2);
        RedLabel label1 = new RedLabel(this, 20);
        label1.setNum(100);
        label1.setTargetView(relativeLayout2);
        RedLabel label2 = new RedLabel(this, 30);
        label2.setNum(20);
        label2.setTargetView(relativeLayout);
        RedLabel label3 = new RedLabel(this, 30);
        label3.setNum(30);
        label3.setTargetView(linearLayout);
        RedLabel label4 = new RedLabel(this, 50);
        label4.setNum(100);
        label4.setBGColor(Color.parseColor("#ff00ff"));
        label4.setTextColor(Color.parseColor("#ffff00"));
        label4.setTargetView(view);
        RedLabel label5 = new RedLabel(this, 20);
        label5.setNum(50);
        label5.setTargetView(editText);

    }
}
