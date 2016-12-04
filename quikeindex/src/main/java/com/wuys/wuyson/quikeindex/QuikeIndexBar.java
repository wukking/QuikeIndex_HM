package com.wuys.wuyson.quikeindex;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Wuyson on 2016/12/2.
 */

public class QuikeIndexBar extends View{
    private String[] indexArr = {"A","B","C","D","E","F","G","H","I","J",
            "K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    private Paint paint;
    private int width;
    private float cellHeight;

    public QuikeIndexBar(Context context) {
        super(context);
        init();
    }

    public QuikeIndexBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public QuikeIndexBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
        paint.setTextSize(32);
        paint.setTextAlign(Paint.Align.CENTER);//设置绘制文本底边中心
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = getMeasuredWidth();
        cellHeight = getMeasuredHeight()*1f/indexArr.length;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float x = width / 2;

        for (int i=0;i<indexArr.length;i++) {
            getTextHeight(indexArr[i]);
            float y = cellHeight/2+getTextHeight(indexArr[i])/2+i*cellHeight;
            //重绘
            paint.setColor(lastIndex ==i?Color.BLACK:Color.WHITE);

            canvas.drawText(indexArr[i], x, y, paint);
        }
    }

    /**
     * 根据触摸点的y坐标，计算对应的字母
     * @param event
     * @return
     */
    private int lastIndex = -1;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float y = event.getY();
                int index = (int) (y/cellHeight);
                if (lastIndex != index) {
                    //Log.e("TAG", "index" + indexArr[index]);
                    //对index安全性检查
                    if (index >= 0 && index < indexArr.length) {
                        if (listener != null) {
                            listener.OnTouchLetter(indexArr[index]);
                        }
                    }
                }
                lastIndex = index;
                break;
            case MotionEvent.ACTION_UP:
                //抬起手来重置
                lastIndex = -1;
                break;
        }
        //重绘，调用OnDraw（）
        invalidate();
        return true;
    }

    private int getTextHeight(String text){
        //获取文本高度
        Rect bounds = new Rect();
        paint.getTextBounds(text,0,text.length(),bounds);
        return bounds.height();
    }

    //设置提供数据（letter)接口
    private OnTouchLetterListener listener;
    public void setOnTouchLetterListener(OnTouchLetterListener listener){
        this.listener = listener;
    }

    public interface OnTouchLetterListener{
        void OnTouchLetter(String letter);
    }
}
