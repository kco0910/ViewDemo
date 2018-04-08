package com.fly.viewdemo.practice4;

import android.view.View;
import android.widget.TextView;

import com.fly.viewdemo.base.BaseFragment;

/**
 * Created by Fj on 2018/4/4.
 */

public class GistFragment extends BaseFragment {

    @Override
    public View createChildView() {
        StringBuilder builder = new StringBuilder();
        builder.append("draw的调度顺序").append("\n");
        builder.append("drawBackground()").append(",")
                .append("onDraw").append(",")
                .append("dispatchDraw").append(",")
                .append("onDrawForeground").append("\n");
        builder.append("出于效率的考虑，ViewGroup 默认会绕过 draw() 方法，换而直接执行 dispatchDraw()，以此来简化绘制流程。所以如果你自定义了某个 ViewGroup 的子类（比如 LinearLayout）并且需要在它的除  dispatchDraw() 以外的任何一个绘制方法内绘制内容，你可能会需要调用 View.setWillNotDraw(false) 这行代码来切换到完整的绘制流程（是「可能」而不是「必须」的原因是，有些 ViewGroup 是已经调用过 setWillNotDraw(false) 了的，例如 ScrollView）");
        builder.append("\n");
        builder.append("有的时候，一段绘制代码写在不同的绘制方法中效果是一样的，这时你可以选一个自己喜欢或者习惯的绘制方法来重写。但有一个例外：如果绘制代码既可以写在 onDraw() 里，也可以写在其他绘制方法里，那么优先写在 onDraw() ，因为 Android 有相关的优化，可以在不需要重绘的时候自动跳过  onDraw() 的重复执行，以提升开发效率。享受这种优化的只有 onDraw() 一个方法");
        TextView textView = new TextView(getActivity());
        textView.setText(builder.toString());
        textView.setTextSize(18);
        return textView;
    }


}
