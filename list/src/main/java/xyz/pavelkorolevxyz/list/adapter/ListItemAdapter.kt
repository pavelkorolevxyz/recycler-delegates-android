package xyz.pavelkorolevxyz.list.adapter

import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import xyz.pavelkorolevxyz.list.item.ListItem

/**
 * Универсальный адаптер для списков.
 * Работает на основе AdapterDelegates.
 *
 * При передаче данных в адаптер, он асинхронно считает diff и вызывает обновления в списках.
 *
 * @param delegates список делегатов, обрабатывающих определённый тип ячейки
 */
class ListItemAdapter(
    vararg delegates: AdapterDelegate<List<ListItem>>
) : AsyncListDifferDelegationAdapter<ListItem>(
    ListItemDiffCallback(),
    *delegates
)
