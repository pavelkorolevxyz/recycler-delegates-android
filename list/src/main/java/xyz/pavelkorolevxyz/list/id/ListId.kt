package xyz.pavelkorolevxyz.list.id

import xyz.pavelkorolevxyz.list.id.IdUtils.hashLong64Bit
import xyz.pavelkorolevxyz.list.id.IdUtils.hashString64Bit

/**
 * Идентификатор модели ячейки списка.
 *
 * Реализация по аналогии с механизмом генерации Id в Epoxy
 *
 * [https://github.com/airbnb/epoxy/blob/688bddb13021d821a346d3ed38283acd2bd23f77/epoxy-adapter/src/main/java/com/airbnb/epoxy/EpoxyModel.java]
 */
@Suppress("MemberVisibilityCanBePrivate", "unused")
class ListId private constructor(
    val id: Long,
) {

    companion object {

        /**
         * Создаёт [ListId] на основе числового идентификатора
         *
         * @param id числовой идентификатор
         */
        fun create(id: Long): ListId {
            return ListId(id)
        }

        /**
         * Создаёт [ListId] на основе [hashCode] любого объекта
         */
        fun create(any: Any): ListId {
            return create(any.hashCode())
        }

        /**
         * Создаёт [ListId] на основе строкового идентификатора
         *
         * @param id строковый идентификатор
         */
        fun create(id: CharSequence): ListId {
            return create(hashString64Bit(id))
        }

        /**
         * Создаёт [ListId] на основе числовых идентификаторов
         *
         * @param ids числовые идентификаторы
         */
        fun create(vararg ids: Number): ListId {
            var result: Long = 0
            for (id in ids) {
                result = 31 * result + hashLong64Bit(id.hashCode().toLong())
            }
            return create(result)
        }

        /**
         * Создаёт [ListId] на основе нескольких строковых идентификаторов
         *
         * @param key основной строковый идентификатор
         * @param otherKeys дополнительные строковые идентификаторы
         */
        fun create(key: CharSequence, vararg otherKeys: CharSequence): ListId {
            var result: Long = hashString64Bit(key)
            for (otherKey in otherKeys) {
                result = 31 * result + hashString64Bit(otherKey)
            }
            return create(result)
        }

        /**
         * Создаёт [ListId] на основе строкового и числового идентификаторов
         *
         * @param key основной строковый идентификатор
         * @param id дополнительный числовой идентификатор
         */
        fun create(key: CharSequence, id: Long): ListId {
            var result = hashString64Bit(key)
            result = 31 * result + hashLong64Bit(id)
            return create(result)
        }

    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ListId) return false

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return id.toString()
    }

}
