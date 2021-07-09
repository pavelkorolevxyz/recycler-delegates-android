package xyz.pavelkorolevxyz.recycler_delegates_android

import android.widget.TextView
import androidx.annotation.StringRes

/**
 * Описание текста
 */
sealed class Text {

    /**
     * Текст в виде строки
     */
    data class Plain(val string: String) : Text()

    /**
     * Текст в виде ссылке на string ресурс
     */
    data class Resource(@StringRes val stringRes: Int) : Text()
}

/**
 * Отображает текст по универсальному описанию
 */
fun TextView.setText(text: Text?) = when (text) {
    is Text.Plain -> setText(text.string)
    is Text.Resource -> setText(text.stringRes)
    null -> setText(null)
}
