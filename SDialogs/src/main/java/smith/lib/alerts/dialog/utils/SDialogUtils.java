/*
 * Created by Dev. Smith (Hussein Shakir) on 9/17/23, 8:01 PM
 * Copyright Ⓒ 2023. All rights reserved Ⓒ 2023 http://github.com/smith8h
 * Last modified: 9/17/23, 7:37 PM
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *                  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package smith.lib.alerts.dialog.utils;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.math.MathUtils;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.Contract;

import java.util.Calendar;

import smith.lib.views.recyclerview.SRecyclerView;

@SuppressWarnings({"unused", "all"})
public class SDialogUtils {

    /**
     * The default color suggested for use in SDialogs.
     */
    public static final int COLOR_DEFAULT = 0xFFA7B4C5;

    /**
     * The light or dark theme setting of SDialogs, based on device's settings.
     */
    public static final int THEME_BY_SYSTEM = 0;
    /**
     * The dark theme setting of SDialogs.
     */
    public static final int THEME_DARK = 1;
    /**
     * The light theme setting of SDialogs.
     */
    public static final int THEME_LIGHT = 2;

    /**
     * The constant key of id of items in {@link  MultiSelectSDialog}.
     * Used to return the id of checked items.
     */
    public static final String KEY_ITEM_ID = "id";
    /**
     * The constant key of text of items in {@link  MultiSelectSDialog}.
     * Used to return the text string of checked items.
     */
    public static final String KEY_ITEM_TEXT = "text";
    /**
     * The constant key of checked items in {@link  MultiSelectSDialog}.
     * Used to return the condition of checked items.
     */
    public static final String KEY_ITEM_CHECKED = "checked";

    private final Context context;

    public SDialogUtils(Context context) {
        this.context = context;
    }

    /**
     * Animate Views with property Alpha.
     *
     * @param view Pass any view to animate it.
     */
    public void animateView(@NonNull View view) {
        view.setAlpha(0f);
        ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
                .setDuration(620)
                .start();
    }

    /**
     * Check if devise dark mode enabled.
     *
     * @return true if dark mode is enabled.
     */
    public boolean isNightModeON() {
        var flags = context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        return flags == Configuration.UI_MODE_NIGHT_YES;
    }

    /**
     * Set a Drawable background with round corners by 24dp.
     */
    public void backgroundColor(@NonNull View view, int color) {
        var gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(color);
        gradientDrawable.setCornerRadius(dp(24));

        var rippleDrawable = new RippleDrawable(ColorStateList.valueOf(color), gradientDrawable, null);

        view.setBackground(rippleDrawable);
    }

    /**
     * Get a darker color from existing one.
     *
     * @param color  the color you want to get a darker degree of it.
     * @param factor the factor to make the color darker or less darker, values are a range of 0.0f-1.0f.
     * @return the new darker color from that original one using the factor as degree of brightness.
     */
    public int darkerColor(int color, float factor) {
        int alpha = Color.alpha(color);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha,
                Math.max((int) (red * factor), 0),
                Math.max((int) (green * factor), 0),
                Math.max((int) (blue * factor), 0));
    }

    /**
     * Get a lighter color from existing one.
     *
     * @param color  the color you want to get a lighter degree of it.
     * @param factor the factor to make the color lighter or less lighter, values are a range of 0.0f-1.0f.
     * @return the new lighter color from that original one using the factor as degree of brightness.
     */
    public int lighterColor(int color, float factor) {
        int alpha = Color.alpha(color);
        int red = (int) ((Color.red(color) * (1 - factor) / 255 + factor) * 255);
        int green = (int) ((Color.green(color) * (1 - factor) / 255 + factor) * 255);
        int blue = (int) ((Color.blue(color) * (1 - factor) / 255 + factor) * 255);
        return Color.argb(alpha, red, green, blue);
    }

    /**
     * Convert pixels to its equivalent density dependent pixels.
     *
     * @param dp Int value of density pixels.
     * @return Returns Int value of dp.
     */
    public int dp(int dp) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                context.getResources().getDisplayMetrics()
        );
    }

    /**
     * Create the progress drawable for {@link smith.lib.alerts.dialog.ProgressSDialog}.
     * this progress will only be shown in SDK 29 and up..
     *
     * @param progressColor   the foreground color (the progress track).
     * @param backgroundColor the background color (the track background).
     * @return progress layer list as drawable.
     */
    @RequiresApi(Build.VERSION_CODES.Q)
    public Drawable createProgressLayerList(int progressColor, int backgroundColor) {
        var cornerRadius = dp(6);

        var backgroundShape = new RoundRectShape(
                new float[]{
                        cornerRadius,
                        cornerRadius,
                        cornerRadius,
                        cornerRadius,
                        cornerRadius,
                        cornerRadius,
                        cornerRadius,
                        cornerRadius
                },
                null, null
        );

        var backgroundDrawable = new ShapeDrawable(backgroundShape);
        backgroundDrawable.getPaint().setColor(backgroundColor);

        var progressShape = new RoundRectShape(
                new float[]{
                        cornerRadius,
                        cornerRadius,
                        cornerRadius,
                        cornerRadius,
                        cornerRadius,
                        cornerRadius,
                        cornerRadius,
                        cornerRadius
                },
                null, null
        );

        var progressDrawable = new ShapeDrawable(progressShape);
        progressDrawable.getPaint().setColor(progressColor);

        // Set the layer list
        var layers = new Drawable[2];
        layers[0] = backgroundDrawable;
        layers[1] = new ScaleDrawable(progressDrawable, Gravity.START, 1f, -1f);

        var layerDrawable = new LayerDrawable(layers);
        layerDrawable.setId(0, android.R.id.background);
        layerDrawable.setId(1, android.R.id.progress);

        return layerDrawable;
    }

    @NonNull
    @Contract(value = " -> new", pure = true)
    public static String[] getDays1Chars() {
        return new String[]{
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
                "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"
        };
    }

    @NonNull
    @Contract(value = " -> new", pure = true)
    public static String[] getDays2Chars() {
        return new String[]{
                "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
                "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
                "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"
        };
    }

    @NonNull
    @Contract(value = " -> new", pure = true)
    public static String[] getMonths1Chars() {
        return new String[]{
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"
        };
    }

    @NonNull
    @Contract(value = " -> new", pure = true)
    public static String[] getMonths2Chars() {
        return new String[]{
                "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"
        };
    }


    @NonNull
    @Contract(value = " -> new", pure = true)
    public static String[] getMonths3Chars() {
        return new String[]{
                "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
        };
    }

    @NonNull
    @Contract(value = " -> new", pure = true)
    public static String[] getMonthsFullChars() {
        return new String[]{
                "January", "February", "March", "April", "May", "June", "July", "August",
                "September", "October", "November", "December"
        };
    }

    @NonNull
    @Contract(value = " -> new", pure = true)
    public static String[] getYears() {
        String[] years = {};
        int year = Calendar.getInstance().get(Calendar.YEAR) - 120;
        for (int i = 0; i < 120; i++) {
            years[i] = String.valueOf(year);
            year++;
        }
        return years;
    }

    /**
     * A spinner which allows the user to scroll the whole list through a single
     * dragging gesture.
     * This is a helper class which binds to an existing recycler
     * view. The use of recycler view ensures better performance and
     * better long-term support.
     * <p>
     * This view supports both vertical and horizontal scrolling;
     * set the orientation by using {@link LinearLayoutManager#setOrientation(int)}
     * <p>
     * Customization is not part of this class; check the companion demo app to see
     * how to add indication arrows and for other tricks.
     *
     * @author code by Darkion Avey; original UX design by Oleg Frolov.
     * @By: Implemented by Dev. Smith
     */
    public static class SwipeSpinner {

        //set to 1 to make scrolling linear; >1 to make it exponential
        private static final double SCROLL_EXPONENT = 2.8;
        private static final boolean DEBUG = false;
        private SRecyclerView mRecyclerView;
        private final static String TAG = "SwipeSpinnerHelper";
        private ScrollCallbacks mScrollCallbacks;
        private float mInitXY;
        private float xyDifference;
        private long mDownTime;
        private int mTouchSlop;
        private boolean mDraggingUp = true;
        private PagerSnapHelper mPagerSnapHelper = new PagerSnapHelper();
        private Runnable mScrollByRunnable = new Runnable() {
            @Override
            public void run() {
                float translation = (isVertical() ?
                        mRecyclerView.getTranslationY() : mRecyclerView.getTranslationX());
                float fraction = MathUtils.clamp(
                        Math.abs((translation / getDragThreshold())), 0f, 1f
                );
                int scroll = (int) (0f - translation * Math.pow(fraction, SCROLL_EXPONENT));
                if (Math.abs(scroll) > 0 &&
                        (isVertical() && mRecyclerView.canScrollVertically(scroll) ||
                                !isVertical() && mRecyclerView.canScrollHorizontally(scroll))) {
                    if (mScrollCallbacks != null)
                        mScrollCallbacks.onScrolled((translation > 0 ? 1f : -1) * fraction);
                    mRecyclerView.scrollBy(isVertical() ? 0 : scroll, isVertical() ? scroll : 0);
                }
                mRecyclerView.post(mScrollByRunnable);
            }
        };

        @NonNull
        @Contract("_ -> new")
        public static SwipeSpinner bindRecyclerView(@NonNull SRecyclerView recyclerView) {
            return new SwipeSpinner(recyclerView);
        }

        public SRecyclerView getRecyclerView() {
            return mRecyclerView;
        }

        private SwipeSpinner(@NonNull final SRecyclerView recyclerView) {
            this.mRecyclerView = recyclerView;
            recyclerView.post(new Runnable() {
                @Override
                public void run() {
                    if (!(recyclerView.getLayoutManager() instanceof LinearLayoutManager))
                        throw new RuntimeException("Layout manager should not be null and should be instance of LinearLayoutManager");
                    mPagerSnapHelper.attachToRecyclerView(mRecyclerView);
                    ViewConfiguration viewConfiguration = ViewConfiguration.get(mRecyclerView.getContext());
                    mTouchSlop = viewConfiguration.getScaledTouchSlop();
                    mRecyclerView.addOnItemTouchListener(new SRecyclerView.OnItemTouchListener() {
                        @Override
                        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                            return handleTouchEvent(e);
                        }

                        @Override
                        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                            handleTouchEvent(e);
                        }

                        @Override
                        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
                        }
                    });
                }
            });
        }

        /**
         * Use this method to register callbacks to scrolling and
         * reset events. See {@link ScrollCallbacks}
         *
         * @param scrollCallbacks the callbacks object that you want to register
         */
        public void setScrollCallbacks(ScrollCallbacks scrollCallbacks) {
            this.mScrollCallbacks = scrollCallbacks;
        }

        private LinearLayoutManager getLinearLayoutManager() {
            return (LinearLayoutManager) mRecyclerView.getLayoutManager();
        }

        /**
         * Convenience method to check if attached linear layout manager
         * is vertical or horizontal. This potentially throws NullPointerException
         *
         * @return if LinearLayoutManager is vertical
         */
        public boolean isVertical() {
            return getLinearLayoutManager().getOrientation() == RecyclerView.VERTICAL;
        }

        /**
         * This is used in both onInterceptTouchEvent and onTouchEvent
         * because of the RecyclerView.OnItemTouchListener works. If
         * you were to create a class extending RecyclerView, you can
         * use this only in onTouchEvent, and return true to touch interception
         */
        private boolean handleTouchEvent(@NonNull MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    xyDifference = 0;
                    //for a lousy fling detection
                    mDownTime = System.currentTimeMillis();
                    mInitXY = isVertical() ? event.getRawY() : event.getRawX();
                    return true;
                case MotionEvent.ACTION_MOVE:
                    float currentXY = isVertical() ? event.getRawY() : event.getRawX();
                    float translation = xyDifference = currentXY - mInitXY;
                    if (Math.abs(xyDifference) > mTouchSlop) {
                        mRecyclerView.removeCallbacks(mScrollByRunnable);
                        mDraggingUp = currentXY < mInitXY;
                        float interpolatedProgress = getInterpolatedProgress(translation, mDraggingUp);
                        if (isVertical())
                            mRecyclerView.setTranslationY(interpolatedProgress);
                        else mRecyclerView.setTranslationX(interpolatedProgress);

                        int direction = (int) (-translation + mRecyclerView.getTranslationY());
                        if (isVertical() && mRecyclerView.canScrollVertically(direction) || !isVertical() && mRecyclerView.canScrollHorizontally(direction))
                            mRecyclerView.post(mScrollByRunnable);
                        return true;

                    }
                    return false;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    mRecyclerView.removeCallbacks(mScrollByRunnable);
                    boolean isFling = System.currentTimeMillis() - mDownTime <= 250 && Math.abs(xyDifference) > mTouchSlop;
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();


                    //not using fling detection would result in displacement
                    //that is not large enough to move the item (due to interpolation)
                    //hence we use try to detect fling and manually shift the item
                    if (isFling) {
                        int position = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                        View view = linearLayoutManager.findViewByPosition(position);
                        if (view == null) view = mRecyclerView;
                        int widthHeight = isVertical() ? view.getHeight() : view.getWidth();
                        if (!mDraggingUp) widthHeight *= -1;
                        final int finalWidthHeight = widthHeight;
                        mRecyclerView.post(new Runnable() {
                            @Override
                            public void run() {
                                mRecyclerView.smoothScrollBy(isVertical() ? 0 : finalWidthHeight, isVertical() ? finalWidthHeight : 0);
                            }
                        });

                    } else {
                        snapToItem();
                    }

                    //using spring animation after flinging is distracting
                    //hence we opt for eye-friendly DecelerateInterpolator
                    if (Math.abs(isVertical() ? mRecyclerView.getTranslationY() : mRecyclerView.getTranslationX()) < getDragThreshold() / 2f)
                        mRecyclerView.animate().setInterpolator((TimeInterpolator) LogDecelerateInterpolator.LOG_DECELERATE_INTERPOLATOR).translationX(0).translationY(0).start();
                    else {
                        final SpringAnimation springAnim = new SpringAnimation(mRecyclerView, isVertical() ? DynamicAnimation.TRANSLATION_Y : DynamicAnimation.TRANSLATION_X, 0);
                        springAnim.getSpring().setStiffness(500f);
                        springAnim.start();
                    }
                    if (mScrollCallbacks != null) mScrollCallbacks.onResetScroll();
                    xyDifference = 0;
                    return true;
            }
            return true;
        }

        /**
         * Manual invocation of SnapHelper since it doesn't work
         * when using {@link RecyclerView#scrollBy(int, int)}; it
         * only works with touch gestures, which is not the case here.
         */
        private void snapToItem() {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
            int id = mDraggingUp ? linearLayoutManager.findLastCompletelyVisibleItemPosition() : linearLayoutManager.findFirstCompletelyVisibleItemPosition();
            if (id == -1)
                id = mDraggingUp ? linearLayoutManager.findLastVisibleItemPosition() : linearLayoutManager.findFirstVisibleItemPosition();
            if (id == -1)
                id = mDraggingUp ? linearLayoutManager.findFirstCompletelyVisibleItemPosition() : linearLayoutManager.findLastCompletelyVisibleItemPosition();
            if (id == -1)
                id = mDraggingUp ? linearLayoutManager.findFirstVisibleItemPosition() : linearLayoutManager.findLastVisibleItemPosition();

            if (id != -1) {
                View view = linearLayoutManager.findViewByPosition(id);
                if (view != null) {
                    int[] values = mPagerSnapHelper.calculateDistanceToFinalSnap(linearLayoutManager, view);
                    if (values != null) mRecyclerView.smoothScrollBy(values[0], values[1]);
                    else if (DEBUG) Log.w(TAG, "snapToItem: values[] is null");
                } else if (DEBUG) Log.w(TAG, "snapToItem: view is null");
            } else if (DEBUG) Log.w(TAG, "snapToItem: ID is -1");
        }

        /**
         * Interpolate drag action to prevent over-drag beyond threshold
         *
         * @author Plaid@Github
         */
        private float getInterpolatedProgress(float translation, boolean draggingUp) {
            float dragFraction = (float) Math.log10(1 + (Math.abs(translation) / getDragThreshold()));
            float dragTo = dragFraction * getDragThreshold();
            if (draggingUp) {
                dragTo *= -1;
            }
            return dragTo;
        }

        /**
         * @return how many pixel this view can be dragged
         */
        private float getDragThreshold() {
            return isVertical() ? mRecyclerView.getHeight() : mRecyclerView.getWidth() * (isVertical() ? 1.5f : 0.9f);
        }

        /**
         * @author Launcher3
         */
        private static class LogDecelerateInterpolator implements TimeInterpolator {
            int mBase;
            int mDrift;
            final float mLogScale;
            static final LogDecelerateInterpolator LOG_DECELERATE_INTERPOLATOR = new LogDecelerateInterpolator(80, 0);

            LogDecelerateInterpolator(int base, int drift) {
                mBase = base;
                mDrift = drift;
                mLogScale = 1f / computeLog(1, mBase, mDrift);
            }

            static float computeLog(float t, int base, int drift) {
                return (float) -Math.pow(base, -t) + 1 + (drift * t);
            }

            @Override
            public float getInterpolation(float t) {
                return Float.compare(t, 1f) == 0 ? 1f : computeLog(t, mBase, mDrift) * mLogScale;
            }
        }

        /**
         * Interface that allows detection of scrolling events.
         * You can use directedInterpolationFraction to detect the
         * scrolling direction as well as the amount.
         * directedInterpolationFraction value is between -1 & 1
         */
        public interface ScrollCallbacks {
            void onScrolled(float directedInterpolationFraction);

            void onResetScroll();
        }
    }

}
