package smith.lib.views.recyclerview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.recyclerview.widget.RecyclerView;
import smith.lib.alerts.dialog.R;

public class SRecyclerView extends RecyclerView {
    
    private int maxHeight, maxWidth;

    public SRecyclerView(Context context) {
        super(context);
    }

    public SRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            init(context, attrs);
        }
    }

    public SRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode()) {
            init(context, attrs);
        }
    }

    @SuppressLint("CustomViewStyleable")
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
        maxHeight = height;
        postInvalidate();
    }

    public void setMaxWidth(int width) {
        maxWidth = width;
        postInvalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(maxHeight, MeasureSpec.AT_MOST);
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(maxWidth, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
