package kg.djedai.app.saper.console;

import kg.djedai.app.saper.Board;
import kg.djedai.app.saper.Cell;

/**
 * Created by User on
 * 14.05.2016.
 */
public class ConsoleBoard implements Board {
    /**
     * содержить карты
     */
    private Cell[][] cells;


    public void drawBoard(Cell[][] cells){
        this.cells = cells;
        this.redraw(false);

    }

    public void drawCell (int x, int y) {
        System.out.println("****** SELECT *******");
        this.redraw(false);
    }

    @Override
    public void drawBum() {
        System.out.println("****** BANG *******");
        this.redraw(true);
    }


    public void drawCongratulate() {
        System.out.println("***** CONGRATULATE *****");
    }


    private void redraw(boolean real ) {
        for( Cell[] row : cells ) {
            for (Cell cell : row) {
                cell.draw(System.out, real);
            }
            System.out.println();
        }
        System.out.println();
    }
}
