package com.fly.viewdemo.layoutManager;

import android.graphics.Path;
import android.graphics.PathMeasure;

/**
 * Created by Fj on 2018/11/2.
 */
public class KeyFrames {
    private float[] mX;
    private float[] mY;
    private float[] mAangle;



    private void init(Path path, int num) {
        PathMeasure pm = new PathMeasure(path, false);
        float length = pm.getLength();
        float[] pos = new float[2];
        float[] tan = new float[2];
        int count = (int) (length / num) + 1;
        for (int i = 0; i < count; i++) {
            pm.getPosTan(i * length, pos, tan);
            mX[i] = pos[0];
            mY[i] = pos[1];
            mAangle[i]= fixAngle((float) (Math.atan2(tan[1],tan[0])*180f/Math.PI));
        }
    }

    private float fixAngle(float rotation) {
        float angle = 360F;
        if (rotation < 0) {
            rotation += angle;
        }
        if (rotation > angle) {
            rotation %= angle;
        }
        return rotation;
    }
}
