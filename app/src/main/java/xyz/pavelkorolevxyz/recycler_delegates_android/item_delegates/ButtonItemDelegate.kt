package xyz.pavelkorolevxyz.recycler_delegates_android.item_delegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import xyz.pavelkorolevxyz.list.base.delegate.ListItemAdapterDelegate
import xyz.pavelkorolevxyz.list.base.viewholder.ListItemViewHolder
import xyz.pavelkorolevxyz.list.id.ListId
import xyz.pavelkorolevxyz.list.item.DiffableListItem
import xyz.pavelkorolevxyz.list.item.ListItem
import xyz.pavelkorolevxyz.recycler_delegates_android.R
import xyz.pavelkorolevxyz.recycler_delegates_android.Text
import xyz.pavelkorolevxyz.recycler_delegates_android.setText

class ButtonItemDelegate(
    private val onClick: (ListId) -> Unit,
) :
    ListItemAdapterDelegate<ButtonItemDelegate.Model, ButtonItemDelegate.ViewHolder>() {

    data class Model(
        override val id: ListId,
        val text: Text,
    ) : DiffableListItem

    class ViewHolder(itemView: View) : ListItemViewHolder(itemView) {
        val button: Button = itemView.findViewById(R.id.button)
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
            .inflate(R.layout.item_button, parent, false)
    )

    override fun onBindViewHolder(
        item: Model,
        holder: ViewHolder,
        payloads: List<Any>
    ) {
        super.onBindViewHolder(item, holder, payloads)
        holder.button.setText(item.text)
        holder.button.setOnClickListener { onClick(item.id) }
    }
}
