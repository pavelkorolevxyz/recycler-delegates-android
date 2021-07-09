package xyz.pavelkorolevxyz.list.decoration.frame

/**
 * Интерфейс для описания декораций фрейма у элемента в списке
 */
interface FrameDecoratedItem {

    /**
     * Делегат описывающий декорации фрейма
     */
    val frameDecoration: FrameDecorationDelegate?
}
