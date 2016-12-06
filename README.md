# RedLabelView
add red label on any view
######在任意view右上角添加红点提醒，不会改变目标view的布局，
![image](https://github.com/killer8000/RedLabelView/blob/master/app/src/main/res/drawable/Screenshot_20161206-150031.jpg)
使用方式：
#####1：跟普通view一样可以添加到布局文件中
#####2：在activiy中使用
    EditText editText = (EditText) findViewById(R.id.et);
        RedLabel label1 = new RedLabel(this, 20);
        label1.setNum(100);
        label1.setTargetView(textView);
