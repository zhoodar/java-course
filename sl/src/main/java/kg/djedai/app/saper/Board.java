package kg.djedai.app.saper;

/**
 * интерфейс поведение доски
 * Created by Zhoodar
 * @since 13.05.2016.
 */
public interface Board {
    /**
     * Рисует доску исходя из входяшего массива ячеек.
     * @param cells массив ячеек.
     */
    void drawBoard(Cell[][] cells);

    /**
     * Рисует ячейку
     * @param x позиция по горизантали
     * @param y позиция по вертикали
     */
    void drawCell(int x, int y);

    /**
     * Рисует взрыва всех бомб
     */
    void drawBum();

    /**
     * Рисует поздравление
     */
    void drawCongratulate();
}
