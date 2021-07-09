package xyz.pavelkorolevxyz.list.item

/**
 * Базовый интерфейс для всех моделей ячеек в списках с возможностью подсчёта diff
 */
interface DiffableListItem : ListItem, Diffable
