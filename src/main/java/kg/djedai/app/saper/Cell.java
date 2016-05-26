package kg.djedai.app.saper;

import java.awt.*;

/**
 * Поведение ячеек
 * Created by Zhoodar
 * @since 13.05.2016.
 */
public interface Cell<E> {
    /**
     * проверка является ли ячейка бомбой
     * @return
     */
    boolean isBomb();

    /**
     * Пользовател предположил это бомба
     * @return
     */
    boolean isSuggestBomb();

    /**
     * Пользовател предположил это пустая ячейка
     * @return
     */
    boolean isSuggestEmpty();

    /**
     * устанавливет пустую ячейку
     */
    void suggestEmpty();

    /**
     * устанавливает бомбу в ячейку
     */
    void suggestBomb();

    /**
     * рисует клетку исходя введенго класаа E
     * @param paint generic передает спомогателный класс который будем рисоват
     * @param real
     */
    void draw(E paint, boolean real);

    void draw(E paint, int x, int y, int size, boolean real);
}
