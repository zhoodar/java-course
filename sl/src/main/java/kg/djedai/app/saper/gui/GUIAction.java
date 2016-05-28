package kg.djedai.app.saper.gui;


import kg.djedai.app.saper.BaseAction;
import kg.djedai.app.saper.Board;
import kg.djedai.app.saper.GeneratorBoard;
import kg.djedai.app.saper.MinerLogic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by User on
 * 15.05.2016.
 */
public class GUIAction extends BaseAction implements ActionListener, MouseListener{

    private GUIBoard board;

    public GUIAction(MinerLogic logic, GUIBoard board, GeneratorBoard generator) {
        super(logic, board, generator);
        this.board = board;
        this.board.addMouseListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        this.initGame();
    }


    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int corX=0;
        int corY=0;
        if(x>50){
            for(;;) {
                if(x<50){
                    break;
                }
                x-=50;
                corX++;
            }
        }
        if(y>50) {
            for(;;) {
                if(y<50){
                    break;
                }
                y-=50;
                corY++;
            }
        }
        select(corX, corY, e.getButton() != 1);
        board.repaint();
    }


    public void mousePressed(MouseEvent e) {

    }


    public void mouseReleased(MouseEvent e) {

    }


    public void mouseEntered(MouseEvent e) {

    }


    public void mouseExited(MouseEvent e) {

    }
}
