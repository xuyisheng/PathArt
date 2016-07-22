package xys.com.pathart.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

/**
 * 曲线上切点
 * <p/>
 * Created by xuyisheng on 16/7/15.
 */
public class PathTan extends View implements View.OnClickListener {

    private Path mPath;
    private float[] pos;
    private float[] tan;
    private Paint mPaint;
    float currentValue = 0;
    private PathMeasure mMeasure;

    public PathTan(Context context) {
        super(context);
    }

    public PathTan(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
        mMeasure = new PathMeasure();
        mPath.addCircle(0, 0, 200, Path.Direction.CW);
        mMeasure.setPath(mPath, false);
        pos = new float[2];
        tan = new float[2];
        setOnClickListener(this);
    }

    public PathTan(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mMeasure.getPosTan(mMeasure.getLength() * currentValue, pos, tan);
        float degrees = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI);

        canvas.save();
        canvas.translate(400, 400);
        canvas.drawPath(mPath, mPaint);
        canvas.drawCircle(pos[0], pos[1], 10, mPaint);
        canvas.rotate(degrees);
        canvas.drawLine(0, -200, 300, -200, mPaint);
        canvas.restore();
    }

    @Override
    public void onClick(View view) {
        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                currentValue = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        animator.setDuration(3000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.start();
    }
}
