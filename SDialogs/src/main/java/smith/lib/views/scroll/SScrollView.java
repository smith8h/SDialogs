package smith.lib.views.scroll;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ScrollView;
import smith.lib.alerts.dialog.R;

public class SScrollView extends ScrollView {
    
    private int maxHeight, maxWidth;

    public SScrollView(Context context) {
        super(context);
    }

    public SScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            init(context, attrs);
        }
    }

    public SScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode()) {
            init(context, attrs);
        }
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.SScrollView);
            int defaultHeight = 200;
            maxHeight = styledAttrs.getDimensionPixelSize(R.styleable.SScrollView_maxHeight, defaultHeight);
            int defaultWidth = 300;
            maxWidth = styledAttrs.getDimensionPixelSize(R.styleable.SScrollView_maxWidth, defaultWidth);
            styledAttrs.recycle();
        }
    }

    public void setMaxHeight(int height) {
        this.maxHeight = height;
        postInvalidate();
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
        postInvalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(maxHeight, MeasureSpec.AT_MOST);
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(maxWidth, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
