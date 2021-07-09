package xyz.pavelkorolevxyz.list.base.delegate

import androidx.annotation.CallSuper
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import xyz.pavelkorolevxyz.list.base.viewholder.ListItemViewHolder
import xyz.pavelkorolevxyz.list.item.ListItem

/**
 * Базовый класс для делегатов для отрисовки [ListItem] в адаптерах
 */
abstract class ListItemAdapterDelegate<T : ListItem, VH : ListItemViewHolder> : AbsListItemAdapterDelegate<T, ListItem, VH>() {

    /**
     * Проверяет может ли этот делегат обработать [item]
     */
    abstract override fun isForViewType(
        item: ListItem,
        items: List<ListItem>,
        position: Int
    ): Boolean

    /**
     * Связывает ViewHolder с данными
     */
    @CallSuper
    override fun onBindViewHolder(item: T, holder: VH, payloads: List<Any>) {
        holder.lastBoundItem = item
    }

}
