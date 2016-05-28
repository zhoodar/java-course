package kg.djedai.app.saper;

import kg.djedai.app.saper.console.ConsoleBoard;
import kg.djedai.app.saper.console.ConsoleCell;
import kg.djedai.app.saper.level.Easy;
import org.junit.Test;

import static org.junit.Assert.*;

public class BaseActionTest {

    final BaseAction action = new BaseAction(
            new Easy(), new ConsoleBoard(),
            new GeneratorBoard(){
                @Override
                public Cell[][] generate(){
                    return new Cell[][]{
                            {new ConsoleCell(true),new ConsoleCell(false)},
                            {new ConsoleCell(true),new ConsoleCell(false)}};
                }
            }
    );

    @Test
    public void successGame(){
        action.initGame();
        action.select(1,1,false);
        action.select(1,0,false);

    }

}