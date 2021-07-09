package xyz.pavelkorolevxyz.list.decoration.divider

/**
 * Интерфейс для описания вертикальных декораций у элемента в списке
 */
interface VerticalDecoratedItem {

    /**
     * Делегат описывающий верхний разделитель над элементом
     */
    val topDecoration: DividerDecorationDelegate?

    /**
     * Делегат описывающий нижний разделитель под элементом
     */
    val bottomDecoration: DividerDecorationDelegate?

}
