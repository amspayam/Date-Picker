package ir.pkokabi.datepicker;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by p.kokabi on 1/2/2018.
 */

public class ItemHorizontalDecoration extends RecyclerView.ItemDecoration {

    private int horizontalPadding;
    private int margin;

    ItemHorizontalDecoration(Context context) {
        horizontalPadding = context.getResources().getDimensionPixelSize(R.dimen.horizontalPadding);
        margin = context.getResources().getDimensionPixelSize(R.dimen.margin);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int position = parent.getChildAdapterPosition(view);

        outRect.right += margin;
        outRect.left += margin;

        if (position == 0)
            outRect.right += horizontalPadding;

        if (position == parent.getAdapter().getItemCount() - 1)
            outRect.left += horizontalPadding;
    }
}