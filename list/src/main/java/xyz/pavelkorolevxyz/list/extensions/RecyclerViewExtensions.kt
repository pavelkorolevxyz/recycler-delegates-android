package xyz.pavelkorolevxyz.list.extensions

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import xyz.pavelkorolevxyz.list.decoration.background.DelegatesBackgroundItemDecoration
import xyz.pavelkorolevxyz.list.decoration.divider.DelegatesDividerItemDecoration
import xyz.pavelkorolevxyz.list.decoration.frame.DelegatesFrameItemDecoration

/**
 * Стартовая настройка [RecyclerView] с помощью [adapter]. Также возможно задать [layoutManager] и [decorations] отличные от значений по умолчанию
 */
fun RecyclerView.setup(
    adapter: RecyclerView.Adapter<*>,
    layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false),
    decorations: List<RecyclerView.ItemDecoration> = listOf(
        DelegatesBackgroundItemDecoration(),
        DelegatesFrameItemDecoration(),
        DelegatesDividerItemDecoration(),
    ),
) {
    // Выключает моргание ячеек при изменении контента
    (itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false

    this.layoutManager = layoutManager
    for (decoration in decorations) {
        addItemDecoration(decoration)
    }
    this.adapter = adapter
}
