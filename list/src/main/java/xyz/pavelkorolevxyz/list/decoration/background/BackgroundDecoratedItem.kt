package xyz.pavelkorolevxyz.list.decoration.background

/**
 * Интерфейс для описания декораций фона у элемента в списке
 */
interface BackgroundDecoratedItem {

    /**
     * Делегат описывающий декорации фона
     */
    val backgroundDecoration: BackgroundDecorationDelegate?
}
