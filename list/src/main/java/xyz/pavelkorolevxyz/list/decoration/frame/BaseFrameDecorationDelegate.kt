package xyz.pavelkorolevxyz.list.decoration.frame

import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView
import java.lang.ref.WeakReference

/**
 * Базовый класс для [RecyclerView.ItemDecoration] с отступами.
 *
 * Инкапсулирует в себе логику получение ресурсов, чтобы не передавать их
 * явным образом при создании [RecyclerView.ItemDecoration].
 */
abstract class BaseFrameDecorationDelegate : FrameDecorationDelegate {

    private lateinit var resourcesWeak: WeakReference<Resources>

    protected val resources: Resources
        get() = resourcesWeak.get() ?: throw IllegalStateException("Resources is null")

    @CallSuper
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        resourcesWeak = WeakReference(parent.resources)
    }
}
