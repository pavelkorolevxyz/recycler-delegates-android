package xyz.pavelkorolevxyz.list.decoration.background

import android.graphics.Canvas
import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView
import xyz.pavelkorolevxyz.list.base.viewholder.ListItemViewHolder

/**
 * [RecyclerView.ItemDecoration] для отображения разделительных линий, описанных
 * в [BackgroundDecoratedItem].
 *
 * Для работы необходимо, чтобы [RecyclerView.ViewHolder] был наследником
 * [ListItemViewHolder] и в нём был закеширован элемент.
 */
class DelegatesBackgroundItemDecoration : RecyclerView.ItemDecoration() {

    private val rect = Rect()

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(canvas, parent, state)

        canvas.save()
        rect.set(parent.paddingLeft, parent.paddingTop, parent.width - parent.paddingRight, parent.height - parent.paddingBottom)
        canvas.clipRect(rect)

        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val viewHolder = parent.getChildViewHolder(child) as? ListItemViewHolder
                ?: continue
            val view = viewHolder.itemView

            (viewHolder.lastBoundItem as? BackgroundDecoratedItem)?.run {
                backgroundDecoration?.onDraw(canvas, view, parent, state)
            }
        }
        canvas.restore()
    }
}
