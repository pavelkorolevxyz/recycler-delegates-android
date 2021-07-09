package xyz.pavelkorolevxyz.list.item

import xyz.pavelkorolevxyz.list.id.ListId

/**
 * Базовый интерфейс для описания логики подсчёта diff модели ячейки
 */
interface Diffable {

    /**
     * Идентификатор элемента в списке.
     *
     * Должен быть уникальным на уровне конкретного адаптера.
     */
    val id: ListId

    /**
     * Проверяет что этот элемент и другой элемент одинаковые
     */
    fun isSameAs(another: Diffable): Boolean =
        this::class == another::class && this.id == another.id

    /**
     * Проверяет что содержимое этого элемента и другого не отличается
     */
    fun hasSameContentsAs(another: ListItem): Boolean =
        this == another

    /**
     * Считает разницу между этим элементом и возвращает любой объект.
     *
     * Например, можно вернуть object-маркер изменения ячейки
     * или data class для более гранулярного изменения.
     */
    fun getChangePayload(another: ListItem): Any? = null

}
