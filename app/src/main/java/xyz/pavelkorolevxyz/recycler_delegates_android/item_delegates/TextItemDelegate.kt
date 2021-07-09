package xyz.pavelkorolevxyz.recycler_delegates_android.item_delegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import xyz.pavelkorolevxyz.list.base.delegate.ListItemAdapterDelegate
import xyz.pavelkorolevxyz.list.base.viewholder.ListItemViewHolder
import xyz.pavelkorolevxyz.list.decoration.divider.DividerDecorationDelegate
import xyz.pavelkorolevxyz.list.decoration.divider.VerticalDecoratedItem
import xyz.pavelkorolevxyz.list.id.ListId
import xyz.pavelkorolevxyz.list.item.DiffableListItem
import xyz.pavelkorolevxyz.list.item.ListItem
import xyz.pavelkorolevxyz.recycler_delegates_android.R
import xyz.pavelkorolevxyz.recycler_delegates_android.Text
import xyz.pavelkorolevxyz.recycler_delegates_android.setText

class TextItemDelegate :
    ListItemAdapterDelegate<TextItemDelegate.Model, TextItemDelegate.ViewHolder>() {

    data class Model(
        override val id: ListId,
        val text: Text,
        override val topDecoration: DividerDecorationDelegate? = null,
        override val bottomDecoration: DividerDecorationDelegate? = null,
    ) : DiffableListItem, VerticalDecoratedItem

    class ViewHolder(itemView: View) : ListItemViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun isForViewType(
        item: ListItem,
        items: List<ListItem>,
        position: Int
    ): Boolean = item is Model

    override fun onCreateViewHolder(
        parent: ViewGroup,
    ): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_text, parent, false)
    )

    override fun onBindViewHolder(
        item: Model,
        holder: ViewHolder,
        payloads: List<Any>
    ) {
        super.onBindViewHolder(item, holder, payloads)
        holder.textView.setText(item.text)
    }
}
