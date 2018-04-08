package com.fly.viewdemo.practice4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fly.viewdemo.R;
import com.fly.viewdemo.base.BaseFragment;

/**
 * Created by Fj on 2018/4/4.
 */

public class OnDrawFragment extends BaseFragment {
    @Override
    public View createChildView() {
        LinearLayout linearLayout = new LinearLayout(getActivity());
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        TextView textView = new TextView(getActivity());
        textView.setText("写在 super.onDraw() 的下面");
        linearLayout.addView(textView,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        MyImageView myImageView = new MyImageView(getActivity());
        myImageView.setImageResource(R.drawable.yezi_2);
        linearLayout.addView(myImageView,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        MyTextView textView3 = new MyTextView(getActivity());
        textView3.setText("写在 super.onDraw() 的上面");
        textView3.setTextColor(Color.RED);
        linearLayout.addView(textView3,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        return linearLayout;
    }

    private class MyImageView extends android.support.v7.widget.AppCompatImageView{

        public MyImageView(Context context) {
            super(context);
        }

        public MyImageView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
        }

        public MyImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint();
            paint.setTextSize(30.0f);
            paint.setColor(Color.RED);
            String text = "w:"+getWidth()+",h:"+getHeight();
            canvas.drawText(text,0,40,paint);
        }
    }


    private class MyTextView extends android.support.v7.widget.AppCompatTextView{

        public MyTextView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawColor(Color.GREEN);
            super.onDraw(canvas);
        }
    }

}
