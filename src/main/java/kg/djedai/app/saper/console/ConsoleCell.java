package kg.djedai.app.saper.console;

import kg.djedai.app.saper.Cell;

import java.io.PrintStream;

/**
 * Created by Zhoodar
 * @since 14.05.2016.
 */
public class ConsoleCell implements Cell<PrintStream> {
    private boolean bomb;
    private boolean suggestBomb = false;
    private boolean suggestEmpty = false;

    public ConsoleCell(boolean bomb) { this.bomb = bomb; }


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
    public void draw(PrintStream paint, boolean real) {
        if(real) {
            if(this.isBomb()) {
                paint.print("[*] ");
            } else {
                paint.print("[ ] ");
            }
        } else {
            if(this.suggestBomb) {
                paint.print("[?] ");
            } else if (this.suggestEmpty) {
                paint.print("[ ] ");
            } else {
                paint.print("[X] ");
            }
        }

    }

    @Override
    public void draw(PrintStream paint, int x, int y, int size, boolean b) {

    }

}
