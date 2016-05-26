package kg.djedai.app.saper;

/**
 * Поведения пользователского дествии
 * Created by User on 13.05.2016.
 */
public interface UserAction {
    /**
     * Иницализация игры
     */
    void initGame();

    /**
     * Действия пользователя который выбирает ячейку и педпологает пустая или бомба
     * @param x позиция по горизантали
     * @param y позиция по вертикали
     * @param bomb предположения пользователя
     */
    void select(int x, int y, boolean bomb);
}
