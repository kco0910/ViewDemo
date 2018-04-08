package com.fly.viewdemo.practice4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.fly.viewdemo.R;
import com.fly.viewdemo.base.BaseFragment;

/**
 * Created by Fj on 2018/4/4.
 */

public class DispatchDrawFragment extends BaseFragment {

    @Override
    public View createChildView() {
        MyLinearLayout linearLayout = new MyLinearLayout(getActivity());
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        ImageView myImageView = new ImageView(getActivity());
        myImageView.setImageResource(R.drawable.woniu_2);
        linearLayout.addView(myImageView,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        return linearLayout;
    }

    private class MyLinearLayout extends LinearLayout{

        public MyLinearLayout(Context context) {
            super(context);
        }


        @Override
        protected void dispatchDraw(Canvas canvas) {
            super.dispatchDraw(canvas);

            //viewgroup 会先调用 自身的onDraw()方法,再绘制子View

            Paint paint = new Paint();
            paint.setColor(Color.RED);
            paint.setStrokeWidth(2);
            paint.setStyle(Paint.Style.FILL);

            canvas.drawCircle(40,40,10,paint);

            canvas.drawCircle(80,200,20,paint);

            canvas.drawCircle(200,400,30,paint);

            canvas.drawCircle(600,300,40,paint);
        }
    }

}
