package xyz.pavelkorolevxyz.recycler_delegates_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import xyz.pavelkorolevxyz.list.adapter.ListItemAdapter
import xyz.pavelkorolevxyz.list.extensions.setup
import xyz.pavelkorolevxyz.list.id.ListId
import xyz.pavelkorolevxyz.list.item.ListItem
import xyz.pavelkorolevxyz.recycler_delegates_android.decorations.LineDividerDecorationDelegate
import xyz.pavelkorolevxyz.recycler_delegates_android.item_delegates.ButtonItemDelegate
import xyz.pavelkorolevxyz.recycler_delegates_android.item_delegates.TextItemDelegate

class MainActivity : AppCompatActivity() {

    private companion object {
        private val addButtonId = ListId.create("add_button")
        private val shuffleButtonId = ListId.create("shuffle_button")
    }

    private val adapter by lazy {
        ListItemAdapter(
            TextItemDelegate(),
            ButtonItemDelegate(onClick = ::onButtonListItemClick),
        )
    }

    private var k = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recycler)
        recyclerView.setup(adapter)

        loadList()
    }

    private fun loadList() {
        val items = mutableListOf<ListItem>().apply {
            ButtonItemDelegate.Model(
                id = shuffleButtonId,
                text = Text.Plain("Shuffle"),
            ).also { add(it) }

            for (i in 0 until 50) {
                createTextItem().also { add(it) }
            }

            ButtonItemDelegate.Model(
                id = addButtonId,
                text = Text.Plain("Add to end"),
            ).also { add(it) }
        }
        setItems(items)
    }

    private fun createTextItem(): TextItemDelegate.Model {
        return TextItemDelegate.Model(
            id = ListId.create("text_item", k.toLong()),
            text = Text.Plain("Text item ${k++}"),
            bottomDecoration = LineDividerDecorationDelegate.LINE,
        )
    }

    private fun setItems(items: List<ListItem>) {
        adapter.items = items
    }

    private fun onButtonListItemClick(listId: ListId) {
        when (listId) {
            shuffleButtonId -> shuffleItems()
            addButtonId -> addItem()
            else -> return
        }
    }

    private fun addItem() {
        setItems(adapter.items + createTextItem())
    }

    private fun shuffleItems() {
        setItems(adapter.items.shuffled())
    }
}