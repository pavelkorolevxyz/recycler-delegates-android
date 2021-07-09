package xyz.pavelkorolevxyz.list.base.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import xyz.pavelkorolevxyz.list.item.ListItem

/**
 * Базовый [RecyclerView.ViewHolder] для типа [ListItem],
 * кэширующий последний обработанный элемент.
 */
abstract class ListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    /**
     * Последний обработанный во ViewHolder элемент
     */
    var lastBoundItem: ListItem? = null
        internal set
}
