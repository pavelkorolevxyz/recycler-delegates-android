package xyz.pavelkorolevxyz.list.decoration.divider

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import xyz.pavelkorolevxyz.list.base.viewholder.ListItemViewHolder

/**
 * [RecyclerView.ItemDecoration] для отображения разделительных линий, описанных
 * в [VerticalDecoratedItem].
 *
 * Для работы необходимо, чтобы [RecyclerView.ViewHolder] был наследником
 * [ListItemViewHolder] и в нём был закеширован элемент.
 */
class DelegatesDividerItemDecoration : RecyclerView.ItemDecoration() {

    private val bounds = Rect()

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        canvas.save()
        bounds.set(
            parent.paddingLeft,
            parent.paddingTop,
            parent.width - parent.paddingRight,
            parent.height - parent.paddingBottom
        )
        canvas.clipRect(bounds)

        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val viewHolder = parent.getChildViewHolder(child) as? ListItemViewHolder
                ?: continue
            val view = viewHolder.itemView

            (viewHolder.lastBoundItem as? VerticalDecoratedItem)?.run {
                topDecoration?.onDrawTop(canvas, view, parent, state)
                bottomDecoration?.onDrawBottom(canvas, view, parent, state)
            }
        }
        canvas.restore()
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val viewHolder = parent.findContainingViewHolder(view) as? ListItemViewHolder ?: return

        (viewHolder.lastBoundItem as? VerticalDecoratedItem)?.run {
            topDecoration?.getItemOffsetsTop(outRect, view, parent, state)
            bottomDecoration?.getItemOffsetsBottom(outRect, view, parent, state)
        }
    }

}
