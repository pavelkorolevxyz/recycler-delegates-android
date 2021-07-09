package xyz.pavelkorolevxyz.recycler_delegates_android.decorations

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.RecyclerView
import xyz.pavelkorolevxyz.list.decoration.divider.BaseDividerDecorationDelegate
import xyz.pavelkorolevxyz.recycler_delegates_android.R

/**
 * Делегат для отрисовки разделительных в вертикальном списке.
 *
 * @param dividerColorRes ресурс цвета разделителя
 * @param paddingColorRes ресурс цвета отступа
 * @param dividerHeightRes ресурс размера разделителя по высоте
 * @param preDividerPaddingRes ресурс размера отступа между контентом и разделителем
 * @param postDividerPaddingRes ресурс размера отступа за разделителем
 * @param dividerPaddingLeftRes ресурс размера отступа разделителя слева
 * @param dividerPaddingRightRes ресурс размера отступа разделителя справа
 * @param animate флаг включена ли анимация в списке
 */
data class LineDividerDecorationDelegate constructor(
    @ColorRes val dividerColorRes: Int = R.color.dividerColor,
    @ColorRes val paddingColorRes: Int = R.color.white,
    @DimenRes val dividerHeightRes: Int = R.dimen.point,
    @DimenRes val preDividerPaddingRes: Int = R.dimen.zero,
    @DimenRes val postDividerPaddingRes: Int = R.dimen.zero,
    @DimenRes val dividerPaddingLeftRes: Int = R.dimen.zero,
    @DimenRes val dividerPaddingRightRes: Int = R.dimen.zero,
    val animate: Boolean = true,
) : BaseDividerDecorationDelegate() {

    private val dividerPaint: Paint by lazy {
        Paint().apply {
            style = Paint.Style.FILL
            color = ResourcesCompat.getColor(resources, dividerColorRes, null)
            isAntiAlias = true
        }
    }

    private val maxDividerPaintAlpha: Int by lazy {
        dividerPaint.alpha
    }

    private val paddingPaint: Paint by lazy {
        Paint().apply {
            style = Paint.Style.FILL
            color = ResourcesCompat.getColor(resources, paddingColorRes, null)
            isAntiAlias = true
        }
    }

    private val maxPaddingPaintAlpha: Int by lazy {
        paddingPaint.alpha
    }

    private val dividerHeightPx by lazy {
        resources.getDimensionPixelSize(dividerHeightRes)
    }

    private val dividerPaddingLeftPx by lazy {
        resources.getDimensionPixelSize(dividerPaddingLeftRes)
    }

    private val dividerPaddingRightPx by lazy {
        resources.getDimensionPixelSize(dividerPaddingRightRes)
    }

    private val preDividerPaddingPx by lazy {
        resources.getDimensionPixelSize(preDividerPaddingRes)
    }

    private val postDividerPaddingPx by lazy {
        resources.getDimensionPixelSize(postDividerPaddingRes)
    }

    private val overallHeightPx by lazy {
        preDividerPaddingPx + dividerHeightPx + postDividerPaddingPx
    }

    override fun onDrawTop(canvas: Canvas, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawTop(canvas, view, parent, state)

        /**
         * View может быть полностью прозрачным или невидимым из-за анимации [RecyclerView]
         * Не отображаем разделитель до того пока View не станет видимым.
         */
        if (view.isInvisible) return
        val lm = parent.layoutManager ?: return

        if (animate) {
            dividerPaint.alpha = (maxDividerPaintAlpha * view.alpha).toInt()
            paddingPaint.alpha = (maxPaddingPaintAlpha * view.alpha).toInt()
        } else {
            dividerPaint.alpha = 255
            paddingPaint.alpha = 255
        }

        canvas.drawRect(
            lm.getDecoratedLeft(view).toFloat() + dividerPaddingLeftPx,
            (view.translatedTop() - postDividerPaddingPx - dividerHeightPx),
            lm.getDecoratedRight(view).toFloat() - dividerPaddingRightPx,
            (view.translatedTop() - postDividerPaddingPx),
            dividerPaint
        )

        if (preDividerPaddingPx != 0) {
            canvas.drawRect(
                lm.getDecoratedLeft(view).toFloat(),
                (view.translatedTop() - overallHeightPx),
                lm.getDecoratedRight(view).toFloat(),
                (view.translatedTop() - postDividerPaddingPx - dividerHeightPx),
                paddingPaint
            )
        }

        if (postDividerPaddingPx != 0) {
            canvas.drawRect(
                lm.getDecoratedLeft(view).toFloat(),
                (view.translatedTop() - postDividerPaddingPx),
                lm.getDecoratedRight(view).toFloat(),
                (view.translatedTop()),
                paddingPaint
            )
        }
    }

    override fun onDrawBottom(canvas: Canvas, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawBottom(canvas, view, parent, state)

        /**
         * View может быть полностью прозрачным или невидимым из-за анимации [RecyclerView]
         * Не отображаем разделитель до того пока View не станет видимым.
         */
        if (view.isInvisible) return
        val lm = parent.layoutManager ?: return

        if (animate) {
            dividerPaint.alpha = (maxDividerPaintAlpha * view.alpha).toInt()
            paddingPaint.alpha = (maxPaddingPaintAlpha * view.alpha).toInt()
        } else {
            dividerPaint.alpha = 255
            paddingPaint.alpha = 255
        }

        canvas.drawRect(
            lm.getDecoratedLeft(view).toFloat() + dividerPaddingLeftPx,
            (view.translatedBottom() + preDividerPaddingPx),
            lm.getDecoratedRight(view).toFloat() - dividerPaddingRightPx,
            (view.translatedBottom() + preDividerPaddingPx + dividerHeightPx),
            dividerPaint
        )

        if (preDividerPaddingPx != 0) {
            canvas.drawRect(
                lm.getDecoratedLeft(view).toFloat(),
                (view.translatedBottom()),
                lm.getDecoratedRight(view).toFloat(),
                (view.translatedBottom() + preDividerPaddingPx),
                paddingPaint
            )
        }

        if (postDividerPaddingPx != 0) {
            canvas.drawRect(
                lm.getDecoratedLeft(view).toFloat(),
                (view.translatedBottom() + preDividerPaddingPx + dividerHeightPx),
                lm.getDecoratedRight(view).toFloat(),
                (view.translatedBottom() + overallHeightPx),
                paddingPaint
            )
        }
    }

    override fun getItemOffsetsTop(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsetsTop(outRect, view, parent, state)
        outRect.top += overallHeightPx
    }

    override fun getItemOffsetsBottom(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsetsBottom(outRect, view, parent, state)
        outRect.bottom += overallHeightPx
    }

    companion object {

        /**
         * Тонкая разделительная линия
         */
        val LINE = LineDividerDecorationDelegate(
            dividerHeightRes = R.dimen.point,
        )
    }
}
