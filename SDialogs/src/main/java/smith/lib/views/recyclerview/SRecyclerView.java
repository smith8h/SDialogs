package smith.lib.views.recyclerview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.recyclerview.widget.RecyclerView;
import smith.lib.alerts.dialog.R;
import smith.lib.alerts.dialog.utils.SDialogUtils;

public class SRecyclerView extends RecyclerView {
    
    private int maxHeight;

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
        SDialogUtils utils = new SDialogUtils(context);
        if (attrs != null) {
            TypedArray styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.SScrollView);
            maxHeight = styledAttrs.getDimensionPixelSize(R.styleable.SScrollView_maxHeight, utils.dp(150));
            styledAttrs.recycle();
        }
    }

    public void setMaxHeight(int height) {
        maxHeight = height;
        postInvalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(maxHeight, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
