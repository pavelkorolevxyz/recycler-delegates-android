package xyz.pavelkorolevxyz.list.decoration.divider

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView
import java.lang.ref.WeakReference

/**
 * Базовый класс для [RecyclerView.ItemDecoration] с разделительными линиями.
 *
 * Инкапсулирует в себе логику получение контекста и ресурсов, чтобы не передавать их
 * явным образом при создании [RecyclerView.ItemDecoration].
 */
@Suppress("unused")
abstract class BaseDividerDecorationDelegate : DividerDecorationDelegate {

    private lateinit var contextWeak: WeakReference<Context>

    /**
     * Ссылка на ресурсы, полученная из контекста [RecyclerView].
     * Становится доступна только после вызова методов onDraw и getItemOffsets.
     */
    protected val resources: Resources
        get() = (contextWeak.get() ?: throw IllegalStateException("Context is null")).resources

    /**
     * Ссылка на контекст [RecyclerView].
     * Становится доступна только после вызова методов onDraw и getItemOffsets.
     */
    protected val context: Context
        get() = (contextWeak.get() ?: throw IllegalStateException("Context is null"))

    @CallSuper
    override fun onDrawOverTop(canvas: Canvas, view: View, parent: RecyclerView, state: RecyclerView.State) {
        contextWeak = WeakReference(parent.context)
    }

    @CallSuper
    override fun onDrawOverBottom(canvas: Canvas, view: View, parent: RecyclerView, state: RecyclerView.State) {
        contextWeak = WeakReference(parent.context)
    }

    @CallSuper
    override fun onDrawTop(canvas: Canvas, view: View, parent: RecyclerView, state: RecyclerView.State) {
        contextWeak = WeakReference(parent.context)
    }

    @CallSuper
    override fun onDrawBottom(canvas: Canvas, view: View, parent: RecyclerView, state: RecyclerView.State) {
        contextWeak = WeakReference(parent.context)
    }

    @CallSuper
    override fun getItemOffsetsTop(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        contextWeak = WeakReference(parent.context)
    }

    @CallSuper
    override fun getItemOffsetsBottom(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        contextWeak = WeakReference(parent.context)
    }

    @CallSuper
    override fun onDrawOverLeft(canvas: Canvas, view: View, parent: RecyclerView, state: RecyclerView.State) {
        contextWeak = WeakReference(parent.context)
    }

    @CallSuper
    override fun onDrawOverRight(canvas: Canvas, view: View, parent: RecyclerView, state: RecyclerView.State) {
        contextWeak = WeakReference(parent.context)
    }

    @CallSuper
    override fun onDrawLeft(canvas: Canvas, view: View, parent: RecyclerView, state: RecyclerView.State) {
        contextWeak = WeakReference(parent.context)
    }

    @CallSuper
    override fun onDrawRight(canvas: Canvas, view: View, parent: RecyclerView, state: RecyclerView.State) {
        contextWeak = WeakReference(parent.context)
    }

    @CallSuper
    override fun getItemOffsetsLeft(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        contextWeak = WeakReference(parent.context)
    }

    @CallSuper
    override fun getItemOffsetsRight(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        contextWeak = WeakReference(parent.context)
    }

    /**
     * Получает координату верх View с учётом сдвига
     */
    fun View.translatedTop() = top + translationY

    /**
     * Получает координату низа View с учётом сдвига
     */
    fun View.translatedBottom() = bottom + translationY

    /**
     * Получает координату правого края View с учётом сдвига
     */
    fun View.translatedRight() = right + translationX

    /**
     * Получает координату левого края View с учётом сдвига
     */
    fun View.translatedLeft() = left + translationX

}
