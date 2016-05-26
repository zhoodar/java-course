package kg.djedai.app.saper;

import kg.djedai.app.saper.console.ConsoleBoard;
import kg.djedai.app.saper.level.Easy;

/**
 * Created by  on
 * 13.05.2016.
 */
public class BaseAction implements UserAction {
    private final GeneratorBoard generator;
    private final Board board;
    private final  MinerLogic logic;

    public BaseAction(final MinerLogic logic,final Board board, final GeneratorBoard generator ) {
        this.generator = generator;
        this.board = board;
        this.logic = logic;
    }



    @Override
    public void initGame() {
        final Cell[][] cells = generator.generate();
        this.board.drawBoard(cells);
        this.logic.loadBoard(cells);
    }

    @Override
    public void select(int x, int y, boolean bomb) {
        this.logic.guess(x,y,bomb);
        board.drawCell(x,y);
        if (this.logic.shouldBang(x,y)) {
            this.board.drawBum();
        }
        if (this.logic.finish()) {
            board.drawCongratulate();
        }
    }
}
