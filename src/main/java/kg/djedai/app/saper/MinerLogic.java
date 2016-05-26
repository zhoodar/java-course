package kg.djedai.app.saper;

/**
 * поведения Логика игры Сапер
 * Created by Zhoodar
 * @since 13.05.2016.
 */
public interface MinerLogic {
    /**
     * Загружает поле
     * @param cells поле
     */
    void loadBoard(Cell[][] cells);

    /**
     * Проверка взрыва в ячейке
     * @param x позиция по горизантали
     * @param y позиция по вертикали
     * @return врыв или нет
     */
    boolean shouldBang(int x, int y);

    /**
     * Проверка разгадал ли пользовател все ячейки если разгадал все закончивает программу
     * @return конец или нет
     */
    boolean finish();

    /**
     * Это события который приходит от пользователя, сохраняем
     * введеную позицию и проверяем догадку пользователя
     * @param x позиция по горизантали
     * @param y позиция по вертикали
     * @param bomb это бомба или нет
     */
    void guess(int x , int y, boolean bomb);


}
