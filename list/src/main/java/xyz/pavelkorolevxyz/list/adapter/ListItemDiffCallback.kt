package xyz.pavelkorolevxyz.list.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import xyz.pavelkorolevxyz.list.item.Diffable
import xyz.pavelkorolevxyz.list.item.ListItem

/**
 * Механизм вычисления diff для типа [ListItem]
 */
class ListItemDiffCallback : DiffUtil.ItemCallback<ListItem>() {

    override fun areItemsTheSame(
        oldItem: ListItem,
        newItem: ListItem
    ): Boolean {
        if (oldItem is Diffable && newItem is Diffable) {
            return newItem.isSameAs(oldItem)
        }
        return newItem === oldItem
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: ListItem,
        newItem: ListItem
    ): Boolean {
        if (oldItem is Diffable && newItem is Diffable) {
            return newItem.hasSameContentsAs(oldItem)
        }
        return newItem == oldItem
    }

    override fun getChangePayload(
        oldItem: ListItem,
        newItem: ListItem
    ): Any? {
        if (oldItem is Diffable && newItem is Diffable) {
            return newItem.getChangePayload(oldItem)
        }
        return super.getChangePayload(oldItem, newItem)
    }

}
