package xyz.pavelkorolevxyz.list.decoration.frame

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import xyz.pavelkorolevxyz.list.base.viewholder.ListItemViewHolder

/**
 * [RecyclerView.ItemDecoration] для отображения разделительных линий, описанных
 * в [FrameDecoratedItem].
 *
 * Для работы необходимо, чтобы [RecyclerView.ViewHolder] был наследником
 * [ListItemViewHolder] и в нём был закеширован элемент.
 */
class DelegatesFrameItemDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val viewHolder = parent.findContainingViewHolder(view) as? ListItemViewHolder ?: return

        (viewHolder.lastBoundItem as? FrameDecoratedItem)?.run {
            frameDecoration?.getItemOffsets(outRect, view, parent, state)
        }
    }
}
