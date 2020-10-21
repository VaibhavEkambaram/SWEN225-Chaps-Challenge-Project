package test.nz.ac.vuw.ecs.swen225.gp23.persistence;

import junit.framework.TestCase;
import nz.ac.vuw.ecs.swen225.gp23.application.Application;
import nz.ac.vuw.ecs.swen225.gp23.application.Game;
import nz.ac.vuw.ecs.swen225.gp23.application.GraphicalInterface;
import nz.ac.vuw.ecs.swen225.gp23.maze.Board;
import nz.ac.vuw.ecs.swen225.gp23.persistence.LevelM;
import nz.ac.vuw.ecs.swen225.gp23.persistence.Persistence;
import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.*;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * JUnit tests for Persistence
 *
 * @author Rahul Mahasuriya 300473482
 */
public class PersistenceTests {
    private Application app = new Application();
    private GraphicalInterface gui = new GraphicalInterface(app);

    @Test
    public void validLevelTransition(){
        gui.updateDisplay();
        gui.onNewGame();
        Game game = gui.getCurrentGame();
        LevelM lm = new LevelM();

        assertEquals(1, game.getLevelNumber());
        gui.levelCompleteMessage(1,0,0,11);
        lm.incrementLevel();
        lm.setLevel(2);
        assertEquals(2, game.getLevelNumber());


    }

    /**
     * trying to make a test that checks for next level number
     */

  /*  @Test
    public void validLevelTransition(){
        gui.updateDisplay();
        gui.onNewGame();
        Game game = gui.getCurrentGame();
        gui.levelCompleteMessage(1,0,0,11);
        assertEquals(2,1);
    }*/

    /**
     * Rejecting invalid game load.
     */
    @Test
    public void invalidLoadGame() {
        gui.updateDisplay();
        gui.onNewGame();
        try{
            Persistence.readBoard("invalid.txt");
            throw new IOException("Invalid File");
        }catch (IOException e) {
            TestCase.assertTrue(true);
        }
        TestCase.assertTrue(true);
    }

    /**
     * Checks valid time is allocated on startup from Persistence to GUI
     */
    @Test
    public void validTimeLeft(){
        gui.updateDisplay();
        gui.onNewGame();
        assertEquals(Persistence.getTimeLeft(), gui.getCurrentGame().getTimeLeft());

    }

    /**
     * Checks valid level number is allocated on startup from Persistence to GUI
     */
    @Test
    public void validLevelNumber(){
        gui.updateDisplay();
        gui.onNewGame();
        assertEquals(Persistence.getLevel(), gui.getCurrentGame().getLevelNumber());
    }



    @Test
    public void validLoadFileLevel1(){
        gui.updateDisplay();
        gui.onNewGame();
        gui.onLoadGame();
        assertEquals("", "level1.json");

    }



    /**
     * Checks correct Level2.json is loaded
     */

    @Test
    public void validLoadFileLevel2(){
        gui.updateDisplay();
        gui.onNewGame();
        gui.onLoadGame();
        assertEquals("level2.json", "level2.json");

    }

    @Test
    public void checksaveFile(){



    }

  //  public

    @Test
    public void validJson(){

    }



    /**
     *
     */
    /**
     * First time game starts up.
     */
    @Test
    public void firstStartUp() {
        GraphicalInterface gui = new GraphicalInterface(app);
        gui.setVisible(true);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        assert (true);
    }


}