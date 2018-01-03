package ir.pkokabi.datepicker;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by p.kokabi on 1/2/2018.
 */

public class ItemVerticalDecoration extends RecyclerView.ItemDecoration {

    private int horizontalPadding;
    private int verticalPadding;
    private int margin;

    ItemVerticalDecoration(Context context) {
        horizontalPadding = context.getResources().getDimensionPixelSize(R.dimen.horizontalPadding);
        verticalPadding = context.getResources().getDimensionPixelSize(R.dimen.verticalPadding);
        margin = context.getResources().getDimensionPixelSize(R.dimen.margin);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int position = parent.getChildAdapterPosition(view);
        outRect.right += horizontalPadding + margin;
        outRect.left += horizontalPadding + margin;
        outRect.top += margin;
        outRect.bottom += margin;

        if (position == 0)
            outRect.top += margin;

        if (position == parent.getAdapter().getItemCount() - 1)
            outRect.bottom += margin;
    }
}