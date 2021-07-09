package xyz.pavelkorolevxyz.list.decoration.frame

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Интерфейс для делегатов описывающих отрисовку декораций фрейма в [RecyclerView.ItemDecoration]
 */
interface FrameDecorationDelegate {

    fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State)
}
