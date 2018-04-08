package com.fly.viewdemo.practice4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.fly.viewdemo.R;
import com.fly.viewdemo.base.BaseFragment;

/**
 * Created by Fj on 2018/4/4.
 */

public class OnDrawForegroundFragment extends BaseFragment {

    @Override
    public View createChildView() {
        LinearLayout linearLayout = new LinearLayout(getActivity());
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        MyImageView myImageView = new MyImageView(getActivity());
        myImageView.setImageResource(R.drawable.woniu_2);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            myImageView.setForeground(new ColorDrawable(0x99FFFFFF));
        }
        linearLayout.addView(myImageView,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        return linearLayout;
    }

    /**
     *
     */
    private class MyImageView extends AppCompatImageView{

        public MyImageView(Context context) {
            super(context);
        }

        @Override
        public void onDrawForeground(Canvas canvas) {
            super.onDrawForeground(canvas);
            //此方法在API26才加入，此前只有在FrameLayout方法中才有
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setTextSize(30.0f);

            String str = "NEW";
            int length = (int) paint.measureText(str);
            Rect rect = new Rect(40,40,40+length,80);
            paint.setColor(Color.GREEN);
            canvas.drawRect(rect,paint);
            paint.setColor(Color.RED);
            canvas.drawText(str,rect.left,rect.bottom,paint);
        }
    }
}
