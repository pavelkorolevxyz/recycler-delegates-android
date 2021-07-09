package xyz.pavelkorolevxyz.list.decoration.divider

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Интерфейс для делегатов описывающих отрисовку разделителей в [RecyclerView.ItemDecoration]
 */
interface DividerDecorationDelegate {

    fun onDrawTop(canvas: Canvas, view: View, parent: RecyclerView, state: RecyclerView.State) = Unit
    fun onDrawBottom(canvas: Canvas, view: View, parent: RecyclerView, state: RecyclerView.State) = Unit
    fun onDrawLeft(canvas: Canvas, view: View, parent: RecyclerView, state: RecyclerView.State) = Unit
    fun onDrawRight(canvas: Canvas, view: View, parent: RecyclerView, state: RecyclerView.State) = Unit

    fun onDrawOverTop(canvas: Canvas, view: View, parent: RecyclerView, state: RecyclerView.State)
    fun onDrawOverBottom(canvas: Canvas, view: View, parent: RecyclerView, state: RecyclerView.State)
    fun onDrawOverLeft(canvas: Canvas, view: View, parent: RecyclerView, state: RecyclerView.State)
    fun onDrawOverRight(canvas: Canvas, view: View, parent: RecyclerView, state: RecyclerView.State)

    fun getItemOffsetsTop(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State)
    fun getItemOffsetsBottom(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State)
    fun getItemOffsetsLeft(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State)
    fun getItemOffsetsRight(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State)

}
