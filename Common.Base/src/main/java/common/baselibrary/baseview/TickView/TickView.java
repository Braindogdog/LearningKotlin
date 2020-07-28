package common.baselibrary.baseview.TickView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import common.baselibrary.R;
import common.baselibrary.baseutil.DensityUtil;

/**
 * Created by chasen on 2018/3/28.
 */

public class TickView extends View {
    //内圆的画笔
    private Paint mPaintCircle;
    //外层圆环的画笔
    private Paint mPaintRing;
    //打钩的画笔
    private Paint mPaintTick;

    //整个圆外切的矩形
    private RectF mRectF = new RectF();

    //控件中心的X,Y坐标
    private int centerX;
    private int centerY;

    //计数器
    private int circleRadius = -1;
    private int ringProgress = 0;

    //控件状态
    private Status status = Status.LOADING;
    //是否处于动画中
    private boolean isAnimationRunning = false;

    //最后扩大缩小动画中，画笔的宽度的最大倍数
    private static final int SCALE_TIMES = 6;

    private AnimatorSet mFinalAnimatorSet;

    private int mRingAnimatorDuration;
    private int mCircleAnimatorDuration;
    private int mScaleAnimatorDuration;

    TickViewConfig mConfig;

    private Path mTickPath;
    private Path mTickPathMeasureDst;
    private PathMeasure mPathMeasure;

    private Path mErrorPath1;
    private Path mErrorPath2;

    private float startAngle = 0f;//加载中动画开始起点

    private enum Status {
        LOADING, SUCCESS, ERROR
    }

    public TickView(Context context) {
        this(context, null);
    }

    public TickView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TickView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
        initPaint();
        initAnimatorCounter();
    }

    private void initAttrs(AttributeSet attrs) {
        if (mConfig == null) {
            mConfig = new TickViewConfig(getContext());
        }
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.TickView);
        mConfig.setUnCheckBaseColor(typedArray.getColor(R.styleable.TickView_uncheck_base_color, getResources().getColor(R.color.grey)))
                .setCheckBaseColor(typedArray.getColor(R.styleable.TickView_check_base_color, getResources().getColor(R.color.color_red_assist)))
                .setCheckTickColor(typedArray.getColor(R.styleable.TickView_check_tick_color, getResources().getColor(R.color.white)))
                .setRadius(typedArray.getDimensionPixelOffset(R.styleable.TickView_radius, (DensityUtil.dp2px(getContext(), 25))))
                .setClickable(typedArray.getBoolean(R.styleable.TickView_clickable, true))
                .setTickRadius(DensityUtil.dp2px(getContext(), 12))
                .setTickRadiusOffset((DensityUtil.dp2px(getContext(), 4)));

        int rateMode = typedArray.getInt(R.styleable.TickView_rate, 1);
        TickRateEnum mTickRateEnum = TickRateEnum.getRateEnum(rateMode);
        mRingAnimatorDuration = mTickRateEnum.getmRingAnimatorDuration();
        mCircleAnimatorDuration = mTickRateEnum.getmCircleAnimatorDuration();
        mScaleAnimatorDuration = mTickRateEnum.getmScaleAnimatorDuration();
        typedArray.recycle();
        applyConfig(mConfig);
        if (mTickPath == null) mTickPath = new Path();
        if (mErrorPath1 == null) mErrorPath1 = new Path();
        if (mErrorPath2 == null) mErrorPath2 = new Path();
        if (mTickPathMeasureDst == null) mTickPathMeasureDst = new Path();
        if (mPathMeasure == null) mPathMeasure = new PathMeasure();
    }

    private void applyConfig(TickViewConfig config) {
        assert mConfig != null : "TickView Config must not be null";
        mConfig.setConfig(config);
        if (mConfig.isNeedToReApply()) {
            initPaint();
            initAnimatorCounter();
            mConfig.setNeedToReApply(false);
        }
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        if (mPaintRing == null) mPaintRing = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintRing.setStyle(Paint.Style.STROKE);
        mPaintRing.setColor(mConfig.getCheckBaseColor());
        mPaintRing.setStrokeCap(Paint.Cap.ROUND);
        mPaintRing.setStrokeWidth((DensityUtil.dp2px(getContext(), 4)));

        if (mPaintCircle == null) mPaintCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintCircle.setColor(mConfig.getCheckBaseColor());

        if (mPaintTick == null) mPaintTick = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintTick.setColor(mConfig.getCheckTickColor());
        mPaintTick.setStyle(Paint.Style.STROKE);
        mPaintTick.setStrokeCap(Paint.Cap.ROUND);
        mPaintTick.setStrokeWidth((DensityUtil.dp2px(getContext(), 4)));
    }

    /**
     * 用ObjectAnimator初始化一些计数器
     */
    private void initAnimatorCounter() {
        //圆环进度
        ObjectAnimator mRingAnimator = ObjectAnimator.ofInt(this, "ringProgress", 0, 360);
        mRingAnimator.setDuration(mRingAnimatorDuration);
        mRingAnimator.setInterpolator(null);
        //收缩动画
        ObjectAnimator mCircleAnimator = ObjectAnimator.ofInt(this, "circleRadius", mConfig.getRadius() - 5, 0);
        mCircleAnimator.setInterpolator(new DecelerateInterpolator());
        mCircleAnimator.setDuration(mCircleAnimatorDuration);
        Animator mTickAnima;
        //勾勾alpha动画
        if (mConfig.getTickAnim() == TickViewConfig.ANIM_ALPHA) {
            //勾出来的透明渐变
            mTickAnima = ObjectAnimator.ofInt(this, "tickAlpha", 0, 255);
            mTickAnima.setDuration(200);
        } else {
            //勾勾采取动态画出
            mTickAnima = ValueAnimator.ofFloat(0.0f, 1.0f);
            mTickAnima.setDuration(400);
            mTickAnima.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    setTickProgress(0);
                    mPathMeasure.nextContour();
                    if (status == Status.SUCCESS) {
                        mPathMeasure.setPath(mTickPath, false);
                        mTickPathMeasureDst.reset();
                    }
                }
            });
            ((ValueAnimator) mTickAnima).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    setTickProgress((Float) animation.getAnimatedValue());
                }
            });
            mTickAnima.setInterpolator(new DecelerateInterpolator());
        }
        //最后的放大再回弹的动画，改变画笔的宽度来实现
        ObjectAnimator mScaleAnimator = ObjectAnimator.ofFloat(this, "ringStrokeWidth", mPaintRing.getStrokeWidth(), mPaintRing.getStrokeWidth() * SCALE_TIMES, mPaintRing.getStrokeWidth() / SCALE_TIMES);
        mScaleAnimator.setInterpolator(null);
        mScaleAnimator.setDuration(mScaleAnimatorDuration);

        //打钩和放大回弹的动画一起执行
        AnimatorSet mAlphaScaleAnimatorSet = new AnimatorSet();
        mAlphaScaleAnimatorSet.playTogether(mTickAnima, mScaleAnimator);

        mFinalAnimatorSet = new AnimatorSet();
        mFinalAnimatorSet.playSequentially(mRingAnimator, mCircleAnimator, mAlphaScaleAnimatorSet);
        mFinalAnimatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (mConfig.getTickAnimatorListener() != null) {
                    mConfig.getTickAnimatorListener().onAnimationEnd(TickView.this);
                }
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                if (mConfig.getTickAnimatorListener() != null) {
                    mConfig.getTickAnimatorListener().onAnimationStart(TickView.this);
                }
            }
        });
    }

    private int getMySize(int defaultSize, int measureSpec) {
        int mySize = defaultSize;

        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        switch (mode) {
            case MeasureSpec.UNSPECIFIED:
            case MeasureSpec.AT_MOST:
                mySize = defaultSize;
                break;
            case MeasureSpec.EXACTLY:
                mySize = size;
                break;
            default:
                break;
        }
        return mySize;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        final int radius = mConfig.getRadius();
        final float tickRadius = mConfig.getTickRadius();
        final float tickRadiusOffset = mConfig.getTickRadiusOffset();

        //控件的宽度等于动画最后的扩大范围的半径
        int width = getMySize((radius + DensityUtil.dp2px(getContext(), 2.5f) * SCALE_TIMES) * 2, widthMeasureSpec);
        int height = getMySize((radius + DensityUtil.dp2px(getContext(), 2.5f) * SCALE_TIMES) * 2, heightMeasureSpec);

        height = width = Math.max(width, height);

        setMeasuredDimension(width, height);

        centerX = getMeasuredWidth() / 2;
        centerY = getMeasuredHeight() / 2;

        //设置圆圈的外切矩形
        mRectF.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius);

        //设置打钩的几个点坐标
        final float startX = centerX - tickRadius + tickRadiusOffset;
        final float startY = (float) centerY;

        final float middleX = centerX - tickRadius / 2 + tickRadiusOffset;
        final float middleY = centerY + tickRadius / 2;

        final float endX = centerX + tickRadius * 2 / 4 + tickRadiusOffset;
        final float endY = centerY - tickRadius * 2 / 4;

        final float startErrorX = centerX - tickRadius;
        final float startErrorY = centerY + tickRadius;

        final float endErrorX = centerX + tickRadius;
        final float endErrorY = centerY - tickRadius;

        mTickPath.reset();
        mTickPath.moveTo(startX, startY);
        mTickPath.lineTo(middleX, middleY);
        mTickPath.lineTo(endX, endY);

        mErrorPath1.reset();
        mErrorPath1.moveTo(startErrorX, startErrorY);
        mErrorPath1.lineTo(endErrorX, endErrorY);

        mErrorPath2.reset();
        mErrorPath2.moveTo(startErrorX, endErrorY);
        mErrorPath2.lineTo(endErrorX, startErrorY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mConfig.isNeedToReApply()) {
            applyConfig(mConfig);
        }
        super.onDraw(canvas);
        switch (status) {
            case LOADING:
                mPaintRing.setStrokeWidth((DensityUtil.dp2px(getContext(), 4)));
                mPaintRing.setColor(Color.argb(100, 255, 255, 255));
                canvas.drawCircle(centerX, centerY, mConfig.getRadius(), mPaintRing);
                mPaintRing.setColor(Color.WHITE);
                canvas.drawArc(mRectF, startAngle, 100
                        , false, mPaintRing);
                break;
            case SUCCESS:
                //画圆弧进度
                canvas.drawArc(mRectF, 90, ringProgress, false, mPaintRing);
                //画黄色的背景
                mPaintCircle.setColor(mConfig.getCheckBaseColor());
                canvas.drawCircle(centerX, centerY, ringProgress == 360 ? mConfig.getRadius() : 0, mPaintCircle);
                //画收缩的白色圆
                if (ringProgress == 360) {
                    mPaintCircle.setColor(mConfig.getCheckTickColor());
                    canvas.drawCircle(centerX, centerY, circleRadius, mPaintCircle);
                }
                //画勾,以及放大收缩的动画
                if (circleRadius == 0) {
                    if (mConfig.getTickAnim() == TickViewConfig.ANIM_DYNAMIC) {
                        mPaintTick.setAlpha((int) (255 * tickProgress));
                        mPathMeasure.getSegment(0, tickProgress * mPathMeasure.getLength(), mTickPathMeasureDst, true);
                        canvas.drawPath(mTickPathMeasureDst, mPaintTick);
                    } else {
                        canvas.drawPath(mTickPath, mPaintTick);
                    }
                    canvas.drawArc(mRectF, 0, 360, false, mPaintRing);
                }
                //ObjectAnimator动画替换计数器
                if (!isAnimationRunning) {
                    isAnimationRunning = true;
                    mFinalAnimatorSet.start();
                }
                break;
            case ERROR:
                //画圆弧进度
                canvas.drawArc(mRectF, 90, ringProgress, false, mPaintRing);
                //画黄色的背景
                mPaintCircle.setColor(mConfig.getCheckBaseColor());
                canvas.drawCircle(centerX, centerY, ringProgress == 360 ? mConfig.getRadius() : 0, mPaintCircle);
                //画收缩的白色圆
                if (ringProgress == 360) {
                    mPaintCircle.setColor(mConfig.getCheckTickColor());
                    canvas.drawCircle(centerX, centerY, circleRadius, mPaintCircle);
                }
                //画勾,以及放大收缩的动画
                if (circleRadius == 0) {
                    canvas.drawPath(mErrorPath1, mPaintTick);
                    canvas.drawPath(mErrorPath2, mPaintTick);
                    canvas.drawArc(mRectF, 0, 360, false, mPaintRing);
                }
                //ObjectAnimator动画替换计数器
                if (!isAnimationRunning) {
                    isAnimationRunning = true;
                    mFinalAnimatorSet.start();
                }
                break;
        }
    }

    /*--------------属性动画---getter/setter begin---------------*/

    private int getRingProgress() {
        return ringProgress;
    }

    private void setRingProgress(int ringProgress) {
        this.ringProgress = ringProgress;
        postInvalidate();
    }

    private int getCircleRadius() {
        return circleRadius;
    }

    private void setCircleRadius(int circleRadius) {
        this.circleRadius = circleRadius;
        postInvalidate();
    }

    private float tickProgress = 0.0f;

    private float getTickProgress() {
        return tickProgress;
    }

    private void setTickProgress(float tickProgress) {
        this.tickProgress = tickProgress;
        Log.i("progress", "setTickProgress: " + tickProgress);
        invalidate();
    }

    private void setTickAlpha(int tickAlpha) {
        mPaintTick.setAlpha(tickAlpha);
        postInvalidate();
    }

    private float getRingStrokeWidth() {
        return mPaintRing.getStrokeWidth();
    }

    private void setRingStrokeWidth(float strokeWidth) {
        mPaintRing.setStrokeWidth(strokeWidth);
        postInvalidate();
    }

     /*--------------属性动画---getter/setter end---------------*/

    public void success() {
        status = Status.SUCCESS;
        reset();
    }

    public void error() {
        status = Status.ERROR;
        reset();
    }

    public void clear() {
        status = Status.LOADING;
    }

    /**
     * 重置
     */
    private void reset() {
        initPaint();
        mFinalAnimatorSet.cancel();
        ringProgress = 0;
        circleRadius = -1;
        isAnimationRunning = false;
        final int radius = mConfig.getRadius();
        mRectF.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius);
        invalidate();
    }

    public TickViewConfig getConfig() {
        return mConfig;
    }

    public void setConfig(TickViewConfig tickViewConfig) {
        if (tickViewConfig == null) return;
        applyConfig(tickViewConfig);
    }

    public void startAnim() {
        stopAnim();
        startViewAnim(0f, 1f, 1000);
    }

    public void stopAnim() {
        if (valueAnimator != null) {
            clearAnimation();
            valueAnimator.setRepeatCount(1);
            valueAnimator.cancel();
            valueAnimator.end();
        }
    }

    ValueAnimator valueAnimator;

    private ValueAnimator startViewAnim(float startF, final float endF, long time) {
        valueAnimator = ValueAnimator.ofFloat(startF, endF);

        valueAnimator.setDuration(time);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);//无限循环
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);//

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                float value = (float) valueAnimator.getAnimatedValue();
                startAngle = 360 * value;

                invalidate();
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        });
        if (!valueAnimator.isRunning()) {
            valueAnimator.start();
        }

        return valueAnimator;
    }
}
