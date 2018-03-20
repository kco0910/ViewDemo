package com.fly.viewdemo.path;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.fly.viewdemo.R;

/**
 * 1、参考文章http://blog.csdn.net/u013831257/article/details/51565591
 * PathMeasure(Path path, boolean forceClosed)	创建 PathMeasure 并关联一个指定的Path(Path需要已经创建完成)。
 * void	setPath(Path path, boolean forceClosed)	关联一个Path
 * boolean	isClosed()	是否闭合
 * float	getLength()	获取Path的长度
 * boolean	nextContour()	跳转到下一个轮廓
 * boolean	getSegment(float startD, float stopD, Path dst, boolean startWithMoveTo)	截取片段
 * boolean	getPosTan(float distance, float[] pos, float[] tan)	获取指定长度的位置坐标及该点切线值
 * boolean	getMatrix(float distance, Matrix matrix, int flags)	获取指定长度的位置坐标及该点Matrix
 * Created by Fj on 2018/3/16.
 */
public class PathMeasureDemo extends View {
    private static final String TAG = "PathMeasureDemo";
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float animValue;
    private float pos[],tan[];
    private Bitmap mBitmap,mBitmap2;
    private Matrix mMatrix;
    public PathMeasureDemo(Context context) {
        super(context);
        init();
    }

    public PathMeasureDemo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PathMeasureDemo(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);

        pos = new float[2];
        tan = new float[2];
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;       // 缩放图片
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fly, options);
        mBitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.fly, options);
        mMatrix = new Matrix();

        initSearch();

        postDelayed(new Runnable() {
            @Override
            public void run() {
                aroundFlyAnim();
            }
        },1000);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float dx = getWidth()/2;
        float dy = getHeight()/2;
        //改变中心点
        canvas.translate(dx,dy);
        Path path = new Path();
        path.lineTo(0,200);
        path.lineTo(200,200);
        path.lineTo(200,0);

        //验证pathmeasure 对path不产生影响，
        PathMeasure pathMeasure = new PathMeasure(path,false);
        PathMeasure pathMeasure1 = new PathMeasure(path,true);
       // Logger.i("pathMeasure :"+pathMeasure.getLength());
       // Logger.i("pathMeasure1 :"+pathMeasure1.getLength());

        //canvas.drawPath(path,mPaint);


        //验证path.length只取一条的长度
        Path path1 = new Path();
        path1.addRect(-100,-100,100,100, Path.Direction.CW);
        path1.addRect(-200,-200,200,200, Path.Direction.CW);

        PathMeasure pathMeasure2 = new PathMeasure(path1,false);
       // Logger.i("path1中的长度:"+pathMeasure2.getLength());
        pathMeasure2.nextContour();//计算下一条线
       // Logger.i("nextContour 后的pah1长度"+pathMeasure2.getLength());


        aroundFly1(canvas);

        aroundFly2(canvas);

        //testSegment(canvas);
        search(canvas);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),MeasureSpec.getSize(heightMeasureSpec));
    }

    private void testSegment(Canvas canvas){
        mPaint.setColor(Color.GREEN);
        Path path = new Path();
        path.addRect(-200,-200,200,200, Path.Direction.CW);
//        canvas.drawPath(path,mPaint);
        Path dst = new Path();
        dst.rLineTo(0, 0);//或者关闭硬件加速，否则在<=4.4会导致绘制出问题
        PathMeasure pathMeasure = new PathMeasure(path,false);
        pathMeasure.getSegment(0,pathMeasure.getLength()*animValue,dst,true);//true保持原来截取到的path
        mPaint.setColor(Color.BLACK);
        canvas.drawPath(dst,mPaint);
    }

    private static final int SEARCHING = 605;
    private static final int SEARCH_START = 806;
    private PathMeasure circleMeasure = new PathMeasure();
    private Path searchPath = new Path();
    private Path circlePath = new Path();
    private int searchState = SEARCH_START;
    private float searchAnimValue;
    private void initSearch(){
        //不要使用addCircle方法，这样不能设置开始角度,不方便得到搜索的把手
        RectF smallRect = new RectF(-50, -50, 50, 50);
        searchPath.addArc(smallRect,45,359.0f);//不要到360度,否则内部会自动优化,测量不能取到需要的数值

        float[] pos = new float[2];
        RectF bigRect = new RectF(-100, -100, 100, 100);
        circlePath.addArc(bigRect,45,359.0f);
        circleMeasure.setPath(circlePath, false);
        circleMeasure.getPosTan(0, pos, null);

        searchPath.lineTo(pos[0], pos[1]);
    }

    /**
     * 搜索框动画
     */
    private void search(Canvas canvas){
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(10.0f);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        canvas.translate(-200,-200);
        Path dst = new Path();
        dst.rLineTo(0,0);
        float length;
        if (searchState == SEARCH_START){
            circleMeasure.setPath(searchPath,false);
            length = circleMeasure.getLength();
            circleMeasure.getSegment(length * animValue, length, dst, true);
            canvas.drawPath(dst,mPaint);
        }else{
            //开始画转圈
            circleMeasure.setPath(circlePath,false);
            length = circleMeasure.getLength();
            float stopD = length*animValue + 10 > length ? length : length*animValue + 10;
            circleMeasure.getSegment(length*animValue,stopD,dst,true);
            canvas.drawPath(dst,mPaint);
        }
//        canvas.drawPath(searchPath,mPaint);
//        canvas.drawPath(circlePath,mPaint);
    }

    /**
     *飞机绕圈 第一种实现
     * 通过 tan 得值计算出图片旋转的角度，tan 是 tangent 的缩写，
     * 即中学中常见的正切， 其中tan0是邻边边长，tan1是对边边长，
     * 而Math中 atan2 方法是根据正切是数值计算出该角度的大小,得到的单位是弧度，所以上面又将弧度转为了角度
     * @param canvas
     */
    private void aroundFly1(Canvas canvas){
        mMatrix.reset();
        Path circlePath = new Path();
        mPaint.setColor(Color.CYAN);
        circlePath.addCircle(0,0,80.0f, Path.Direction.CW);
        PathMeasure circleMeasure = new PathMeasure(circlePath,false);
        float length = circleMeasure.getLength();
        circleMeasure.getPosTan(length*getAnimValue(),pos,tan);
        float degrees = (float)(Math.atan2(tan[1],tan[0])*180.0/Math.PI);
        mMatrix.postRotate(degrees,mBitmap.getWidth()/2,mBitmap.getHeight()/2);
        mMatrix.postTranslate(pos[0] -mBitmap.getWidth()/2,pos[1]-mBitmap.getHeight()/2);
        canvas.drawPath(circlePath,mPaint);
        canvas.drawBitmap(mBitmap,mMatrix,mPaint);
    }

    /**
     *飞机绕圈 第二种实现
     * 矩阵对旋转角度默认为图片的左上角，我们此处需要使用 preTranslate 调整为图片中心。
     * @param canvas
     */
    private void aroundFly2(Canvas canvas){
        mPaint.setColor(Color.RED);
        Path circlePath2 = new Path();
        circlePath2.addCircle(200,200,80.0f, Path.Direction.CW);
        PathMeasure pm = new PathMeasure(circlePath2,false);
        float l = pm.getLength();
        pm.getMatrix(l*getAnimValue(),mMatrix,
                PathMeasure.TANGENT_MATRIX_FLAG | PathMeasure.POSITION_MATRIX_FLAG);
        mMatrix.preTranslate(-mBitmap.getWidth()/2,-mBitmap.getHeight()/2);
        canvas.drawPath(circlePath2,mPaint);
        canvas.drawBitmap(mBitmap,mMatrix,mPaint);
    }

    private ObjectAnimator mObjectAnimator =null;
    private void aroundFlyAnim(){
        mObjectAnimator = ObjectAnimator.ofFloat(this, "animValue", 0f, 1f);
        mObjectAnimator.setDuration(10*1000);
        mObjectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        mObjectAnimator.setRepeatMode(ValueAnimator.RESTART);
        mObjectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mObjectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                postInvalidate();
            }
        });
        mObjectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                searchState = SEARCHING;
            }
        });
        mObjectAnimator.start();
    }

    public float getAnimValue() {
        return animValue;
    }

    public void setAnimValue(float animValue) {
        this.animValue = animValue;
    }
}
