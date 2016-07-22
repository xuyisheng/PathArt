package xys.com.pathart.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * 路径绘制——DashPathEffect
 * <p/>
 * Created by xuyisheng on 16/7/15.
 */
public class PathPainterEffect extends View implements View.OnClickListener{

    private Paint mPaint;
    private Path mPath;
    private PathMeasure mPathMeasure;
    private PathEffect mEffect;
    private float fraction = 0;
    private ValueAnimator mAnimator;

    public PathPainterEffect(Context context) {
        super(context);
    }

    public PathPainterEffect(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPath = new Path();
        mPath.reset();
        mPath.moveTo(100, 100);
        mPath.lineTo(100, 500);
        mPath.lineTo(400, 300);
        mPath.close();

        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);

        mPathMeasure = new PathMeasure(mPath, false);
        final float length = mPathMeasure.getLength();

        mAnimator = ValueAnimator.ofFloat(1, 0);
        mAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        mAnimator.setDuration(2000);
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                fraction = (float) valueAnimator.getAnimatedValue();
                mEffect = new DashPathEffect(new float[]{length, length}, fraction * length);
                mPaint.setPathEffect(mEffect);
                invalidate();
            }
        });
        setOnClickListener(this);
    }

    public PathPainterEffect(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    public void onClick(View view) {
        mAnimator.start();
    }
}
