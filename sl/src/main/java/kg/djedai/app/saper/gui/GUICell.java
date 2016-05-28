package kg.djedai.app.saper.gui;

import kg.djedai.app.saper.Cell;

import java.awt.*;

/**
 * Created by User on 15.05.2016.
 */
public class GUICell implements Cell<Graphics> {

    private boolean bomb;
    private boolean suggestBomb = false;
    private boolean suggestEmpty = false;

    public GUICell(boolean bomb){
        this.bomb=bomb;
    }

    @Override
    public boolean isBomb() {
        return this.bomb;
    }

    @Override
    public boolean isSuggestBomb() {
        return this.suggestBomb;
    }

    @Override
    public boolean isSuggestEmpty() {
        return this.suggestEmpty;
    }

    @Override
    public void suggestEmpty() {
        this.suggestEmpty = true;
    }

    @Override
    public void suggestBomb() {
        this.suggestBomb = true;
    }

    @Override
    public void draw(Graphics paint, boolean real) {

    }

    public void draw(Graphics paint, int cordX,int cordY, int cellSize, boolean real) {

        if(real) {
            if(this.isBomb()) {
                paint.setColor(Color.orange);
                paint.fillOval(cordX+10, cordY+10,cellSize-20,cellSize-20);
                paint.setColor(Color.RED);
                paint.drawOval(cordX+10, cordY+10,cellSize-20,cellSize-20);
            } else {
                paint.setColor(Color.DARK_GRAY);
                paint.fillRect(cordX+2,cordY+2, cellSize-3,cellSize-3);
                paint.setColor(Color.DARK_GRAY);
                paint.drawRect(cordX+2,cordY+2, cellSize-2,cellSize-2);
            }
        } else {
            if(this.suggestBomb) {
                paint.setColor(Color.blue);
                paint.fillOval(cordX+10, cordY+10,cellSize-20,cellSize-20);
                paint.setColor(Color.cyan);
                paint.drawOval(cordX + 10, cordY + 10, cellSize - 20, cellSize - 20);
            } else if (this.suggestEmpty) {
                paint.setColor(Color.DARK_GRAY);
                paint.fillRect(cordX + 2, cordY + 2, cellSize - 3, cellSize - 3);
                paint.setColor(Color.DARK_GRAY);
                paint.drawRect(cordX+2,cordY+2, cellSize-2,cellSize-2);
            } else {
                paint.setColor(Color.LIGHT_GRAY);
                paint.drawRect(cordX + 1, cordY + 1, cellSize - 1, cellSize - 1);
            }
        }
    }
}
