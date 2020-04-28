package com.fly.viewdemo.practice2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.fly.viewdemo.base.BaseFragment;
import com.fly.viewdemo.base.BaseView;

/**
 * Created by Fj on 2018/4/4.
 */

public class OtherFragment extends BaseFragment {

    @Override
    public View createChildView() {
        return new MyView(getActivity());
    }

    private class  MyView extends BaseView{

        public MyView(Context context) {
            super(context);
        }

        public MyView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
        }

        public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);


        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setTextSize(30.0f);
            paint.setColor(Color.BLACK);

            StringBuilder builder = new StringBuilder();
            builder.append("其他的一些方法").append("\n");
            builder.append("hasGlyph:").append("检查指定的字符串中是否是一个单独的字形");
            TextPaint textPaint = new TextPaint();
            textPaint.setColor(Color.RED);
            textPaint.setTextSize(32.0f);
            StaticLayout staticLayout = new StaticLayout(builder.toString(),textPaint,500, Layout.Alignment.ALIGN_NORMAL,1,0,true);
            canvas.save();
            canvas.translate(0,50.f);
            staticLayout.draw(canvas);
            canvas.restore();


            TextPaint textPaint1 = new TextPaint(Paint.ANTI_ALIAS_FLAG);
            textPaint1.setTextSize(30);
            textPaint1.setColor(Color.BLUE);
//            textPaint1.setTextAlign(Paint.Align.CENTER);
            String str  = "在一些场景下。比如界面上有大量的聊天并且活跃度高，内容包含了文字,emoji,图片等各种信息的复杂文本，采用TextView来展示这些内容信息。就容易观察到，聊天消息在频繁刷新的时候，性能有明显下降，GPU火焰图抖动也更加频繁。";
            StaticLayout staticLayout1 = new StaticLayout(str,textPaint1,300,
                    Layout.Alignment.ALIGN_CENTER,1,0,true);
            canvas.save();
            canvas.translate(400,50);
            staticLayout1.draw(canvas);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawRect(0,0,300,staticLayout1.getHeight(),paint);
            canvas.restore();

        }

        @Override
        protected boolean toCenter() {
            return false;
        }
    }
}
