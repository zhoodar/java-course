package kg.djedai.app.saper.gui;

import kg.djedai.app.saper.Board;
import kg.djedai.app.saper.Cell;

import javax.swing.*;
import java.awt.*;

/**
 * Created by User on 15.05.2016.
 */
public class GUIBoard extends JPanel implements Board {

    public static final int PADDING = 50;

    public boolean real = false;

    public Cell<Graphics>[][] cells;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(this.cells != null) {
            for (int x=0;x!=cells.length;x++) {
                for (int y = 0; y!=cells[0].length; y++) {
                    cells[x][y].draw (g, x * PADDING, y * PADDING, PADDING, real);
                    g.setColor(Color.black);
                    g.drawRect(x * PADDING, y * PADDING, PADDING, PADDING);
                }
            }
        }
    }


    public void drawBoard(Cell[][] cells) {
        this.cells = cells;
        real = false;
        this.repaint();
    }

    public void drawCell(int x, int y) {
        this.repaint();

    }

    public void drawBum() {
        real = true;
        this.repaint();
    }


    public void drawCongratulate() {
        real = true;
        this.repaint();
    }
}
