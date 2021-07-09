package xyz.pavelkorolevxyz.list.decoration.background

import android.graphics.Canvas
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Интерфейс для делегатов описывающих отрисовку фона в [RecyclerView.ItemDecoration]
 */
interface BackgroundDecorationDelegate {

    fun onDraw(canvas: Canvas, view: View, parent: RecyclerView, state: RecyclerView.State)
}
