package com.udacity.udacitynanodegreemovieapp.presentation.ui.recyclerview;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.DimenRes;
import android.support.annotation.Px;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/** An item decorator that adds equal spacing between grid items. */
public class GridItemDecoration extends RecyclerView.ItemDecoration {

  private final int space;
  private final int spanCount;

  public GridItemDecoration(Context context, @DimenRes int spaceDimenRes, int spanCount) {
    this(context.getResources().getDimensionPixelSize(spaceDimenRes), spanCount);
  }

  public GridItemDecoration(@Px int space, int spanCount) {
    this.space = space;
    this.spanCount = spanCount;
  }

  @Override
  public void getItemOffsets(
      Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
    int position = parent.getChildAdapterPosition(view);

    if (position >= 0) {
      int column = position % spanCount;

      outRect.left = column * space / spanCount;
      outRect.right = space - (column + 1) * space / spanCount;

      if (position >= spanCount) {
        outRect.top = space;
      }
    } else {
      outRect.left = 0;
      outRect.right = 0;
      outRect.top = 0;
      outRect.bottom = 0;
    }
  }
}
